package com.agna.realmvp.realmvpsample.ui.screen.catalog.filter;


import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.domain.Filter;
import com.agna.realmvp.realmvpsample.ui.base.dagger.CustomScreenModule;

import dagger.Module;
import dagger.Provides;

@Module
public class FilterScreenModule extends CustomScreenModule<FilterRoute> {

    public FilterScreenModule(FilterRoute route) {
        super(route);
    }

    @Provides
    @PerScreen
    public Filter provideFilter(FilterRoute route){
        return route.getFilter();
    }
}
