package com.agna.realmvp.realmvpsample.ui.base.navigation.activity.route;


import android.content.Intent;

import com.agna.realmvp.realmvpsample.ui.base.delegate.activity.result.SupportOnActivityResultRoute;

import java.io.Serializable;

public abstract class ActivityWithResultRoute<T extends Serializable> extends ActivityRoute
        implements SupportOnActivityResultRoute<T> {

    @Override
    public Intent prepareResultIntent(T resultData){
        Intent i = new Intent();
        i.putExtra(EXTRA_FIRST, resultData);
        return i;
    }

    @Override
    public T parseResultIntent(Intent resultIntent){
        return (T)resultIntent.getSerializableExtra(EXTRA_FIRST);
    }

    @Override
    public final int getRequestCode(){
        return this.getClass().getCanonicalName().hashCode();
    }

}
