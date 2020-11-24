package com.zyjy.service.inter;

import com.zyjy.dto.ResponseBean;
import com.zyjy.pojo.ViewPatient;

public interface ScheduleService {
    /**
     * 按照开始结束日期，获取数据
     *
     * @param date 日期
     */
    ResponseBean findList(String date);

    /**
     * 获取预约数据
     * @param doctorId 医生id
     * @param schDate 日期
     * @return
     */
    ResponseBean findAppointment(int doctorId, String schDate);

    /**
     * 预约
     * @param scheduleId 预约id
     * @param viewPatient 病人信息
     * @return
     */
    ResponseBean appoint(int scheduleId, ViewPatient viewPatient);

    /**
     * 取消预约
     * @param scheduleId  订单id
     * @param viewPatient 病人信息
     * @return
     */
    ResponseBean cancelAppoint(int scheduleId, ViewPatient viewPatient);

    /**
     * 获取该病人的待取号预约
     * @param patientId 病人id
     */
    ResponseBean getAppointList(int patientId);


    /**
     * 取号
     * @param scheduleIdArr  订单id
     * @param viewPatient 病人信息
     * @return
     */
    ResponseBean takeNum(Integer[] scheduleIdArr, ViewPatient viewPatient);

}
