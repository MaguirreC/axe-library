package com.example.Axe_library.services;


import com.example.Axe_library.models.Book;
import com.example.Axe_library.repositorys.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book>getBooks(){
            return bookRepository.findAll();
    }

public Book addBook(Book book){
        return bookRepository.save(book);
}

    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }
    public List<Book> getBooksByName(String bookName){
return bookRepository.findByBookNameContainingIgnoreCase(bookName);
    }

    public List<Book> getBooksByAuthor(String bookAuthor){
        return bookRepository.findByBookAuthorContainingIgnoreCase(bookAuthor);
    }
}

