package com.agna.realmvp.realmvpsample.ui.screen.catalog.grid;


import com.agna.realmvp.realmvpsample.domain.Book;

public interface BookItemListener {
    void onDownloadClick(Book book);
    void onReadClick(Book book);
    void onClick(Book book);
}
