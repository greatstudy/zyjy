package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.ViewSchDao;
import com.zyjy.myAnnotation.MyDao;
import com.zyjy.pojo.ViewSch;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName ViewSchDaoImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/12 21:49
 */
@MyDao("ViewSchDao")
public class ViewSchDaoImpl extends FatherDao<ViewSch> implements ViewSchDao {
    /**
     * 按照开始结束日期，获取数据
     *
     * @param date 日期
     */
    @Override
    public List<ViewSch> findList(Connection connection, String date) throws Exception {
        String sql = "select * from view_sch where sch_date >= (trunc(to_date(?,'yyyy-MM-dd'),'d')+1) " +
                "and sch_date <= (trunc(to_date(?,'yyyy-MM-dd'),'d')+7)";

        return commonQuery(connection, sql, date, date);
    }

}
