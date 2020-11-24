package com.zyjy.dao.inter;

import com.zyjy.pojo.State;

import java.sql.Connection;

public interface StateDao {
    /**
     * 根据状态名称和状态类型获取状态
     * @param connection
     * @param stateName 状态名称
     * @param stateType 状态类型
     * @return
     * @throws Exception
     */
    State findBy(Connection connection, String stateName, String stateType) throws Exception;

}
