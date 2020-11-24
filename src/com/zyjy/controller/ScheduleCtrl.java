package com.zyjy.controller;

import com.zyjy.dto.ResponseBean;
import com.zyjy.myAnnotation.AutoSet;
import com.zyjy.myAnnotation.MyParam;
import com.zyjy.pojo.ViewPatient;
import com.zyjy.service.inter.ScheduleService;
import com.zyjy.util.RegexUtils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName ScheduleCtrl
 * @Description
 * @Author 清Great
 * @Date 2020/11/13 12:56
 */
public class ScheduleCtrl {
    @AutoSet
    private ScheduleService scheduleService;

    /**
     * 获取排班数据
     * @param date 要查询周日期
     * @return
     */
    public ResponseBean findSchedule(@MyParam("date") String date) {
        return scheduleService.findList(date);
    }

    /**
     * 获取预约信息
     * @param doctorIdStr 医生id
     * @param schDate 日期
     * @return
     */
    public ResponseBean findAppointment(@MyParam("doctorId") String doctorIdStr,
                                        @MyParam("schDate") String schDate) {

        if (!RegexUtils.isInt(doctorIdStr)) {
            return new ResponseBean(1, null, "数据错误");
        }

        return scheduleService.findAppointment(Integer.parseInt(doctorIdStr), schDate);
    }

    /**
     * 预约
     * @param scheduleIdStr 排班id
     * @param cardNum 卡号
     * @return
     */
    public ResponseBean appoint(@MyParam("request") HttpServletRequest request,
                                @MyParam("scheduleId") String scheduleIdStr,
                                @MyParam("cardNum") String cardNum) {

        if (!RegexUtils.isInt(scheduleIdStr)) {
            return new ResponseBean(1, null, "数据错误");
        }

        Object cardInfo = request.getSession().getAttribute("cardInfo");
        if (cardInfo == null) {
            return new ResponseBean(1, null, "请先读卡");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ViewPatient viewPatient = null;
        try {
            viewPatient = objectMapper.readValue(String.valueOf(cardInfo), ViewPatient.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(1, null, "系统异常");
        }

        if (cardNum == null || !cardNum.equals(viewPatient.getCardNum())) {
            return new ResponseBean(1, null, "卡号与读卡信息不一致，请重新读卡");
        }

        return scheduleService.appoint(Integer.parseInt(scheduleIdStr), viewPatient);
    }

    /**
     * 取消预约
     * @param scheduleIdStr 排班id
     * @param cardNum 卡号
     * @return
     */
    public ResponseBean cancelAppoint(@MyParam("request") HttpServletRequest request,
                                      @MyParam("scheduleId") String scheduleIdStr,
                                      @MyParam("cardNum") String cardNum) {

        if (!RegexUtils.isInt(scheduleIdStr)) {
            return new ResponseBean(1, null, "数据错误");
        }

        Object cardInfo = request.getSession().getAttribute("cardInfo");
        if (cardInfo == null) {
            return new ResponseBean(1, null, "请先读卡");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ViewPatient viewPatient = null;
        try {
            viewPatient = objectMapper.readValue(String.valueOf(cardInfo), ViewPatient.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(1, null, "系统异常");
        }

        if (cardNum == null || !cardNum.equals(viewPatient.getCardNum())) {
            return new ResponseBean(1, null, "卡号与读卡信息不一致，请重新读卡");
        }

        return scheduleService.cancelAppoint(Integer.parseInt(scheduleIdStr), viewPatient);
    }

    /**
     * 获取预约信息
     * @param request
     * @param cardNum 卡号
     * @return
     */
    public ResponseBean getAppointment(@MyParam("request") HttpServletRequest request,
                                       @MyParam("cardNum") String cardNum) {

        Object cardInfo = request.getSession().getAttribute("cardInfo");
        if (cardInfo == null) {
            return new ResponseBean(1, null, "请先读卡");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ViewPatient viewPatient = null;
        try {
            viewPatient = objectMapper.readValue(String.valueOf(cardInfo), ViewPatient.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(1, null, "系统异常");
        }

        if (cardNum == null || !cardNum.equals(viewPatient.getCardNum())) {
            return new ResponseBean(1, null, "卡号与读卡信息不一致，请重新读卡");
        }

        return scheduleService.getAppointList(viewPatient.getPatientId());
    }


    /**
     * 取号
     * @param cardNum  卡号
     * @param schIdArrStr 要取得号码
     * @return
     */
    public ResponseBean takeNumber(@MyParam("request") HttpServletRequest request,
                                @MyParam("cardNum") String cardNum,
                                @MyParam("schIdArr") String schIdArrStr) {
        Object cardInfo = request.getSession().getAttribute("cardInfo");
        if (cardInfo == null) {
            return new ResponseBean(1, null, "请先读卡");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ViewPatient viewPatient = null;
        Integer[] schIdArr = null;
        try {
            viewPatient = objectMapper.readValue(String.valueOf(cardInfo), ViewPatient.class);

            schIdArr = objectMapper.readValue(schIdArrStr, Integer[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(1, null, "系统异常");
        }

        if (cardNum == null || !cardNum.equals(viewPatient.getCardNum())) {
            return new ResponseBean(1, null, "卡号与读卡信息不一致，请重新读卡");
        }

        return scheduleService.takeNum(schIdArr,viewPatient);
//        return new ResponseBean(1, null, "执行");
    }

}
