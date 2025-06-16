package fr.humanbooster.mlangumier.service;

import fr.humanbooster.mlangumier.model.Book;
import fr.humanbooster.mlangumier.model.Review;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ReviewService {
    List<Review> reviews;

    /**
     * Constructor
     */
    public ReviewService(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Get the reviews written by a user
     *
     * @param username the name of the user we want the reviews from
     * @return a list of reviews written by the user
     */
    public List<Review> getReviewsByUsername(String username) {
        return this.getReviews()
                .stream()
                .filter(review -> review.getUsername().equals(username))
                .sorted(Comparator.comparing(Review::getDate).reversed())
                .toList();
    }

    /**
     * Get reviews written about a given book
     *
     * @param bookId id of the book that the reviews are about
     * @return a list of reviews sorted by the date they were written (descending)
     */
    public List<Review> getReviewsOfBookId(Long bookId) {
        return this.getReviews()
                .stream()
                .filter(review -> review.getBookId().equals(bookId))
                .sorted(Comparator.comparing(Review::getDate).reversed())
                .toList();
    }

    /**
     * Gets the average score from the reviews written about a book
     *
     * @param bookId the id of the book we want the rating of
     * @return the average rating of the book
     */
    public Double getAverageRatingsForBook(Long bookId) {
        return this.getReviews()
                .stream()
                .filter(review -> review.getBookId().equals(bookId))
                .collect(Collectors.averagingDouble(Review::getRating));
    }

    /**
     * Gets the average overall score from the reviews written about multiple books
     *
     * @param books A list of book for which we want the ratings
     * @return the average rating for this list of books
     */
    public Double getAverageRatingsForBooks(List<Book> books) {
        return books
                .stream()
                .mapToDouble(book -> this.getAverageRatingsForBook(book.getId()))
                .average()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public String toString() {
        return "ReviewService{" +
                "reviews=" + reviews +
                '}';
    }
}
