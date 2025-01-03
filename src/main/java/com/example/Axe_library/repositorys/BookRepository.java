package com.example.Axe_library.repositorys;

import com.example.Axe_library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    public List<Book>findByBookNameContainingIgnoreCase(String bookName);

    public  List<Book> findByBookAuthorContainingIgnoreCase(String authorName);
}
