package com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.navigator;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.realmvp.realmvpsample.ui.base.screen.dialog.BaseDialogFragment;
import com.agna.realmvp.realmvpsample.ui.base.screen.dialog.BaseDialogFragmentWithResult;
import com.agna.realmvp.realmvpsample.ui.base.navigation.Navigator;
import com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.route.DialogRoute;

public abstract class DialogNavigator implements Navigator {

    private ActivityProvider activityProvider;

    public DialogNavigator(ActivityProvider activityProvider) {

        this.activityProvider = activityProvider;
    }

    public void show(DialogRoute dialogRoute){
        BaseDialogFragment dialog = dialogRoute.createFragment();
        if(dialog instanceof BaseDialogFragmentWithResult) {
            show((BaseDialogFragmentWithResult) dialog);
        } else {
            dialog.show(((FragmentActivity)activityProvider.get()).getSupportFragmentManager(), dialogRoute.getTag());
        }

    }

    public void dismiss(DialogRoute dialogRoute){
        FragmentManager fragmentManager = ((AppCompatActivity) activityProvider.get()).getSupportFragmentManager();
        BaseDialogFragment dialogFragment = (BaseDialogFragment) fragmentManager
                .findFragmentByTag(dialogRoute.getTag());
        dialogFragment.dismiss();
    }

    protected abstract void show(BaseDialogFragmentWithResult fragment);
}
