package com.agna.realmvp.realmvpsample.ui.screen.splash;


import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.interactor.scheduler.SchedulersProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManagerProvider;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator.ActivityNavigator;
import com.agna.realmvp.realmvpsample.ui.base.screen.presenter.CorePresenter;
import com.agna.realmvp.realmvpsample.ui.common.error.ErrorHandler;
import com.agna.realmvp.realmvpsample.ui.screen.catalog.CatalogRoute;

import javax.inject.Inject;

@PerScreen
public class SplashPresenter extends CorePresenter<SplashActivityView> {

    private LocationPermissionChecker locationPermissionChecker;
    private final ActivityNavigator activityNavigator;

    @Inject
    public SplashPresenter(SchedulersProvider schedulersProvider,
                           ErrorHandler errorHandler,
                           ScreenEventDelegateManagerProvider delegateManagerProvider,
                           LocationPermissionChecker locationPermissionChecker,
                           ActivityNavigator activityNavigator) {
        super(schedulersProvider, errorHandler, delegateManagerProvider, locationPermissionChecker);
        this.locationPermissionChecker = locationPermissionChecker;
        this.activityNavigator = activityNavigator;
    }

    @Override
    public void onLoad(boolean viewRecreated) {
        super.onLoad(viewRecreated);
        if(!viewRecreated) {
            subscribe(locationPermissionChecker.check(),
                    permissionGranted -> {
                        if(permissionGranted){
                            //do smth here
                        }
                        activityNavigator.start(new CatalogRoute());
                    });
        }
    }
}
