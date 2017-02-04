package com.agna.realmvp.realmvpsample.ui.screen.book.inner;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.agna.realmvp.realmvpsample.ui.base.navigation.fragment.route.FragmentWithParamsRoute;

public class BookFragmentRoute extends FragmentWithParamsRoute {

    private String bookId;

    public BookFragmentRoute(String bookId) {
        this.bookId = bookId;
    }

    public BookFragmentRoute(Bundle bundle){
        super(bundle);
    }

    public String getBookId() {
        return bookId;
    }

    @Override
    protected Bundle prepareBundle() {
        Bundle b = new Bundle();
        b.putString(EXTRA_FIRST, bookId);
        return b;
    }

    @Override
    protected void parseBundle(Bundle bundle) {
        bookId = bundle.getString(EXTRA_FIRST);
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return BookFragmentView.class;
    }
}
