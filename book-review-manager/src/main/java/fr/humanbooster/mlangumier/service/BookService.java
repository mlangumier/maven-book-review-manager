package fr.humanbooster.mlangumier.service;

import fr.humanbooster.mlangumier.model.Book;
import fr.humanbooster.mlangumier.model.Order;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookService {
    private List<Book> books;
    protected ReviewService reviewService;

    /**
     * Constructor
     *
     * @param books         A list of books
     * @param reviewService allows access to the methods from the service
     */
    public BookService(List<Book> books, ReviewService reviewService) {
        this.books = books;
        this.reviewService = reviewService;
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
        return books
                .stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }


    /**
     * Get all the books that were released before a given year (exclusive)
     * @param year Year of the release we want to check
     * @return a list of books ordered by release date (descending)
     */
    public List<Book> getBooksReleasedBeforeYear(int year) {
        return books
                .stream()
                .filter(book -> book.getReleaseDate() > year)
                .sorted(Comparator.comparing(Book::getReleaseDate).reversed())
                .toList();
    }

    /**
     * Gets a list of books and their average ratings.
     *
     * @return A list of books and their average rating.
     */
    public Map<Book, Double> getBooksAndAverageRatings() {
        return books
                .stream()
                .collect(Collectors.toMap(
                        book -> book,
                        book -> this.reviewService.getAverageRatingsForBook(book.getId())
                ));
    }

    /**
     * Sorts a list of books by their average rating
     *
     * @param booksAndAverageRatings A list of books and ratings
     * @param order                  The sorting order (ascending or descending)
     * @return The list of books sorted by their average rating, in ascending or descending order.
     */
    public Map<Book, Double> sortBooksByAverageRatings(Map<Book, Double> booksAndAverageRatings, Order order) {
        Order sortOrder = order == null ? Order.DESC : order;

        return booksAndAverageRatings
                .entrySet()
                .stream()
                .sorted(
                        sortOrder == Order.ASC
                                ? Map.Entry.comparingByValue()
                                : Map.Entry.comparingByValue(Comparator.reverseOrder())
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)
                );
    }

    @Override
    public String toString() {
        return "BookService{" +
                "books=" + books +
                '}';
    }
}
