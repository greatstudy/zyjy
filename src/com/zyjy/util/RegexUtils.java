package com.zyjy.util;

import java.util.regex.Pattern;

/**
 * @ClassName RegexUtils
 * @Description
 * @Author 清Great
 * @Date 2020/10/30 15:24
 */
public class RegexUtils {
    //正则表达式：纯数字
    private static final String numReg = "^\\d{1,9}$";

    //正则表达式：纯字母
    private static final String engReg = "^[A-Za-z]*$";


    /**
     * 纯数字正则校验
     *
     * @param num 数字字符
     * @return true为纯数字
     */
    public static boolean isInt(String num) {
        if (num == null) {
            return false;
        }
        return Pattern.matches(numReg, num);
    }

    /**
     * 纯字母正则校验
     *
     * @param eng 字母字符
     * @return true为纯字母
     */
    public static boolean isEng(String eng) {
        if (eng == null) {
            return false;
        }

        return Pattern.matches(engReg, eng);
    }

    /**
     * 判断是否为null或者""
     * @param str 字符串
     * @return
     */
    public static boolean isNotNull(String str){
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        return true;
    }


}
