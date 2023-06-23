package com.code.java.juniors;

import com.code.java.juniors.helpers.Enums.Genre;
import com.code.java.juniors.helpers.Enums.Languages;

import java.util.List;

// You are not allowed to touch that file.
public class Main {

    public static void main(String[] args) throws Exception {

        createAndBooks();

        // Test adding mechanism

        assertLongNumber(
                bookManagementSystem.getAllAvailableCopies(atlasShrugged).size(), 3,
                "TEST 1",
                "Adding 'Atlas Shrugged' to library collection should be OK, and it should have three physical copies."
        );

        bookManagementSystem.addNewBookCopiesToCollection(atlasShrugged, 2);

        assertLongNumber(
                bookManagementSystem.getAllAvailableCopies(atlasShrugged).size(), 5,
                "TEST 2",
                "Adding two more physical copies of 'Atlas Shrugged' should make total of 5."
        );



        // Test remove available book from the collection

        assertBoolean(
                bookManagementSystem.removeBookFromCollection(atlasShrugged), true,
                "TEST 3",
                "Removing 'Atlas Shrugged' from library collection should be possible, it is not borrowed yet and all of the copies available."
        );

        assertLongNumber(
                bookManagementSystem.getAllAvailableCopies(atlasShrugged).size(), 0,
                "TEST 4",
                "After removing 'Atlas Shrugged', the number of available copies should be total of zero."
        );




        // Test the borrowing mechanism.

        List<BookPhysicalCopy> listOfAvailableCopies_NothingToLose = bookManagementSystem.getAllAvailableCopies(nothingToLose);
        BookPhysicalCopy copyToRicky = listOfAvailableCopies_NothingToLose.get(0);
        bookManagementSystem.lendBookToReader(copyToRicky, ricky);

        long expectedNumberOfCopies_afterLendingToRicky = listOfAvailableCopies_NothingToLose.size() - 1;
        long numberOfCopiesAvailable_afterLendingToRicky = bookManagementSystem.getAllAvailableCopies(nothingToLose).size();
        assertLongNumber(
                numberOfCopiesAvailable_afterLendingToRicky, expectedNumberOfCopies_afterLendingToRicky,
                "TEST 5",
                "Lending 'Nothing To Lose' to Ricky, should reduce the total number of physical copies to "
                        + expectedNumberOfCopies_afterLendingToRicky + ", but it " + numberOfCopiesAvailable_afterLendingToRicky
        );

        assertBoolean(
                bookManagementSystem.getAllAvailableCopies(nothingToLose).stream().noneMatch(bookPhysicalCopy -> bookPhysicalCopy.equals(copyToRicky)), true,
                "TEST 6",
                "After lending a specific physical copy to Ricky, the copy should not be available on available list anymore. However, it is still there."
        );

        assertBoolean(
                bookManagementSystem.lendBookToReader(copyToRicky, avi), false,
                "TEST 7",
                "Avi should not be able to borrow the physical copy Ricky borrowed before."
        );

        bookManagementSystem.addNewBookCopiesToCollection(nothingToLose, 1);
        List<BookPhysicalCopy> listOfAvailableCopies_afterAddingAnotherCopy = bookManagementSystem.getAllAvailableCopies(nothingToLose);

        assertLongNumber(
                listOfAvailableCopies_afterAddingAnotherCopy.size(), 2,
                "TEST 8",
                "Was unable to add a new copy to an existing title that that one of it copies is borrowed by customer reader."
        );

        BookPhysicalCopy copyToAvi = listOfAvailableCopies_afterAddingAnotherCopy.get(0);
        BookPhysicalCopy copyToElad = listOfAvailableCopies_afterAddingAnotherCopy.get(1);

        bookManagementSystem.lendBookToReader(copyToAvi, avi);
        bookManagementSystem.lendBookToReader(copyToElad, elad);

        long expectedNumberOfCopies_afterBorrowingAllBooks = bookManagementSystem.getAllAvailableCopies(nothingToLose).size();
        assertLongNumber(
                expectedNumberOfCopies_afterBorrowingAllBooks, 0,
                "TEST 9",
                "After borrowing all three physical copies of 'Nothing To Lose' by Ricky, Avi, and Elad - the total number of available copies should be zero. However, it is " + expectedNumberOfCopies_afterBorrowingAllBooks + "."
        );

        bookManagementSystem.returnBookFromReader(copyToAvi);

        assertBoolean(
                bookManagementSystem.getAllAvailableCopies(nothingToLose).get(0).equals(copyToAvi), true,
                "TEST 10",
                "Returning physical copy back should make it available on available list."
        );



        // Removing title that not all of its copies are available

        List<BookPhysicalCopy> listOfCopies_JourneyToCentreOfTheEarth = bookManagementSystem.getAllAvailableCopies(journeyToCentreOfTheEarth);
        BookPhysicalCopy journeyToCentreToBorrow = listOfCopies_JourneyToCentreOfTheEarth.get(0);
        bookManagementSystem.lendBookToReader(journeyToCentreToBorrow, dor);

        assertBoolean(
                bookManagementSystem.removeBookFromCollection(journeyToCentreOfTheEarth), false,
                "TEST 11",
                "Dor holds one copy of 'A Journey To The Centre of The Earth', therefore the title should be removable. However, it is."
        );

        bookManagementSystem.returnBookFromReader(journeyToCentreToBorrow);

        assertBoolean(
                bookManagementSystem.removeBookFromCollection(journeyToCentreOfTheEarth), true,
                "TEST 12",
                "After all of the copies returned back to library collection, the 'A Journey To The Centre of The Earth' should be removable. But it isn't."
        );


        // Borrow book that was removed from the collection

        assertBoolean(
                bookManagementSystem.lendBookToReader(journeyToCentreToBorrow, dor), false,
                "TEST 13",
                "Book 'A Journey To The Centre of The Earth' is not on library collection anymore, should be available for borrowing. However, it is."
        );

        // Testing that one customer read cannot have more than 3 copies at the same time.

        bookManagementSystem.lendBookToReader(bookManagementSystem.getAllAvailableCopies(theFountainhead).get(0), amir);
        bookManagementSystem.lendBookToReader(bookManagementSystem.getAllAvailableCopies(warAndPeace).get(0), amir);
        bookManagementSystem.lendBookToReader(bookManagementSystem.getAllAvailableCopies(studyInScarlet).get(0), amir);

        assertBoolean(
                bookManagementSystem.lendBookToReader(bookManagementSystem.getAllAvailableCopies(critiqueOfPureReason).get(0), amir), false,
                "TEST 14",
                "Amir already has copies of of three titles, he cannot have the fourth until he returns at least one."
        );

        // Test that same person cannot have the same title more than once
        bookManagementSystem.lendBookToReader(bookManagementSystem.getAllAvailableCopies(studyInScarlet).get(0), yossi);
        assertBoolean(
                bookManagementSystem.lendBookToReader(bookManagementSystem.getAllAvailableCopies(studyInScarlet).get(0), yossi), false,
                "TEST 15",
                "Yossi already borrowed a copy 'A Study in Scarlet', he cannot have the two copies of the same title."
        );


    }

    private static void assertBoolean(boolean currentValue, boolean expectedValue, String testName, String testMsg) {
        if(currentValue == expectedValue) {
            System.out.println(testName + " = PASSED");
        } else {
            System.out.println(testName + " = FAILED: " + testMsg);
        }
    }

    private static void assertLongNumber(long currentValue, long expectedValue, String testName, String testMsg) {
        if(currentValue == expectedValue) {
            System.out.println(testName + " = PASSED");
        } else {
            System.out.println(testName + " = FAILED: " + testMsg);
        }
    }

    public static void createAndBooks() {

        atlasShrugged = bookManagementSystem.addNewBookToCollection(
                "Atlas Shrugged",
                "Ayn Rand",
                Languages.ENGLISH,
                Genre.MYSTERY,
                "Random House",
                3
        );

        theFountainhead = bookManagementSystem.addNewBookToCollection(
                "The Fountainhead",
                "Ayn Rand",
                Languages.ENGLISH,
                Genre.ROMANCE,
                "Random House",
                5
        );

        journeyToCentreOfTheEarth = bookManagementSystem.addNewBookToCollection(
                "A Journey To The Centre of The Earth",
                "Joul Verne",
                Languages.FRENCH,
                Genre.SCI_FI,
                "Pierre-Jules Hetzel",
                10
        );

        nothingToLose = bookManagementSystem.addNewBookToCollection(
                "Nothing to Lose",
                "Lee Child",
                Languages.ENGLISH,
                Genre.THRILLER,
                "Bantman Press",
                2
        );

        warAndPeace = bookManagementSystem.addNewBookToCollection(
                "War And Peace",
                "Leo Tolstoy",
                Languages.RUSSIAN,
                Genre.NOVEL,
                "The Russian Messenger",
                4
        );

        studyInScarlet = bookManagementSystem.addNewBookToCollection(
                "A Study in Scarlet",
                "Arthur Conan Doyle",
                Languages.ENGLISH,
                Genre.DETECTIVE,
                "Ward Lick & Co",
                10
        );

        critiqueOfPureReason = bookManagementSystem.addNewBookToCollection(
                "Critique of Pure Reason",
                "Immanuel Kant",
                Languages.GERMAN,
                Genre.PHILOSOPHY,
                "Not Known",
                1
        );
    }

    public static CustomerReader avi = new CustomerReader("Avi", "Kanukaev");
    public static CustomerReader yossi = new CustomerReader("Yossi", "Saadi");
    public static CustomerReader ricky = new CustomerReader("Ricky", "Benkovich");
    public static CustomerReader elad = new CustomerReader("Elad", "Vengrover");
    public static CustomerReader amir = new CustomerReader("Amir", "Zaushnizer");
    public static CustomerReader dor = new CustomerReader("Dor", "Eitan");

    public static Book theFountainhead;
    public static Book atlasShrugged;
    public static Book journeyToCentreOfTheEarth;
    public static Book nothingToLose;
    public static Book warAndPeace;
    public static Book studyInScarlet;
    public static Book critiqueOfPureReason;


    public static BooksManagementSystem bookManagementSystem = BooksManagementSystem.getInstance();
}
