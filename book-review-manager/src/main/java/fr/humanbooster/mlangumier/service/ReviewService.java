package fr.humanbooster.mlangumier.service;

import fr.humanbooster.mlangumier.data.FakeDatabase;
import fr.humanbooster.mlangumier.model.Order;
import fr.humanbooster.mlangumier.model.Review;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewService {
    List<Review> reviews;

    /**
     * Constructor
     */
    public ReviewService() {
        this.reviews = FakeDatabase.getReviews();
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
        return reviews
                .stream()
                .filter(review -> review.getUsername().equals(username))
                .toList();
    }

    /**
     * Sort a list of reviews by their publication date
     *
     * @param order The sorting order (ascending or descending)
     * @return The list of reviews sorted by their publication date, in ascending or descending order.
     */
    public List<Review> sortReviewsByDate(List<Review> reviews, Order order) {
        return reviews
                .stream()
                .sorted(
                        order == Order.ASC
                                ? Comparator.comparing(Review::getDate)
                                : Comparator.comparing(Review::getDate).reversed()
                )
                .toList();
    }

    /**
     * Gets the average score from the reviews written about a book
     *
     * @param bookId the id of the book we want the rating of
     * @return the average rating of the book
     */
    public Double getAverageRatingsForBook(Long bookId) {
        return reviews
                .stream()
                .filter(review -> review.getBookId().equals(bookId))
                .collect(Collectors.averagingDouble(Review::getRating));
    }

    @Override
    public String toString() {
        return "ReviewService{" +
                "reviews=" + reviews +
                '}';
    }
}
