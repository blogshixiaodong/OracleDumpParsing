package com.sxd.oracle.analyse.domain;

import com.sxd.oracle.analyse.HexUtils;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月22日 11:37:00
 */
public class Column {

    /**
     * 字段定义
     */
    private ColumnStruct columnStruct;

    /**
     * dump读取的值
     */
    private byte[] bytes;

    /**
     * 根据struct.type解码的值
     */
    private Object value;

    public Column(ColumnStruct struct, byte[] bytes) {
        this.columnStruct = struct;
        this.bytes = bytes;
        this.value = decodeBytes();
    }

    public Object getValue() {
        return value;
    }

    public ColumnStruct getColumnStruct() {
        return columnStruct;
    }

    public byte[] getBytes() {
        return bytes;
    }

    private Object decodeBytes() {
        String value = "";
        if (bytes[0] == -2 && bytes[1] == -1) {
            return null;
        }
        String type = columnStruct.getType();
        if (type.contains("CHAR")) {
            try {
                String decodeValue = new String(bytes, "GB2312");
                return "'" + decodeValue + "'";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if ("TIMESTAMP".equals(type)) {
            int century = bytes[0] - 100;
            int year = bytes[1] - 100;
            int month = bytes[2] - 1;
            int day = bytes[3];
            int hour = bytes[4] - 1;
            int minute = bytes[5] - 1;
            int second = bytes[6] - 1;
            byte[] milliSecondBytes = Arrays.copyOfRange(bytes, 7, 11);
            String hexStr = HexUtils.bytesToHexString(milliSecondBytes);
            int milliSecond = Integer.parseInt(hexStr, 16) / 1000 / 1000;
            Date date = new Date(century * 100 + year - 1900, month, day, hour, minute, second);
            Timestamp timestamp = new Timestamp(date.getTime() + milliSecond);
            return timestamp;
        }
        try {
            return new String(bytes, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
