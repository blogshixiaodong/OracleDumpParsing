package com.sxd.oracle.analyse;

import com.sxd.oracle.analyse.domain.Column;
import com.sxd.oracle.analyse.domain.ColumnStruct;
import com.sxd.oracle.analyse.domain.Row;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 10:02:00
 */
public class InsertValueReader {

    private RandomAccessFile file;

    private int next;

    private boolean isInsertEnd;

    private boolean isRowEnd;

    private List<Row> rows;

    private List<Column> columns = new ArrayList<Column>();

    private String tableName;

    public InsertValueReader(String tableName, RandomAccessFile file) {
        this.tableName = tableName;
        this.file = file;
        rows = new ArrayList<Row>();
        next = 2;
    }

    public List<Row> read() throws IOException {
        while (true) {
            byte[] ctrl = readCtrlByte();
            if (isInsertEnd) {
                break;
            }
            next = toInt(ctrl);
            byte[] value = readValueByte();
            int size = columns.size();
            int s = TableThreadLocal.get(tableName).getTableStruct().getColumnStruct().size();
            ColumnStruct columnStruct = TableThreadLocal.get(tableName).getTableStruct().getColumnStruct().get(size);
            Column column = new Column(columnStruct, value);
            columns.add(column);
            next = 2;
        }
        return rows;
    }

    /**
     * 读取控制字符
     * @return
     * @throws IOException
     */
    public byte[] readCtrlByte() throws IOException {
        byte[] b = new byte[next];
        for (int i = 0; i < next; i++) {
            b[i] = file.readByte();
        }
        // NULL字段
        if (b[0] == -2 && b[1] == -1) {
            System.out.println("NULL");
            int size = columns.size();
            ColumnStruct columnStruct = TableThreadLocal.get(tableName).getTableStruct().getColumnStruct().get(size);
            Column column = new Column(columnStruct, b);
            columns.add(column);
            return readCtrlByte();
        }
        // 行结束
        if (b[0] == 0 && b[1] == 0) {
            System.out.println("ROW END");
            isRowEnd = true;
            // 收集一行的所有列
            Row row = new Row();
            row.addAll(columns);
            rows.add(row);
            columns.clear();
            return readCtrlByte();
        }
        // 批处理结束
        if (b[0] == -1 && b[1] == -1) {
            System.out.println("INSERT END");
            isInsertEnd = true;
        }
        return b;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public byte[] readValueByte() throws IOException {
        byte[] b = new byte[next];
        for (int i = 0; i < next; i++) {
            b[i] = file.readByte();
        }
        return b;
    }

    public static int toInt(byte[] bytes) {
        int size = bytes.length;
        StringBuilder builder = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            builder.append(bytes[i]);
        }
        return Integer.parseInt(builder.toString());
    }

    public static void main(String[] args) {
        byte[] b = new byte[] {20, 00};
        System.out.println(toInt(b));
    }
}
