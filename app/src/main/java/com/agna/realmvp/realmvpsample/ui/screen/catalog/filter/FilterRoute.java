package com.agna.realmvp.realmvpsample.ui.screen.catalog.filter;


import android.content.Context;
import android.content.Intent;

import com.agna.realmvp.realmvpsample.domain.Filter;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.route.ActivityWithResultRoute;

public class FilterRoute extends ActivityWithResultRoute<Filter> {

    private static final String EXTRA_FILTER = "EXTRA_FILTER";
    private Filter filter;

    public FilterRoute(Filter filter) {
        this.filter = filter;
    }

    public FilterRoute(Intent startIntent) {
        this.filter = (Filter) startIntent.getSerializableExtra(EXTRA_FILTER);
    }

    public Filter getFilter() {
        return filter;
    }

    @Override
    public Intent prepareResultIntent(Filter resultData) {
        Intent i = new Intent();
        i.putExtra(EXTRA_FILTER, resultData);
        return i;
    }

    @Override
    public Filter parseResultIntent(Intent resultIntent) {
        return (Filter)resultIntent.getSerializableExtra(EXTRA_FILTER);
    }

    @Override
    public int getRequestCode() {
        return 11;
    }

    @Override
    public Intent prepareIntent(Context context) {
        Intent i = new Intent(context, FilterActivityView.class);
        i.putExtra(EXTRA_FILTER, filter);
        return i;
    }
}
