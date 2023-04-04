package com.programmersjail.tmuian.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.programmersjail.tmuian.LoginActivity;


public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "naim_rahat";
    private static final String KEY_ID= "id";
    private static final String KEY_FULLNAME = "full_name";
    private static final String KEY_STUDENTID = "student_id";
    private static final String KEY_REGNO = "reg_no";
    private static final String KEY_DEPARTMENT = "department";
    private static final String KEY_PROGRAM = "program";
    private static final String KEY_BATCHNO = "batch_no";
    private static final String KEY_PRESENTSEMESTER ="present_semester";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBNO = "mob_no";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_SESSION = "session";





    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }


    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(StudentModel studentModel) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, studentModel.getId());
        editor.putString(KEY_FULLNAME,studentModel.getFull_name());
        editor.putString(KEY_STUDENTID,studentModel.getStudent_id());
        editor.putString(KEY_REGNO,studentModel.getReg_no());
        editor.putString(KEY_DEPARTMENT,studentModel.getDepartment());
        editor.putString(KEY_PROGRAM,studentModel.getProgram());
        editor.putString(KEY_BATCHNO,studentModel.getBatch_no());
        editor.putString(KEY_PRESENTSEMESTER,studentModel.getPresent_semester());
        editor.putString(KEY_EMAIL, studentModel.getEmail());
        editor.putString(KEY_MOBNO,studentModel.getMob_no());
        editor.putString(KEY_PASSWORD,studentModel.getPassword());
        editor.putString(KEY_SESSION,studentModel.getSession());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_STUDENTID, null) != null;
    }


    //this method will give the logged in user
    public StudentModel getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new StudentModel(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_FULLNAME, null),
                sharedPreferences.getString(KEY_STUDENTID,null),
                sharedPreferences.getString(KEY_REGNO, null),
                sharedPreferences.getString(KEY_DEPARTMENT, null),
                sharedPreferences.getString(KEY_PROGRAM, null),
                sharedPreferences.getString(KEY_BATCHNO, null),
                sharedPreferences.getString(KEY_PRESENTSEMESTER, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_MOBNO, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_SESSION,null)


        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }


}
