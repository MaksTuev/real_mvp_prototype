package com.agna.realmvp.realmvpsample.ui.base.navigation.activity.route;


import android.content.Intent;

import java.io.Serializable;

public abstract class ActivityWithParamsAndResultRoute<T extends Serializable> extends ActivityWithResultRoute<T> {

    public ActivityWithParamsAndResultRoute(){
    }

    public ActivityWithParamsAndResultRoute(Intent intent){
        parseIntent(intent);
    }

    protected abstract void parseIntent(Intent intent);
}
