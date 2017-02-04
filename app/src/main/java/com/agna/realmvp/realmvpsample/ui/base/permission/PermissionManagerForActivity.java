package com.agna.realmvp.realmvpsample.ui.base.permission;


import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.RequestPermissionsResultDelegate;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.BehaviorSubject;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class PermissionManagerForActivity extends PermissionManager {

    private ActivityProvider activityProvider;

    public PermissionManagerForActivity(ActivityProvider activityProvider) {
        super(activityProvider);
        this.activityProvider = activityProvider;
    }

    @Override
    protected void requestPermission(PermissionRequest request) {
        ActivityCompat.requestPermissions(activityProvider.get(), request.getPermissions(),
                request.getRequestCode());
    }
}
