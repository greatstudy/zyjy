package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.CardDao;
import com.zyjy.myAnnotation.MyDao;

import javax.smartcardio.Card;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName CardDaoImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/11 16:55
 */
@MyDao("CardDao")
public class CardDaoImpl extends FatherDao<Card> implements CardDao {
    /**
     * 卡充值
     *
     * @param connection
     * @param money      充值金额
     * @param cardNum    卡号
     * @return
     */
    @Override
    public int updateMoney(Connection connection, int money, String cardNum) throws SQLException {
        String sql = "update c_card set balance = balance + ? where card_num = ? ";

        return commonUpdate(connection, sql, money, cardNum);
    }

}
