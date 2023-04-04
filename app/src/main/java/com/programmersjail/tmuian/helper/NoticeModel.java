package com.programmersjail.tmuian.helper;

public class NoticeModel {

    private int id;
    private String notice_date,notice_title,notice_disc,notice_dept;

    public NoticeModel(int id, String notice_date, String notice_title, String notice_disc, String notice_dept) {
        this.id = id;
        this.notice_date = notice_date;
        this.notice_title = notice_title;
        this.notice_disc = notice_disc;
        this.notice_dept = notice_dept;
    }

    public int getId() {
        return id;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public String getNotice_disc() {
        return notice_disc;
    }

    public String getNotice_dept() {
        return notice_dept;
    }
}
