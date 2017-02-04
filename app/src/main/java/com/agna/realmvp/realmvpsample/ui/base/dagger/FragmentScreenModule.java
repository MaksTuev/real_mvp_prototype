package com.agna.realmvp.realmvpsample.ui.base.dagger;

import com.agna.ferro.core.PersistentScreenScope;
import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.provider.FragmentProvider;
import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.FrgmentScreenEventDalagateManagerProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManagerProvider;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator.ActivityNavigator;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator.ActivityNavigatorForFragment;
import com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.navigator.DialogNavigator;
import com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.navigator.DialogNavigatorForFragmentScreen;
import com.agna.realmvp.realmvpsample.ui.base.permission.PermissionManager;
import com.agna.realmvp.realmvpsample.ui.base.permission.PermissionManagerForFragment;
import com.agna.realmvp.realmvpsample.ui.common.error.ErrorHandlerModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ErrorHandlerModule.class})
public class FragmentScreenModule {

    private PersistentScreenScope persistentScreenScope;

    public FragmentScreenModule(PersistentScreenScope persistentScreenScope) {
        this.persistentScreenScope = persistentScreenScope;
    }

    @Provides
    @PerScreen
    ActivityProvider provideActivityProvider() {
        return new ActivityProvider(persistentScreenScope);
    }

    @Provides
    @PerScreen
    FragmentProvider provideFragmentProvider() {
        return new FragmentProvider(persistentScreenScope);
    }

    @Provides
    @PerScreen
    DialogNavigator provideDialogNavigator(ActivityProvider activityProvider, FragmentProvider fragmentProvider){
        return new DialogNavigatorForFragmentScreen(activityProvider, fragmentProvider);
    }

    @Provides
    @PerScreen
    ActivityNavigator provideActivityNavigator(ActivityProvider activityProvider, FragmentProvider fragmentProvider){
        return new ActivityNavigatorForFragment(activityProvider, fragmentProvider);
    }

    @Provides
    @PerScreen
    ScreenEventDelegateManagerProvider provideEventDelegateManagerProvider(FragmentProvider fragmentProvider){
        return new FrgmentScreenEventDalagateManagerProvider(fragmentProvider);
    }

    @Provides
    @PerScreen
    PermissionManager providePermissionManager(ActivityProvider activityProvider, FragmentProvider fragmentProvider){
        return new PermissionManagerForFragment(activityProvider, fragmentProvider);
    }
}
