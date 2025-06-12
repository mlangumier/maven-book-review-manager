# BOOK REVIEW MANAGER

Author: Mathieu LANGUMIER

## Features

### MVP
- [x] Maven project
- [x] Use a `FakeDatabase.java` to generate fake data
- [x] Books: id, title, author, releaseDate, genre
- [x] Critic: username, bookId, rating, review, date
- [ ] Stream API
  - [x] List books (sort by rating, desc)
  - [x] List reviews from a specific author (sort by date, desc)
  - [ ] For each genre, get the book with the highest rating (sort by rating, desc)
  - [ ] List reviews from books released before a given date
  - [ ] Display the number of books and the average rating of each genre

### Bonuses
- [ ] CLI: to add books or reviews
- [ ] Export data to JSON or CSV (`Gson` or `Jackson` libraries)
- [ ] Unit testing
- [ ] Other features...