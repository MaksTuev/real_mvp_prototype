package com.agna.realmvp.realmvpsample.ui.base.screen.fragment;


import android.support.v4.widget.SwipeRefreshLayout;

import com.agna.realmvp.realmvpsample.ui.base.screen.model.LdsSwrScreenModel;
import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.SwipeRefreshState;

public abstract class BaseLdsSwrFragmentView<M extends LdsSwrScreenModel>
        extends BaseLdsFragmentView<M> {

    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();

    @Override
    public void render(M screenModel) {
        renderLoadState(screenModel.getLoadState());
        renderSwipeRefreshState(screenModel.getSwipeRefreshState());
        renderInternal(screenModel);
    }

    protected void renderSwipeRefreshState(SwipeRefreshState swipeRefreshState) {
        getSwipeRefreshLayout().setRefreshing(swipeRefreshState == SwipeRefreshState.REFRESHING);
    }
}
