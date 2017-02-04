package com.agna.realmvp.realmvpsample.ui.base.recycler;


import android.support.v7.widget.RecyclerView;

import com.agna.realmvp.realmvpsample.ui.base.recycler.changes.ListChanges;
import com.agna.realmvp.realmvpsample.ui.base.recycler.changes.Range;
import com.agna.realmvp.realmvpsample.ui.base.recycler.changes.Slide;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    //todo support any ranges and positions
    protected Range getHeadersRange(){
        return Range.empty();
    }

    public void notifyListChanged(ListChanges listChanges) {
        Range headersRange = getHeadersRange();
        if(headersRange.getPositionStart() != 0){
            throw new IllegalStateException("#getHeadersRange() must return range started from 0 position");
        }
        ListChanges internalListChanges = new ListChanges(
                internalRanges(listChanges.getChangedRanges(), headersRange),
                internalRanges(listChanges.getInsertedRanges(), headersRange),
                internalRanges(listChanges.getRemovedRanges(), headersRange),
                internalSlides(listChanges.getSlides(), headersRange)
        );
        notifyInternal(internalListChanges);

    }

    protected void notifyInternal(ListChanges listChanges){
        for (Range changedRange : listChanges.getChangedRanges()) {
            notifyItemRangeChanged(changedRange.getPositionStart(), changedRange.getCount());
        }
        for (Range insertedRange : listChanges.getInsertedRanges()) {
            notifyItemRangeInserted(insertedRange.getPositionStart(), insertedRange.getCount());
        }
        for (Range removedRange : listChanges.getRemovedRanges()) {
            notifyItemRangeRemoved(removedRange.getPositionStart(), removedRange.getCount());
        }
        for (Slide slide: listChanges.getSlides()) {
            notifyItemMoved(slide.getFromPosition(), slide.getToPosition());
        }
    }

    private List<Slide> internalSlides(List<Slide> slides, Range headersRange) {
        List<Slide> result = new ArrayList<>();
        int headersCount = headersRange.getCount();
        for(Slide slide : slides){
            result.add(new Slide(
                    slide.getFromPosition() + headersCount,
                    slide.getToPosition() + headersCount));
        }
        return result;
    }

    private List<Range> internalRanges(List<Range> ranges, Range headersRange) {
        List<Range> result = new ArrayList<>();
        int headersCount = headersRange.getCount();
        for(Range range : ranges){
            result.add(new Range(range.getPositionStart() + headersCount, range.getCount()));
        }
        result.add(headersRange);
        return result;
    }
}
