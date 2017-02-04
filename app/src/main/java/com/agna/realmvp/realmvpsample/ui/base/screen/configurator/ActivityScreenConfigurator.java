package com.agna.realmvp.realmvpsample.ui.base.screen.configurator;


import android.content.Context;
import android.content.Intent;

import com.agna.ferro.core.PersistentScreenScope;
import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.realmvp.realmvpsample.app.AppComponent;
import com.agna.realmvp.realmvpsample.ui.base.dagger.ActivityScreenModule;

public abstract class ActivityScreenConfigurator extends ScreenConfigurator {

    private ActivityScreenModule activityScreenModule;
    private Intent intent;

    public ActivityScreenConfigurator(Context context, PersistentScreenScope persistentScreenScope, Intent intent) {
        super(context, persistentScreenScope);
        this.activityScreenModule = new ActivityScreenModule(persistentScreenScope);
        this.intent = intent;
    }

    protected abstract ScreenComponent createScreenComponent(AppComponent appComponent,
                                                             ActivityScreenModule activityScreenModule,
                                                             Intent intent);

    @Override
    public final ScreenComponent createScreenComponent() {
        return createScreenComponent(getAppComponent(), activityScreenModule, intent);
    }

    protected Intent getIntent() {
        return intent;
    }
 }
