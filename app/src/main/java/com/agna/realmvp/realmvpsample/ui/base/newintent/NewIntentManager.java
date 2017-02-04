package com.agna.realmvp.realmvpsample.ui.base.newintent;


import android.content.Intent;
import android.util.Log;

import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.ui.base.delegate.NewIntentDelegate;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

@PerScreen
public class NewIntentManager implements NewIntentDelegate {

    @Inject
    public NewIntentManager() {
    }

    private Map<NewIntentRoute, Subject> newIntentSubjects = new HashMap<>();

    @Override
    public boolean onNewIntent(Intent intent) {
        for(NewIntentRoute route : newIntentSubjects.keySet()){
            Object event = route.parseIntent(intent);
            if(event!=null) {
                Subject resultSubject = newIntentSubjects.get(route);
                resultSubject.onNext(event);
                return true;
            }
        }
        return false;
    }

    public <T> Observable<T> observe(NewIntentRoute<T> newIntentRoute){
        tryRemoveDuplicateEventSubjects(newIntentRoute);
        PublishSubject<T> eventSubject = PublishSubject.create();
        newIntentSubjects.put(newIntentRoute, eventSubject);
        return eventSubject;
    }

    private void tryRemoveDuplicateEventSubjects(NewIntentRoute eventParser) {
        for(NewIntentRoute registeredRoute: newIntentSubjects.keySet()){
            if(registeredRoute.getClass().getCanonicalName().equals(eventParser.getClass().getCanonicalName())){
                newIntentSubjects.get(registeredRoute).onCompleted();
                newIntentSubjects.remove(registeredRoute);
                Log.v(this.getClass().getName(), "duplicate registered NewIntentRoute :"
                        + registeredRoute + " old route unregistered");
            }
        }
    }
}
