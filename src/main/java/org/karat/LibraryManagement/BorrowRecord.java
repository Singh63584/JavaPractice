package org.karat.LibraryManagement;

public class BorrowRecord {
    private Book book;

    BorrowRecord(Book book) {
        this.book = book;
    }

    Book getBook() {
        return book;
    }
}
