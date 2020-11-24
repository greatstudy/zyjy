package com.zyjy.pojo;

import java.sql.Date;

/**
 * @ClassName ViewSch
 * @Description
 * @Author 清Great
 * @Date 2020/11/12 21:46
 */
public class ViewSch {
    private int userId;
    private String userName;
    private String depName;
    private Date schDate;
    private int startTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
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


    @Override
    public String toString() {
        return "ViewSch{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", depName='" + depName + '\'' +
                ", schDate=" + schDate +
                ", startTime=" + startTime +
                '}';
    }
}
