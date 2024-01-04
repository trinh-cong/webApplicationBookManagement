package librarymanagement.bookmanagement.service;

import librarymanagement.bookmanagement.model.Book;
import librarymanagement.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CloudinaryService cloudinaryService; // Cần tạo CloudinaryService

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public void saveBook(Book book, MultipartFile file) throws IOException {

    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return null;
    }

    @Override
    public Page<Book> getAllBooksPaged(Pageable pageable) {
        return null;
    }

    // Implement các phương thức từ BookService
}
