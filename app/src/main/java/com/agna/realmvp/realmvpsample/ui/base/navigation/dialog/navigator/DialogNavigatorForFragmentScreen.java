package com.agna.realmvp.realmvpsample.ui.base.navigation.dialog.navigator;


import com.agna.ferro.mvp.component.provider.ActivityProvider;
import com.agna.ferro.mvp.component.provider.FragmentProvider;
import com.agna.realmvp.realmvpsample.ui.base.screen.dialog.BaseDialogFragmentWithResult;
import com.agna.realmvp.realmvpsample.ui.base.screen.fragment.CoreFragmentView;

public class DialogNavigatorForFragmentScreen extends DialogNavigator {

    private FragmentProvider fragmentProvider;

    public DialogNavigatorForFragmentScreen(ActivityProvider activityProvider, FragmentProvider fragmentProvider) {
        super(activityProvider);
        this.fragmentProvider = fragmentProvider;
    }

    @Override
    protected void show(BaseDialogFragmentWithResult fragment) {
        fragment.show((CoreFragmentView) fragmentProvider.get());

    }
}
