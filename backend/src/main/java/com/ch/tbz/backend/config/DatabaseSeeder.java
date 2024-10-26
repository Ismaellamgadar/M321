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
                new Book("The Great Gatsby", "15.99", "https://example.com/gatsby.jpg", "F. Scott Fitzgerald", "A novel about the American dream and the roaring twenties."),
                new Book("1984", "12.99", "https://example.com/1984.jpg", "George Orwell", "A dystopian novel about totalitarianism and surveillance."),
                new Book("To Kill a Mockingbird", "10.99", "https://example.com/mockingbird.jpg", "Harper Lee", "A novel about racial injustice in the American South."),
                new Book("Pride and Prejudice", "9.99", "https://example.com/pride-prejudice.jpg", "Jane Austen", "A classic romance about manners and marriage in Regency England."),
                new Book("The Catcher in the Rye", "14.99", "https://example.com/catcher.jpg", "J.D. Salinger", "A novel about teenage rebellion and alienation."),
                new Book("Moby-Dick", "18.99", "https://example.com/moby-dick.jpg", "Herman Melville", "An epic tale of obsession and revenge."),
                new Book("War and Peace", "20.99", "https://example.com/war-peace.jpg", "Leo Tolstoy", "A historical novel about the Napoleonic Wars."),
                new Book("Crime and Punishment", "13.99", "https://example.com/crime.jpg", "Fyodor Dostoevsky", "A philosophical novel about guilt and redemption."),
                new Book("The Hobbit", "16.99", "https://example.com/hobbit.jpg", "J.R.R. Tolkien", "A fantasy adventure set in Middle-earth."),
                new Book("Brave New World", "11.99", "https://example.com/brave-new-world.jpg", "Aldous Huxley", "A dystopian novel about a technologically controlled society.")
        );

        bookService.saveBooks(books);
    }
}
