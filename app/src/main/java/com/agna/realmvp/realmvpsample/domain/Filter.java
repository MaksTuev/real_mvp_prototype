package com.agna.realmvp.realmvpsample.domain;


import java.io.Serializable;

public class Filter implements Serializable {
    ShowFirstFilterValue showFirst;

    public Filter() {
        this.showFirst = ShowFirstFilterValue.NEW;
    }

    public ShowFirstFilterValue getShowFirst() {
        return showFirst;
    }

    public void setShowFirst(ShowFirstFilterValue showFirst) {
        this.showFirst = showFirst;
    }
}
