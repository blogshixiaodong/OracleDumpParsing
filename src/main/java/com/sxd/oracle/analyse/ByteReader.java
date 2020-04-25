package com.sxd.oracle.analyse;

import com.sxd.oracle.analyse.domain.Row;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月21日 09:41:00
 */
public class ByteReader {

    private RandomAccessFile file;

    private byte[] bytes;

    /**
     * 当前数组指针位置
     */
    private int index;

    /**
     * 下次数组指针偏移量
     */
    private int next;

    private boolean isOver;

    private List<Row> rows;

    private String tableName;

    public ByteReader(String tableName, RandomAccessFile file) {
        this.tableName = tableName;
        this.file = file;
        bytes = new byte[10240];
        index = 0;
        next = 1;
        isOver = false;
    }

    public void put(byte b) throws IOException {
        bytes[index] = b;
        index++;
        flush();
    }

    public byte[] get() throws IOException {
        byte[] read = new byte[next];
        for (int i = 0; i < next; i++) {
            read[i] = file.readByte();
        }
        return read;
    }

    public void clear() {
        index = 0;
    }

    public void flush() throws IOException {
        if (bytes[index - 1] == 0 && bytes[index - 2] == 0 && bytes[index - 3] == 0 && bytes[index - 4] == 0 && bytes[index - 5] == 0) {
            next = 2;
            InsertValueReader insertValueReader = new InsertValueReader(tableName, file);
            rows = insertValueReader.read();
            isOver = true;
            clear();
        }
    }

    public boolean isOver() {
        return isOver;
    }

    public List<Row> getRows() {
        return rows;
    }

}
