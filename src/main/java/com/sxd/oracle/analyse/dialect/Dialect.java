package com.sxd.oracle.analyse.dialect;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description SQL方言
 * @createTime 2020年04月28日 13:54:00
 */
public interface Dialect {

    /**
     * 将原始字段类型转化成目标类型
     * @param columnType 原始字段类型
     * @return
     */
    String getColumnType(String columnType);

}
