package com.agna.realmvp.realmvpsample.domain.datalist;


public class DataListMergeChanges {
    private int lastSize;
    private int newBlockSize;
    private int newBlockOffset;

    public DataListMergeChanges(int lastSize, int newBlockSize, int newBlockOffset) {
        this.lastSize = lastSize;
        this.newBlockSize = newBlockSize;
        this.newBlockOffset = newBlockOffset;
    }

    public int getLastSize() {
        return lastSize;
    }

    public int getNewBlockSize() {
        return newBlockSize;
    }

    public int getNewBlockOffset() {
        return newBlockOffset;
    }
}
