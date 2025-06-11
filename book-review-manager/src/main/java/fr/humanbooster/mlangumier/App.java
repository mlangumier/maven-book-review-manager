package fr.humanbooster.mlangumier;

import fr.humanbooster.mlangumier.data.FakeDatabase;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("\n========== BOOK REVIEW MANAGER ==========\n");

        System.out.println("----- BOOKS\n" + FakeDatabase.getBooks());
        System.out.println("----- REVIEWS\n" + FakeDatabase.getReviews());
    }
}
