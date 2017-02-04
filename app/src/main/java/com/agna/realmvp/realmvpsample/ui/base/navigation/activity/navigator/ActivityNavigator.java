package com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator;


import android.app.Activity;
import android.content.Intent;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.ui.base.delegate.activity.result.BaseActivityResultDelegate;
import com.agna.realmvp.realmvpsample.ui.base.delegate.activity.result.SupportOnActivityResultRoute;
import com.agna.realmvp.realmvpsample.ui.base.navigation.Navigator;
import com.agna.realmvp.realmvpsample.ui.base.navigation.ScreenResult;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.route.ActivityRoute;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.route.ActivityWithResultRoute;

import java.io.Serializable;

import javax.inject.Inject;

import rx.Observable;

@PerScreen
public abstract class ActivityNavigator extends BaseActivityResultDelegate implements Navigator {

    private final ActivityProvider activityProvider;


    @Inject
    public ActivityNavigator(ActivityProvider activityProvider) {
        this.activityProvider = activityProvider;
    }

    protected abstract void startActivityForResult(Intent intent, int requestCode);

    public <T extends Serializable> Observable<ScreenResult<T>> observeActivityResult(
            Class<? extends ActivityWithResultRoute<T>> routeClass) {
        return super.observeOnActivityResult(routeClass);
    }

    public void finishCurrent(){
        activityProvider.get().finish();
    }

    public <T extends Serializable> void finishWithResult(ActivityWithResultRoute<T> activeScreenRoute,
                                                          boolean success) {
        finishWithResult(activeScreenRoute, null, success);
    }

    public <T extends Serializable> void finishWithResult(ActivityWithResultRoute<T> activeScreenRoute,
                                                          T result){
        finishWithResult(activeScreenRoute, result, true);
    }

    public <T extends Serializable> void finishWithResult(ActivityWithResultRoute<T> currentScreenRoute,
                                                          T result, boolean success){
            Intent resultIntent = currentScreenRoute.prepareResultIntent(result);
            activityProvider.get().setResult(
                    success ? Activity.RESULT_OK : Activity.RESULT_CANCELED,
                    resultIntent);
            finishCurrent();
    }

    public void start(ActivityRoute route){
        activityProvider.get().startActivity(route.prepareIntent(activityProvider.get()));
    }

    public void startForResult(ActivityWithResultRoute route){
        if (!super.isObserved(route)) {
            throw new IllegalStateException("route class " + route.getClass().getSimpleName()
                    + " must be registered by method ActivityNavigator#observeActivityResult");
        }
        startActivityForResult(
                route.prepareIntent(activityProvider.get()),
                route.getRequestCode());
    }


}
