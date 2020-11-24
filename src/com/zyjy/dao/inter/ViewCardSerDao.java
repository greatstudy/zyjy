package com.zyjy.dao.inter;

import com.zyjy.dto.PageBean;
import com.zyjy.pojo.ViewCardSer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ViewCardSerDao {

    /**
     * 根据页码获取账单
     * @param cardNum 用户卡号
     * @param pageBean  页码
     */
    List<ViewCardSer> findViewCardSerList(Connection connection, String cardNum, PageBean pageBean) throws Exception;

    int count(Connection connection, String cardNum) throws SQLException;
}
