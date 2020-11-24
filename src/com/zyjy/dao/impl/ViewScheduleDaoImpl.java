package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.ViewScheduleDao;
import com.zyjy.myAnnotation.MyDao;
import com.zyjy.pojo.ViewSchedule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ViewScheduleDaoImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/13 10:45
 */
@MyDao("ViewScheduleDao")
public class ViewScheduleDaoImpl extends FatherDao<ViewSchedule> implements ViewScheduleDao {


    /**
     * 获取预约的数据
     *
     * @param connection
     * @param doctorId   医生id
     * @param schDate    日期
     */
    @Override
    public List<ViewSchedule> findList(Connection connection, int doctorId, String schDate) throws Exception {
        String sql = "select * from view_schedule where user_id = ? and sch_date = to_date(?,'yyyy-MM-dd')";
        return commonQuery(connection, sql, doctorId, schDate);
    }

    /**
     * 获取指定排班数据
     *
     * @param scheduleId 排班id
     * @return
     */
    @Override
    public ViewSchedule findSch(Connection connection, int scheduleId) throws Exception {
        String sql = "select * from view_schedule where schedule_id = ?";
        return findSingleBean(connection, sql, scheduleId);
    }

    /**
     * 获取该病人的待取号预约
     *
     * @param connection
     * @param patientId  病人id
     */
    @Override
    public List<ViewSchedule> findAppointList(Connection connection, int patientId) throws Exception {
        String sql = "select * from view_schedule where patient_id = ? and state_name = '待取号'";
        return commonQuery(connection, sql, patientId);
    }

    /**
     * 根据日期获取已取号个数，
     *
     * @param connection
     * @param date
     */
    @Override
    public int findHaveTakeNum(Connection connection, Date date) throws SQLException {
        String sql = "select count(*) from view_schedule where state_name = '已取号' " +
                " and sch_date = ?";
        return countTotalRecord(connection, sql, date);
    }

}
