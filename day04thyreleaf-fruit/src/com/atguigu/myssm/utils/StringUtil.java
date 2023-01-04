package com.atguigu.myssm.utils;

/**
 * @author xujian
 * @create 2022-12-18 11:03
 * 工具类
 */
public class StringUtil {
    public static boolean isEmpty(String s){
        return s==null || "".equals(s);
    }

    public static boolean isNotEmpty(String s){
        return !isEmpty(s);
    }
}
