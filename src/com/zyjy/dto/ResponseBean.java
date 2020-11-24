package com.zyjy.dto;

/**
 * @ClassName ResponseBean
 * @Description 应答传输Bean
 * @Author 清Great
 * @Date 2020/10/28 11:39
 */
public class ResponseBean {
    //应答状态：0表示成功，1表示失败
    private int state;

    //返回主要对象数据(单个对象或者集合、数组等)
    private Object data;

    //提示信息
    private String msgInfo;//默认：传输成功


    //分页对象
    private PageBean pageBean;

    public ResponseBean() {
    }

    public ResponseBean(int state, Object data, String msgInfo) {
        this.state = state;
        this.data = data;
        this.msgInfo = msgInfo;
    }

    public ResponseBean(int state, Object data, String msgInfo, PageBean pageBean) {
        this.state = state;
        this.data = data;
        this.msgInfo = msgInfo;
        this.pageBean = pageBean;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "state=" + state +
                ", data=" + data +
                ", msgInfo='" + msgInfo + '\'' +
                ", pageBean=" + pageBean +
                '}';
    }
}
