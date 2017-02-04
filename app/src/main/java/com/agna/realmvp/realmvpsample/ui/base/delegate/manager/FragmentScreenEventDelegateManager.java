package com.agna.realmvp.realmvpsample.ui.base.delegate.manager;


import com.agna.realmvp.realmvpsample.ui.base.delegate.NewIntentDelegate;
import com.agna.realmvp.realmvpsample.ui.base.delegate.ScreenEventDelegate;
import com.agna.realmvp.realmvpsample.ui.base.delegate.SupportScreenEventDelegation;

public class FragmentScreenEventDelegateManager extends BaseScreenEventDelegateManager {

    private SupportScreenEventDelegation activitySupportEventDelegation;

    public FragmentScreenEventDelegateManager(SupportScreenEventDelegation activitySupportEventDelegation) {
        this.activitySupportEventDelegation = activitySupportEventDelegation;
    }

    @Override
    public void registerDelegate(ScreenEventDelegate delegate) {
        if(NewIntentDelegate.class.isInstance(delegate)){
            activitySupportEventDelegation.getScreenEventDelegateManager().registerDelegate(delegate);
        } else {
            super.registerDelegate(delegate);
        }
    }
}
