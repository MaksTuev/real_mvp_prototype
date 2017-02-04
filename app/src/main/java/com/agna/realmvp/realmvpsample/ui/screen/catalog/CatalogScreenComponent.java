package com.agna.realmvp.realmvpsample.ui.screen.catalog;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.app.AppComponent;
import com.agna.realmvp.realmvpsample.ui.base.dagger.ActivityScreenModule;

import dagger.Component;

/**
 * Component for Catalog screen
 */
@PerScreen
@Component(dependencies = AppComponent.class, modules = ActivityScreenModule.class)
interface CatalogScreenComponent extends ScreenComponent<CatalogActivityView> {
}
