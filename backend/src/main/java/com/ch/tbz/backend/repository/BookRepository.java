package com.ch.tbz.backend.repository;

import com.ch.tbz.backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
