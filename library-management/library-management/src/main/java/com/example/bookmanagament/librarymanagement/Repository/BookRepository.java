package com.example.bookmanagament.librarymanagement.Repository;


import com.example.bookmanagament.librarymanagement.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.tools.JavaFileManager;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String keyword);
}
