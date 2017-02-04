package com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.route;

import com.agna.realmvp.realmvpsample.ui.base.screen.dialog.BaseDialogFragment;
import com.agna.realmvp.realmvpsample.ui.base.screen.dialog.BaseDialogFragmentWithResult;
import com.agna.realmvp.realmvpsample.ui.base.navigation.fragment.route.FragmentRoute;


public abstract class DialogRoute extends FragmentRoute {

    protected abstract Class<? extends BaseDialogFragment> getFragmentClass();

    @Override
    public BaseDialogFragment createFragment(){
        return (BaseDialogFragmentWithResult)super.createFragment();
    }
}
