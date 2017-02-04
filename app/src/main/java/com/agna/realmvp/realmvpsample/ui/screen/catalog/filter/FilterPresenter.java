package com.agna.realmvp.realmvpsample.ui.screen.catalog.filter;


import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.domain.Filter;
import com.agna.realmvp.realmvpsample.domain.ShowFirstFilterValue;
import com.agna.realmvp.realmvpsample.interactor.scheduler.SchedulersProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManagerProvider;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator.ActivityNavigator;
import com.agna.realmvp.realmvpsample.ui.base.screen.presenter.CorePresenter;
import com.agna.realmvp.realmvpsample.ui.common.error.ErrorHandler;

import javax.inject.Inject;

@PerScreen
public class FilterPresenter extends CorePresenter<FilterActivityView> {
    private final Filter filter;
    private final FilterRoute route;
    private final ActivityNavigator activityNavigator;

    @Inject
    public FilterPresenter(Filter filter,
                           FilterRoute route,
                           SchedulersProvider schedulersProvider,
                           ErrorHandler errorHandler,
                           ScreenEventDelegateManagerProvider delegateManagerProvider,
                           ActivityNavigator activityNavigator) {
        super(schedulersProvider, errorHandler, delegateManagerProvider, activityNavigator);
        this.filter = filter;
        this.route = route;
        this.activityNavigator = activityNavigator;
    }

    @Override
    public void onLoad(boolean viewRecreated) {
        super.onLoad(viewRecreated);
        getView().showFilter(filter);
    }

    public void applyFilter(){
        activityNavigator.finishWithResult(route, filter);
    }

    public void onShowFirstChanged(ShowFirstFilterValue newValue) {
        filter.setShowFirst(newValue);
    }
}
