package com.agna.realmvp.realmvpsample.ui.base.error;

import com.agna.ferro.mvp.component.scope.PerScreen;

import dagger.Module;
import dagger.Provides;

@Module
public class ErrorHandlerModule {

    @Provides
    @PerScreen
    NetworkErrorHandler provideNetworkErrorHandler(StandardErrorHandler standardErrorHandler){
        return standardErrorHandler;
    }
}
