package com.sxd.oracle.analyse;

import java.util.regex.Pattern;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 17:21:00
 */
public class PatternConstant {

    /**
     * 建表语句匹配
     */
    public static Pattern TABLE_PATTERN = Pattern.compile("CREATE TABLE \"(\\w*)\"");

    /**
     * 字段申明语句匹配
     */
    public static Pattern COLUMNS_SCOPE_PATTERN = Pattern.compile("CREATE TABLE \"(\\w*)\"\\s*(\\(.*\\)\\))");

    /**
     * 字段类型匹配
     */
    public static Pattern COLUMNS_TYPE_PATTERN = Pattern.compile("\"(\\w*)\"\\s{1}(\\w*)\\s*\\(?(\\w*)\\)?");

    /**
     * 插入语句匹配
     */
    public static Pattern INSERT_PATTERN = Pattern.compile("INSERT INTO \"(\\w*)\"");

    /**
     * 字段值匹配
     */
    public static Pattern COLUMNS_VALUE_PATTERN = Pattern.compile("\"(\\w*)\"\\s*[,|)]");

    /**
     * 数值类型匹配
     */
    public static Pattern NUMBER_PATTERN = Pattern.compile("^[-\\+]?[\\d]+$");

    /**
     * 存储空间匹配
     */
    public static Pattern STORAGE_SCOPE_PATTERN = Pattern.compile("STORAGE\\((.*)\\)");

    public static Pattern INDEX_PATTERN = Pattern.compile("CREATE UNIQUE INDEX \"(.*)\" ON \"(.*)\" \\((.*)\\)");
    //Pattern.compile("CREATE UNIQUE INDEX .* ON\\s?\"\\w*\"\\s?\\((.*)\\s?\\) ");



}
