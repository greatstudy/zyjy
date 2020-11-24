package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.ViewCardSerDao;
import com.zyjy.dto.PageBean;
import com.zyjy.myAnnotation.MyDao;
import com.zyjy.pojo.ViewCardSer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ViewCardSerDaoImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/14 11:49
 */
@MyDao("ViewCardSerDao")
public class ViewCardSerDaoImpl extends FatherDao<ViewCardSer> implements ViewCardSerDao {
    /**
     * 根据卡号获取账单
     *
     * @param connection
     * @param cardNum    用户卡号
     * @param pageBean   页码
     */
    @Override
    public List<ViewCardSer> findViewCardSerList(Connection connection, String cardNum, PageBean pageBean) throws Exception {
        String sql = "select * from (select rownum rn ,a.* from view_card_ser a where card_num = ?) " +
                "where rn > ? and rn <= ?";
        return commonQuery(connection, sql, cardNum, pageBean.getStart(), pageBean.getEnd());
    }

    /**
     * 根据卡号获取账单 数量
     *
     * @param connection
     * @param cardNum    用户卡号
     */
    @Override
    public int count(Connection connection, String cardNum) throws SQLException {
        String sql = "select count(*) from view_card_ser where card_num = ?";
        return countTotalRecord(connection, sql, cardNum);
    }


}
