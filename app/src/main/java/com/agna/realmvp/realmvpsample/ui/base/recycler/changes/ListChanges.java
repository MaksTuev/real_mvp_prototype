package com.agna.realmvp.realmvpsample.ui.base.recycler.changes;


import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.agna.realmvp.realmvpsample.domain.datalist.DataListMergeChanges;

import java.util.ArrayList;
import java.util.List;

/**
 * todo прикрутить {@link DiffUtil} если потребуется
 */
public class ListChanges {

    private List<Range> changedRanges = new ArrayList<>();
    private List<Range> insertedRanges = new ArrayList<>();
    private List<Range> removedRanges = new ArrayList<>();
    private List<Slide> slides = new ArrayList<>();

    public static ListChanges allChanged(int size){
        return new ListChanges(new Range(0, size), null, null);
    }

    public static ListChanges singleChanged(int position){
        return new ListChanges(new Range(position, 1), null, null);
    }

    public static ListChanges singleInserted(int position){
        return new ListChanges(null, new Range(position, 1), null);
    }

    public static ListChanges singleRemoved(int position){
        return new ListChanges(null, null, new Range(position, 1));
    }

    public static ListChanges newInstance(DataListMergeChanges dataListMergeChanges){
        return newInstance(
                dataListMergeChanges.getLastSize(),
                dataListMergeChanges.getNewBlockSize(),
                dataListMergeChanges.getNewBlockOffset());
    }

    public static ListChanges newInstance(int lastSize, int newBlockSize, int newBlockOffset) {
        int newSize = Math.max(newBlockOffset + newBlockSize, lastSize);
        int numItemsChanged = 0;
        int numItemsRemoved = 0;
        int numItemsInserted = 0;
        if (newSize >= lastSize) {
            numItemsChanged = lastSize;
            numItemsInserted = newSize - lastSize;
        } else if (newSize < lastSize) {
            numItemsChanged = newSize;
            numItemsRemoved = lastSize - newSize;
        }
        return new ListChanges(
                new Range(0, numItemsChanged),
                new Range(lastSize, numItemsInserted),
                new Range(newSize - numItemsRemoved, numItemsRemoved));
    }

    public ListChanges(List<Range> changedRanges, List<Range> insertedRanges,
                       List<Range> removedRanges, List<Slide> slides) {
        this.changedRanges = changedRanges;
        this.insertedRanges = insertedRanges;
        this.removedRanges = removedRanges;
        this.slides = slides;
    }

    public ListChanges(@Nullable Range changedRange, @Nullable Range insertedRange, @Nullable Range removedRange) {
        if(changedRange != null) {
            this.changedRanges.add(changedRange);
        }
        if(insertedRange != null) {
            this.insertedRanges.add(insertedRange);
        }
        if(removedRange != null) {
            this.removedRanges.add(removedRange);
        }
    }

    public List<Range> getChangedRanges() {
        return changedRanges;
    }

    public List<Range> getInsertedRanges() {
        return insertedRanges;
    }

    public List<Range> getRemovedRanges() {
        return removedRanges;
    }

    public List<Slide> getSlides() {
        return slides;
    }
}
