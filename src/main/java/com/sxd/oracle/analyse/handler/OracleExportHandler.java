package com.sxd.oracle.analyse.handler;

import com.sxd.oracle.analyse.domain.Table;

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
        return null;
    }

    class InsertHelper
}
