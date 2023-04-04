package com.programmersjail.tmuian;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.programmersjail.tmuian.alarm.Remainder;
import com.programmersjail.tmuian.helper.SharedPrefManager;
import com.programmersjail.tmuian.helper.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;

    private ViewPager viewPager;
    MenuItem prevMenuItem;

    HomeFragment homeFragment;
    ClassRoutineFragment classRoutineFragment;
    DashboardFragment dashboardFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_class_routine:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);

                    SharedPrefManager.getInstance(getApplicationContext()).logout();
                    finish();
                    return true;
            }
            return false;
        }
    };

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        StudentModel studentModel = SharedPrefManager.getInstance(this).getUser();

        t2 = (TextView) findViewById(R.id.id_fuul_name);
        t2.setText(studentModel.getFull_name());
        t3 = (TextView) findViewById(R.id.id_stu_id);
        t3.setText(studentModel.getStudent_id());
        t4 = (TextView) findViewById(R.id.id_reg_no);
        t4.setText(studentModel.getReg_no());
        t5 = (TextView) findViewById(R.id.id_dept);
        t5.setText(studentModel.getDepartment());
        t6 = (TextView) findViewById(R.id.id_program);
        t6.setText(studentModel.getProgram());
        t7 = (TextView) findViewById(R.id.id_batch_no);
        t7.setText(studentModel.getBatch_no());
        t8 = (TextView) findViewById(R.id.id_pre_semester);
        t8.setText(studentModel.getPresent_semester());
        t9 = (TextView) findViewById(R.id.id_email);
        t9.setText(studentModel.getEmail());
        t10 = (TextView) findViewById(R.id.id_mob_no);
        t10.setText(studentModel.getMob_no());
        t11 = (TextView) findViewById(R.id.id_session);
        t11.setText(studentModel.getSession());


        mTextMessage = (TextView) findViewById(R.id.message);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);


        ///GridLayoutManager mGridLayoutManager = new GridLayoutManager(PhotoActivity.this, 2);
        //recyclerView.setLayoutManager(mGridLayoutManager);

    }



    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        classRoutineFragment = new ClassRoutineFragment();
        dashboardFragment = new DashboardFragment();

        adapter.addFragment(homeFragment);
        adapter.addFragment(classRoutineFragment);
        adapter.addFragment(dashboardFragment);
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
        public void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }

    }

}
