package com.programmersjail.tmuian.helper;

public class StudentModel {

    private int id;
    private  String full_name,student_id,reg_no,department,program,batch_no,present_semester,
            email,mob_no,password,session;

    public StudentModel(int id, String full_name, String student_id, String reg_no, String department, String program,
                        String batch_no, String present_semester, String email, String mob_no, String password, String session) {
        this.id = id;
        this.full_name = full_name;
        this.student_id = student_id;
        this.reg_no = reg_no;
        this.department = department;
        this.program = program;
        this.batch_no = batch_no;
        this.present_semester = present_semester;
        this.email = email;
        this.mob_no = mob_no;
        this.password = password;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getReg_no() {
        return reg_no;
    }

    public String getDepartment() {
        return department;
    }

    public String getProgram() {
        return program;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public String getPresent_semester() {
        return present_semester;
    }

    public String getEmail() {
        return email;
    }

    public String getMob_no() {
        return mob_no;
    }

    public String getPassword() {
        return password;
    }

    public String getSession() {
        return session;
    }
}
