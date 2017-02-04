package com.agna.realmvp.realmvpsample.ui.base.delegate.manager;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.ui.base.delegate.SupportScreenEventDelegation;

import javax.inject.Inject;


public interface ScreenEventDelegateManagerProvider {

    ScreenEventDelegateManager get();
}
