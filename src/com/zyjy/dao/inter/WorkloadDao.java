package com.zyjy.dao.inter;

import com.zyjy.pojo.Workload;

import java.sql.Connection;
import java.sql.SQLException;

public interface WorkloadDao {
    /**
     * 添加工作统计信息
     *
     * @param connection
     * @param workload   工作统计bean
     * @return
     * @throws SQLException
     */
    int insert(Connection connection, Workload workload) throws SQLException;

}
