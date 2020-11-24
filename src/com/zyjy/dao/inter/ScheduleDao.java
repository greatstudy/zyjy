package com.zyjy.dao.inter;

import java.sql.Connection;
import java.sql.SQLException;

public interface ScheduleDao {

    /**
     * 预约 和取消预约 和 取号
     *
     * @param patientId  病人id
     * @param stateId    状态改为待取号
     * @param scheduleId 排班id
     */
    int appoint(Connection connection, int patientId, int stateId, int scheduleId, int oldStateId) throws SQLException;


}
