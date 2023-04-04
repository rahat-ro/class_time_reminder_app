package com.programmersjail.tmuian.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.programmersjail.tmuian.NoticeActivity;
import com.programmersjail.tmuian.R;
import com.programmersjail.tmuian.helper.NoticeModel;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private Context mCtx;
    private List<NoticeModel> noticeModelList;

    public NoticeAdapter(Context mCtx, List<NoticeModel> noticeModelList) {
        this.mCtx = mCtx;
        this.noticeModelList = noticeModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.notice_card_view,viewGroup,false);
        return new NoticeAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final NoticeModel noticeModel = noticeModelList.get(i);

        viewHolder.nt.setText(noticeModel.getNotice_date());
        viewHolder.nt2.setText(noticeModel.getNotice_title());
        viewHolder.nt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mCtx,NoticeActivity.class);
                intent.putExtra("noticeDate",noticeModelList.get(viewHolder.getAdapterPosition()).getNotice_date());
                intent.putExtra("noticeTitle",noticeModelList.get(viewHolder.getAdapterPosition()).getNotice_title());
                intent.putExtra("noticeDisc",noticeModelList.get(viewHolder.getAdapterPosition()).getNotice_disc());
                mCtx.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nt,nt2,nt3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nt = (TextView) itemView.findViewById(R.id.notice_date);
            nt2 = (TextView) itemView.findViewById(R.id.notice_title);
            nt3 = (TextView) itemView.findViewById(R.id.more);




        }
    }

}
