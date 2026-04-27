package org.karat.LibraryManagement;

import org.junit.Assert;

public class libraryManagementProb {
    public static void main(String[] args) {
        testBorrowRecord();
        testMemberAccount();
        testGetMostBorrowedGenre();
    }

    public static void testBorrowRecord() {
        System.out.println("Running testBorrowRecord");
        Book book = new Book("The Hobbit", "Fantasy");
        BorrowRecord record = new BorrowRecord(book);

        Assert.assertEquals("Fantasy", record.getBook().getGenre());
        System.out.println("Running testBorrowRecord : " + record.getBook().getGenre());
    }

    public static void testMemberAccount() {
        System.out.println("Running testMemberAccount");
        MemberAccount account = new MemberAccount();

        Assert.assertEquals(0, account.getTotalBorrowedBooks());

        Book b1 = new Book("The Hobbit", "Fantasy");
        Book b2 = new Book("1984", "Science Fiction");
        Book b3 = new Book("Dune", "Science Fiction");

        account.addBorrowRecord(new BorrowRecord(b1));
        account.addBorrowRecord(new BorrowRecord(b2));
        account.addBorrowRecord(new BorrowRecord(b3));
        account.addBorrowRecord(new BorrowRecord(b1));

        Assert.assertEquals(4, account.getTotalBorrowedBooks());
        Assert.assertEquals(3, account.getUniqueBooksBorrowed());
        Assert.assertEquals(1.33, account.getAverageBorrowsPerBook(), 0.01);
    }

    public static void testGetMostBorrowedGenre() {
        System.out.println("Running testGetMostBorrowedGenre");
        MemberAccount account = new MemberAccount();

        Assert.assertNull(account.getMostBorrowedGenre());

        Book b1 = new Book("The Hobbit", "Fantasy");
        Book b2 = new Book("1984", "Science Fiction");
        Book b3 = new Book("Dune", "Science Fiction");
        Book b4 = new Book("Harry Potter", "Fantasy");
        Book b5 = new Book("Foundation", "Science Fiction");
        Book b6 = new Book("LOTR", "Fantasy");
        Book b7 = new Book("LOTR2",null);
        Book b8 = new Book("Harry Potter2", null);
        Book b9 = new Book("Harry Potter3", "Science Fiction");

        account.addBorrowRecord(new BorrowRecord(b1));
        account.addBorrowRecord(new BorrowRecord(b2));
        account.addBorrowRecord(new BorrowRecord(b3));
        account.addBorrowRecord(new BorrowRecord(b4));
        account.addBorrowRecord(new BorrowRecord(b5));
        account.addBorrowRecord(new BorrowRecord(b6));
        account.addBorrowRecord(new BorrowRecord(b7));
        account.addBorrowRecord(new BorrowRecord(b8));
        account.addBorrowRecord(new BorrowRecord(b9));

        Object[] result = account.getMostBorrowedGenre();
        //Assert.assertEquals("Science Fiction", result[0]);
        //Assert.assertEquals(3, result[1]);
        System.out.println("Genre Type : " + result[0] +" Count genre : "+ result[1]);
    }
}
