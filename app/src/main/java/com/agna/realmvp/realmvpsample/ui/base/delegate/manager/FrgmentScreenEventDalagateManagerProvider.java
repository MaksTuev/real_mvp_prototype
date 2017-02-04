package com.agna.realmvp.realmvpsample.ui.base.delegate.manager;


import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.provider.FragmentProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.SupportScreenEventDelegation;

public class FrgmentScreenEventDalagateManagerProvider implements ScreenEventDelegateManagerProvider {

    private FragmentProvider fragmentProvider;

    public FrgmentScreenEventDalagateManagerProvider(FragmentProvider fragmentProvider) {
        this.fragmentProvider = fragmentProvider;
    }

    @Override
    public ScreenEventDelegateManager get(){
        return ((SupportScreenEventDelegation)fragmentProvider.get()).getScreenEventDelegateManager();
    }
}
