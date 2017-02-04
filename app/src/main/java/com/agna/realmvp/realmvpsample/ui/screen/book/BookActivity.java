package com.agna.realmvp.realmvpsample.ui.screen.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.agna.realmvp.realmvpsample.R;
import com.agna.realmvp.realmvpsample.ui.base.screen.activity.BaseFragmentActivity;
import com.agna.realmvp.realmvpsample.ui.screen.book.inner.BookFragmentRoute;
import com.agna.realmvp.realmvpsample.ui.screen.book.inner.BookFragmentView;

import timber.log.Timber;

/**
 * Container for {@link BookFragmentView}
 */
public class BookActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        FragmentManager fm = getSupportFragmentManager();
        BookActivityRoute activityRoute = new BookActivityRoute(getIntent());
        BookFragmentRoute fragmentRoute = new BookFragmentRoute(activityRoute.getBookId());
        if (fm.findFragmentByTag(fragmentRoute.getTag()) == null) {
            Fragment fragment = fragmentRoute.createFragment();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(R.id.container, fragment, BookFragmentView.class.getSimpleName());
            fragmentTransaction.commit();
        }
    }

    @Override
    public String getName() {
        return "df";
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Timber.d("onActivityResult");
    }
}
