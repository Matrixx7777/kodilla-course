package com.kodilla.testing.library;

import java.util.ArrayList;
import java.util.List;

// .

public class BookLibrary {
    LibraryDatabase libraryDatabase;

    public BookLibrary(LibraryDatabase libraryDatabase) {
        this.libraryDatabase = libraryDatabase;
    }

    public List<Book> listBooksWithCondition(String titleFragment) {
        List<Book> bookList = new ArrayList<Book>();
        if (titleFragment.length() < 3) return bookList;
        List<Book> resultList = libraryDatabase
                .listBooksWithCondition(titleFragment);
        if (resultList.size() > 20) return bookList;
        bookList = resultList;
        return bookList;
    }

    public  List<Book> listBooksInHandsOf(LibraryUser libraryUser) {

        List<Book> listBorrowedBooks = new ArrayList<>();
        List<Book> resultList = libraryDatabase
                .listBooksInHandsOf(libraryUser);
        if (resultList.size() == 0) System.out.println(listBorrowedBooks);

        List<Book> resultList1 = libraryDatabase
                .listBooksInHandsOf(libraryUser);
        if (resultList1.size() == 1) System.out.println(listBorrowedBooks);

        List<Book> resultList2 = libraryDatabase
                .listBooksInHandsOf(libraryUser);
        if (resultList2.size() == 5) System.out.println(listBorrowedBooks);

        return libraryDatabase.listBooksInHandsOf(libraryUser);

    }


}
