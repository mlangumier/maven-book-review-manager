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
                .forEach((book, avgRating) -> System.out.printf("- %s (%s) %n", book.getTitle(), avgRating));

        //----------
        System.out.println("\n>>> 2. Display all reviews written by a given reviewer (ordered by date, desc)");

        List<Review> reviewsFromUser = reviewService.getReviewsByUsername("Alice");
        List<Review> reviewFromUserOrderedByDate = reviewService.sortReviewsByDate(reviewsFromUser, Order.DESC);

        reviewFromUserOrderedByDate
                .forEach(review -> System.out.printf(
                        "- Book: \"%s\" | Review: \"%s\" | Rating: %s | Date: %s %n",
                        bookService.getBookById(review.getBookId()).getTitle(), review.getText(), review.getRating(), review.getDate().format(dtf)
                ));

        //----------
        System.out.println("\n>>> 3. Display the book with the highest rating for each genre (ordered by rating, desc)");

        Map<Genre, Book> bestBookByGenre = bookService.getBestBookOfEachGenre();

        bestBookByGenre.forEach((genre, books) -> System.out.printf("- %s: %s %n", genre.label, books.getTitle()));

        //----------
        System.out.println("\n>>> 4. Display reviews for books released before a given date");

        List<Book> booksReleasedBeforeYear = bookService.getBooksReleasedBeforeYear(1950);
        Map<Book, List<Review>> reviewsFromBooks = bookService.getBooksAndTheirReviews(booksReleasedBeforeYear);

        reviewsFromBooks
                .forEach((book, reviews) -> System.out.printf("- Reviews for \"%s\": %n%s %n", book.getTitle(), reviews.toString()));

        //----------
        System.out.println("\n>>> 5. Display the number of books and average score of each genre");

        Map<Genre, List<Book>> booksByGenre = bookService.getBooksGroupedByGenre();

        booksByGenre
                .forEach((genre, books) -> System.out.printf(
                        "- %s | Avg. Rating: %s | Nbr of books: %s %n",
                        genre.label,
                        books
                                .stream()
                                .mapToDouble(book -> reviewService.getAverageRatingsForBook(book.getId()))
                                .average()
                                .orElseThrow(NoSuchElementException::new),
                        books.size()
                ));

        System.out.println("\n========== APP CLOSED ==========\n");
    }
}