package com.example.books_spring.controller;


import com.example.books_spring.entity.Book;
import com.example.books_spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(@Qualifier("bookService") BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public Book getBook(
            @PathVariable Long id
    ){
        return bookService.getBook(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("")
    public Book addBook(
            @RequestBody Book book
    ){
        return bookService.saveBook(book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Book deleteBook(
            @PathVariable Long id
    ){
        return bookService.deleteBook(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("")
    public Book editBook(
            @RequestBody Book book
    ){
        return bookService.editBook(book);
    }
}
