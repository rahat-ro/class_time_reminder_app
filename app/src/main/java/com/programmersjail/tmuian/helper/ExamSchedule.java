package com.programmersjail.tmuian.helper;

public class ExamSchedule {

    private int id;
    private String date,time,days,room_no,course_code,course_title,batch_no,dept,session,invigilators_one,
            invigilators_two,invigilators_three,invigilators_four,invigilators_five;


    public ExamSchedule(int id, String date, String time, String days, String room_no, String course_code, String course_title, String batch_no, String dept, String session, String invigilators_one,
                        String invigilators_two, String invigilators_three, String invigilators_four, String invigilators_five) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.days = days;
        this.room_no = room_no;
        this.course_code = course_code;
        this.course_title = course_title;
        this.batch_no = batch_no;
        this.dept = dept;
        this.session = session;
        this.invigilators_one = invigilators_one;
        this.invigilators_two = invigilators_two;
        this.invigilators_three = invigilators_three;
        this.invigilators_four = invigilators_four;
        this.invigilators_five = invigilators_five;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDays() {
        return days;
    }

    public String getRoom_no() {
        return room_no;
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

    public String getSession() {
        return session;
    }

    public String getInvigilators_one() {
        return invigilators_one;
    }

    public String getInvigilators_two() {
        return invigilators_two;
    }

    public String getInvigilators_three() {
        return invigilators_three;
    }

    public String getInvigilators_four() {
        return invigilators_four;
    }

    public String getInvigilators_five() {
        return invigilators_five;
    }
}
