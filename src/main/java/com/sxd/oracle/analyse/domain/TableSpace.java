package com.sxd.oracle.analyse.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static com.sxd.oracle.analyse.PatternConstant.STORAGE_SCOPE_PATTERN;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description Oracle Dump中表空间定义
 * @createTime 2020年04月28日 18:58:00
 */
public class TableSpace {

    private String statement;

    private String name;

    private Integer pctfree;

    private Integer pctused;

    private Integer ininTrans;

    private Integer maxTrans;

    private Storage storage;

    private String logging;

    public TableSpace(String tableSpaceStatement) {
        this.statement = tableSpaceStatement;

        Matcher storageScopeMatcher = STORAGE_SCOPE_PATTERN.matcher(tableSpaceStatement);
        String storageStatement = tableSpaceStatement;
        if (storageScopeMatcher.find()) {
            storageStatement = storageScopeMatcher.group(0);
        }
        storage = new Storage(storageStatement);
        String tmpTableSpaceStatement = statement.replace(storageStatement, "");

        tmpTableSpaceStatement = tmpTableSpaceStatement.replace("  ", " ").trim();
        String[] split = tmpTableSpaceStatement.split(" ");
        for (int i = 0; i < split.length; i = i + 2) {
            if (split[i].equals("PCTFREE")) {
                this.pctfree = Integer.parseInt(split[i + 1]);;
            } else if (split[i].equals("PCTUSED")) {
                this.pctused = Integer.parseInt(split[i + 1]);;
            } else if (split[i].equals("INITRANS")) {
                this.ininTrans = Integer.parseInt(split[i + 1]);;
            } else if (split[i].equals("MAXTRANS")) {
                this.maxTrans = Integer.parseInt(split[i + 1]);;
            } else if (split[i].equals("TABLESPACE")) {
                this.name = split[i + 1];
            } else if (split[i].equals("LOGGING")) {
                this.logging = split[i + 1];
            }
        }
    }

    public int getPctfree() {
        return pctfree;
    }

    public void setPctfree(int pctfree) {
        this.pctfree = pctfree;
    }

    public int getPctused() {
        return pctused;
    }

    public void setPctused(int pctused) {
        this.pctused = pctused;
    }

    public int getIninTrans() {
        return ininTrans;
    }

    public void setIninTrans(int ininTrans) {
        this.ininTrans = ininTrans;
    }

    public int getMaxTrans() {
        return maxTrans;
    }

    public void setMaxTrans(int maxTrans) {
        this.maxTrans = maxTrans;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
