package com.zyjy.service.impl;

import com.zyjy.dao.base.BaseDao;
import com.zyjy.dao.inter.ViewCardSerDao;
import com.zyjy.dto.PageBean;
import com.zyjy.dto.ResponseBean;
import com.zyjy.myAnnotation.AutoSet;
import com.zyjy.myAnnotation.MyService;
import com.zyjy.pojo.ViewCardSer;
import com.zyjy.service.inter.CardSerService;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName CardSerServiceImpl
 * @Description 卡业务
 * @Author 清Great
 * @Date 2020/11/14 11:57
 */
@MyService("CardSerService")
public class CardSerServiceImpl implements CardSerService {

    @AutoSet
    private ViewCardSerDao viewCardSerDao;

    /**
     * 获取卡账单
     *
     * @param cardNum
     * @param pageBean
     */
    @Override
    public ResponseBean findCardSerList(String cardNum, PageBean pageBean) {
        Connection connAutoCommit = null;
        try {
            connAutoCommit = BaseDao.getBaseDao().getConnAutoCommit();

            List<ViewCardSer> viewCardSerList = viewCardSerDao.findViewCardSerList(connAutoCommit, cardNum, pageBean);

            int count = viewCardSerDao.count(connAutoCommit, cardNum);

            pageBean.setTotalRecord(count);

            return new ResponseBean(0, viewCardSerList, "获取数据成功", pageBean);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        BaseDao.closeConnection(connAutoCommit);
        }
        return new ResponseBean(1, null, "获取数据失败", pageBean);

    }
}
