package com.zyjy.dao.inter;

import com.zyjy.pojo.ViewSchedule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ViewScheduleDao {

    /**
     * 获取预约的数据
     *
     * @param doctorId 医生id
     * @param schDate  日期
     */
    List<ViewSchedule> findList(Connection connection, int doctorId, String schDate) throws Exception;

    ViewSchedule findSch(Connection connection, int schedule) throws Exception;

    /**
     * 获取该病人的待取号预约
     *
     * @param patientId 病人id
     */
    List<ViewSchedule> findAppointList(Connection connection, int patientId) throws Exception;

    /**
     * 根据日期获取已取号个数，
     */
    int findHaveTakeNum(Connection connection, Date date) throws SQLException;


}
