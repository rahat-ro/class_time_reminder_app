package com.programmersjail.tmuian;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.programmersjail.tmuian.adapter.ClassRoutineAdapter;
import com.programmersjail.tmuian.deadline.DeadlineModel;
import com.programmersjail.tmuian.deadline.DeadlineReceiver;
import com.programmersjail.tmuian.deadline.Receiver;
import com.programmersjail.tmuian.helper.AccountantModel;
import com.programmersjail.tmuian.helper.ClassRoutine;
import com.programmersjail.tmuian.helper.SharedPrefManager;
import com.programmersjail.tmuian.helper.StudentModel;
import com.programmersjail.tmuian.helper.URLs;
import com.programmersjail.tmuian.helper.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Accountant extends AppCompatActivity {

    DatePicker pickerDate;
    TimePicker pickerTime;

    private ImageView accBack;

    TextView info;
    private TextView a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26;


    final static int RQS_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountant);

        info = (TextView)findViewById(R.id.info);

        pickerDate = (DatePicker)findViewById(R.id.pickerdate);
        pickerTime = (TimePicker)findViewById(R.id.pickertime);
        accBack = (ImageView) findViewById(R.id.acc_back);

        accBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accountant.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });



        /*pickerDate.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);*/



        TextViewInit();

        loadAccountant();
        loadDeadline();


    }

    public void loadDeadline(){


        StudentModel studentModel = SharedPrefManager.getInstance(this).getUser();
        final String Session = studentModel.getSession();

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_DEADLINE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressBar.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("deadline");
                                //userJson.getString("user");

                                //creating a new user object
                                DeadlineModel deadlineModel = new DeadlineModel(
                                        userJson.getInt("id"),
                                        userJson.getString("year"),
                                        userJson.getString("month"),
                                        userJson.getString("dayOfMonth"),
                                        userJson.getString("hourOfDay"),
                                        userJson.getString("minute"),
                                        userJson.getString("second"),
                                        userJson.getString("session")


                                );


                                String year = deadlineModel.getYear();
                                String month = deadlineModel.getMonth();
                                String day = deadlineModel.getDayOfMonth();
                                String hour = deadlineModel.getHourOfDay();
                                String minutes = deadlineModel.getMinute();
                                String second = deadlineModel.getSecond();

                                int y = Integer.parseInt(year);
                                int m = Integer.parseInt(month);
                                int d = Integer.parseInt(day);

                                int h = Integer.parseInt(hour);
                                int mi = Integer.parseInt(minutes);
                                int se = Integer.parseInt(second);
                                pickerDate.init(y,m,d,null);


                                //Calendar now = Calendar.getInstance();

                                pickerTime.setCurrentHour(h);
                                pickerTime.setCurrentMinute(mi);



                                save();


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
                params.put("session", Session);
                //params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void loadAccountant(){


        //first getting the values
        StudentModel studentModel = SharedPrefManager.getInstance(this).getUser();
        final String studentId = studentModel.getStudent_id();
        //final String password = ePass.getText().toString();

        //validating inputs




        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_ACCOUNTANT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressBar.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("accountant");
                                //userJson.getString("user");

                                //creating a new user object
                                AccountantModel user = new AccountantModel(
                                        userJson.getInt("id"),
                                        userJson.getString("studentName"),
                                        userJson.getString("idNo"),
                                        userJson.getString("adFormOfHers"),
                                        userJson.getString("yearlyFee"),
                                        userJson.getString("totalYearlyFee"),
                                        userJson.getString("runningYearlyFee"),
                                        userJson.getString("perSemester"),
                                        userJson.getString("totalSemester"),
                                        userJson.getString("runningSemester"),
                                        userJson.getString("additionalFee"),
                                        userJson.getString("monthlyPaid"),
                                        userJson.getString("totalMonth"),
                                        userJson.getString("totalTuitionFee"),
                                        userJson.getString("totalWillBePaid"),
                                        userJson.getString("payCompleteMonth"),
                                        userJson.getString("tuitionFeePayable"),
                                        userJson.getString("totalPaid"),
                                        userJson.getString("lastPaidRecord"),
                                        userJson.getString("totalDue"),
                                        userJson.getString("defaulter"),
                                        userJson.getString("dateOfAdmission"),
                                        userJson.getString("dueMonth"),
                                        userJson.getString("remarks"),
                                        userJson.getString("session"),
                                        userJson.getString("deadline")
                                );

                                //storing the user in shared preferences
                                //SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                a1.setText(user.getStudentName());
                                a2.setText(user.getIdNo());
                                a3.setText(user.getAdFormOfHers());
                                a4.setText(user.getYearlyFee());
                                a5.setText(user.getTotalYearlyFee());
                                a6.setText(user.getRunningYearlyFee());
                                a7.setText(user.getPerSemester());
                                a8.setText(user.getTotalSemester());
                                a9.setText(user.getRunningSemester());
                                a10.setText(user.getAdditionalFee());
                                a11.setText(user.getMonthlyPaid());
                                a12.setText(user.getTotalMonth());
                                a13.setText(user.getTotalTuitionFee());
                                a14.setText(user.getTotalWillBePaid());
                                a15.setText(user.getPayCompleteMonth());
                                a16.setText(user.getTuitionFeePayable());
                                a17.setText(user.getTotalPaid());
                                a18.setText(user.getLastPaidRecord());
                                a19.setText(user.getTotalDue());
                                a20.setText(user.getDefaulter());
                                a21.setText(user.getDateOfAdmission());
                                a22.setText(user.getDueMonth());
                                a23.setText(user.getRemarks());
                                a24.setText(user.getSession());
                                a25.setText(user.getDeadline());


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
                params.put("idNo", studentId);
                //params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    public void TextViewInit(){

        a1 = (TextView) findViewById(R.id.stuName);
        a2 = (TextView) findViewById(R.id.id_no);
        a3 = (TextView) findViewById(R.id.ad_her);
        a4 = (TextView) findViewById(R.id.yearly_fee);
        a5 = (TextView) findViewById(R.id.total_y_fee);
        a6 = (TextView) findViewById(R.id.run_y_fee);
        a7 = (TextView) findViewById(R.id.per_sem);
        a8 = (TextView) findViewById(R.id.total_sem);
        a9 = (TextView) findViewById(R.id.run_sem);
        a10 = (TextView) findViewById(R.id.ad_fee);
        a11 = (TextView) findViewById(R.id.month_paid);
        a12 = (TextView) findViewById(R.id.total_month);
        a13 = (TextView) findViewById(R.id.t_tuition_fee);
        a14 = (TextView) findViewById(R.id.t_w_b_paid);
        a15 = (TextView) findViewById(R.id.pay_c_month);
        a16 = (TextView) findViewById(R.id.t_f_payable);
        a17 = (TextView) findViewById(R.id.total_paid);
        a18 = (TextView) findViewById(R.id.l_paid_record);
        a19 = (TextView) findViewById(R.id.t_due);
        a20 = (TextView) findViewById(R.id.defaulter);
        a21 = (TextView) findViewById(R.id.date_of_admission);
        a22 = (TextView) findViewById(R.id.due_month);
        a23 = (TextView) findViewById(R.id.remark);
        a24 = (TextView) findViewById(R.id.session);
        a25 = (TextView) findViewById(R.id.deadline);



    }


    public void save(){


        Calendar current = Calendar.getInstance();

        Calendar cal = Calendar.getInstance();
        cal.set(pickerDate.getYear(),
                pickerDate.getMonth(),
                pickerDate.getDayOfMonth(),
                pickerTime.getCurrentHour(),
                pickerTime.getCurrentMinute(),
                00);

        if(cal.compareTo(current) <= 0){
            //The set Date/Time already passed
            Toast.makeText(getApplicationContext(),
                    "Deadline overdue",
                    Toast.LENGTH_LONG).show();
        }else{
            setAlarm(cal);
        }



    }




    private void setAlarm(Calendar targetCal){

        info.setText(
                 "Deadline@ " + targetCal.getTime()
                );

        Intent intent = new Intent(getBaseContext(), DeadlineReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }




}
