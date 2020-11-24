package com.zyjy.dao.impl;

import com.zyjy.dao.base.FatherDao;
import com.zyjy.dao.inter.ViewPatientDao;
import com.zyjy.myAnnotation.MyDao;
import com.zyjy.pojo.ViewPatient;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName ViewPatientDaoImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/11 11:05
 */
@MyDao("ViewPatientDao")
public class ViewPatientDaoImpl extends FatherDao<ViewPatient> implements ViewPatientDao {
    /**
     * 卡号/手机号码/身份证号码
     * @param connection
     * @param readCard
     * @return
     * @throws Exception
     */
    @Override
    public List<ViewPatient> readCard(Connection connection, String readCard) throws Exception {
        String sql = "select * from view_patient where (card_num = ? or tel = ? or id_num = ?) " +
                " and (state_name = '启用' or state_name = '禁用')";

        return commonQuery(connection, sql, readCard, readCard, readCard);
    }
}
