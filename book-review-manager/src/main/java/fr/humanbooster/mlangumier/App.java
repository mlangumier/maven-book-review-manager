package fr.humanbooster.mlangumier;

import fr.humanbooster.mlangumier.service.BookService;
import fr.humanbooster.mlangumier.service.ReviewService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("\n========== BOOK REVIEW MANAGER ==========\n");

        BookService bookService = new BookService();
        ReviewService reviewService = new ReviewService();

        System.out.println("----- BOOKS\n" + bookService.getBooks());
        System.out.println("----- REVIEWS\n" + reviewService.getReviews());
    }
}
