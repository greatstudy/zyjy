package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.WorkloadDao;
import com.zyjy.myAnnotation.MyDao;
import com.zyjy.pojo.Workload;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName WorkloadDaoImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/12 9:34
 */
@MyDao("WorkloadDao")
public class WorkloadDaoImpl extends FatherDao<Workload> implements WorkloadDao {
    /**
     * 添加工作统计信息
     * @param connection
     * @param workload 工作统计bean
     * @return
     * @throws SQLException
     */
    @Override
    public int insert(Connection connection, Workload workload) throws SQLException {
        String sql = "insert into c_workload(WORKLOAD_ID, TOLL_ID, WORK_TYPE, WORK_VALUE) " +
                "values (seq_workload.nextVal,?,?,?)";
        return commonUpdate(connection, sql, workload.getTollId(), workload.getWordType(), workload.getWorkValue());
    }
}
