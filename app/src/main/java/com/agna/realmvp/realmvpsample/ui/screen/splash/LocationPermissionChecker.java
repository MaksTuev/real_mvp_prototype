package com.agna.realmvp.realmvpsample.ui.screen.splash;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.ui.base.delegate.RequestPermissionsResultDelegate;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

@PerScreen
public class LocationPermissionChecker implements RequestPermissionsResultDelegate {
    private static final int REQUEST_LOCATION_INTRO_ACTIVITY = 301;

    private ActivityProvider activityProvider;
    private BehaviorSubject<Boolean> requestPermissionResultSubject;

    @Inject
    public LocationPermissionChecker(ActivityProvider activityProvider) {
        this.activityProvider = activityProvider;
    }

    /**
     * проверяет доступность сервисов геолокации
     *
     * @return эмитит true, если геолокация доступна, и false в противном случае
     */
    public Observable<Boolean> check() {
        requestPermissionResultSubject = BehaviorSubject.create();
        requestPermission();
        return requestPermissionResultSubject.single();
    }

    @Override
    public boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_INTRO_ACTIVITY) {
            if (grantResults[0] == PERMISSION_GRANTED && grantResults[1] == PERMISSION_GRANTED) {
                requestPermissionResultSubject.onNext(true);
            } else {
                requestPermissionResultSubject.onNext(false);
            }
            return true;
        }
        return false;
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(activityProvider.get(), ACCESS_FINE_LOCATION) != PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(activityProvider.get(), ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activityProvider.get(),
                    new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION_INTRO_ACTIVITY);
        } else {
            requestPermissionResultSubject.onNext(true);
        }
    }

}