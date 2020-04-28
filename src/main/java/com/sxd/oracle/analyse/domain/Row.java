package com.sxd.oracle.analyse.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 13:46:00
 */
public class Row {

    private List<Column> columnList;

    public Row() {
        columnList = new ArrayList<Column>();
    }

    public void add(Column column) {
        columnList.add(column);
    }

    public void addAll(List<Column> columns) {
        columnList.addAll(columns);
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public Column getColumn(int index) {
        return columnList.get(index);
    }


}
