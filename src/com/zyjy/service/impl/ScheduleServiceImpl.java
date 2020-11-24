package com.zyjy.service.impl;

import com.zyjy.dao.base.BaseDao;
import com.zyjy.dao.inter.*;
import com.zyjy.dto.ResponseBean;
import com.zyjy.myAnnotation.AutoSet;
import com.zyjy.myAnnotation.MyService;
import com.zyjy.pojo.*;
import com.zyjy.service.inter.ScheduleService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ScheduleServiceImpl
 * @Description
 * @Author 清Great
 * @Date 2020/11/13 10:49
 */
@MyService("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService {
    @AutoSet
    private ViewScheduleDao viewScheduleDao;

    @AutoSet
    private ViewSchDao viewSchDao;

    @AutoSet
    private StateDao stateDao;

    @AutoSet
    private ScheduleDao scheduleDao;

    @AutoSet
    private CardDao cardDao;

    @AutoSet
    private LogInfoDao logInfoDao;

    @AutoSet
    private CardSerDao cardSerDao;

    @AutoSet
    private WorkloadDao workloadDao;


    /**
     * 按照开始结束日期，获取数据
     *
     * @param date 日期
     */
    @Override
    public ResponseBean findList(String date) {
        Connection connAutoCommit = null;
        try {
            connAutoCommit = BaseDao.getBaseDao().getConnAutoCommit();
            List<ViewSch> list = viewSchDao.findList(connAutoCommit, date);

            return new ResponseBean(0, list, "获取数据成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeConnection(connAutoCommit);
        }
        return new ResponseBean(1, null, "获取数据失败");
    }

    /**
     * 获取预约数据
     *
     * @param doctorId 医生id
     * @param schDate  日期
     * @return
     */
    @Override
    public ResponseBean findAppointment(int doctorId, String schDate) {
        Connection connAutoCommit = null;
        try {
            connAutoCommit = BaseDao.getBaseDao().getConnAutoCommit();
            List<ViewSchedule> list = viewScheduleDao.findList(connAutoCommit, doctorId, schDate);

            return new ResponseBean(0, list, "获取数据成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeConnection(connAutoCommit);
        }
        return new ResponseBean(1, null, "获取数据失败");
    }
    /**
     * 预约
     * @param scheduleId 预约id
     * @param viewPatient 病人信息
     * @return
     */

    @Override
    public ResponseBean appoint(int scheduleId, ViewPatient viewPatient) {

        Connection connection = null;
        try {
            connection = BaseDao.getConnection();

            //判断余额是否足够挂号
            ViewSchedule viewSchedule = viewScheduleDao.findSch(connection, scheduleId);
            if (viewSchedule.getAppointFee() > viewPatient.getBalance()) {
                return new ResponseBean(1, null, "余额不足，请先充值，挂号费需"
                        + viewSchedule.getAppointFee() + "元");
            }

            //获取待预约状态
            State state1 = stateDao.findBy(connection, "待预约", "预约状态");

            //获取待取号状态
            State state2 = stateDao.findBy(connection, "待取号", "预约状态");

            //进行预约
            scheduleDao.appoint(connection, viewPatient.getPatientId(), state2.getStateId(), scheduleId, state1.getStateId());

            //预约完成，扣除金额
            cardDao.updateMoney(connection, -viewSchedule.getAppointFee(), viewPatient.getCardNum());

            //记录到卡业务
            cardSerDao.insert(connection, new CardSer(viewPatient.getCardNum(), "预约挂号",
                    -viewSchedule.getAppointFee(), 1, ""));

            //记录到统计表
            workloadDao.insert(connection, new Workload(viewSchedule.getUserId(), "预约挂号", 1));

            LogInfo logInfo = new LogInfo(1, "预约", "");

            logInfoDao.insert(connection, logInfo);

            BaseDao.commit(connection);
            return new ResponseBean(0, null, "预约成功");
        } catch (Exception e) {
            BaseDao.rollback(connection);
            e.printStackTrace();
        } finally {
            BaseDao.closeConnection(connection);
        }
        return new ResponseBean(1, null, "预约失败");
    }

    /**
     * 取消预约
     *
     * @param scheduleId  排班id
     * @param viewPatient 病人信息
     * @return
     */
    @Override
    public ResponseBean cancelAppoint(int scheduleId, ViewPatient viewPatient) {
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();

            ViewSchedule viewSchedule = viewScheduleDao.findSch(connection, scheduleId);
            //
            if (viewSchedule.getPatientId() != viewPatient.getPatientId()) {
                return new ResponseBean(1, null, "不是您的预约，您不能取消");
            }
            //获取待预约状态
            State state1 = stateDao.findBy(connection, "待预约", "预约状态");

            //获取待取号状态
            State state2 = stateDao.findBy(connection, "待取号", "预约状态");

            //获取预约单

            //取消预约
            scheduleDao.appoint(connection, -1, state1.getStateId(), scheduleId, state2.getStateId());

            //取消预约，金额回复
            cardDao.updateMoney(connection, viewSchedule.getAppointFee(), viewPatient.getCardNum());

            //记录到卡业务
            cardSerDao.insert(connection, new CardSer(viewPatient.getCardNum(), "取消挂号",
                    viewSchedule.getAppointFee(), 1, ""));

            LogInfo logInfo = new LogInfo(1, "取消挂号", "");

            logInfoDao.insert(connection, logInfo);

            BaseDao.commit(connection);
            return new ResponseBean(0, null, "取消预约成功");
        } catch (Exception e) {
            BaseDao.rollback(connection);
            e.printStackTrace();
        } finally {
            BaseDao.closeConnection(connection);
        }
        return new ResponseBean(1, null, "取消预约失败");
    }

    /**
     * 获取该病人的待取号预约
     *
     * @param patientId 病人id
     */
    @Override
    public ResponseBean getAppointList(int patientId) {

        Connection connAutoCommit = null;
        try {
            connAutoCommit = BaseDao.getBaseDao().getConnAutoCommit();

            List<ViewSchedule> appointList = viewScheduleDao.findAppointList(connAutoCommit, patientId);

            return new ResponseBean(0, appointList, "获取数据成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeConnection(connAutoCommit);
        }
        return new ResponseBean(1, null, "获取数据失败");

    }

    /**
     * 取号
     *
     * @param scheduleIdArr 排班id
     * @param viewPatient   病人信息
     * @return
     */
    @Override
    public ResponseBean takeNum(Integer[] scheduleIdArr, ViewPatient viewPatient) {
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();

            //获取待取号状态
            State state2 = stateDao.findBy(connection, "待取号", "预约状态");
            //获取已取号状态
            State state3 = stateDao.findBy(connection, "已取号", "预约状态");

            ArrayList<ResponseBean> takeNumList = new ArrayList<ResponseBean>();
            for (int scheduleId : scheduleIdArr) {
                //取号
                scheduleDao.appoint(connection, viewPatient.getPatientId(), state3.getStateId(), scheduleId, state2.getStateId());
                logInfoDao.insert(connection, new LogInfo(1, "自助取号", ""));

                BaseDao.commit(connection);
                //打印排队号码
                ViewSchedule sch = viewScheduleDao.findSch(connection, scheduleId);
                int haveTakeNum = viewScheduleDao.findHaveTakeNum(connection, sch.getSchDate());
                StringBuilder sb = new StringBuilder();
                sb.append(sch.getSchDate()).append("   科室：").append(sch.getDepName()).append("   就诊医生：")
                        .append(sch.getUserName()).append("   排队号码：").append(haveTakeNum).append("\n");
                takeNumList.add(new ResponseBean(0, null, sb.toString()));

            }

            return new ResponseBean(0, takeNumList, "取号成功");
        } catch (Exception e) {
            BaseDao.rollback(connection);
            e.printStackTrace();
        } finally {
            BaseDao.closeConnection(connection);
        }
        return new ResponseBean(1, null, "取号失败");

    }


}
