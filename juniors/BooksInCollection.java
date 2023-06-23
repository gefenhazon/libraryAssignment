package com.code.java.juniors;
import java.util.*;

public class BooksInCollection { // For each book in the library, we store how many copies are taken and by whom.
    private List<BookPhysicalCopy> copies;
    private int takenCopies;

    public BooksInCollection(List<BookPhysicalCopy> copies){
        this.copies = copies;
        this.takenCopies = 0;
    }

    public List<BookPhysicalCopy> getCopies(){return this.copies;}
    public int getTakenCopies(){return this.takenCopies;}


    public void addNewCopy(BookPhysicalCopy physicalCopy){
        this.copies.add(physicalCopy);
    }
    public void returnCopy(BookPhysicalCopy physicalCopy){
        addNewCopy(physicalCopy);
        this.takenCopies -= 1;
    }

    public void takeCopy(BookPhysicalCopy physicalCopy){
        this.copies.remove(physicalCopy);
        this.takenCopies += 1;
    }
}
