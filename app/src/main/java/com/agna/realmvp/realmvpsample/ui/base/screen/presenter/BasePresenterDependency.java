package com.agna.realmvp.realmvpsample.ui.base.screen.presenter;


import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.interactor.scheduler.SchedulersProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.ScreenEventDelegate;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManagerProvider;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator.ActivityNavigator;
import com.agna.realmvp.realmvpsample.ui.base.newintent.NewIntentManager;
import com.agna.realmvp.realmvpsample.ui.base.permission.PermissionManager;

import javax.inject.Inject;

@PerScreen
public class BasePresenterDependency {

    private SchedulersProvider schedulersProvider;
    private ScreenEventDelegateManagerProvider delegateManagerProvider;

    private ActivityNavigator activityNavigator;
    private PermissionManager permissionManager;
    private NewIntentManager newIntentManager;

    @Inject
    public BasePresenterDependency(SchedulersProvider schedulersProvider,
                                   ScreenEventDelegateManagerProvider delegateManagerProvider,
                                   ActivityNavigator activityNavigator,
                                   PermissionManager permissionManager,
                                   NewIntentManager newIntentManager) {
        this.schedulersProvider = schedulersProvider;
        this.delegateManagerProvider = delegateManagerProvider;
        this.activityNavigator = activityNavigator;
        this.permissionManager = permissionManager;
        this.newIntentManager = newIntentManager;
    }

    public SchedulersProvider getSchedulersProvider() {
        return schedulersProvider;
    }

    public ScreenEventDelegateManagerProvider getDelegateManagerProvider() {
        return delegateManagerProvider;
    }

    public ScreenEventDelegate[] getScreenEventDelegates() {
        return new ScreenEventDelegate[]{ activityNavigator, permissionManager, newIntentManager };
    }
}
