package com.sxd.oracle.analyse.domain;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sxd.oracle.analyse.PatternConstant.*;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description 表定义结构体
 * @createTime 2020年04月21日 14:33:00
 */
public class TableStruct {

    private String statement;

    private String tableName;

    private Map<String, ColumnStruct> columns;

    public TableStruct(String statement) {
        this.statement = statement;
        Matcher matcher = TABLE_PATTERN.matcher(statement);
        if (matcher.find()) {
            tableName = matcher.group(1);
        }
        // 截取字段定义部分做下一阶段匹配
        String columnScope = statement;
        Matcher columnScopeMatcher = COLUMNS_SCOPE_PATTERN.matcher(statement);
        if (columnScopeMatcher.find()) {
            columnScope = columnScopeMatcher.group(2);
        }
        matcher = COLUMNS_TYPE_PATTERN.matcher(columnScope);
        columns = new LinkedHashMap<String, ColumnStruct>();
        while (matcher.find()) {
            String sizeStr = matcher.group(3);
            int size = isInteger(sizeStr) ? Integer.parseInt(sizeStr) : 0;
            ColumnStruct columnStruct = new ColumnStruct(matcher.group(1), matcher.group(2), size);
            columns.put(matcher.group(1), columnStruct);
        }

        System.out.println("a");
    }

    public boolean isInteger(String str) {
        return NUMBER_PATTERN.matcher(str).matches();
    }

    public String getStatement() {
        return statement;
    }

    public String getTableName() {
        return tableName;
    }

    public int getColumnSize() {
        return columns.keySet().size();
    }

    public List<String> getColumnNames() {
        return new ArrayList<String>(columns.keySet());
    }

    public List<ColumnStruct> getColumnStruct() {
        return new ArrayList<ColumnStruct>(columns.values());
    }

}
