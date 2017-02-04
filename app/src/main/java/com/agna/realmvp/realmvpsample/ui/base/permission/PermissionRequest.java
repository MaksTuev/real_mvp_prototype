package com.agna.realmvp.realmvpsample.ui.base.permission;


import java.util.List;

public abstract class PermissionRequest {

    public abstract String[] getPermissions();

    public int getRequestCode(){
        return this.getClass().getCanonicalName().hashCode();
    }
}
