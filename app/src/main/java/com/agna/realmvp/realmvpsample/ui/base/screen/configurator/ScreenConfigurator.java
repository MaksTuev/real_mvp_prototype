package com.agna.realmvp.realmvpsample.ui.base.screen.configurator;


import android.content.Context;

import com.agna.ferro.core.HasName;
import com.agna.ferro.core.PersistentScreenScope;
import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.realmvp.realmvpsample.app.App;
import com.agna.realmvp.realmvpsample.app.AppComponent;

public abstract class ScreenConfigurator implements HasName {

    private AppComponent appComponent;
    private PersistentScreenScope persistentScreenScope;

    public ScreenConfigurator(Context context, PersistentScreenScope persistentScreenScope) {
        this.appComponent = ((App)context.getApplicationContext()).getAppComponent();
        this.persistentScreenScope = persistentScreenScope;
    }

    public abstract ScreenComponent createScreenComponent();

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ScreenComponent getScreenComponent(){
        return persistentScreenScope.getObject(ScreenComponent.class);
    }

}
