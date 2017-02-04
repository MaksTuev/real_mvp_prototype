package com.agna.realmvp.realmvpsample.ui.base.recycler.changes;

public class Range {

    private int positionStart;
    private int count;

    public static Range empty() {
        return new Range(0, 0);
    }

    public static Range single(int position) {
        return new Range(position, 1);
    }

    public Range(int positionStart, int count) {
        this.positionStart = positionStart;
        this.count = count;
    }

    public int getPositionStart() {
        return positionStart;
    }

    public int getCount() {
        return count;
    }

    public void setPositionStart(int positionStart) {
        this.positionStart = positionStart;
    }

    public void setCount(int count) {
        this.count = count;
    }
}