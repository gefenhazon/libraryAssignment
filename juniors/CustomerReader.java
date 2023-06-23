package com.code.java.juniors;

import com.code.java.juniors.helpers.Helpers;

import java.util.Objects;
import java.util.UUID;
import java.util.*;


/**
 * This class represents a reader user. The one who is a customer of library.
 */
public class CustomerReader {

    private final String readingCardId;
    private final String firstName;
    private final String lastName;
    private final long socialIdNumber;

    public CustomerReader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.readingCardId = UUID.randomUUID().toString();
        this.socialIdNumber = Helpers.getRandomNumber(1, Helpers.MAX_RANGE);
    }

    /**
     * This function notifies the user of a ready book, and should be called if and only if he is registered on
     * a waiting list.
     */
    public void notifyUserBookIsReady(String bookTitle) {
    }

    @Override
    public String toString() {
        return "CustomerReader{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", socialIdNumber=" + socialIdNumber +
                ", readingCardId=" + readingCardId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerReader that = (CustomerReader) o;
        return socialIdNumber == that.socialIdNumber
                && Objects.equals(readingCardId, that.readingCardId)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, socialIdNumber, readingCardId);
    }
}
