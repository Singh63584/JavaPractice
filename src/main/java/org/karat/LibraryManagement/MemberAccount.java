package org.karat.LibraryManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemberAccount {
    /**
     * Manages borrow records and provides analytics.
     */
    ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();

    void addBorrowRecord(BorrowRecord record) {
        borrowRecords.add(record);
    }

    int getTotalBorrowedBooks() {
        return borrowRecords.size();
    }

    int getUniqueBooksBorrowed() {
        return (int) borrowRecords.stream().map(r -> r.getBook()).distinct().count();
    }

    double getAverageBorrowsPerBook() {
        /**
         * Returns average borrow count per unique book.
         * BUG: This method has a bug - fix it!
         */
        return (double) getTotalBorrowedBooks() / getUniqueBooksBorrowed();
    }

    public Object[] getMostBorrowedGenre() {
        String resultGenre = null;
        int maxCount = 0;

        if (borrowRecords.isEmpty()) {
            return null;
        }

        Map<String, Integer> genreCount = new HashMap<>();
        for (BorrowRecord record : borrowRecords) {
            String genre = record.getBook().getGenre();
            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : genreCount.entrySet()) {
            String genre = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount ||
                    (count == maxCount && genre.compareTo(resultGenre) < 0)) {
                maxCount = count;
                resultGenre = genre;
            }
        }
        return new Object[]{resultGenre, maxCount};
    }
}
