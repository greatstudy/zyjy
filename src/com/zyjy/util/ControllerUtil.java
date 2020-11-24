package com.zyjy.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ControllerUtil
 * @Description
 * @Author 清Great
 * @Date 2020/11/2 13:19
 */
public class ControllerUtil {

    private static Map<String, Class<?>> classMap = new HashMap<>();
    private static Map<String, Object> objMap = new HashMap<>();


    public static void setPackName(String packName) {

        //获取某个包下的所有文件
//        String packName = "com.zyjy.servlet";

        String path = packName.replace(".", "/");
        URL url = ControllerUtil.class.getClassLoader().getResource(path);
        File file = null;
        try {
            if (url != null) {
                file = new File(url.toURI());

                File[] files = file.listFiles(pathname -> pathname.toString().endsWith(".class"));
                for (File file1 : files) {
                    String className = packName + "." + file1.getName().replace(".class", "");

                    Class<?> clazz = Class.forName(className);
                    Object o = clazz.newInstance();
                    classMap.put(clazz.getSimpleName(), clazz);
                    objMap.put(clazz.getSimpleName(), o);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public static Class<?> getClassMap(String className) {
        return classMap.get(className);
    }

    public static <T> T getObjMap(String className) {
        return (T) objMap.get(className);
    }


    public static Map<String, Class<?>> getClassMap() {
        return classMap;
    }


    public static Map<String, Object> getObjMap() {
        return objMap;
    }

}

