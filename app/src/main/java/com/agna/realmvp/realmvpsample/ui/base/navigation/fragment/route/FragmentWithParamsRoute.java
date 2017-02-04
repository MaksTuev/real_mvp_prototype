package com.agna.realmvp.realmvpsample.ui.base.navigation.fragment.route;

import android.os.Bundle;
import android.support.v4.app.Fragment;


public abstract class FragmentWithParamsRoute extends FragmentRoute {

    public FragmentWithParamsRoute(){
    }

    public FragmentWithParamsRoute(Bundle bundle){
        parseBundle(bundle);
    }

    protected abstract Bundle prepareBundle();

    protected abstract void parseBundle(Bundle bundle);

    @Override
    public Fragment createFragment(){
        Fragment fragment = super.createFragment();
        fragment.setArguments(prepareBundle());
        return fragment;
    }
}
