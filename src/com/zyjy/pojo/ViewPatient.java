package com.zyjy.pojo;

/**
 * @ClassName ViewPatient
 * @Description
 * @Author 清Great
 * @Date 2020/11/11 11:02
 */
public class ViewPatient extends Patient{

    private String cardNum;
    private int balance;
    private int deposit;


    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }


}
