package fr.humanbooster.mlangumier;

import fr.humanbooster.mlangumier.model.Book;
import fr.humanbooster.mlangumier.model.Genre;
import fr.humanbooster.mlangumier.model.Review;
import fr.humanbooster.mlangumier.service.BookService;
import fr.humanbooster.mlangumier.service.ReviewService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("\n========== BOOK REVIEW MANAGER ==========\n");

        BookService bookService = new BookService();
        ReviewService reviewService = new ReviewService();

        // TODO - List books (sort by average rating, desc)

        // 1. Calculate average ratings per bookId
        Map<Long, Double> avgRatingByBookId = reviewService.getReviews()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Review::getBookId, // Replace by method: `Book getBookById(Long id){}` // bookService.getBooks().stream().filter().toList().get(0)
                                Collectors.averagingDouble(Review::getRating)
                        ));

        // 2. Get books by bookID and replace in map // Not needed after rework
        Map<Book, Double> avgRatingByBook = avgRatingByBookId.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                entry -> bookService.getBooks().stream().filter(book -> book.getId().equals(entry.getKey())).toList().get(0),
                                Map.Entry::getValue
                        ));

        // 3. Return the ordered list (param: ORDER.ASC / ORDER.DESC)
        Map<Book, Double> sortedAvgRatingByBook = avgRatingByBook.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).toList();

        // TODO - List reviews from a specific author (sort by date, desc)
        List<Review> reviewFromAuthor = reviewService.getReviews().stream().filter(review -> review.getUsername().equals("Alice")).toList();
        System.out.println("\n>> Reviews by Alice: \n" + reviewFromAuthor);

        // TODO - For each genre, get the book with the highest rating (sort by rating, desc)

        // TODO - List reviews for books released before a given date

        // TODO - Book - Get books released before [date]

        // TODO - Reviews - Get reviews for all books (or IDs?) from list

        // TODO - Display the number of books and the average rating of each genre
    }
}
