package com.agna.realmvp.realmvpsample.ui.screen.book.inner;


import com.agna.realmvp.realmvpsample.domain.Book;

/**
 * inner model, which used inside Book screen
 */
class FullBookModel {
    private Book book;
    private String description;

    public FullBookModel(Book book, String description) {
        this.book = book;
        this.description = description;
    }

    public Book getBook() {
        return book;
    }

    public String getDescription() {
        return description;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
