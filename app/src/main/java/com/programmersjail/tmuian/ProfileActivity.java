package com.programmersjail.tmuian;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.programmersjail.tmuian.deadline.DeadlineModel;
import com.programmersjail.tmuian.helper.ClassRoutine;
import com.programmersjail.tmuian.helper.SharedPrefManager;
import com.programmersjail.tmuian.helper.StudentModel;
import com.programmersjail.tmuian.helper.URLs;
import com.programmersjail.tmuian.helper.VolleySingleton;
import com.qhutch.bottomsheetlayout.BottomSheetLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private ImageView proBack,imgExpand,imgCollapse;
    private TextView a,b,c,d,e,f,g,h,i1,j,k;
    private EditText editPass,editSem,editTextSession;
    private Button upBtn;

    private BottomSheetBehavior bottomSheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        imgExpand = (ImageView) findViewById(R.id.sheet_expand);
        imgExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        imgCollapse = (ImageView) findViewById(R.id.sheet_collapse);
        imgCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });


        proBack = (ImageView) findViewById(R.id.pro_back);
        proBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        StudentModel studentModel = SharedPrefManager.getInstance(this).getUser();

        a = (TextView) findViewById(R.id.a);
        a.setText(studentModel.getFull_name());
        b = (TextView) findViewById(R.id.b);
        b.setText(studentModel.getStudent_id());
        c = (TextView) findViewById(R.id.c);
        c.setText(studentModel.getReg_no());
        d = (TextView) findViewById(R.id.d);
        d.setText(studentModel.getDepartment());
        e = (TextView) findViewById(R.id.e);
        e.setText(studentModel.getProgram());
        f = (TextView) findViewById(R.id.f);
        f.setText(studentModel.getBatch_no());
        g = (TextView) findViewById(R.id.g);
        g.setText(studentModel.getPresent_semester());
        h = (TextView) findViewById(R.id.h);
        h.setText(studentModel.getEmail());
        i1 = (TextView) findViewById(R.id.i);
        i1.setText(studentModel.getMob_no());
        j = (TextView) findViewById(R.id.j);
        j.setText(studentModel.getSession());

        editSem = (EditText) findViewById(R.id.edit_sem);
        editSem.setText(studentModel.getPresent_semester());

        editTextSession = (EditText) findViewById(R.id.edit_session);
        editTextSession.setText(studentModel.getSession());



        upBtn = (Button) findViewById(R.id.updateBtn);
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();


            }
        });



    }




    private void updateProfile() {

        final String fulName = a.getText().toString().trim();
        final String stuId = b.getText().toString().trim();
        final String regNo = c.getText().toString().trim();
        final String dept = d.getText().toString().trim();
        final String program = e.getText().toString().trim();
        final String batchNo = f.getText().toString().trim();
        //final String preSemester = g.getText().toString().trim();
        final String email= h.getText().toString().trim();
        final String mobNo = i1.getText().toString().trim();
        //final String session = j.getText().toString().trim();
        final String editSemester = editSem.getText().toString().trim();
        final String editSession = editTextSession.getText().toString().trim();

        //first we will do the validations


        if (TextUtils.isEmpty(editSemester)){
            editSem.setError("enter semester name");
            editSem.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(editSession)){
            editTextSession.setError("enter semester name");
            editTextSession.requestFocus();
            return;
        }




        ///......................

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_UPDATE_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);

                        try {


                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();



                                //SharedPrefManager.getInstance(getApplicationContext()).logout();
                                //finish();

                                Intent i = new Intent(getApplicationContext(),Accountant.class);
                                startActivity(i);

                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
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
                params.put("full_name", fulName);
                params.put("student_id", stuId);
                params.put("reg_no", regNo);
                params.put("department", dept);
                params.put("program",program);
                params.put("batch_no", batchNo);
                params.put("present_semester", editSemester);
                params.put("email", email);
                params.put("mob_no", mobNo);
                params.put("session", editSession);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);



    }

}
