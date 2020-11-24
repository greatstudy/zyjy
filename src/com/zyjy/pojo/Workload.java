package com.zyjy.pojo;

import java.sql.Timestamp;

/**
 * @ClassName Workload
 * @Description
 * @Author 清Great
 * @Date 2020/11/12 9:30
 */
public class Workload {
    private int workloadId;
    private int tollId;
    private String wordType;
    private int workValue;
    private Timestamp workTile;
    private String workDesc;


    public Workload() {
    }

    public Workload(int tollId, String wordType, int workValue) {
        this.tollId = tollId;
        this.wordType = wordType;
        this.workValue = workValue;
    }

    public int getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(int workloadId) {
        this.workloadId = workloadId;
    }

    public int getTollId() {
        return tollId;
    }

    public void setTollId(int tollId) {
        this.tollId = tollId;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public int getWorkValue() {
        return workValue;
    }

    public void setWorkValue(int workValue) {
        this.workValue = workValue;
    }

    public Timestamp getWorkTile() {
        return workTile;
    }

    public void setWorkTile(Timestamp workTile) {
        this.workTile = workTile;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }
}
