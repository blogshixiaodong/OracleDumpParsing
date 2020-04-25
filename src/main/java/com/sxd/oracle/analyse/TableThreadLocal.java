package com.sxd.oracle.analyse;

import com.sxd.oracle.analyse.domain.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月22日 09:15:00
 */
public class TableThreadLocal {

    private static ThreadLocal<Map<String, Table>> threadLocal = new ThreadLocal<Map<String, Table>>();

    public static Table get(String tableName) {
        return threadLocal.get().get(tableName);
    }

    public static List<String> getTableColumns(String tableName) {
        return threadLocal.get().get(tableName).getInsertStruct().getColumnList();
    }

    public static void put(String tableName, Table table) {
        Map<String, Table> tableMap = threadLocal.get();
        if (tableMap == null) {
            tableMap = new HashMap<String, Table>();
            threadLocal.set(tableMap);
        }
        tableMap.put(tableName, table);
    }

    public static Map<String, Table> get() {
        return threadLocal.get();
    }



}
