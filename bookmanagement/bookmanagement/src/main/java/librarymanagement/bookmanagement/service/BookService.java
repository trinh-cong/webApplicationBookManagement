package librarymanagement.bookmanagement.service;

import librarymanagement.bookmanagement.model.Book;
import librarymanagement.bookmanagement.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    void saveBook(Book book, MultipartFile file) throws IOException;
    void deleteBook(Long id);
    List<Book> searchBooks(String keyword);
    Page<Book> getAllBooksPaged(Pageable pageable);
}


