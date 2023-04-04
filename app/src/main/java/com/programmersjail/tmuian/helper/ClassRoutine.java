package com.programmersjail.tmuian.helper;

public class ClassRoutine {

    private int id,alarm_hour,alarm_min;
    private String days,time,course_code,course_title,batch_no,dept,room_no,teacher;

    public ClassRoutine(int id, int alarm_hour, int alarm_min, String days, String time,
                        String course_code, String course_title, String batch_no, String dept, String room_no, String teacher) {
        this.id = id;
        this.alarm_hour = alarm_hour;
        this.alarm_min = alarm_min;
        this.days = days;
        this.time = time;
        this.course_code = course_code;
        this.course_title = course_title;
        this.batch_no = batch_no;
        this.dept = dept;
        this.room_no = room_no;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public int getAlarm_hour() {
        return alarm_hour;
    }

    public int getAlarm_min() {
        return alarm_min;
    }

    public String getDays() {
        return days;
    }

    public String getTime() {
        return time;
    }

    public String getCourse_code() {
        return course_code;
    }

    public String getCourse_title() {
        return course_title;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public String getDept() {
        return dept;
    }

    public String getRoom_no() {
        return room_no;
    }

    public String getTeacher() {
        return teacher;
    }
}
