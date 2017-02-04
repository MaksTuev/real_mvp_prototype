package com.agna.realmvp.realmvpsample.ui.base.navigation.activity.route;


import android.content.Context;
import android.content.Intent;

import com.agna.realmvp.realmvpsample.ui.base.navigation.Route;

public abstract class ActivityRoute implements Route {

    public abstract Intent prepareIntent(Context context);

}
