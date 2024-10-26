package com.ch.tbz.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    public Book(String name, String price, String image, String author, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.author = author;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private String image;
    private String author;
    private String description;

}
