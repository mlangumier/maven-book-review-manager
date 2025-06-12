package fr.humanbooster.mlangumier;

import fr.humanbooster.mlangumier.data.FakeDatabase;
import fr.humanbooster.mlangumier.model.Book;
import fr.humanbooster.mlangumier.model.Order;
import fr.humanbooster.mlangumier.model.Review;
import fr.humanbooster.mlangumier.service.BookService;
import fr.humanbooster.mlangumier.service.ReviewService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("\n========== BOOK REVIEW MANAGER ==========\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.FRANCE); //TODO: setup in /utils/DateTimeFormatter.java

        ReviewService reviewService = new ReviewService();
        BookService bookService = new BookService(FakeDatabase.getBooks(), reviewService);

        //----------
        System.out.println(">>> 1. Display all books and their average rating (ordered by rating, desc)");
        Map<Book, Double> booksAndAverageRatings = bookService.getBooksAndAverageRatings();
        Map<Book, Double> bookSortedByAverageRatingDesc = bookService.sortBooksByAverageRatings(booksAndAverageRatings, Order.DESC);
        bookSortedByAverageRatingDesc
                .forEach((key, value) -> System.out.printf("- %s (%s) %n", key.getTitle(), value));

        //----------
        System.out.println("\n>>> 2. Display all reviews written by a given reviewer (ordered by date, desc)");
        List<Review> reviewsFromUser = reviewService.getReviewsByUsername("Alice");
        reviewService
                .sortReviewsByDate(reviewsFromUser, Order.DESC)
                .forEach(review -> System.out.printf(
                        "- Book: \"%s\" | Review: \"%s\" | Rating: %s | Written: %s %n",
                        bookService.getBookById(review.getBookId()).getTitle(), review.getText(), review.getRating(), review.getDate().format(dtf)
                ));

        //----------
        //TODO - 3. For each genre, get the book with the highest rating (sort by rating, desc)
        // System.out.println("\n>>> 3. Display the book with the highest rating for each genre (ordered by rating, desc)");

        //----------
        System.out.println("\n>>> 4. Display reviews for books released before a given date");
        //TODO - move to ReviewService
        Map<Book, List<Review>> reviewsFromBooks = bookService.getBooksReleasedBeforeYear(1950)
                .stream()
                .collect(Collectors.toMap(
                        book -> book,
                        book -> reviewService.getReviewsOfBookId(book.getId())
                ));
        //TODO - DISPLAY:
        reviewsFromBooks.forEach((book, reviews) -> System.out.printf("--- %s %n%s%n", book, reviews));

        //----------
        //TODO - 5. Display the number of books and the average rating of each genre
        // System.out.println("\n>>> 7. Display the number of books and average score of each genre");
    }
}