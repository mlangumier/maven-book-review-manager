package fr.humanbooster.mlangumier.model;

public enum Genre {
    SCIENCE_FICTION("Science-fiction"),
    FANTASY("Fantasy"),
    POLICIER("Policier");

    public final String label;

    Genre(String label) {
        this.label = label;
    }
}
