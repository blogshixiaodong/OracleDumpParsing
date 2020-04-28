package com.sxd.oracle.analyse.domain;

import java.util.List;
import java.util.regex.Matcher;

import static com.sxd.oracle.analyse.PatternConstant.INDEX_PATTERN;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description 索引结构体
 * @createTime 2020年04月28日 09:19:00
 */
public class IndexStruct {

    private String statement;

    private String indexName;

    private String tableName;

    private String[] columns;

    private TableSpace tableSpace;

    public IndexStruct(String statement) {
        this.statement = statement;
        // 截取表空间申明与语句
        String[] split = statement.split("  ");
        if (split.length > 1) {
            tableSpace = new TableSpace(split[1]);
        }
        String tmpStatement = split[0];
        Matcher matcher = INDEX_PATTERN.matcher(tmpStatement);
        if (matcher.find()) {
            indexName = matcher.group(1);
            tableName = matcher.group(2);
            String colmunStr = matcher.group(3).replace("\"", "").replace(" ", "").trim();
            columns = colmunStr.split(",");
        }
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public TableSpace getTableSpace() {
        return tableSpace;
    }

    public void setTableSpace(TableSpace tableSpace) {
        this.tableSpace = tableSpace;
    }
}
