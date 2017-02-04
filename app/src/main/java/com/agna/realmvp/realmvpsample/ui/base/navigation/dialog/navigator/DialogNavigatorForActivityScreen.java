package com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.navigator;


import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.realmvp.realmvpsample.ui.base.screen.activity.CoreActivityView;
import com.agna.realmvp.realmvpsample.ui.base.screen.dialog.BaseDialogFragmentWithResult;

public class DialogNavigatorForActivityScreen extends DialogNavigator {

    private ActivityProvider activityProvider;

    public DialogNavigatorForActivityScreen(ActivityProvider activityProvider) {
        super(activityProvider);
        this.activityProvider = activityProvider;
    }

    @Override
    protected void show(BaseDialogFragmentWithResult fragment) {
        fragment.show((CoreActivityView) activityProvider.get());

    }
}
