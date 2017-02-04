package com.agna.realmvp.realmvpsample.ui.base.screen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;

import com.agna.ferro.core.PersistentScreenScope;
import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.view.activity.MvpActivityView;
import com.agna.realmvp.realmvpsample.ui.base.screen.configurator.HasScreenConfigurator;
import com.agna.realmvp.realmvpsample.ui.base.screen.configurator.ScreenConfigurator;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ActivityScreenEventDelegateManager;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManager;
import com.agna.realmvp.realmvpsample.ui.base.delegate.SupportScreenEventDelegation;

/**
 * Base class for view, based on Activity
 * todo add delegates from ferro instead inheritance
 */
public abstract class CoreActivityView extends MvpActivityView implements
        SupportScreenEventDelegation,
        HasScreenConfigurator {

    private ActivityScreenEventDelegateManager delegateManager = new ActivityScreenEventDelegateManager();
    private ScreenConfigurator screenConfigurator;

    protected abstract ScreenConfigurator createScreenConfigurator(Context context,
                                                                   PersistentScreenScope persistentScreenScope,
                                                                   Intent intent);

    @Override
    protected void onCreate(Bundle savedInstanceState, boolean viewRecreated) {
        super.onCreate(savedInstanceState, viewRecreated);
    }

    @Override
    public ScreenEventDelegateManager getScreenEventDelegateManager() {
        return delegateManager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        screenConfigurator = createScreenConfigurator(this, getPersistentScreenScope(), getIntent()); //todo another place
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        delegateManager.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        delegateManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        delegateManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public ScreenComponent createScreenComponent() {
        return getScreenConfigurator().createScreenComponent();
    }

    @Override
    public ScreenConfigurator getScreenConfigurator() {
        return screenConfigurator;
    }

    @Override
    public String getName() {
        return getScreenConfigurator().getName();
    }
}
