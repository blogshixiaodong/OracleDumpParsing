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

    public static Pattern INSERT_PATTERN = Pattern.compile("INSERT INTO \"(\\w*)\"");

    public static Pattern COLUMNS_PATTERN = Pattern.compile("\"(\\w*)\"\\s*[,|)]");

}
