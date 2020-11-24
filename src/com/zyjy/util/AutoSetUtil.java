package com.zyjy.util;

import com.zyjy.myAnnotation.AutoSet;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @ClassName AutoSetUtil
 * @Description
 * @Author 清Great
 * @Date 2020/11/3 13:00
 */
public class AutoSetUtil {


    //自动注入服务层的dao属性
    public static void AutoSetDao() {

        //获取所有服务层类
        Map<String, Class<?>> serClassMap = ServiceUtil.getClassMap();

        for (String serClassKey : serClassMap.keySet()) {

            Class<?> clazz = serClassMap.get(serClassKey);

            Field[] declaredFields = clazz.getDeclaredFields();
            Object objMap = ServiceUtil.getObjMap(serClassKey);

            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(AutoSet.class)) {

                    Object obj = DaoUtil.getObjMap(declaredField.getType().getSimpleName());

                    try {
                        declaredField.setAccessible(true);
                        declaredField.set(objMap, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    /**
     * 自动注入控制层中的service属性
     */
    public static void AutoSetService() {

        //获取所有控制层类
        Map<String, Class<?>> ctrlClassMap = ControllerUtil.getClassMap();

        for (String ctrlClassKey : ctrlClassMap.keySet()) {

            Class<?> clazz = ctrlClassMap.get(ctrlClassKey);

            Field[] declaredFields = clazz.getDeclaredFields();
            Object objMap = ControllerUtil.getObjMap(ctrlClassKey);

            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(AutoSet.class)) {
                    Object obj = ServiceUtil.getObjMap(declaredField.getType().getSimpleName());
                    try {
                        declaredField.setAccessible(true);
                        declaredField.set(objMap, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }




}
