package com.programmersjail.tmuian.helper;

public class AccountantModel {

    private int id;
    private String studentName,idNo,adFormOfHers,yearlyFee,totalYearlyFee,runningYearlyFee,perSemester,totalSemester,
            runningSemester,additionalFee,monthlyPaid,totalMonth,totalTuitionFee,totalWillBePaid,payCompleteMonth,
            tuitionFeePayable,totalPaid,lastPaidRecord,totalDue,defaulter,dateOfAdmission,dueMonth,remarks,
            session,deadline;

    public AccountantModel(int id, String studentName, String idNo, String adFormOfHers, String yearlyFee, String totalYearlyFee, String runningYearlyFee, String perSemester, String totalSemester, String runningSemester, String additionalFee, String monthlyPaid, String totalMonth,
                           String totalTuitionFee, String totalWillBePaid, String payCompleteMonth, String tuitionFeePayable, String totalPaid, String lastPaidRecord, String totalDue, String defaulter, String dateOfAdmission, String dueMonth, String remarks, String session, String deadline) {
        this.id = id;
        this.studentName = studentName;
        this.idNo = idNo;
        this.adFormOfHers = adFormOfHers;
        this.yearlyFee = yearlyFee;
        this.totalYearlyFee = totalYearlyFee;
        this.runningYearlyFee = runningYearlyFee;
        this.perSemester = perSemester;
        this.totalSemester = totalSemester;
        this.runningSemester = runningSemester;
        this.additionalFee = additionalFee;
        this.monthlyPaid = monthlyPaid;
        this.totalMonth = totalMonth;
        this.totalTuitionFee = totalTuitionFee;
        this.totalWillBePaid = totalWillBePaid;
        this.payCompleteMonth = payCompleteMonth;
        this.tuitionFeePayable = tuitionFeePayable;
        this.totalPaid = totalPaid;
        this.lastPaidRecord = lastPaidRecord;
        this.totalDue = totalDue;
        this.defaulter = defaulter;
        this.dateOfAdmission = dateOfAdmission;
        this.dueMonth = dueMonth;
        this.remarks = remarks;
        this.session = session;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getAdFormOfHers() {
        return adFormOfHers;
    }

    public String getYearlyFee() {
        return yearlyFee;
    }

    public String getTotalYearlyFee() {
        return totalYearlyFee;
    }

    public String getRunningYearlyFee() {
        return runningYearlyFee;
    }

    public String getPerSemester() {
        return perSemester;
    }

    public String getTotalSemester() {
        return totalSemester;
    }

    public String getRunningSemester() {
        return runningSemester;
    }

    public String getAdditionalFee() {
        return additionalFee;
    }

    public String getMonthlyPaid() {
        return monthlyPaid;
    }

    public String getTotalMonth() {
        return totalMonth;
    }

    public String getTotalTuitionFee() {
        return totalTuitionFee;
    }

    public String getTotalWillBePaid() {
        return totalWillBePaid;
    }

    public String getPayCompleteMonth() {
        return payCompleteMonth;
    }

    public String getTuitionFeePayable() {
        return tuitionFeePayable;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public String getLastPaidRecord() {
        return lastPaidRecord;
    }

    public String getTotalDue() {
        return totalDue;
    }

    public String getDefaulter() {
        return defaulter;
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    public String getDueMonth() {
        return dueMonth;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getSession() {
        return session;
    }

    public String getDeadline() {
        return deadline;
    }
}
