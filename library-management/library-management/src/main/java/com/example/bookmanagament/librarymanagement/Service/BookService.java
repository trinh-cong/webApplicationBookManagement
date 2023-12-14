package com.example.bookmanagament.librarymanagement.Service;

import com.example.bookmanagament.librarymanagement.Book;
import com.example.bookmanagament.librarymanagement.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }
    public  void saveBook(Book book){
        bookRepository.save(book);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }


}
