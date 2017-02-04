package com.agna.realmvp.realmvpsample.ui.base.dagger;


import com.agna.ferro.core.PersistentScreenScope;
import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ActivityScreenEventDalagateManagerProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManagerProvider;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator.ActivityNavigator;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator.ActivityNavigatorForActivity;
import com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.navigator.DialogNavigator;
import com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.navigator.DialogNavigatorForActivityScreen;
import com.agna.realmvp.realmvpsample.ui.base.permission.PermissionManager;
import com.agna.realmvp.realmvpsample.ui.base.permission.PermissionManagerForActivity;
import com.agna.realmvp.realmvpsample.ui.common.error.ErrorHandlerModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ErrorHandlerModule.class})
public class ActivityScreenModule {

    private PersistentScreenScope persistentScreenScope;

    public ActivityScreenModule(PersistentScreenScope persistentScreenScope) {
        this.persistentScreenScope = persistentScreenScope;
    }

    @Provides
    @PerScreen
    ActivityProvider provideActivityProvider() {
        return new ActivityProvider(persistentScreenScope);
    }

    @Provides
    @PerScreen
    DialogNavigator provideDialogNavigator(ActivityProvider activityProvider){
        return new DialogNavigatorForActivityScreen(activityProvider);
    }

    @Provides
    @PerScreen
    ActivityNavigator provideActivityNavigator(ActivityProvider activityProvider){
        return new ActivityNavigatorForActivity(activityProvider);
    }

    @Provides
    @PerScreen
    ScreenEventDelegateManagerProvider provideEventDelegateManagerProvider(ActivityProvider activityProvider){
        return new ActivityScreenEventDalagateManagerProvider(activityProvider);
    }

    @Provides
    @PerScreen
    PermissionManager providePermissionManager(ActivityProvider activityProvider){
        return new PermissionManagerForActivity(activityProvider);
    }

}
