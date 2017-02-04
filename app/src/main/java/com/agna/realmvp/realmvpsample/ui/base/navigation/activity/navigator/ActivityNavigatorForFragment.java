package com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator;


import android.content.Intent;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.provider.FragmentProvider;

public class ActivityNavigatorForFragment extends ActivityNavigator {

    private FragmentProvider fragmentProvider;

    public ActivityNavigatorForFragment(ActivityProvider activityProvider, FragmentProvider fragmentProvider) {
        super(activityProvider);
        this.fragmentProvider = fragmentProvider;
    }

    @Override
    protected void startActivityForResult(Intent intent, int requestCode) {
        fragmentProvider.get().startActivityForResult(intent, requestCode);
    }
}
