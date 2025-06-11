package fr.humanbooster.mlangumier.service;

import fr.humanbooster.mlangumier.data.FakeDatabase;
import fr.humanbooster.mlangumier.model.Book;

import java.util.List;

public class BookService {
    List<Book> books;

    /**
     * Constructor
     */
    public BookService() {
        this.books = FakeDatabase.getBooks();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookService{" +
                "books=" + books +
                '}';
    }
}
