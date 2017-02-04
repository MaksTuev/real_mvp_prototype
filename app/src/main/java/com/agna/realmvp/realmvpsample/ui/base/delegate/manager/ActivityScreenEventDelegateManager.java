package com.agna.realmvp.realmvpsample.ui.base.delegate.manager;


import android.content.Intent;

import com.agna.realmvp.realmvpsample.ui.base.delegate.NewIntentDelegate;

public class ActivityScreenEventDelegateManager  extends BaseScreenEventDelegateManager {

    public void onNewIntent(Intent intent) {
        Class<NewIntentDelegate> delegateType = NewIntentDelegate.class;
        for(NewIntentDelegate delegate : getDelegates(delegateType)){
            if(delegate.onNewIntent(intent)){
                return;
            }
        }
        logUnhandled(delegateType, "new Intent=" + intent);
    }
}
