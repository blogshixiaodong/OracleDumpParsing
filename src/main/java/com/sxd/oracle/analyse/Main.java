package com.sxd.oracle.analyse;

import com.sxd.oracle.analyse.domain.InsertStruct;
import com.sxd.oracle.analyse.domain.Row;
import com.sxd.oracle.analyse.domain.Table;
import com.sxd.oracle.analyse.domain.TableStruct;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月20日 18:55:00
 */
public class Main {

    public final static String FILE_PATH = "F:\\sxd_workspace\\analyse\\src\\main\\resources\\0422.dmp";

    public static List<String> createList = new ArrayList<String>();

    public static List<String> insertList = new ArrayList<String>();

    public static List<String> createUniqueIndex = new ArrayList<String>();

    public static List<String> commentOn = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        File file = new File(FILE_PATH);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        String s = "";

        while (null != (s = randomAccessFile.readLine())) {
            boolean isCreateTable = s.contains("CREATE TABLE");
            if (isCreateTable) {
                Matcher matcher = PatternConstant.TABLE_PATTERN.matcher(s);
                if (matcher.find()) {
                    String tableName = matcher.group(1);
                    Table table = new Table();
                    TableStruct tableStruct = new TableStruct(s);
                    table.setTableStruct(tableStruct);
                    TableThreadLocal.put(tableName, table);
                }
                createList.add(s);
                continue;
            }
            boolean isInsertInto = s.contains("INSERT INTO");
            if (isInsertInto) {
                String tableName = "";
                Table table = null;
                Matcher matcher = PatternConstant.INSERT_PATTERN.matcher(s);
                if (matcher.find()) {
                    tableName = matcher.group(1);
                    table = TableThreadLocal.get(tableName);
                }
                if (table == null) {
                    table = new Table();
                    TableThreadLocal.put(tableName, table);
                }
                InsertStruct insertStruct = new InsertStruct(s);
                table.setInsertStruct(insertStruct);
                insertList.add(s);

                ByteReader byteReader = new ByteReader(tableName, randomAccessFile);
                while (true) {
                    byteReader.put(randomAccessFile.readByte());
                    boolean over = byteReader.isOver();
                    if (over) {
                        List<Row> rows = byteReader.getRows();
                        table.setRows(rows);
                        break;
                    }
                }
            }
            boolean isUniqueIndex = s.contains("CREATE UNIQUE INDEX");
            if (isUniqueIndex) {
                createUniqueIndex.add(s);
            }
            boolean isCommentOn = s.contains("COMMENT ON");
            if (isCommentOn) {
                commentOn.add(s);
            }
        }
        System.out.println(createList.size());
    }

    private static String hexString = "0123456789ABCDEF";

    public static String decode(String bytes, String mode) throws UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2) {
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        }
        return new String(baos.toByteArray(), mode);
    }

    public static String enUnicode(String str) {
        String st = "";
        try {
            //这里要非常的注意,在将字符串转换成字节数组的时候一定要明确是什么格式的,这里使用的是gb2312格式的,还有utf-8,ISO-8859-1等格式
            byte[] by = str.getBytes("gb2312");
            for (int i = 0; i < by.length; i++) {
                String strs = Integer.toHexString(by[i]);
                if (strs.length() > 2){
                    strs = strs.substring(strs.length() - 2);
                }
                st += strs;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

}
