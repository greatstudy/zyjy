package com.zyjy.pojo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName Schedule
 * @Description
 * @Author 清Great
 * @Date 2020/11/12 16:46
 */
public class Schedule {
    private int scheduleId;
    private int userId;
    private Date schDate;
    private int startTime;
    private int patientId;
    private int stateId;
    private Timestamp appointTime;
    private String schDesc;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getSchDate() {
        return schDate;
    }

    public void setSchDate(Date schDate) {
        this.schDate = schDate;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getSchDesc() {
        return schDesc;
    }

    public void setSchDesc(String schDesc) {
        this.schDesc = schDesc;
    }

    public Timestamp getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Timestamp appointTime) {
        this.appointTime = appointTime;
    }
}
