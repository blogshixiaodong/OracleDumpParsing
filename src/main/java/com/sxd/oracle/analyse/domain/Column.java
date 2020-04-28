package com.sxd.oracle.analyse.domain;

import java.io.UnsupportedEncodingException;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 13:44:00
 */
public class Column {

    private byte[] value;

    private Object decode;

    private String name;

    private String type;

    public Column(int index, byte[] bytes) {
        this.value = bytes;
        try {
            decode = new String(bytes, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public Object getDecode() {
        return decode;
    }

    public void setDecode(Object decode) {
        this.decode = decode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
