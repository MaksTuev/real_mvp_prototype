package com.agna.realmvp.realmvpsample.ui.base.delegate;


import android.support.annotation.NonNull;

public interface RequestPermissionsResultDelegate extends ScreenEventDelegate {

    boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
