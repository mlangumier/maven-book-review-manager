package fr.humanbooster.mlangumier.service;

import fr.humanbooster.mlangumier.model.Book;
import fr.humanbooster.mlangumier.model.Genre;
import fr.humanbooster.mlangumier.model.Order;
import fr.humanbooster.mlangumier.model.Review;

import java.util.*;
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
     * Get the book with the highest rating (average from the reviews).
     *
     * @return the highest rated book
     */
    public Book getBookWithHighestAverageRating(List<Book> books) {
        //INFO: If unintended behaviour with `.max().orElseThrow()`, use `.sortedBy().limit()` instead.

        return books
                .stream()
                .max(Comparator.comparing(book -> reviewService.getAverageRatingsForBook(book.getId())))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Get all the books that were released before a given year (exclusive)
     *
     * @param year Year of the release we want to check
     * @return a list of books ordered by release date (descending)
     */
    public List<Book> getBooksReleasedBeforeYear(int year) {
        return this.getBooks()
                .stream()
                .filter(book -> book.getReleaseDate() > year)
                .sorted(Comparator.comparing(Book::getReleaseDate).reversed())
                .toList();
    }

    /**
     * Get the reviews from a list of books and groups them together.
     *
     * @param bookList a list of books for which we want to get the reviews
     * @return a list {map} containing the books {key} and their reviews {values}
     */
    public Map<Book, List<Review>> getBooksAndTheirReviews(List<Book> bookList) {
        return bookList
                .stream()
                .collect(Collectors.toMap(
                        book -> book,
                        book -> reviewService.getReviewsOfBookId(book.getId())
                ));
    }

    /**
     * Gets a list of books and their average ratings.
     *
     * @return A list of books and their average rating.
     */
    public Map<Book, Double> getBooksAndAverageRatings() {
        return this.getBooks()
                .stream()
                .collect(Collectors.toMap(
                        book -> book,
                        book -> this.reviewService.getAverageRatingsForBook(book.getId()) // Use map & reduce?
                ));
    }

    /**
     * Get all the genres and their corresponding books
     *
     * @return a list {HashMap} of genre and the books of this genre
     */
    public Map<Genre, List<Book>> getBooksGroupedByGenre() {
        return this.getBooks()
                .stream()
                .collect(Collectors.groupingBy(Book::getGenre));
    }

    /**
     * Get the books with the highest average review score for each genre.
     * Uses the method {@link #getBooksGroupedByGenre() getBooksGroupedByGenre} to group the books by genre.
     *
     * @return a list {hashMap} of genres and their highest rated book
     */
    public Map<Genre, Book> getBestBookOfEachGenre() {
        //INFO: If a version of this method with parameters if needed, use method Overload.

        return this.getBooksGroupedByGenre().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.getBookWithHighestAverageRating(entry.getValue())
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
