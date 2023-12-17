package com.example.bookmanagament.librarymanagement.Repository;


import com.example.bookmanagament.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingOrDescriptionContainingOrPublisherContaining(String keyword1, String keyword2, String keyword3);}
