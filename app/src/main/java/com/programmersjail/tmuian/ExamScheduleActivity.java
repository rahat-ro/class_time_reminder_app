package com.programmersjail.tmuian;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.programmersjail.tmuian.adapter.ExamScheduleAdapter;
import com.programmersjail.tmuian.helper.ExamSchedule;
import com.programmersjail.tmuian.helper.SharedPrefManager;
import com.programmersjail.tmuian.helper.StudentModel;
import com.programmersjail.tmuian.helper.URLs;
import com.programmersjail.tmuian.helper.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamScheduleActivity extends AppCompatActivity {

    private static final String URL_PRODUCTS = "https://www.sagorcabletvnetwork.com/apps/node.php";
    RecyclerView recyclerView1;
    private List<ExamSchedule> examScheduleList;
    private LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeLayout;
    private ImageView proBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_schedule);

        recyclerView1 = findViewById(R.id.node_rv);
        recyclerView1.setHasFixedSize(true);
        //recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        linearLayoutManager = new LinearLayoutManager(ExamScheduleActivity.this);
        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        recyclerView1.setLayoutManager(linearLayoutManager);

        examScheduleList = new ArrayList<>();
        examRoutine();

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                //loadNodeList();
                examRoutine();

            }
        });
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        proBack = (ImageView) findViewById(R.id.ex_back);
        proBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExamScheduleActivity.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }

    public void examRoutine(){

        StudentModel studentModel = SharedPrefManager.getInstance(this).getUser();
        //first getting the values
        final String BatchNo = studentModel.getBatch_no();
        final String Dept = studentModel.getDepartment();
        final String Session = studentModel.getSession();

        //validating inputs




        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_FINAL_EXAM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressBar.setVisibility(View.GONE);
                        swipeLayout.setRefreshing(false);

                        try {
                            //converting response to json object
                            JSONArray array = new JSONArray(response);

                            if(examScheduleList != null){
                                examScheduleList.clear();
                            }


                            for (int i = 0; i < array.length(); i++) {

                                JSONObject node = array.getJSONObject(i);
                                examScheduleList.add(new ExamSchedule(
                                        node.getInt("id"),
                                        node.getString("date"),
                                        node.getString("time"),
                                        node.getString("days"),
                                        node.getString("room_no"),
                                        node.getString("course_code"),
                                        node.getString("course_title"),
                                        node.getString("batch_no"),
                                        node.getString("dept"),
                                        node.getString("session"),
                                        node.getString("invigilators_one"),
                                        node.getString("invigilators_two"),
                                        node.getString("invigilators_three"),
                                        node.getString("invigilators_four"),
                                        node.getString("invigilators_five")




                                        ));
                            }


                            ExamScheduleAdapter adapter = new ExamScheduleAdapter(ExamScheduleActivity.this,examScheduleList);
                            recyclerView1.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("batch_no", BatchNo);
                params.put("dept", Dept);
                params.put("session", Session);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}
