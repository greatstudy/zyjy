package com.zyjy.pojo;

/**
 * @ClassName Patient
 * @Description
 * @Author 清Great
 * @Date 2020/11/10 14:43
 */
public class Patient {
    private int patientId;
    private String patientName;
    private String sex;
    private int age;
    private String nativeArea;
    private String idNum;
    private String tel;
    private String addr;
    private String patientDesc;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNativeArea() {
        return nativeArea;
    }

    public void setNativeArea(String nativeArea) {
        this.nativeArea = nativeArea;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPatientDesc() {
        return patientDesc;
    }

    public void setPatientDesc(String patientDesc) {
        this.patientDesc = patientDesc;
    }
}
