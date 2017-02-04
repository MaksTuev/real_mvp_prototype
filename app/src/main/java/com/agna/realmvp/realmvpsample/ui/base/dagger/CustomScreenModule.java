package com.agna.realmvp.realmvpsample.ui.base.dagger;


import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.ui.base.navigation.Route;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class CustomScreenModule<R extends Route> {

    private R route;

    public CustomScreenModule(R route) {
        this.route = route;
    }

    @Provides
    @PerScreen
    public R provideRoute(){
        return route;
    }
}
