package com.programmersjail.tmuian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticeActivity extends AppCompatActivity {


    private TextView nt1,nt2,nt3;
    private ImageView noticeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        nt1 = (TextView) findViewById(R.id.noticeDate);
        nt2 = (TextView) findViewById(R.id.noticeTitle);
        nt3 = (TextView) findViewById(R.id.noticeDisc);

        Bundle mBundle = getIntent().getExtras();

        if(mBundle!= null){

            nt1.setText(mBundle.getString("noticeDate"));
            nt2.setText(mBundle.getString("noticeTitle"));
            nt3.setText(mBundle.getString("noticeDisc"));

        }

        noticeBack = (ImageView) findViewById(R.id.notice_back);
        noticeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NoticeActivity.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }
}
