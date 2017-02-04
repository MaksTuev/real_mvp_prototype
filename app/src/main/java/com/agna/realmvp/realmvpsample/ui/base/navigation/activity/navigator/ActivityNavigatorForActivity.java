package com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator;


import android.content.Intent;

import com.agna.ferro.mvp.component.provider.ActivityProvider;

public class ActivityNavigatorForActivity extends ActivityNavigator {

    private ActivityProvider activityProvider;

    public ActivityNavigatorForActivity(ActivityProvider activityProvider) {
        super(activityProvider);
        this.activityProvider = activityProvider;
    }

    @Override
    protected void startActivityForResult(Intent intent, int requestCode) {
        activityProvider.get().startActivityForResult(intent, requestCode);
    }
}
