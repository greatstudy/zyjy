package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.ScheduleDao;
import com.zyjy.myAnnotation.MyDao;
import com.zyjy.pojo.Schedule;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName ScheduleDaoImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/13 20:17
 */
@MyDao("ScheduleDao")
public class ScheduleDaoImpl extends FatherDao<Schedule> implements ScheduleDao {
    /**
     * 预约
     *
     * @param connection
     * @param patientId  病人id
     * @param stateId    状态改为待取号
     * @param scheduleId 排班id
     */
    @Override
    public int appoint(Connection connection, int patientId, int stateId, int scheduleId, int oldStateId) throws SQLException {
        String sql = "update c_schedule set patient_id = ? ,state_id = ?,appoint_time = sysdate" +
                " where schedule_id = ? and state_id = ?";
        return commonUpdate(connection, sql, patientId, stateId, scheduleId, oldStateId);
    }
}
