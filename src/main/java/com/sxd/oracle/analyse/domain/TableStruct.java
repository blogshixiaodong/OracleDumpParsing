package com.sxd.oracle.analyse.domain;

import java.util.regex.Matcher;

import static com.sxd.oracle.analyse.PatternConstant.TABLE_PATTERN;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 14:33:00
 */
public class TableStruct {

    private String statement;

    private String tableName;

    public TableStruct(String statement) {
        this.statement = statement;
        Matcher matcher = TABLE_PATTERN.matcher(statement);
        while (matcher.find()) {
            tableName = matcher.group(1);
            break;
        }
    }

    public String getStatement() {
        return statement;
    }

    public String getTableName() {
        return tableName;
    }

    public static byte[] getByteTime(String date) {
        String[] timeString = date.split(" ");
        int year = stringToInt(timeString[0]);
        //年 2018 十六进制  7 E2   byte[] {7,-30 }
        byte y1 = (byte) ((year >> 8) & 0xff);//年高八位
        byte y2 = (byte) (year & 0xff);//年低八位
        int month = stringToInt(timeString[1]);
        int day = stringToInt(timeString[2]);
        int hour = stringToInt(timeString[3]);
        int minute = stringToInt(timeString[4]);
        int second = stringToInt(timeString[5]);
        int week = stringToInt(timeString[6]);
        byte sum = (byte) ((y1 + y2 + month + day + hour + minute + second + week) & 0xff);//和校验位,可加可不加
        byte[] today = { y1, y2, (byte) month, (byte) day, (byte) hour, (byte) minute, (byte) second, (byte) week, sum};
        return today;
    }

    public static int stringToInt(String s) {
        return Integer.parseInt(s);
    }

}
