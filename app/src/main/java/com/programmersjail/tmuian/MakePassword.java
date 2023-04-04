package com.programmersjail.tmuian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.programmersjail.tmuian.helper.SharedPrefManager;
import com.programmersjail.tmuian.helper.StudentModel;
import com.programmersjail.tmuian.helper.URLs;
import com.programmersjail.tmuian.helper.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MakePassword extends AppCompatActivity {


    private EditText editStuId,editPassword;
    private TextView singIn;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_password);


        editStuId = (EditText) findViewById(R.id.edit_stu_id);
        //editPassword = (EditText) findViewById(R.id.edit_password);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        singIn = (TextView) findViewById(R.id.sign_in);
        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MakePassword.this,LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });


    }


    private void registerUser() {

        final String Password = editStuId.getText().toString().trim();
        //final String Password = editPassword.getText().toString().trim();


       // final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();

        //first we will do the validations


        if (TextUtils.isEmpty(Password)) {
            editStuId.setError("Please enter student Id");
            editPassword.requestFocus();
            return;
        }



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_JOIN,
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

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("tmu_students");

                                //creating a new user object
                                StudentModel user = new StudentModel(
                                        userJson.getInt("id"),
                                        userJson.getString("full_name"),
                                        userJson.getString("student_id"),
                                        userJson.getString("reg_no"),
                                        userJson.getString("department"),
                                        userJson.getString("program"),
                                        userJson.getString("batch_no"),
                                        userJson.getString("present_semester"),
                                        userJson.getString("email"),
                                        userJson.getString("mob_no"),
                                        userJson.getString("password"),
                                        userJson.getString("session")
                                );

                                //starting the profile activity
                                startActivity(new Intent(getApplicationContext(),ConfigLogDetail.class));
                                finish();

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);




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
                params.put("password", Password);
               // params.put("password", Password);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

}
