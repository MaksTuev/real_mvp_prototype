package com.agna.realmvp.realmvpsample.ui.screen.book;


import android.content.Context;
import android.content.Intent;

import com.agna.realmvp.realmvpsample.ui.base.navigation.activity.route.ActivityWithParamsRoute;

public class BookActivityRoute extends ActivityWithParamsRoute{
    private String bookId;

    public BookActivityRoute(String bookId) {
        this.bookId = bookId;
    }

    public BookActivityRoute(Intent intent){
        super(intent);
    }


    public String getBookId() {
        return bookId;
    }

    @Override
    public Intent prepareIntent(Context context) {
        Intent i = new Intent(context, BookActivity.class);
        i.putExtra(EXTRA_FIRST, bookId);
        return i;
    }

    @Override
    protected void parseIntent(Intent intent) {
        intent.getIntExtra(EXTRA_FIRST, 0);
    }
}
