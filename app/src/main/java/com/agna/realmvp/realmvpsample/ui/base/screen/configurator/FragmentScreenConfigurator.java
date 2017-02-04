package com.agna.realmvp.realmvpsample.ui.base.screen.configurator;


import android.content.Context;
import android.os.Bundle;

import com.agna.ferro.core.PersistentScreenScope;
import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.realmvp.realmvpsample.app.AppComponent;
import com.agna.realmvp.realmvpsample.ui.base.dagger.FragmentScreenModule;

public abstract class FragmentScreenConfigurator extends ScreenConfigurator {

    private FragmentScreenModule fragmentScreenModule;
    private Bundle args;

    public FragmentScreenConfigurator(Context context, PersistentScreenScope persistentScreenScope, Bundle args) {
        super(context, persistentScreenScope);
        this.fragmentScreenModule = new FragmentScreenModule(persistentScreenScope);
        this.args = args;
    }

    protected abstract ScreenComponent createScreenComponent(AppComponent appComponent,
                                                             FragmentScreenModule fragmentScreenModule,
                                                             Bundle args);

    @Override
    public final ScreenComponent createScreenComponent() {
        return createScreenComponent(getAppComponent(), fragmentScreenModule, args);
    }

    protected Bundle getArgs(){
        return args;
    }

}
