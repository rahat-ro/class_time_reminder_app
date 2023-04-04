package com.programmersjail.tmuian.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.programmersjail.tmuian.R;
import com.programmersjail.tmuian.helper.ClassRoutine;
import com.programmersjail.tmuian.helper.RunningCourse;

import java.util.List;

public class RunningCourseAdapter extends RecyclerView.Adapter<RunningCourseAdapter.viewHolder>{

    private Context mCtx;
    private List<RunningCourse> runningCourseList;

    public RunningCourseAdapter(Context mCtx, List<RunningCourse> runningCourseList) {
        this.mCtx = mCtx;
        this.runningCourseList = runningCourseList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.running_course_card_view,viewGroup,false);
        return new RunningCourseAdapter.viewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        final RunningCourse runningCourse = runningCourseList.get(i);

        viewHolder.rt1.setText(runningCourse.getTeacher_name());
        viewHolder.rt2.setText(runningCourse.getCourse_title());
        viewHolder.rt3.setText(runningCourse.getCourse_code());
        viewHolder.rt4.setText(runningCourse.getCredit_hour());
        viewHolder.rt5.setText(runningCourse.getDept());
        viewHolder.rt6.setText(runningCourse.getBatch());
        viewHolder.rt7.setText(runningCourse.getSession());
    }

    @Override
    public int getItemCount() {
        return runningCourseList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        private TextView rt1,rt2,rt3,rt4,rt5,rt6,rt7;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            rt1 = (TextView) itemView.findViewById(R.id.r_teacher_name);
            rt2 = (TextView) itemView.findViewById(R.id.r_course_title);
            rt3 = (TextView) itemView.findViewById(R.id.r_course_code);
            rt4 = (TextView) itemView.findViewById(R.id.r_credit_hour);
            rt5 = (TextView) itemView.findViewById(R.id.r_dept);
            rt6 = (TextView) itemView.findViewById(R.id.r_batch);
            rt7 = (TextView) itemView.findViewById(R.id.r_sesson);


        }
    }

}
