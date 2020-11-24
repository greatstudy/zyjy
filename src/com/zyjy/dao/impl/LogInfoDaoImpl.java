package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.LogInfoDao;
import com.zyjy.myAnnotation.MyDao;
import com.zyjy.pojo.LogInfo;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName LogInfoDaoImpl
 * @Description 日志
 * @Author 清Great
 * @Date 2020/11/6 9:10
 */
@MyDao("LogInfoDao")
public class LogInfoDaoImpl extends FatherDao<LogInfo> implements LogInfoDao {
    /**
     * 添加日志
     * @param connection
     * @param logInfo 日志bean
     * @return
     * @throws SQLException
     */
    @Override
    public int insert(Connection connection,LogInfo logInfo) throws SQLException {
        String sql = "insert into c_log_info (LOG_INFO_ID, USER_ID, LOG_NAME, DESC_INFO) " +
                "values (SEQ_LOG_INFO.nextval,?,?,?)";
        return commonUpdate(connection, sql, logInfo.getUserId(), logInfo.getLogName(), logInfo.getDescInfo());
    }
}
