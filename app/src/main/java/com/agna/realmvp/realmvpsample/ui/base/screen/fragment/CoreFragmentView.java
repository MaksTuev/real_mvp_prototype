package com.agna.realmvp.realmvpsample.ui.base.screen.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.agna.ferro.core.PersistentScreenScope;
import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.view.fragment.MvpFragmentV4View;
import com.agna.realmvp.realmvpsample.ui.base.screen.configurator.HasScreenConfigurator;
import com.agna.realmvp.realmvpsample.ui.base.screen.configurator.ScreenConfigurator;
import com.agna.realmvp.realmvpsample.ui.base.delegate.SupportScreenEventDelegation;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.FragmentScreenEventDelegateManager;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManager;

/**
 * todo add delegates from ferro instead inheritance
 */
public abstract class CoreFragmentView extends MvpFragmentV4View implements
        SupportScreenEventDelegation,
        HasScreenConfigurator {

    private ScreenEventDelegateManager delegateManager;
    private ScreenConfigurator screenConfigurator;

    protected abstract ScreenConfigurator createScreenConfigurator(Context context,
                                                                   PersistentScreenScope persistentScreenScope,
                                                                   Bundle arguments);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, boolean viewRecreated) {
        delegateManager = new FragmentScreenEventDelegateManager((SupportScreenEventDelegation)getActivity());
        super.onActivityCreated(savedInstanceState, viewRecreated);
        screenConfigurator = createScreenConfigurator(getActivity(), getPersistentScreenScope(), getArguments()); //todo another place
    }


    @Override
    public ScreenEventDelegateManager getScreenEventDelegateManager() {
        return delegateManager;
    }

    @Override
    public ScreenComponent createScreenComponent() {
        return getScreenConfigurator().createScreenComponent();
    }

    @Override
    public ScreenConfigurator getScreenConfigurator() {
        return screenConfigurator;
    }

    @Override
    public String getName() {
        return getScreenConfigurator().getName();
    }
}
