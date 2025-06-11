package fr.humanbooster.mlangumier;

import fr.humanbooster.mlangumier.model.Book;
import fr.humanbooster.mlangumier.model.Review;
import fr.humanbooster.mlangumier.service.BookService;
import fr.humanbooster.mlangumier.service.ReviewService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("\n========== BOOK REVIEW MANAGER ==========\n");

        BookService bookService = new BookService();
        ReviewService reviewService = new ReviewService();

        // List books (sort by average rating, desc)
        // 1. Review - Get average-rating for each bookId
        // 2. Review - map & display book - rating

        // List reviews from a specific author (sort by date, desc)
        List<Review> reviewFromAuthor = reviewService.getReviews().stream().filter(review -> review.getUsername().equals("Alice")).toList();
        System.out.println("\n>> Reviews by Alice: \n" +  reviewFromAuthor);

        // For each genre, get the book with the highest rating (sort by rating, desc)

        // List reviews from books released before a given date
        // Book - Get books released before [date]
        // Reviews - Get reviews for all books (or IDs?) from list

        // Display the number of books and the average rating of each genre
    }
}
