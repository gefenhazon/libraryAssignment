package com.code.java.juniors;

import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a book's title physical copy. For example, a Lee Child's book of "Never Go Back" can have 5
 * different physical copies in library. The actual copies are those who customer reader can get home to read.
 *
 * IMPORTANT: This class is already implemented for you. You should touch it, and you are not allowed changing any APIs
 * of the class. However, you are required to use it in your code.
 */
class BookPhysicalCopy {

    private final UUID bookTitleUuid;                  // The unique ID for specific physical copy.
    private final UUID physicalCopyUuid;                  // The unique ID for specific physical copy.

    public BookPhysicalCopy(UUID bookTitleUuid) {
        this.bookTitleUuid = bookTitleUuid;
        this.physicalCopyUuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return this.physicalCopyUuid;
    }

    public UUID getBookTitleUuid() {
        return this.bookTitleUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookPhysicalCopy that = (BookPhysicalCopy) o;
        return Objects.equals(bookTitleUuid, that.bookTitleUuid) && Objects.equals(physicalCopyUuid, that.physicalCopyUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitleUuid, physicalCopyUuid);
    }
}
