package com.programmersjail.tmuian;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.programmersjail.tmuian.adapter.NoticeAdapter;
import com.programmersjail.tmuian.adapter.RunningCourseAdapter;
import com.programmersjail.tmuian.helper.NoticeModel;
import com.programmersjail.tmuian.helper.RunningCourse;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TextView profile;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private List<NoticeModel> noticeModelList;
    private List<RunningCourse> runningCourseList;
    private LinearLayoutManager linearLayoutManager;




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = v.findViewById(R.id.rv_notice);
        //recyclerView.setHasFixedSize(true);
        //recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        noticeModelList = new ArrayList<>();
        noticeBoard();

        //.......................running course..........

        recyclerView1 = v.findViewById(R.id.rvv_running_course);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));


        runningCourseList = new ArrayList<>();
        loadRunningCourse();


        profile = (TextView) v.findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),ProfileActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });


        return v;
    }

    private void loadRunningCourse() {
        StudentModel studentModel = SharedPrefManager.getInstance(getActivity()).getUser();
        //first getting the values
        final String Dept = studentModel.getDepartment();
        final String BatchNo = studentModel.getBatch_no();
        final String Session = studentModel.getSession();


        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_RUNNING_COURSE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressBar.setVisibility(View.GONE);
                        //swipeLayout.setRefreshing(false);

                        try {
                            //converting response to json object
                            JSONArray array = new JSONArray(response);

                            /*if(noticeModelList != null){
                                noticeModelList.clear();
                            }*/


                            for (int i = 0; i < array.length(); i++) {

                                JSONObject notice = array.getJSONObject(i);
                                runningCourseList.add(new RunningCourse(
                                        notice.getInt("id"),
                                        notice.getString("teacher_name"),
                                        notice.getString("teacher_img"),
                                        notice.getString("course_title"),
                                        notice.getString("course_code"),
                                        notice.getString("credit_hour"),
                                        notice.getString("dept"),
                                        notice.getString("batch"),
                                        notice.getString("session"),
                                        notice.getString("message")

                                ));
                            }


                            //Toast.makeText(Routine.this, "abc", Toast.LENGTH_SHORT).show();


                            //NoticeAdapter noticeAdapter = new NoticeAdapter(getActivity(),noticeModelList);
                            RunningCourseAdapter runningCourseAdapter  = new RunningCourseAdapter(getActivity(),runningCourseList);
                            recyclerView1.setAdapter(runningCourseAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dept", Dept);
                params.put("batch", BatchNo);
                params.put("session", Session);
                return params;
            }
        };

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }





    public void noticeBoard(){

        StudentModel studentModel = SharedPrefManager.getInstance(getActivity()).getUser();
        //first getting the values
        //final String BatchNo = studentModel.getBatch_no();
        final String noticeDept = studentModel.getDepartment();

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_NOTICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressBar.setVisibility(View.GONE);
                        //swipeLayout.setRefreshing(false);

                        try {
                            //converting response to json object
                            JSONArray array = new JSONArray(response);

                            /*if(noticeModelList != null){
                                noticeModelList.clear();
                            }*/


                            for (int i = 0; i < array.length(); i++) {

                                JSONObject notice = array.getJSONObject(i);
                                noticeModelList.add(new NoticeModel(
                                        notice.getInt("id"),
                                        notice.getString("notice_date"),
                                        notice.getString("notice_title"),
                                        notice.getString("notice_disc"),
                                        notice.getString("notice_dept")

                                ));
                            }


                            //Toast.makeText(Routine.this, "abc", Toast.LENGTH_SHORT).show();


                            //NoticeAdapter noticeAdapter = new NoticeAdapter(getActivity(),noticeModelList);
                            NoticeAdapter noticeAdapter = new NoticeAdapter(getActivity(),noticeModelList);
                            recyclerView.setAdapter(noticeAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
           @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("batch_no", BatchNo);
                params.put("notice_dept", noticeDept);
                return params;
            }
        };

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }


}
