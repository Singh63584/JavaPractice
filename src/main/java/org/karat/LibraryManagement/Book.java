package org.karat.LibraryManagement;

import java.util.Objects;

public class Book {
    /**
     * Data about a book.
     */
    private String title;
    private String genre;

    Book(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    String getTitle() {
        return title;
    }

    String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Book)) return false;
        Book book = (Book) other;
        return title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
