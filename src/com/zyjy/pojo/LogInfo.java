package com.zyjy.pojo;

import java.sql.Timestamp;

/**
 * @ClassName LogInfo
 * @Description
 * @Author 清Great
 * @Date 2020/11/6 9:05
 */
public class LogInfo {
    private int logInfoId;
    private int userId;
    private String logName;
    private Timestamp logTime;
    private String descInfo;

    public LogInfo() {
    }

    public LogInfo(int userId, String logName, String descInfo) {
        this.userId = userId;
        this.logName = logName;
        this.descInfo = descInfo;
    }

    public int getLogInfoId() {
        return logInfoId;
    }

    public void setLogInfoId(int logInfoId) {
        this.logInfoId = logInfoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }
}
