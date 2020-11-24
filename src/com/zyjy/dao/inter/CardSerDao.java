package com.zyjy.dao.inter;

import com.zyjy.pojo.CardSer;

import java.sql.Connection;
import java.sql.SQLException;

public interface CardSerDao {
    /**
     * 卡业务信息插入
     * @param connection
     * @param cardSer 卡业务bean
     * @return
     * @throws SQLException
     */
    int insert(Connection connection, CardSer cardSer) throws SQLException;

}
