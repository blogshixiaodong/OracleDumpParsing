package com.sxd.oracle.analyse.domain;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description 字段定义
 * @createTime 2020年04月21日 13:44:00
 */
public class ColumnStruct {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段大小
     */
    private int size;

    /**
     * 字段精度, 仅针对数值类型有效
     */
    private int accuracy;

    /**
     * 是否受到约束条件限制
     */
    private boolean isEnable;

    /**
     * 字段默认值
     */
    private Object defaultValue;

    /**
     * 是否为主键
     */
    private boolean isPrimary;

    /**
     * 是否唯一
     */
    private boolean isQnique;

    /**
     * 是否允许为空
     */
    private boolean isNull;

    public ColumnStruct(String name, String type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public boolean isQnique() {
        return isQnique;
    }

    public void setQnique(boolean qnique) {
        isQnique = qnique;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }
}
