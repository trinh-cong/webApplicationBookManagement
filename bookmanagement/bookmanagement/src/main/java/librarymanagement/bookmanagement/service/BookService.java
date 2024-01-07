package librarymanagement.bookmanagement.service;

import librarymanagement.bookmanagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface BookService {


    Page<Book> getAllBooks(Pageable pageable);

    void saveBook(Book book, MultipartFile imageFile);


    Book getBookById(Long id);

    void deleteBook(Long id);

    Page<Book> searchBooks(String title, Pageable pageable);

}



