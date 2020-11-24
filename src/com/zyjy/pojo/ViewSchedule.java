package com.zyjy.pojo;

/**
 * @ClassName ViewSchedule
 * @Description
 * @Author 清Great
 * @Date 2020/11/12 19:02
 */
public class ViewSchedule extends Schedule {
    private String userName;
    private String patientName;
    private String stateName;
    private String depName;
    private int appointFee;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getAppointFee() {
        return appointFee;
    }

    public void setAppointFee(int appointFee) {
        this.appointFee = appointFee;
    }
}
