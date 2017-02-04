package com.agna.realmvp.realmvpsample.ui.screen.catalog;

import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.ferro.rx.OperatorFreeze;
import com.agna.realmvp.realmvpsample.book.BookRepository;
import com.agna.realmvp.realmvpsample.domain.Book;
import com.agna.realmvp.realmvpsample.domain.Filter;
import com.agna.realmvp.realmvpsample.interactor.scheduler.SchedulersProvider;
import com.agna.realmvp.realmvpsample.ui.base.delegate.manager.ScreenEventDelegateManagerProvider;
import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.navigator.ActivityNavigator;
import com.agna.realmvp.realmvpsample.ui.base.navigation.ScreenResult;
import com.agna.realmvp.realmvpsample.ui.base.screen.presenter.CorePresenter;
import com.agna.realmvp.realmvpsample.ui.common.error.ErrorHandler;
import com.agna.realmvp.realmvpsample.ui.screen.book.BookActivityRoute;
import com.agna.realmvp.realmvpsample.ui.screen.catalog.filter.FilterRoute;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Presenter for Catalog screen
 * <p>
 * <p>
 * Presenter with freeze logic.
 * If subscribe to {@link Observable} via one of {@link #subscribe(Observable, Subscriber)} method,
 * all rx events (onNext, onError, onComplete) would be frozen when view destroyed and unfrozen
 * when view recreated (see {@link OperatorFreeze}).
 * All events would be also frozen when screen paused and unfrozen when screen resumed.
 * When screen finally destroyed, all subscriptions would be automatically unsubscribed.
 * <p>
 * When configuration changed, presenter isn't destroyed and reused for new view
 */

@PerScreen
class CatalogPresenter extends CorePresenter<CatalogActivityView> {

    private final BookRepository bookRepository;
    private final ActivityNavigator activityNavigator;


    private List<Book> books = new ArrayList<>();
    private Filter filter = new Filter();
    private Subscription loadBookSubscription;

    @Inject
    public CatalogPresenter(SchedulersProvider schedulersProvider,
                            ErrorHandler errorHandler,
                            ScreenEventDelegateManagerProvider delegateManagerProvider,
                            ActivityNavigator activityNavigator,
                            BookRepository bookRepository) {
        super(schedulersProvider, errorHandler, delegateManagerProvider, activityNavigator);
        this.activityNavigator = activityNavigator;
        this.bookRepository = bookRepository;
    }


    @Override
    public void onLoad(boolean viewRecreated) {
        super.onLoad(viewRecreated);
        tryLoadData();
        if (!viewRecreated) {
            observeChangingBooks();
        }
    }

    /**
     * example of request, which load main data for screen
     */
    private void tryLoadData() {
        if (books.size() != 0) {
            //if books already loaded, just show data
            onLoadBooksSuccess(books);
        } else if (isSubscriptionInactive(loadBookSubscription)) {
            //if data isn't loading now, start loading
            getView().showLoading();
            loadData();
        } else {
            //else simple wait while data has loaded
            getView().showLoading();
        }
    }

    /**
     * example of simple request
     * you do not check already loaded data and loading status
     */
    public void reloadData() {
        loadData();
    }

    private void loadData() {
        Observable<List<Book>> observable = bookRepository.getBooks(filter)
                .observeOn(AndroidSchedulers.mainThread());
        loadBookSubscription = subscribe(observable,
                this::onLoadBooksSuccess,
                this::onLoadBooksError);
    }

    /**
     * example for subscribing to observable, which emits many events
     */
    private void observeChangingBooks() {
        Observable<Book> observable = bookRepository.observeChangingBooks()
                .observeOn(AndroidSchedulers.mainThread());
        subscribe(observable,
                //Keep only last book with different id in freeze buffer.
                //This prevent handling not relevant events when buffer would be unfrozen.
                //You can simple unsubscribe/subscrube to this observable and not use
                //  replaceFrozenEventPredicate, but then you can miss important event
                (frozenBook, newBook) -> frozenBook.getId().equals(newBook.getId()),
                this::updateBook,
                e -> Timber.e(e, "load data error"));

    }

    private void updateBook(Book newBook) {
        for(int i = 0; i<books.size(); i++){
            Book book = books.get(i);
            if (book.getId().equals(newBook.getId())) {
                books.set(i, newBook);
                getView().updateBooksData(books);
                getView().notifyItemChanged(i);
                break;
            }

        }
    }

    public void downloadBook(Book book) {
        bookRepository.startDownload(book.getId());
    }

    public void openBookScreen(Book book) {
        activityNavigator.start(new BookActivityRoute(book.getId()));
    }

    private void onLoadBooksError(Throwable e) {
        getView().hideLoading();
        Timber.e(e, "load data error");
        //handle error
    }

    private void onLoadBooksSuccess(List<Book> books) {
        this.books = books;
        getView().hideLoading();
        getView().updateBooksData(books);
        getView().notifyDataChanged();
    }

    public void openFilter() {
        subscribe(activityNavigator.startForResult(new FilterRoute(filter))
                .filter(ScreenResult::isSuccess)
                .map(ScreenResult::getData),
                newFilter-> {
                    this.filter = newFilter;
                    reloadData();
                });
    }
}
