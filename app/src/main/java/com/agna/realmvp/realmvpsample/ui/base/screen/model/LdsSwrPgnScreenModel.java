package com.agna.realmvp.realmvpsample.ui.base.screen.model;


import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.PaginationState;

public class LdsSwrPgnScreenModel extends LdsSwrScreenModel {
    private PaginationState paginationState = PaginationState.READY;

    public PaginationState getPaginationState() {
        return paginationState;
    }

    public void setPaginationState(PaginationState paginationState) {
        this.paginationState = paginationState;
    }
}
