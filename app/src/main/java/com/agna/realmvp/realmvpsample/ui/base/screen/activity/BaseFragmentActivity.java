package com.agna.realmvp.realmvpsample.ui.base.screen.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.agna.ferro.core.PSSActivity;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ActivityScreenEventDelegateManager;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManager;
import com.agna.realmvp.realmvpsample.ui.base.delegate.SupportScreenEventDelegation;

public abstract class BaseFragmentActivity extends PSSActivity implements SupportScreenEventDelegation {

    private ActivityScreenEventDelegateManager delegateManager = new ActivityScreenEventDelegateManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ScreenEventDelegateManager getScreenEventDelegateManager() {
        return delegateManager;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        delegateManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        delegateManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        delegateManager.onNewIntent(intent);
    }
}
