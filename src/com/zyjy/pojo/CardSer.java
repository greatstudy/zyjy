package com.zyjy.pojo;

import java.sql.Timestamp;

/**
 * @ClassName CardSer
 * @Description 卡的业务表
 * @Author 清Great
 * @Date 2020/11/11 20:27
 */
public class CardSer {

    private int cardSerId;
    private String cardNum;
    private String serType;
    private int serCost;
    private Timestamp serTime;
    private int userId;
    private String serDesc;

    public CardSer() {
    }

    public CardSer(String cardNum, String serType, int serCost, int userId,String serDesc) {
        this.cardNum = cardNum;
        this.serType = serType;
        this.serCost = serCost;
        this.userId = userId;
        this.serDesc = serDesc;
    }

    public int getCardSerId() {
        return cardSerId;
    }

    public void setCardSerId(int cardSerId) {
        this.cardSerId = cardSerId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getSerType() {
        return serType;
    }

    public void setSerType(String serType) {
        this.serType = serType;
    }

    public int getSerCost() {
        return serCost;
    }

    public void setSerCost(int serCost) {
        this.serCost = serCost;
    }

    public Timestamp getSerTime() {
        return serTime;
    }

    public void setSerTime(Timestamp serTime) {
        this.serTime = serTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSerDesc() {
        return serDesc;
    }

    public void setSerDesc(String serDesc) {
        this.serDesc = serDesc;
    }
}
