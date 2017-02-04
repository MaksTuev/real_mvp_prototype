package com.agna.realmvp.realmvpsample.interactor.scheduler;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

    @Provides
    @PerApplication
    public SchedulersProvider provideSchedulerProvider(){
        return new SchedulersProviderImpl();
    }
}