package com.agna.realmvp.realmvpsample.ui.base.newintent;


import android.content.Intent;
import android.support.annotation.Nullable;

public interface NewIntentRoute<T> {

    @Nullable
    T parseIntent(Intent newIntent);
}
