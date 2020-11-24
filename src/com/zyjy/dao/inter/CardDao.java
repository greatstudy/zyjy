package com.zyjy.dao.inter;

import java.sql.Connection;
import java.sql.SQLException;

public interface CardDao {
    /**
     * 卡充值
     * @param money 充值金额
     * @param cardNum 卡号
     * @return
     */
    int updateMoney(Connection connection, int money, String cardNum) throws SQLException;



}
