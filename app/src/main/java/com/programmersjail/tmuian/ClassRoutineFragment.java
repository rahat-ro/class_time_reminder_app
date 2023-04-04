package com.programmersjail.tmuian;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.programmersjail.tmuian.adapter.ClassRoutineAdapter;
import com.programmersjail.tmuian.helper.ClassRoutine;
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
public class ClassRoutineFragment extends Fragment {

    private RecyclerView recyclerView12;
    private List<ClassRoutine> classRoutineList;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeLayout;


    public ClassRoutineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_class_routine, container, false);

        recyclerView12 = v.findViewById(R.id.rv_clas);
        recyclerView12.setHasFixedSize(true);
        //recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        recyclerView12.setLayoutManager(linearLayoutManager);

        classRoutineList = new ArrayList<>();
        classRoutine();

        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                //loadNodeList();
                classRoutine();

            }
        });
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return v;
    }


    public void classRoutine(){

        StudentModel studentModel = SharedPrefManager.getInstance(getActivity()).getUser();
        //first getting the values
        final String BatchNo = studentModel.getBatch_no();
        final String Dept = studentModel.getDepartment();



        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_CLASS_ROUTINE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressBar.setVisibility(View.GONE);
                        swipeLayout.setRefreshing(false);

                        try {
                            //converting response to json object
                            JSONArray array = new JSONArray(response);

                            if(classRoutineList != null){
                                classRoutineList.clear();
                            }


                            for (int i = 0; i < array.length(); i++) {

                                JSONObject routine = array.getJSONObject(i);
                                classRoutineList.add(new ClassRoutine(
                                        routine.getInt("id"),
                                        routine.getInt("alarm_hour"),
                                        routine.getInt("alarm_min"),
                                        routine.getString("days"),
                                        routine.getString("time"),
                                        routine.getString("course_code"),
                                        routine.getString("course_title"),
                                        routine.getString("batch_no"),
                                        routine.getString("dept"),
                                        routine.getString("room_no"),
                                        routine.getString("teacher")
                                ));
                            }


                            //Toast.makeText(Routine.this, "abc", Toast.LENGTH_SHORT).show();


                            ClassRoutineAdapter classRoutineAdapter = new ClassRoutineAdapter(getActivity(),classRoutineList);
                            recyclerView12.setAdapter(classRoutineAdapter);


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
                params.put("batch_no", BatchNo);
                params.put("dept", Dept);
                return params;
            }
        };

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }


}
