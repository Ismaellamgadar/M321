package com.ch.tbz.backend.config;

import com.ch.tbz.backend.entity.Book;
import com.ch.tbz.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) {
        List<Book> books = Arrays.asList(
                new Book("The Great Gatsby", "15.99", "https://upload.wikimedia.org/wikipedia/commons/7/7a/The_Great_Gatsby_Cover_1925_Retouched.jpg", "F. Scott Fitzgerald", "A novel about the American dream and the roaring twenties."),
                new Book("1984", "12.99", "https://images.thalia.media/-/BF2000-2000/d72c48d84f1b40618fd95ea1b1162522/george-orwell-1984-gebundene-ausgabe-george-orwell.jpeg", "George Orwell", "A dystopian novel about totalitarianism and surveillance."),
                new Book("To Kill a Mockingbird", "10.99", "https://images.thalia.media/-/BF2000-2000/c7ab5ae71a58466ab6962b4677133581/to-kill-a-mockingbird-taschenbuch-harper-lee-englisch.jpeg", "Harper Lee", "A novel about racial injustice in the American South."),
                new Book("Pride and Prejudice", "9.99", "https://images.thalia.media/-/BF750-750/c1e05ea82898457c93799260ff2bdc38/pride-and-prejudice-taschenbuch-jane-austen-englisch.jpegg", "Jane Austen", "A classic romance about manners and marriage in Regency England."),
                new Book("The Catcher in the Rye", "14.99", "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1398034300i/5107.jpg", "J.D. Salinger", "A novel about teenage rebellion and alienation."),
                new Book("Moby-Dick", "18.99", "https://m.media-amazon.com/images/M/MV5BZWUyOTgyMzktMjhmNi00NThkLTkxMGEtMGU0ZDEzZWQxNjNlXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg", "Herman Melville", "An epic tale of obsession and revenge."),
                new Book("War and Peace", "20.99", "https://m.media-amazon.com/images/I/71wXZB-VtBL.jpg", "Leo Tolstoy", "A historical novel about the Napoleonic Wars."),
                new Book("Crime and Punishment", "13.99", "https://images.penguinrandomhouse.com/cover/9780451530066", "Fyodor Dostoevsky", "A philosophical novel about guilt and redemption."),
                new Book("The Hobbit", "16.99", "https://images.thalia.media/-/BF2000-2000/4bfa66d9758f4846a217fafa7fc19077/the-hobbit-or-there-and-back-again-taschenbuch-j-r-r-tolkien-englisch.jpeg", "J.R.R. Tolkien", "A fantasy adventure set in Middle-earth."),
                new Book("Brave New World", "11.99", "https://images.thalia.media/-/BF2000-2000/d6adb59c9940430f937a27a1b76ae4a1/brave-new-world-taschenbuch-aldous-huxley-englisch.jpeg", "Aldous Huxley", "A dystopian novel about a technologically controlled society.")
        );

        bookService.saveBooks(books);
    }
}
