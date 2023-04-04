package com.programmersjail.tmuian.helper;

public class RunningCourse {

    private int id;
    private String teacher_name,teacher_img,course_title,course_code,credit_hour,dept,batch,session,message;

    public RunningCourse(int id, String teacher_name, String teacher_img, String course_title,
                         String course_code, String credit_hour, String dept, String batch, String session, String message) {
        this.id = id;
        this.teacher_name = teacher_name;
        this.teacher_img = teacher_img;
        this.course_title = course_title;
        this.course_code = course_code;
        this.credit_hour = credit_hour;
        this.dept = dept;
        this.batch = batch;
        this.session = session;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getTeacher_img() {
        return teacher_img;
    }

    public String getCourse_title() {
        return course_title;
    }

    public String getCourse_code() {
        return course_code;
    }

    public String getCredit_hour() {
        return credit_hour;
    }

    public String getDept() {
        return dept;
    }

    public String getBatch() {
        return batch;
    }

    public String getSession() {
        return session;
    }

    public String getMessage() {
        return message;
    }
}
