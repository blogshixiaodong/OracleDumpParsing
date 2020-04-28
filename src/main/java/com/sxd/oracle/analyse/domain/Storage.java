package com.sxd.oracle.analyse.domain;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description Oracle 存储参数
 * @createTime 2020年04月28日 19:27:00
 */
public class Storage {

    private String statement;

    /**
     * 初始数据区大小
     */
    private Integer initial;

    /**
     * 下一个数据区大小
     */
    private Integer next;

    /**
     * 第一次分配最小的数据区数量
     */
    private Integer minExtents;

    /**
     * 分配最大数据区数量
     */
    private Integer maxExtents;

    /**
     * 数据区分配增长百分比
     */
    private Float pctIncrease;

    private Integer freeLists;

    private Integer freeListGroups;

    private String bufferPool;

    public Storage(String storageStatement) {
        this.statement = storageStatement;
        String tmpStorageStatement = storageStatement.trim().replace("STORAGE", "").replace("(", "").replace(")", "");
        tmpStorageStatement = tmpStorageStatement.replace("FREELIST GROUPS", "FREELIST_GROUPS");
        String[] split = tmpStorageStatement.split(" ");
        for (int i = 0; i < split.length; i = i + 2) {
            if (split[i].equals("INITIAL")) {
                this.initial = Integer.parseInt(split[i + 1]);;
            } else if (split[i].equals("NEXT")) {
                this.next = Integer.parseInt(split[i + 1]);;
            } else if (split[i].equals("MINEXTENTS")) {
                this.minExtents = Integer.parseInt(split[i + 1]);;
            } else if (split[i].equals("FREELISTS")) {
                this.freeLists = Integer.parseInt(split[i + 1]);;
            } else if (split[i].equals("FREELIST_GROUPS")) {
                this.freeListGroups = Integer.parseInt(split[i + 1]);
            } else if (split[i].equals("BUFFER_POOL")) {
                this.bufferPool = split[i + 1];
            }
        }
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getMinExtents() {
        return minExtents;
    }

    public void setMinExtents(int minExtents) {
        this.minExtents = minExtents;
    }

    public int getMaxExtents() {
        return maxExtents;
    }

    public void setMaxExtents(int maxExtents) {
        this.maxExtents = maxExtents;
    }

    public float getPctIncrease() {
        return pctIncrease;
    }

    public void setPctIncrease(float pctIncrease) {
        this.pctIncrease = pctIncrease;
    }

    public int getFreeLists() {
        return freeLists;
    }

    public void setFreeLists(int freeLists) {
        this.freeLists = freeLists;
    }

    public int getFreeListGroups() {
        return freeListGroups;
    }

    public void setFreeListGroups(int freeListGroups) {
        this.freeListGroups = freeListGroups;
    }

    public String getBufferPool() {
        return bufferPool;
    }

    public void setBufferPool(String bufferPool) {
        this.bufferPool = bufferPool;
    }
}
