package com.ch.tbz.backend.service;

import com.ch.tbz.backend.entity.Book;
import com.ch.tbz.backend.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void saveBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
