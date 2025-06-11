package fr.humanbooster.mlangumier.service;

import fr.humanbooster.mlangumier.data.FakeDatabase;
import fr.humanbooster.mlangumier.model.Review;

import java.util.List;

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

    @Override
    public String toString() {
        return "ReviewService{" +
                "reviews=" + reviews +
                '}';
    }
}
