package com.sxd.oracle.analyse.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static com.sxd.oracle.analyse.PatternConstant.COLUMNS_VALUE_PATTERN;
import static com.sxd.oracle.analyse.PatternConstant.INSERT_PATTERN;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 14:35:00
 */
public class InsertStruct {

    private String statement;

    private String tableName;

    private List<String> columnNames;

    public InsertStruct(String statement) {
        this.statement = statement;
        this.columnNames = new ArrayList<String>();
        Matcher matcher = INSERT_PATTERN.matcher(statement);
        while (matcher.find()) {
            tableName = matcher.group(1);
            break;
        }
        matcher = COLUMNS_VALUE_PATTERN.matcher(statement);
        while (matcher.find()) {
            columnNames.add(matcher.group(1));
        }
    }

    public String getStatement() {
        return statement;
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getColumnList() {
        return columnNames;
    }

    public int getColumnSize() {
        return columnNames.size();
    }

}
