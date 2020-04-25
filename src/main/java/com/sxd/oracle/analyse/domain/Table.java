package com.sxd.oracle.analyse.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 14:33:00
 */
public class Table {

    /**
     * 表结构定义
     */
    private TableStruct tableStruct;

    /**
     * 批量insert语句
     */
    private InsertStruct insertStruct;

    /**
     * 行数据
     */
    private List<Row> rows;

    public TableStruct getTableStruct() {
        return tableStruct;
    }

    public void setTableStruct(TableStruct tableStruct) {
        this.tableStruct = tableStruct;
    }

    public InsertStruct getInsertStruct() {
        return insertStruct;
    }

    public void setInsertStruct(InsertStruct insertStruct) {
        this.insertStruct = insertStruct;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
        // 设置数据后就开始解析
        // 时间类型，BLOB类型
        List<String> columnList = insertStruct.getColumnList();
        if (rows.size() != columnList.size()) {
            System.out.println("解析异常");
        }
        List<String> columnType = new ArrayList<String>(columnList.size());
        for (int i = 0; i < columnList.size(); i++) {
//            columnType.add()
        }
    }

    public static void main(String[] args) {
        List<String> l = new ArrayList<String>();
        l.add("1");
        l.add("2");
        l.add("3");

        System.out.println(l.toString().replace("[", "(").replace("]", ")"));

    }
}
