package com.example.Axe_library.controllers;

import com.example.Axe_library.models.Book;
import com.example.Axe_library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping
    public ResponseEntity<Book> registerBook(@RequestBody Book book){
       return ResponseEntity.ok(bookService.addBook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long  id){
        try {
                bookService.deleteBookById(id);
                return ResponseEntity.ok("book delete");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String bookName,
                                  @RequestParam(required = false) String bookAuthor){

        if (bookName != null){
        return bookService.getBooksByName(bookName);
        }else if (bookAuthor != null){
            return bookService.getBooksByAuthor(bookAuthor);
        }
        return Collections.emptyList();
    }


}
