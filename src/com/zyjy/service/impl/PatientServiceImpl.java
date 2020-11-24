package com.zyjy.service.impl;

import com.zyjy.dao.base.BaseDao;
import com.zyjy.dao.inter.ViewPatientDao;
import com.zyjy.dto.ResponseBean;
import com.zyjy.myAnnotation.AutoSet;
import com.zyjy.myAnnotation.MyService;
import com.zyjy.pojo.ViewPatient;
import com.zyjy.service.inter.PatientService;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName PatientServiceImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/11 16:25
 */
@MyService("PatientService")
public class PatientServiceImpl implements PatientService {

    @AutoSet
    private ViewPatientDao viewPatientDao;

    /**
     * 读卡
     * @param readCard 卡号
     * @return
     */
    @Override
    public ResponseBean findForReadCard(String readCard) {

        ResponseBean responseBean = new ResponseBean(1, null, "服务器异常");
        Connection connAutoCommit = null;
        try {
            connAutoCommit = BaseDao.getBaseDao().getConnAutoCommit();
            List<ViewPatient> viewPatients = viewPatientDao.readCard(connAutoCommit, readCard);

            if (viewPatients.size() > 0) {
                responseBean = new ResponseBean(0, viewPatients, "查询成功");
            } else {
                responseBean = new ResponseBean(1, null, "未查到该用户");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeConnection(connAutoCommit);
        }
        return responseBean;
    }

}
