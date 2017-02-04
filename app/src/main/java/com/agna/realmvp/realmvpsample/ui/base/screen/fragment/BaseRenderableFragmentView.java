package com.agna.realmvp.realmvpsample.ui.base.screen.fragment;


import com.agna.realmvp.realmvpsample.ui.base.screen.HandlableErrorView;
import com.agna.realmvp.realmvpsample.ui.base.screen.RenderableView;
import com.agna.realmvp.realmvpsample.ui.base.screen.activity.CoreActivityView;
import com.agna.realmvp.realmvpsample.ui.base.screen.model.ScreenModel;
import com.agna.realmvp.realmvpsample.ui.common.error.ErrorHandler;
import com.agna.realmvp.realmvpsample.ui.common.error.StandardErrorHandler;

import javax.inject.Inject;

public abstract class BaseRenderableFragmentView<M extends ScreenModel> extends CoreActivityView
        implements RenderableView<M>, HandlableErrorView {

    @Inject
    StandardErrorHandler standardErrorHandler;

    protected abstract void renderInternal(M screenModel);

    @Override
    public void render(M screenModel) {
        renderInternal(screenModel);
    }

    @Override
    public void handleError(Throwable error){
        getErrorHandler().handleError(error);
    }

    protected ErrorHandler getErrorHandler() {
        return standardErrorHandler;
    }
}
