package com.programmersjail.tmuian.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.programmersjail.tmuian.R;
import com.programmersjail.tmuian.alarm.AddEditAlarmActivity;
import com.programmersjail.tmuian.alarm.Remainder;
import com.programmersjail.tmuian.helper.ClassRoutine;
import com.programmersjail.tmuian.util.AlarmUtils;

import java.util.List;

public class ClassRoutineAdapter extends RecyclerView.Adapter<ClassRoutineAdapter.ViewHolder>{

    private Context mCtx;
    private List<ClassRoutine> classRoutineList;

    public ClassRoutineAdapter(Context mCtx, List<ClassRoutine> classRoutineList) {
        this.mCtx = mCtx;
        this.classRoutineList = classRoutineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.class_routine_card_view,viewGroup,false);
        return new ClassRoutineAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final ClassRoutine classRoutine = classRoutineList.get(i);
        viewHolder.rt1.setText(classRoutine.getDays());
        viewHolder.rt2.setText(classRoutine.getTime());
        //viewHolder.rt3.setText(String.valueOf(classRoutine.getAlarm_hour()));
        //viewHolder.rt33.setText(String.valueOf(classRoutine.getAlarm_min()));
        viewHolder.rt4.setText(classRoutine.getCourse_code());
        viewHolder.rt5.setText(classRoutine.getCourse_title());
        viewHolder.rt6.setText(classRoutine.getBatch_no());
        viewHolder.rt7.setText(classRoutine.getDept());
        viewHolder.rt8.setText(classRoutine.getRoom_no());
        viewHolder.rt9.setText(classRoutine.getTeacher());
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlarmUtils.checkAlarmPermissions((Activity) mCtx);
                final Intent i1 =
                        AddEditAlarmActivity.buildAddEditAlarmActivityIntent(
                                mCtx, AddEditAlarmActivity.ADD_ALARM
                        );
                i1.putExtra("alarm_hour",classRoutineList.get(viewHolder.getAdapterPosition()).getAlarm_hour());
                i1.putExtra("alarm_min",classRoutineList.get(viewHolder.getAdapterPosition()).getAlarm_min());
                i1.putExtra("days",classRoutineList.get(viewHolder.getAdapterPosition()).getDays());
                i1.putExtra("course_title",classRoutineList.get(viewHolder.getAdapterPosition()).getCourse_title());
                mCtx.startActivity(i1);
                //viewHolder.btn.setText("done");

                //Intent i = new Intent(mCtx, AddEditAlarmActivity.class);

                //i.putExtra("symbol",nodeList.get(viewHolder.getAdapterPosition()).getSymbol());
                //i.putExtra("nodeId",nodeList.get(viewHolder.getAdapterPosition()).getId());
                //mCtx.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return classRoutineList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView rt1,rt2,rt3,rt33,rt4,rt5,rt6,rt7,rt8,rt9;

        private FloatingActionButton btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rt1 = (TextView) itemView.findViewById(R.id.cr_day);
            rt2 = (TextView) itemView.findViewById(R.id.cr_time);
            //rt3 = (TextView) itemView.findViewById(R.id.cr_alarmTime);
            //rt33 = (TextView) itemView.findViewById(R.id.cr_alarmMin);
            rt4 = (TextView) itemView.findViewById(R.id.cr_courseCode);
            rt5 = (TextView) itemView.findViewById(R.id.cr_courseTitle);
            rt6 = (TextView) itemView.findViewById(R.id.cr_batch);
            rt7 = (TextView) itemView.findViewById(R.id.cr_dept);
            rt8 = (TextView) itemView.findViewById(R.id.cr_roomNo);
            rt9 = (TextView) itemView.findViewById(R.id.cr_teacher);

            btn = (FloatingActionButton) itemView.findViewById(R.id.alarm);



        }
    }

}
