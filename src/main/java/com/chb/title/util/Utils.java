package com.chb.title.util;


import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共处理方法类
 *
 * @file Utils.java
 */
public class Utils extends StringUtils {

    private static String pattern = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 取得UUID数据
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    /**
     * 字符转整型
     *
     * @param value
     * @return
     */
    public static Integer toInteger(String value) {
        if (value == null || "".equals(value)) return new Integer(0);
        return new Integer(value);
    }

    /**
     * 转为整型
     *
     * @param value
     * @return
     */
    public static int toInt(String value) {
        return Utils.toInteger(value).intValue();
    }

    /**
     * 字符串Null处理
     *
     * @param value
     * @return
     */
    public static String disNull(String value) {
        return value == null ? "--" : value;
    }

    /**
     * 把非数字转为数字
     *
     * @param value
     * @return
     */
    public static String toDoubleString(String value) {
        String ret = "0";
        try {
            ret = Double.parseDouble(value) + "";
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * 字符串Null处理
     *
     * @param value
     * @return
     */
    public static String NULL(String value) {
        return value == null ? "" : value;
    }

    /**
     * HTML字符串Null处理
     *
     * @param value
     * @return
     */
    public static String htmlDisNull(String value) {
        if (value == null) {
            value = "--";
        }
        return "".equals(value) ? "--" : value;
    }

    public static String getString(String data, String defaults) {

        if (StringUtils.isEmpty(data)) {
            return defaults;
        } else {
            return data;
        }
    }

    /**
     * 把逗号转为特别的字符
     *
     * @param str
     * @return
     */
    public static String replaceSplit(String str) {
        if (str != null) {
            str = str.replaceAll(",", "▓");
        }
        return str;
    }

    /**
     * @param value
     * @return
     * @TODO (暂时添加此方法自用)
     */
    public static boolean isNullValue(String value) {
        return (null == value || "".equals(value) ? true : false);
    }

    public static boolean isNotNullValue(String value) {
        return (null == value || "".equals(value) ? false : true);
    }

    /**
     * 判断单个对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean isNullValue(Object o) {
        return (null == o) ? true : false;
    }

    /**
     * 判断多个对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean isNullObjects(Object... o) {
        boolean bool = false;
        for (Object o1 : o) {
            if (null == o1 || "".equals(o1.toString())) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    /**
     * 输出带连接符的字符串(左右两边输出连接字符)
     *
     * @param cs      连接字符
     * @param content 连接内容
     * @param n       分隔符内容
     * @return
     */
    public static String printStrAll(String cs, String content, int n) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n * 2; i++) {
            if (i == n - 1) {
                s.append(content);
            } else {
                s.append(cs);
            }
        }
        return s.toString();
    }

    /**
     * 输出带连接符的字符串(左边输出连接字符)
     *
     * @param cs
     * @param content
     * @param n
     * @return
     */
    public static String printStrLeft(String cs, String content, int n) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            s.append(cs);
        }
        s.append(content);
        return s.toString();
    }

    /**
     * 输出带连接符的字符串(右边输出连接字符)
     *
     * @param cs
     * @param content
     * @param n
     * @return
     */
    public static String printStrRight(String cs, String content, int n) {
        StringBuilder s = new StringBuilder(content);
        for (int i = 0; i < n; i++) {
            s.append(cs);
        }
        return s.toString();
    }

    /**
     * 运用正则表达式获取数据 获取字符串列表
     *
     * @param rex       正则表达式
     * @param sourceStr 原文本
     * @return 返回通过正则表达式获取出来的新文本集合
     */
    public static List<String> rexMatcher(String rex, String sourceStr) {
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(sourceStr);
        while (matcher.find()) {
            String data = matcher.group();
            list.add(data);
        }
        return list;
    }

    /**
     * 运用正则表达式获取数据 获取单个字符串
     *
     * @param rex       正则表达式
     * @param sourceStr 原文本
     * @return 返回通过正则表达式获取出来的新文本
     */
    public static String rexMatcherStr(String rex, String sourceStr) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(sourceStr);
        while (matcher.find()) {
            String data = matcher.group();
            return data;
        }
        return "";
    }

    /**
     * 字符串集合 加上环绕分割
     *
     * @param liststr   字符串集合
     * @param separator 分隔符号
     * @param wrapWith  环绕符号
     * @return
     */
    public static String joinWithWarp(List liststr, String separator, String wrapWith) {

        for (int i = 0; i < liststr.size(); i++) {
            if (isNotBlank(liststr.get(i).toString())) {
                liststr.set(i, wrap(liststr.get(i).toString(), wrapWith));
            }
        }
        return join(liststr, separator);
    }

    /**
     * 获取随机数
     *
     * @return
     */
    public static Integer getRandom() {
        int max = 2550;
        int min = 0;
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 判断原字符串<code>source</code>不为空并且和<code>value</code>进行equals
     *
     * @param source 原字符串
     * @param value  比较值
     * @return
     */
    public static boolean isBlankWithEquals(String source, String value) {
        return isNotBlank(source) && equals(source, value);
    }

    /**
     * 在集合元素中,对每个元素值进行修改
     *
     * @param left  在元素左边添加的值
     * @param list  集合对象
     * @param right 在元素右边添加的值
     */
    public static void warpListElement(String left, List<String> list, String right) {
        if (null != list && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                String tempval=String.valueOf(list.get(i));
                StringBuilder val = new StringBuilder(tempval);
                if (isNotBlank(left)) {
                    val.insert(0, left);
                }
                if (isNotBlank(right)) {
                    val.append(right);
                }
                list.set(i, val.toString());
            }
        }
    }

    public static List<String> join(String source, String separator) {
        return isBlank(source) ? null : Arrays.asList(source.split(separator));
    }

    public static String findWithReplace(String source, String start, String end){
        String res="";
        if (isNotBlank(source)) {
            if (source.startsWith("\"{")&&source.endsWith("}\"")) {
                res=source.replaceFirst("^(\"\\{)","{").replaceFirst("(\\}\")","\\}");
            }else {
                res=source;
            }
        }
        return res;
    }

}
