package com.agna.realmvp.realmvpsample.ui.base.screen.activity;


import com.agna.realmvp.realmvpsample.ui.base.placeholder.PlaceHolderView;
import com.agna.realmvp.realmvpsample.ui.base.screen.model.LdsScreenModel;
import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.LoadState;

public abstract class BaseLdsActivityView<M extends LdsScreenModel>
        extends BaseRenderableActivityView<M> {

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
