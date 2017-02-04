package com.agna.realmvp.realmvpsample.ui.base.screen.model;


import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.LoadState;

public class LdsScreenModel extends ScreenModel {
    private LoadState loadState = LoadState.UNSPECIFIED;

    public void setLoadState(LoadState loadState) {
        this.loadState = loadState;
    }

    public LoadState getLoadState() {
        return loadState;
    }
}
