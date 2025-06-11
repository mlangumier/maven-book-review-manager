package fr.humanbooster.mlangumier.model;

public class Book {
    private Long id = 0L;
    private String title;
    private String author;
    private int releaseDate;
    private Genre genre;    // Bonus: transform into -> private Set<Genre> genres;

    /**
     * Constructor - Create a new book
     *
     * @param title Title of the book
     * @param author Name of the author of the book
     * @param releaseDate Date at which the book was released
     * @param genre Genre of the book
     */
    public Book(String title, String author, int releaseDate, Genre genre) {
        this.id++;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                '}';
    }
}
