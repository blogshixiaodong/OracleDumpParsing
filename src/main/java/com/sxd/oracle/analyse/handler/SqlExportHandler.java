package com.sxd.oracle.analyse.handler;

import com.sxd.oracle.analyse.domain.Table;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description SQL导出处理器
 * @createTime 2020年04月28日 13:49:00
 */
public interface SqlExportHandler {

    String exportCreateTableStatement(Table table);

    String exportInsertStatement(Table table);

}
