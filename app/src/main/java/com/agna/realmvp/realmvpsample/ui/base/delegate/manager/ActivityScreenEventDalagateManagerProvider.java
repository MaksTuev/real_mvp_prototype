package com.agna.realmvp.realmvpsample.ui.base.delegate.manager;


import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.SupportScreenEventDelegation;

public class ActivityScreenEventDalagateManagerProvider implements ScreenEventDelegateManagerProvider {

    private ActivityProvider activityProvider;

    public ActivityScreenEventDalagateManagerProvider(ActivityProvider activityProvider) {
        this.activityProvider = activityProvider;
    }

    @Override
    public ScreenEventDelegateManager get(){
        return ((SupportScreenEventDelegation)activityProvider.get()).getScreenEventDelegateManager();
    }
}
