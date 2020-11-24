package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.CardSerDao;
import com.zyjy.myAnnotation.MyDao;
import com.zyjy.pojo.CardSer;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName CardSerDaoImpl
 * @Description 卡业务
 * @Author 清Great
 * @Date 2020/11/11 20:43
 */
@MyDao("CardSerDao")
public class CardSerDaoImpl extends FatherDao<CardSer> implements CardSerDao {

    /**
     * 卡业务信息插入
     * @param connection
     * @param cardSer 卡业务bean
     * @return
     * @throws SQLException
     */
    @Override
    public int insert(Connection connection, CardSer cardSer) throws SQLException {
        String sql = "insert into c_card_ser(CARD_SER_ID, CARD_NUM, SER_TYPE, SER_COST, USER_ID, SER_DESC) " +
                "values (seq_card_ser.nextVal,?,?,?,?,?)";
        Object[] args = {cardSer.getCardNum(), cardSer.getSerType(), cardSer.getSerCost(), cardSer.getUserId(), cardSer.getSerDesc()};
        return commonUpdate(connection, sql, args);
    }
}
