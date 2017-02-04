package com.agna.realmvp.realmvpsample.ui.base.screen.presenter;


import com.agna.ferro.mvp.view.BaseView;
import com.agna.ferro.mvprx.MvpRxPresenter;
import com.agna.realmvp.realmvpsample.interactor.scheduler.SchedulersProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.ScreenEventDelegate;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManagerProvider;
import com.agna.realmvp.realmvpsample.ui.common.error.ErrorHandler;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.internal.util.InternalObservableUtils;

public class CorePresenter<V extends BaseView> extends MvpRxPresenter<V> {

    private final ScreenEventDelegateManagerProvider delegateManagerProvider;
    private final ScreenEventDelegate[] delegates;
    private SchedulersProvider schedulersProvider;
    private ErrorHandler errorHandler;

    public CorePresenter(BasePresenterDependency basePresenterDependency){
        this.schedulersProvider = basePresenterDependency.getSchedulersProvider();
        this.delegateManagerProvider = basePresenterDependency.getDelegateManagerProvider();
        this.delegates = basePresenterDependency.getScreenEventDelegates();
    }

    @Override
    public void onLoad(boolean viewRecreated) {
        super.onLoad(viewRecreated);
        for(ScreenEventDelegate delegate: delegates){
            delegateManagerProvider.get().registerDelegate(delegate);
        }
    }

    @Override
    protected <T> Subscription subscribe(Observable<T> observable,
                                         Action1<T> onNext,
                                         Action1<Throwable> onError) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribe(observable, onNext, onError);
    }


    protected <T> Subscription subscribe(Observable<T> observable,
                                         Action1<T> onNext) {
        return subscribe(observable, onNext, InternalObservableUtils.ERROR_NOT_IMPLEMENTED);
    }

    @Override
    protected <T> Subscription subscribe(Observable<T> observable,
                                         Func2<T, T, Boolean> replaceFrozenEventPredicate,
                                         Action1<T> onNext,
                                         Action1<Throwable> onError) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribe(observable, replaceFrozenEventPredicate, onNext, onError);
    }

    @Override
    protected <T> Subscription subscribe(Observable<T> observable,
                                         Func2<T, T, Boolean> replaceFrozenEventPredicate,
                                         Subscriber<T> subscriber) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribe(observable, replaceFrozenEventPredicate, subscriber);
    }

    @Override
    protected <T> Subscription subscribe(Observable<T> observable,
                                         Subscriber<T> subscriber) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribe(observable, subscriber);
    }

    @Override
    protected <T> Subscription subscribeWithoutFreezing(Observable<T> observable,
                                                        Action1<T> onNext,
                                                        Action1<Throwable> onError) {
        return super.subscribeWithoutFreezing(observable, onNext, onError);
    }

    @Override
    protected <T> Subscription subscribeWithoutFreezing(Observable<T> observable,
                                                        Subscriber<T> subscriber) {
        observable = observable.observeOn(schedulersProvider.main(), true);
        return super.subscribeWithoutFreezing(observable, subscriber);
    }

    /**
     * Работает также как {@link #subscribe}, кроме того предоставляет стандартную обработку
     * ошибок сетевых запросов
     */
    protected <T> Subscription subscribeNetworkQuery(Observable<T> observable,
                                                     final Action1<T> onNext,
                                                     final Action1<Throwable> onError) {
        return subscribeNetworkQuery(observable, onNext, onError, errorHandler);
    }

    /**
     * Работает также как {@link #subscribe}, кроме того предоставляет стандартную обработку
     * ошибок сетевых запросов
     */
    protected <T> Subscription subscribeNetworkQuery(Observable<T> observable,
                                                     final Action1<T> onNext) {
        return subscribeNetworkQuery(observable, onNext, null, errorHandler);
    }

    /**
     * Работает также как {@link #subscribeNetworkQuery}, кроме того позволяет указать обработчик
     * ошибок сетевых запросов
     */
    protected <T> Subscription subscribeNetworkQuery(Observable<T> observable,
                                                     final Action1<T> onNext,
                                                     ErrorHandler errorHandler) {
        return subscribeNetworkQuery(observable, onNext, null, errorHandler);
    }

    /**
     * Работает также как {@link #subscribeNetworkQuery}, кроме того позволяет указать обработчик
     * ошибок сетевых запросов
     */
    protected <T> Subscription subscribeNetworkQuery(Observable<T> observable,
                                                     final Action1<T> onNext,
                                                     final Action1<Throwable> onError,
                                                     ErrorHandler errorHandler) {
        observable = observable.subscribeOn(schedulersProvider.worker());
        return subscribe(observable, onNext, e -> onNetworkError(e, onError, errorHandler));
    }

    private void onNetworkError(Throwable e, Action1<Throwable> onError, ErrorHandler errorHandler) {
        errorHandler.handleError(e);
        if (onError != null) {
            onError.call(e);
        }
    }
}
