package com.agna.realmvp.realmvpsample.ui.base.screen.presenter;


import com.agna.ferro.mvp.view.BaseView;
import com.agna.realmvp.realmvpsample.ui.base.screen.HandlableErrorView;
import com.agna.realmvp.realmvpsample.ui.base.screen.RenderableView;

public abstract class BasePresenter<V extends BaseView & HandlableErrorView> extends CorePresenter<V> {

    public BasePresenter(BasePresenterDependency basePresenterDependency) {
        super(basePresenterDependency);
    }
}
