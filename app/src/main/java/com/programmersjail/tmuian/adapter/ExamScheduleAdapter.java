package com.programmersjail.tmuian.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.programmersjail.tmuian.R;
import com.programmersjail.tmuian.helper.ExamSchedule;

import java.util.List;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class ExamScheduleAdapter extends RecyclerView.Adapter<ExamScheduleAdapter.ViewHolder> {

    private Context mCtx;
    private List<ExamSchedule> examScheduleList;

    public ExamScheduleAdapter(Context mCtx, List<ExamSchedule> examScheduleList) {
        this.mCtx = mCtx;
        this.examScheduleList = examScheduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.exam_schedule_card_view,viewGroup,false);
        return new ExamScheduleAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ExamSchedule examSchedule = examScheduleList.get(i);
        viewHolder.tt2.setText(examSchedule.getDate());
        viewHolder.tt3.setText(examSchedule.getTime());
        viewHolder.tt4.setText(examSchedule.getDays());
        viewHolder.tt5.setText(examSchedule.getRoom_no());
        viewHolder.tt6.setText(examSchedule.getCourse_code());
        viewHolder.tt7.setText(examSchedule.getCourse_title());
        viewHolder.tt8.setText(examSchedule.getBatch_no());
        viewHolder.tt9.setText(examSchedule.getDept());
        viewHolder.tt15.setText(examSchedule.getSession());
        viewHolder.tt10.setText(examSchedule.getInvigilators_one());
        viewHolder.tt11.setText(examSchedule.getInvigilators_two());
        viewHolder.tt12.setText(examSchedule.getInvigilators_three());
        viewHolder.tt13.setText(examSchedule.getInvigilators_four());
        viewHolder.tt14.setText(examSchedule.getInvigilators_five());

    }

    @Override
    public int getItemCount() {
        return examScheduleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9,tt10,tt11,tt12,tt13,tt14,tt15;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tt2 = (TextView) itemView.findViewById(R.id.date);
            tt3 = (TextView) itemView.findViewById(R.id.time);
            tt4 = (TextView) itemView.findViewById(R.id.days);
            tt5 = (TextView) itemView.findViewById(R.id.roomNo);
            tt6 = (TextView) itemView.findViewById(R.id.courseCode);
            tt7 = (TextView) itemView.findViewById(R.id.courseTitle);
            tt8 = (TextView) itemView.findViewById(R.id.batchNo);
            tt9 = (TextView) itemView.findViewById(R.id.dept);
            tt10 = (TextView) itemView.findViewById(R.id.invigilators_one);
            tt11 = (TextView) itemView.findViewById(R.id.invigilators_two);
            tt12 = (TextView) itemView.findViewById(R.id.invigilators_three);
            tt13 = (TextView) itemView.findViewById(R.id.invigilators_four);
            tt14 = (TextView) itemView.findViewById(R.id.invigilators_five);
            tt15 = (TextView) itemView.findViewById(R.id.session);




        }
    }
}
