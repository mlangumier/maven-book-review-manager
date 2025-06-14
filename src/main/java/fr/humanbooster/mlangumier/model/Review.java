package fr.humanbooster.mlangumier.model;

import java.time.LocalDate;

public class Review {
    private Long bookId;
    private String username;
    private double rating;
    private String text;
    private LocalDate date;

    /**
     * Constructor - Create a new review
     *
     * @param bookId ID of the book this review is reviewing
     * @param username Username of the person writing this review
     * @param rating Rating given by the user for this book {1-5}
     * @param text Text of the review
     * @param date Date at which the review was written
     */
    public Review(Long bookId, String username, double rating, String text, LocalDate date) {
        this.bookId = bookId;
        this.username = username;
        this.rating = rating;
        this.text = text;
        this.date = date;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Review{" +
                "bookId=" + bookId +
                ", username='" + username + '\'' +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
