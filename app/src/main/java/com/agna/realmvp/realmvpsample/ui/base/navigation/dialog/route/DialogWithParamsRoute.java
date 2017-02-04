package com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.route;

import com.agna.realmvp.realmvpsample.ui.base.screen.dialog.BaseDialogFragment;
import com.agna.realmvp.realmvpsample.ui.base.screen.dialog.BaseDialogFragmentWithResult;
import com.agna.realmvp.realmvpsample.ui.base.navigation.fragment.route.FragmentWithParamsRoute;


public abstract class DialogWithParamsRoute extends FragmentWithParamsRoute {

    protected abstract Class<? extends BaseDialogFragment> getFragmentClass();

    @Override
    public BaseDialogFragment createFragment(){
        return (BaseDialogFragment)super.createFragment();
    }
}
