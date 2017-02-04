package com.agna.realmvp.realmvpsample.ui.base.navigation.activity.route;


import android.content.Intent;

public abstract class ActivityWithParamsRoute extends ActivityRoute {

    public ActivityWithParamsRoute(){
    }

    public ActivityWithParamsRoute(Intent intent){
        parseIntent(intent);
    }

    protected abstract void parseIntent(Intent intent);

}
