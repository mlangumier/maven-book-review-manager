package fr.humanbooster.mlangumier;

import fr.humanbooster.mlangumier.data.FakeDatabase;
import fr.humanbooster.mlangumier.model.Book;
import fr.humanbooster.mlangumier.model.Genre;
import fr.humanbooster.mlangumier.model.Order;
import fr.humanbooster.mlangumier.model.Review;
import fr.humanbooster.mlangumier.service.BookService;
import fr.humanbooster.mlangumier.service.ReviewService;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class App {
    static DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.FRANCE);
    static ReviewService reviewService = new ReviewService(FakeDatabase.getReviews());
    static BookService bookService = new BookService(FakeDatabase.getBooks(), reviewService);

    public static void main(String[] args) {
        System.out.println("\n========== BOOK REVIEW MANAGER ==========\n");

        //----------
        displayBooksByAverageRating();

        //----------
        displayReviewsByUser();

        //----------
        displayBestBookByGenre();

        //----------
        displayReviewsFromBooksReleasedBeforeDate();

        //----------
        displayNbrBooksAndAverageRatingForGenres();
    }

    public static void displayBooksByAverageRating() {
        System.out.println(">>> 1. Display all books and their average rating (ordered by rating, desc)");

        Map<Book, Double> booksAndAverageRatings = bookService.getBooksAndAverageRatings();
        Map<Book, Double> bookSortedByAverageRatingDesc = bookService.sortBooksByAverageRatings(booksAndAverageRatings, Order.DESC);

        bookSortedByAverageRatingDesc
                .forEach((book, avgRating) -> System.out.printf(
                        "- %s (%s) %n",
                        book.getTitle(), avgRating
                ));
    }

    public static void displayReviewsByUser() {
        System.out.println("\n>>> 2. Display all reviews written by a given reviewer (\"Alice\") (ordered by date, desc)");

        reviewService.getReviewsByUsername("Alice")
                .forEach(review -> System.out.printf(
                        "- Book: \"%s\" | Review: \"%s\" | Rating: %s | Date: %s %n",
                        bookService.getBookById(review.getBookId()).getTitle(), review.getText(), review.getRating(), review.getDate().format(dtf)
                ));
    }

    public static void displayBestBookByGenre() {
        System.out.println("\n>>> 3. Display the book with the highest rating for each genre (ordered by rating, desc)");

        bookService.getBestBookOfEachGenre()
                .forEach((genre, books) -> System.out.printf(
                        "- %s: %s %n",
                        genre.label, books.getTitle()
                ));
    }

    public static void displayReviewsFromBooksReleasedBeforeDate() {
        System.out.println("\n>>> 4. Display reviews for books released before a given date (1950)");

        List<Book> booksReleasedBeforeYear = bookService.getBooksReleasedBeforeYear(1950);
        Map<Book, List<Review>> reviewsFromBooks = bookService.getBooksAndTheirReviews(booksReleasedBeforeYear);

        reviewsFromBooks.forEach((book, reviews) -> {
            System.out.printf(
                    "Reviews for \"%s\" (%s): %n",
                    book.getTitle(), book.getReleaseDate()
            );
            reviews.forEach(review -> System.out.printf(
                    "- \"%s\" | Author: %s | Rating: %s | Date: %s %n",
                    review.getText(), review.getUsername(), review.getRating(), review.getDate().format(dtf)
            ));
        });
    }

    public static void displayNbrBooksAndAverageRatingForGenres() {
        System.out.println("\n>>> 5. Display the number of books and average score of each genre");

        Map<Genre, List<Book>> booksByGenre = bookService.getBooksGroupedByGenre();

        booksByGenre
                .forEach((genre, books) -> System.out.printf(
                        "- %s | Avg. Rating: %s | Nbr of books: %s %n",
                        genre.label,
                        reviewService.getAverageRatingsForBooks(books),
                        books.size()
                ));
    }
}