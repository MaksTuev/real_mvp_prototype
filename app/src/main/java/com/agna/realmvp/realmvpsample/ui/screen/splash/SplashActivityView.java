package com.agna.realmvp.realmvpsample.ui.screen.splash;


import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.presenter.MvpPresenter;
import com.agna.realmvp.realmvpsample.R;
import com.agna.realmvp.realmvpsample.ui.base.screen.activity.CoreActivityView;

import javax.inject.Inject;

public class SplashActivityView extends CoreActivityView {

    @Inject
    SplashPresenter presenter;

    @Override
    public String getName() {
        return "Splash";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected ScreenComponent createScreenComponent() {
        return DaggerSplashScreenComponent.builder()
                .appComponent(getAppComponent())
                .activityScreenModule(getActivityScreenModule())
                .build();
    }

    @Override
    public MvpPresenter getPresenter() {
        return presenter;
    }
}
