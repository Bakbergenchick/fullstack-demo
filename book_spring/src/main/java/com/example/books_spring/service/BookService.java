package com.example.books_spring.service;

import com.example.books_spring.entity.Book;
import com.example.books_spring.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(@Qualifier("bookRepository") BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBook(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent())
            return book.get();

        throw new IllegalArgumentException();
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);

    }

    public Book deleteBook(Long id){
        Optional<Book> book = bookRepository.findById(id);


        if (book.isPresent()){
            bookRepository.delete(book.get());
            return book.get();
        }


        throw new IllegalArgumentException();
    }

    public Book editBook(Book book){
        return bookRepository.save(book);
    }


}
