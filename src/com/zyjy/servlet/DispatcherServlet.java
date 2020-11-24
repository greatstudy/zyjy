package com.zyjy.servlet;

import com.zyjy.dto.ResponseBean;
import com.zyjy.myAnnotation.MyParam;
import com.zyjy.util.AutoSetUtil;
import com.zyjy.util.ControllerUtil;
import com.zyjy.util.DaoUtil;
import com.zyjy.util.ServiceUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Map;

/**
 * @ClassName DispatcherServlet
 * @Description 分发器
 * @Author 清Great
 * @Date 2020/11/3 18:46
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //扫描所有controller类
        ControllerUtil.setPackName("com.zyjy.controller");
        ServiceUtil.setPackName("com.zyjy.service.impl");
        DaoUtil.setPackName("com.zyjy.dao.impl");

        AutoSetUtil.AutoSetDao();
        AutoSetUtil.AutoSetService();

        //参数加载进内存，以后可以直接获取
//        ParamsUtil.loadParams();


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取方法名
        StringBuffer requestURL = req.getRequestURL();
        String methodName = req.getParameter("method");

        //获取类名
        String url1 = requestURL.substring(0, requestURL.indexOf(".do"));
        String className = url1.substring(url1.lastIndexOf("/") + 1);

//        System.out.println("类名："+className);
//        System.out.println("方法名："+methodName);
        Class<?> clazz = ControllerUtil.getClassMap(className);
        Object objMap = ControllerUtil.getObjMap(className);
//        System.out.println("类2："+clazz);
//        System.out.println("方法2："+objMap);


        Method[] declaredMethods = clazz.getDeclaredMethods();
        Method method = null;
        for (Method method1 : declaredMethods) {
            if (method1.getName().equals(methodName)) {
                method = method1;
                break;
            }
        }

        if (method == null) {
//            在此处直接进行返回
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBean responseBean = new ResponseBean();
            responseBean.setState(1);
            responseBean.setMsgInfo("无此方法");
            String value = objectMapper.writeValueAsString(responseBean);
            resp.getWriter().write(value);
            resp.getWriter().flush();
            return;
        }
        //获取参数传递给方法
        Map<String, String[]> reqParameterMap = req.getParameterMap();

        Parameter[] parameters = method.getParameters();

        ArrayList<Object> objList = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            boolean flag = false;
            MyParam param = parameters[i].getAnnotation(MyParam.class);
            for (String key : reqParameterMap.keySet()) {

                if (param.value().equals("request")) {
                    flag = true;
                    objList.add(req);
                    break;
                }
                if (param.value().equals("response")) {
                    flag = true;
                    objList.add(resp);
                    break;
                }
                if (param.value().equals(key)) {
                    flag = true;
                    objList.add(reqParameterMap.get(key)[0]);
                    break;
                }
            }
            if (!flag) {
                objList.add(null);
            }
        }


        try {

//            System.out.println(method);

            //调用方法并且获取返回值
            Object responseBean = method.invoke(objMap, objList.toArray());

            if (responseBean != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String value = objectMapper.writeValueAsString(responseBean);
                resp.getWriter().write(value);
                resp.getWriter().flush();
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
