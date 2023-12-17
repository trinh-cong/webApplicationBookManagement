package com.example.bookmanagament.librarymanagement.Service;

import com.example.bookmanagament.librarymanagement.model.Book;
import com.example.bookmanagament.librarymanagement.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;




@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void saveBook(Book book, MultipartFile file) {
        if (!file.isEmpty()) {
            String imageUrl = fileStorageService.storeFile(file);
            book.setImageUrl(imageUrl);
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingOrDescriptionContainingOrPublisherContaining(keyword, keyword, keyword);
    }
}

