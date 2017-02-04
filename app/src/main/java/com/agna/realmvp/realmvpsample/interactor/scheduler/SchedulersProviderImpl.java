package com.agna.realmvp.realmvpsample.interactor.scheduler;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SchedulersProviderImpl implements SchedulersProvider {

    @Override
    public Scheduler main(){
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler worker(){
        return Schedulers.io();
    }
}