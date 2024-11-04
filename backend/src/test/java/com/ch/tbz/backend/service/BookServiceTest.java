package com.ch.tbz.backend.service;

import com.ch.tbz.backend.entity.Book;
import com.ch.tbz.backend.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveBooks() {
        Book book1 = new Book("Book Title 1", "19.99", "image1.png", "Author 1", "Description 1");
        Book book2 = new Book("Book Title 2", "29.99", "image2.png", "Author 2", "Description 2");
        List<Book> books = Arrays.asList(book1, book2);

        bookService.saveBooks(books);

        verify(bookRepository, times(1)).saveAll(books);
    }

    @Test
    public void testFindAllBooks() {
        Book book1 = new Book("Book Title 1", "19.99", "image1.png", "Author 1", "Description 1");
        Book book2 = new Book("Book Title 2", "29.99", "image2.png", "Author 2", "Description 2");
        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.findAllBooks();

        assertEquals(2, result.size());
        assertEquals(books, result);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testFindBookById_found() {
        Long bookId = 1L;
        Book book = new Book("Book Title", "19.99", "image.png", "Author", "Description");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book result = bookService.findBookById(bookId);

        assertNotNull(result);
        assertEquals(book, result);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    public void testFindBookById_notFound() {
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Book result = bookService.findBookById(bookId);

        assertNull(result);
        verify(bookRepository, times(1)).findById(bookId);
    }
}
