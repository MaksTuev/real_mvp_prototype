package com.agna.realmvp.realmvpsample.ui.base.screen.model;


import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.LoadState;
import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.SwipeRefreshState;

public class LdsSwrScreenModel extends LdsScreenModel {
    private SwipeRefreshState swipeRefreshState = SwipeRefreshState.HIDE;

    public SwipeRefreshState getSwipeRefreshState() {
        return swipeRefreshState;
    }

    public void setSwipeRefreshState(SwipeRefreshState swipeRefreshState) {
        this.swipeRefreshState = swipeRefreshState;
    }

    @Override
    public void setLoadState(LoadState loadState) {
        setSwipeRefreshState(SwipeRefreshState.HIDE); //todo comment
        super.setLoadState(loadState);
    }
}
