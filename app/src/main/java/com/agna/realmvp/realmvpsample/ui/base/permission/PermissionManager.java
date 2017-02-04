package com.agna.realmvp.realmvpsample.ui.base.permission;


import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.RequestPermissionsResultDelegate;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.BehaviorSubject;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public abstract class PermissionManager implements RequestPermissionsResultDelegate {
    private ActivityProvider activityProvider;

    private Map<Integer, BehaviorSubject<Boolean>> requestSubjects = new HashMap<>();

    public PermissionManager(ActivityProvider activityProvider) {
        this.activityProvider = activityProvider;
    }

    protected abstract void requestPermission(PermissionRequest request);

    public boolean check(PermissionRequest request){
        boolean result = true;
        for(String permission: request.getPermissions()){
            result = result && check(permission);
        }
        return result;
    }

    public Observable<Boolean> request(PermissionRequest request) {
        BehaviorSubject<Boolean> requestPermissionResultSubject = BehaviorSubject.create();
        int requestCode = request.getRequestCode();
        requestSubjects.put(requestCode, requestPermissionResultSubject);
        requestPermissionIfNeeded(request);
        return requestPermissionResultSubject
                .take(1)
                .doOnNext(result -> requestSubjects.remove(requestCode));
    }



    @Override
    public boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestSubjects.containsKey(requestCode)) {
            requestSubjects.get(requestCode).onNext(isAllGranted(grantResults));
            return true;
        } else {
            return false;
        }
    }

    private boolean check(String permission){
        return ContextCompat.checkSelfPermission(activityProvider.get(), permission) == PERMISSION_GRANTED;
    }

    private Boolean isAllGranted(int[] grantResults) {
        boolean allGranted = true;
        for(int result : grantResults) {
            allGranted = allGranted && result == PERMISSION_GRANTED;
        }
        return allGranted;
    }

    private void requestPermissionIfNeeded(PermissionRequest request) {
        if (!check(request)) {
            requestPermission(request);
        } else {
            requestSubjects.get(request.getRequestCode()).onNext(true);
        }
    }
}
