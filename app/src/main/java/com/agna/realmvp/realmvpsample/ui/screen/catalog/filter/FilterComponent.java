package com.agna.realmvp.realmvpsample.ui.screen.catalog.filter;


import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.app.App;
import com.agna.realmvp.realmvpsample.app.AppComponent;
import com.agna.realmvp.realmvpsample.ui.base.dagger.ActivityScreenModule;

import dagger.Component;

@PerScreen
@Component(dependencies = AppComponent.class, modules = {
        ActivityScreenModule.class,
        FilterScreenModule.class})
public interface FilterComponent extends ScreenComponent<FilterActivityView> {
}
