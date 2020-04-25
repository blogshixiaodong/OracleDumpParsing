package com.sxd.oracle.analyse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 14:50:00
 */
public class demo {

    private static Pattern tablePattern = Pattern.compile("CREATE TABLE \"(\\w*)\"");

    private static Pattern insertPattern = Pattern.compile("INSERT INTO \"(\\w*)\"");

    public static void main(String[] args) {
        String table = "CREATE TABLE \"AFA_USER\" (VARCHAR )";
        String insert = "INSERT INTO \"AFA_ORG\"";

        Matcher matcher = tablePattern.matcher(table);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }

        Matcher matcher1 = insertPattern.matcher(insert);
        while (matcher1.find()) {
            System.out.println(matcher1.group(1));
        }


    }

}
