package com.code.java.juniors;

import com.code.java.juniors.helpers.Enums.Genre;
import com.code.java.juniors.helpers.Enums.Languages;

import java.util.*;

/**
 * Here is a singleton implementation of the book management system.
 */
class BooksManagementSystem {

    private static BooksManagementSystem instance = null;

    public BooksManagementSystem() {
    }

    public static BooksManagementSystem getInstance() {
        if (instance == null) {
            instance = new BooksManagementSystem();
        }
        return instance;
    }

    /* YOUR CODE SHOULD GO HERE */

    private Map<UUID, BooksInCollection> collection = new HashMap<>(); // Maps each book to it's available collection status
    private Map<CustomerReader, List<BookPhysicalCopy>> currentCustomers = new HashMap<>(); // Maps each reader to it's current taken books
    private Map<BookPhysicalCopy, CustomerReader> physicalBooksMap = new HashMap<>(); // Maps each physical book to it's owner

    public Book addNewBookToCollection(Book book, int numberOfPhysicalCopies) {
        if (collection.containsKey(book.getUuid())) { // If book already in collection
            addNewBookCopiesToCollection(book, numberOfPhysicalCopies);
            return book;
        }
        UUID uuid = book.getUuid();
        List<BookPhysicalCopy> copies = new ArrayList<>();
        for (int i = 0; i < numberOfPhysicalCopies; i++) {
            copies.add(new BookPhysicalCopy(uuid));
        }
        collection.put(uuid, new BooksInCollection(copies));
        return book;
    }

    public Book addNewBookToCollection(String bookTitle, String authorName, Languages language,
                                       Genre genre, String publisher, int numberOfPhysicalCopies) {
        return this.addNewBookToCollection(new Book(bookTitle, authorName, language, genre, publisher), numberOfPhysicalCopies);
    }

    public void addNewBookCopiesToCollection(Book book, int numberOfPhysicalCopies) {
        UUID uuid = book.getUuid();
        //If the book is not already in collection
        if (!collection.containsKey(uuid)) {
            addNewBookToCollection(book, numberOfPhysicalCopies);
        }

        for (int i = 0; i < numberOfPhysicalCopies; i++) {
            collection.get(uuid).addNewCopy(new BookPhysicalCopy(uuid));
        }
    }

    public boolean removeBookFromCollection(Book book) {
        if (!collection.containsKey(book.getUuid())) {
            return false;
        } // Book not in collection
        BooksInCollection availableBooks = collection.get(book.getUuid());
        if (availableBooks == null) {
            return false;
        } // In case book number in collection without any copies
        if (availableBooks.getTakenCopies() == 0) {
            collection.remove(book.getUuid());
            return true;
        } else {
            return false;
        } // There are taken copies

    }

    public List<BookPhysicalCopy> getAllAvailableCopies(Book book) {
        if (!collection.containsKey(book.getUuid())) {
            return new ArrayList<>();
        } //Book not available
        BooksInCollection availableBooks = collection.get(book.getUuid());
        if (availableBooks == null) {
            return new ArrayList<>();
        } // No available copies

        // Create clone
        List<BookPhysicalCopy> availableCopies = availableBooks.getCopies();
        List<BookPhysicalCopy> copiesClone = new ArrayList<>(availableCopies);

        if (copiesClone.size() == 0) {
            return new ArrayList<>();
        }
        return copiesClone;
    }

    public boolean lendBookToReader(BookPhysicalCopy physicalCopy, CustomerReader reader) {
        if (physicalBooksMap.get(physicalCopy) != null) {
            return false;
        } // Book is Taken
        if (!collection.containsKey(physicalCopy.getBookTitleUuid())) {
            return false;
        } // Book is not available at all

        if (!currentCustomers.containsKey(reader)) {
            currentCustomers.put(reader, new ArrayList<>());
        }

        List<BookPhysicalCopy> readerBooks = currentCustomers.get(reader);
        int len = readerBooks.size();
        if (len >= 3) {
            return false;
        } // Too many books taken

        //Check if reader already has the same book title:
        for (BookPhysicalCopy book : readerBooks) {
            if (book.getBookTitleUuid() == physicalCopy.getBookTitleUuid()) {
                return false;
            }
        }

        readerBooks.add(physicalCopy);
        physicalBooksMap.put(physicalCopy, reader);
        collection.get(physicalCopy.getBookTitleUuid()).takeCopy(physicalCopy); // Remove copy from library
        return true;
    }

    public boolean returnBookFromReader(BookPhysicalCopy physicalCopy) {
        if (!physicalBooksMap.containsKey(physicalCopy)) {
            return false;
        } // Book not taken

        physicalBooksMap.remove(physicalCopy);
        collection.get(physicalCopy.getBookTitleUuid()).returnCopy(physicalCopy); // Add the copy back to the library
        return true;
    }
}
