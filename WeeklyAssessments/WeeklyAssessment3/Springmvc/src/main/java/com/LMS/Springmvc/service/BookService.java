package com.LMS.Springmvc.service;

import com.LMS.Springmvc.exception.BookNotFoundException;
import com.LMS.Springmvc.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    // Add Book
    public void addBook(Book book) {

        // Optional: Check for duplicate ID
        boolean exists = books.stream()
                .anyMatch(b -> b.getId() == book.getId());

        if (exists) {
            throw new RuntimeException("Book with ID " + book.getId() + " already exists");
        }

        books.add(book);
    }

    // Get All Books
    public List<Book> getAllBooks() {
        return books;
    }

    // Get Book By ID
    public Book getBookById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new BookNotFoundException("Book with ID " + id + " is not found"));
    }

    // Delete Book
    public void deleteBook(int id) {
        Book book = getBookById(id);
        books.remove(book);
    }
}