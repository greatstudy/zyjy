package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.StateDao;
import com.zyjy.myAnnotation.MyDao;
import com.zyjy.pojo.State;

import java.sql.Connection;

/**
 * @ClassName StateDaoImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/13 20:59
 */
@MyDao("StateDao")
public class StateDaoImpl extends FatherDao<State> implements StateDao {

    /**
     * 根据状态名称和状态类型获取状态
     * @param connection
     * @param stateName 状态名称
     * @param stateType 状态类型
     * @return
     * @throws Exception
     */
    @Override
    public State findBy(Connection connection, String stateName, String stateType) throws Exception {
        String sql = "select * from c_state where state_name = ? and state_type = ?";
        return findSingleBean(connection, sql, stateName, stateType);
    }
}
