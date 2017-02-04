package com.agna.realmvp.realmvpsample.ui.screen.book.inner;


import android.content.Context;
import android.os.Bundle;

import com.agna.ferro.core.PersistentScreenScope;
import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.app.AppComponent;
import com.agna.realmvp.realmvpsample.ui.base.screen.configurator.FragmentScreenConfigurator;
import com.agna.realmvp.realmvpsample.ui.base.dagger.CustomScreenModule;
import com.agna.realmvp.realmvpsample.ui.base.dagger.FragmentScreenModule;

import dagger.Component;
import dagger.Module;

public class BookScreenConfigurator extends FragmentScreenConfigurator {

    @PerScreen
    @Component(dependencies = AppComponent.class, modules = {FragmentScreenModule.class, BookScreenModule.class})
    interface BookScreenComponent extends ScreenComponent<BookFragmentView> {
    }

    @Module
    public static class BookScreenModule extends CustomScreenModule<BookFragmentRoute>{
        public BookScreenModule(BookFragmentRoute route) {
            super(route);
        }
    }

    public BookScreenConfigurator(Context context, PersistentScreenScope persistentScreenScope, Bundle args) {
        super(context, persistentScreenScope, args);
    }

    @Override
    protected ScreenComponent createScreenComponent(AppComponent appComponent,
                                                    FragmentScreenModule fragmentScreenModule,
                                                    Bundle args) {
        return DaggerBookScreenConfigurator_BookScreenComponent.builder()
                .appComponent(appComponent)
                .bookScreenModule(new BookScreenModule(new BookFragmentRoute(args)))
                .fragmentScreenModule(fragmentScreenModule)
                .build();
    }

    @Override
    public String getName() {
        return "Book";
    }

}
