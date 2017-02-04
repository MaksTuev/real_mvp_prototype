package com.agna.realmvp.realmvpsample.ui.base.permission;


import android.support.v4.app.ActivityCompat;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.provider.FragmentProvider;

public class PermissionManagerForFragment extends PermissionManager {

    private final FragmentProvider fragmentProvider;

    public PermissionManagerForFragment(ActivityProvider activityProvider, FragmentProvider fragmentProvider) {
        super(activityProvider);
        this.fragmentProvider = fragmentProvider;
    }

    @Override
    protected void requestPermission(PermissionRequest request) {
        fragmentProvider.get().requestPermissions(request.getPermissions(), request.getRequestCode());
    }
}
