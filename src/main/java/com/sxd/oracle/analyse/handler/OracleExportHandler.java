package com.sxd.oracle.analyse.handler;

import com.sxd.oracle.analyse.domain.Column;
import com.sxd.oracle.analyse.domain.ColumnStruct;
import com.sxd.oracle.analyse.domain.Row;
import com.sxd.oracle.analyse.domain.Table;

import java.util.List;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description Oracle导出处理器
 * @createTime 2020年04月28日 13:51:00
 */
public class OracleExportHandler implements SqlExportHandler {

    @Override
    public String exportCreateTableStatement(Table table) {
        return table.getTableStruct().getStatement();
    }

    @Override
    public String exportInsertStatement(Table table) {
        StringBuilder builder = new StringBuilder();
        for (Row row :  table.getRows()) {
            builder.append("insert into ");
            builder.append(table.getTableStruct().getTableName() + " (");
            int columnSize = table.getInsertStruct().getColumnSize();
            List<String> columnList = table.getInsertStruct().getColumnList();
            for (int i = 0; i < columnSize; i++) {
                builder.append(columnList.get(i) + ",");
            }
            builder.append(") values(");
            for (Column column : row.getColumnList()) {
                builder.append(getColumnValue(column) + ",");
            }
            builder.append(");\n");
        }
        return builder.toString().replaceAll(",\\)", ")");
    }

    private String getColumnValue(Column column) {
        byte[] bytes = column.getBytes();
        if (bytes[0] == -2 && bytes[1] == -1) {
            return "null";
        }
        if (column.getColumnStruct().getType().contains("CHAR")) {
            return "'" + column.getValue().toString() + "'";
        }
        if (column.getColumnStruct().getType().contains("TIMESTAMP")) {
            return "TO_TIMESTAMP('" + column.getValue() + "', 'SYYYY-MM-DD HH24:MI:SS:FF6')";
        }

        return column.getValue().toString();
    }

}
