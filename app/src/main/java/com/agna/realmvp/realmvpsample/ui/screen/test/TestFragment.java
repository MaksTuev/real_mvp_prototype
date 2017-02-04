package com.agna.realmvp.realmvpsample.ui.screen.test;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class TestFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TestActivity.startForResult(getActivity());

    }
}
