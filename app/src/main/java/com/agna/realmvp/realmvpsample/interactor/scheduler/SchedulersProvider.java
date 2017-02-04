package com.agna.realmvp.realmvpsample.interactor.scheduler;

import rx.Scheduler;

public interface SchedulersProvider {
    Scheduler main();

    Scheduler worker();
}