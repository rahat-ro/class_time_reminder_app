package com.programmersjail.tmuian.alarm;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.programmersjail.tmuian.MainActivity;
import com.programmersjail.tmuian.ProfileActivity;
import com.programmersjail.tmuian.R;
import com.programmersjail.tmuian.adapter.AlarmsAdapter;
import com.programmersjail.tmuian.helper.Alarm;
import com.programmersjail.tmuian.service.LoadAlarmsReceiver;
import com.programmersjail.tmuian.service.LoadAlarmsService;
import com.programmersjail.tmuian.util.AlarmUtils;
import com.programmersjail.tmuian.view.DividerItemDecoration;

import java.util.ArrayList;

public class Remainder extends AppCompatActivity implements LoadAlarmsReceiver.OnAlarmsLoadedListener {

    private Button btn;
    private LoadAlarmsReceiver mReceiver;
    private AlarmsAdapter mAdapter;

    private ImageView proBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);
        mReceiver = new LoadAlarmsReceiver(this);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler);
        mAdapter = new AlarmsAdapter();
        //rv.setEmptyView(v.findViewById(R.id.empty_view));
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(this));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        proBack = (ImageView) findViewById(R.id.re_back);
        proBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Remainder.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmUtils.checkAlarmPermissions(Remainder.this);
                final Intent i1 =
                        AddEditAlarmActivity.buildAddEditAlarmActivityIntent(
                                Remainder.this, AddEditAlarmActivity.ADD_ALARM
                        );
                startActivity(i1);
            }
        });




    }

    @Override
    public void onStart() {
        super.onStart();
        final IntentFilter filter = new IntentFilter(LoadAlarmsService.ACTION_COMPLETE);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filter);
        LoadAlarmsService.launchLoadAlarmsService(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    @Override
    public void onAlarmsLoaded(ArrayList<Alarm> alarms) {
        mAdapter.setAlarms(alarms);
    }
}
