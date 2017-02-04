package com.agna.realmvp.realmvpsample.ui.base.screen.fragment;


import com.agna.realmvp.realmvpsample.ui.base.recycler.PaginationableAdapter;
import com.agna.realmvp.realmvpsample.ui.base.screen.ChangeableListView;
import com.agna.realmvp.realmvpsample.ui.base.screen.model.LdsSwrPgnScreenModel;
import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.PaginationState;

public abstract class BaseLdsSwrPgnFragmentView<M extends LdsSwrPgnScreenModel>
        extends BaseLdsSwrFragmentView<M> implements ChangeableListView {

    protected abstract PaginationableAdapter getPaginationableAdapter();

    @Override
    public void render(M screenModel) {
        renderLoadState(screenModel.getLoadState());
        renderSwipeRefreshState(screenModel.getSwipeRefreshState());
        renderPaginationState(screenModel.getPaginationState());
        renderInternal(screenModel);
    }

    private void renderPaginationState(PaginationState paginationState) {
        getPaginationableAdapter().setState(paginationState);
    }
}
