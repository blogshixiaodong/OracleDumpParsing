package com.sxd.oracle.analyse;

import java.util.regex.Pattern;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 17:21:00
 */
public class PatternConstant {

    public static Pattern TABLE_PATTERN = Pattern.compile("CREATE TABLE \"(\\w*)\"");

    public static Pattern COLUMNS_SCOPE_PATTERN = Pattern.compile("CREATE TABLE \"(\\w*)\"\\s*(\\(.*\\)\\))");

    public static Pattern COLUMNS_TYPE_PATTERN = Pattern.compile("\"(\\w*)\"\\s{1}(\\w*)\\s*\\(?(\\w*)\\)?");

    public static Pattern INSERT_PATTERN = Pattern.compile("INSERT INTO \"(\\w*)\"");

    public static Pattern COLUMNS_VALUE_PATTERN = Pattern.compile("\"(\\w*)\"\\s*[,|)]");

    public static Pattern NUMBER_PATTERN = Pattern.compile("^[-\\+]?[\\d]+$");

}
