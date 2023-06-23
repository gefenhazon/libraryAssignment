package com.code.java.juniors;

import com.code.java.juniors.helpers.Enums.Genre;
import com.code.java.juniors.helpers.Enums.Languages;

import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a book's data. This class is a data class that stores information like title, author, publisher,
 * language and more.
 *
 * IMPORTANT: This class is already implemeted for you! No need to implement it and you are not allowed to change its
 * API.
 */
class Book {

    private final UUID uuid;
    private final String bookTitle;
    private final String authorName;
    private final Languages language;
    private final Genre genre;
    private final String publisher;

    public static Book create(String bookTitle, String authorName,
                              Languages language, Genre genre, String publisher) {
        return new Book(bookTitle, authorName, language, genre, publisher);
    }

    public Book(String bookTitle, String authorName,
                 Languages language, Genre genre, String publisher) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.language = language;
        this.genre = genre;
        this.publisher = publisher;
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Languages getLanguage() {
        return language;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(uuid, book.uuid) && Objects.equals(bookTitle, book.bookTitle) && Objects.equals(authorName, book.authorName) && language == book.language && genre == book.genre && Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
