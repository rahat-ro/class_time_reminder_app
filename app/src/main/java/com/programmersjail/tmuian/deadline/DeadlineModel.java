package com.programmersjail.tmuian.deadline;

import java.util.List;

public class DeadlineModel {
    private int id;
    private String year,month,dayOfMonth,hourOfDay,minute,second,session;


    public DeadlineModel(int id, String year, String month, String dayOfMonth,
                         String hourOfDay, String minute, String second, String session) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public String getHourOfDay() {
        return hourOfDay;
    }

    public String getMinute() {
        return minute;
    }

    public String getSecond() {
        return second;
    }

    public String getSession() {
        return session;
    }
}
