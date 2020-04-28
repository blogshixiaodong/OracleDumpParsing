package com.sxd.oracle.analyse.handler;

import com.sxd.oracle.analyse.domain.Table;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description Excel导出处理器
 * @createTime 2020年04月28日 13:52:00
 */
public class ExcelExportHandler implements SqlExportHandler {

    @Override
    public String exportCreateTableStatement(Table table) {
        return null;
    }

    @Override
    public String exportInsertStatement(Table table) {
        return null;
    }
}
