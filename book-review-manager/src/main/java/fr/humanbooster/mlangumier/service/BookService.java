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

    /**
     * Find a book by looking up its id.
     *
     * @param bookId id of the book
     * @return the book found.
     */
    public Book getBookById(Long bookId) {
        return books.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "books=" + books +
                '}';
    }
}
