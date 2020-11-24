package com.zyjy.dao.inter;

import com.zyjy.pojo.ViewSch;

import java.sql.Connection;
import java.util.List;

public interface ViewSchDao {
    /**
     * 按照开始结束日期，获取数据
     * @param date 日期
     */
    List<ViewSch> findList(Connection connection, String date) throws Exception;


}
