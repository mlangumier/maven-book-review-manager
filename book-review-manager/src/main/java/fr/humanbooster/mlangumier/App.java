package fr.humanbooster.mlangumier;

import fr.humanbooster.mlangumier.model.Order;
import fr.humanbooster.mlangumier.model.Review;
import fr.humanbooster.mlangumier.service.BookService;
import fr.humanbooster.mlangumier.service.ReviewService;

import java.util.Comparator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("\n========== BOOK REVIEW MANAGER ==========\n");

        BookService bookService = new BookService();
        ReviewService reviewService = new ReviewService();

        System.out.println(">>> 1. Get books and their average rating (ordered by rating, desc)");
        bookService
                .sortBooksByAverageRatings(bookService.getBooksAndAverageRatings(), Order.DESC)
                .forEach((key, value) -> System.out.printf("- %s (%s) %n", key.getTitle(), value));

        System.out.println("\n>>> 2. Get all reviews written by a given reviewer (ordered by date, desc)");
        reviewService
                .sortReviewsByDate(reviewService.getReviewsByUsername("Alice"), Order.DESC)
                .forEach(review -> System.out.printf(
                        "- Book: \"%s\" | Review: \"%s\" | Rating: %s | Written: %s %n",
                        bookService.getBookById(review.getBookId()).getTitle(), review.getText(), review.getRating(), review.getDate()
                ));

        // TODO - 3. For each genre, get the book with the highest rating (sort by rating, desc)

        // TODO - 4. List reviews for books released before a given date

        // TODO - 5. Book - Get books released before [date]

        // TODO - 6. Reviews - Get reviews for all books (or IDs?) from list

        // TODO - 7. Display the number of books and the average rating of each genre
    }
}