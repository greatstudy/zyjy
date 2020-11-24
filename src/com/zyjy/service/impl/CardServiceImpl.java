package com.zyjy.service.impl;

import com.zyjy.dao.base.BaseDao;
import com.zyjy.dao.inter.CardDao;
import com.zyjy.dao.inter.CardSerDao;
import com.zyjy.dao.inter.LogInfoDao;
import com.zyjy.dto.ResponseBean;
import com.zyjy.myAnnotation.AutoSet;
import com.zyjy.myAnnotation.MyService;
import com.zyjy.pojo.CardSer;
import com.zyjy.pojo.LogInfo;
import com.zyjy.service.inter.CardService;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName CardServiceImpl
 * @Description 卡
 * @Author 清Great
 * @Date 2020/11/11 16:50
 */
@MyService("CardService")
public class CardServiceImpl implements CardService {

    @AutoSet
    private CardDao cardDao;

    @AutoSet
    private LogInfoDao logInfoDao;

    @AutoSet
    private CardSerDao cardSerDao;

    /**
     * 卡充值
     * @param money 充值金额
     * @param cardNum 卡号
     * @return
     */
    @Override
    public ResponseBean recharge(int money, String cardNum) {

        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            //充值
            cardDao.updateMoney(connection, money, cardNum);

            //添加到业务表
            CardSer cardSer = new CardSer(cardNum, "充值", money, 1, "");
            cardSerDao.insert(connection, cardSer);

            logInfoDao.insert(connection, new LogInfo(1, "自助充值", cardNum + "充值：" + money + "元"));

            BaseDao.commit(connection);
            return new ResponseBean(0, null, "充值" + money + "成功");
        } catch (SQLException e) {
            BaseDao.rollback(connection);
            e.printStackTrace();
        } finally {
            BaseDao.closeConnection(connection);
        }
        return new ResponseBean(1, null, "充值失败");
    }


}
