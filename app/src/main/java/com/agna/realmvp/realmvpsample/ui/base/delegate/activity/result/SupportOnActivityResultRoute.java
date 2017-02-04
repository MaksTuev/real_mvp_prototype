package com.agna.realmvp.realmvpsample.ui.base.delegate.activity.result;


import android.content.Intent;

import java.io.Serializable;

public interface SupportOnActivityResultRoute<T extends Serializable> {

    Intent prepareResultIntent(T resultData);

    T parseResultIntent(Intent resultIntent);

    int getRequestCode();
}
