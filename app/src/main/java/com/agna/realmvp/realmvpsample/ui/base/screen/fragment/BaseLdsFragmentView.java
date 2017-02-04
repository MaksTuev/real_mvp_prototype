package com.agna.realmvp.realmvpsample.ui.base.screen.fragment;


import com.agna.realmvp.realmvpsample.ui.base.screen.model.LdsScreenModel;
import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.LoadState;
import com.agna.realmvp.realmvpsample.ui.base.placeholder.PlaceHolderView;

public abstract class BaseLdsFragmentView<M extends LdsScreenModel>
        extends BaseRenderableFragmentView<M> {

    protected abstract PlaceHolderView getPlaceHolderView();

    @Override
    public void render(M screenModel) {
        renderLoadState(screenModel.getLoadState());
        renderInternal(screenModel);
    }

    protected void renderLoadState(LoadState loadState) {
        getPlaceHolderView().showState(loadState);
    }
}
