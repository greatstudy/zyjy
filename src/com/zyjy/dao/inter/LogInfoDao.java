package com.zyjy.dao.inter;

import com.zyjy.pojo.LogInfo;

import java.sql.Connection;
import java.sql.SQLException;

public interface LogInfoDao {
    /**
     * 添加日志
     * @param connection
     * @param logInfo 日志bean
     * @return
     * @throws SQLException
     */
    int insert(Connection connection, LogInfo logInfo) throws SQLException;
}
