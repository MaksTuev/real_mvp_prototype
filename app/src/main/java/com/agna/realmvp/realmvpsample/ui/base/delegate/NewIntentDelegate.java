package com.agna.realmvp.realmvpsample.ui.base.delegate;


import android.content.Intent;
import android.support.annotation.NonNull;

public interface NewIntentDelegate extends ScreenEventDelegate {

    boolean onNewIntent(Intent intent);
}
