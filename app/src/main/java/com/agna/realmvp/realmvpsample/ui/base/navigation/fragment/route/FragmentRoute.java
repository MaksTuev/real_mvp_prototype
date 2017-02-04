package com.agna.realmvp.realmvpsample.ui.base.navigation.fragment.route;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.agna.realmvp.realmvpsample.ui.base.navigation.Route;


public abstract class FragmentRoute implements Route {

    protected abstract Class<? extends Fragment> getFragmentClass();

    public String getTag(){
        return getFragmentClass().getCanonicalName();
    }

    public Fragment createFragment(){
        try {
            Fragment fragment = getFragmentClass().newInstance();
            return fragment;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
