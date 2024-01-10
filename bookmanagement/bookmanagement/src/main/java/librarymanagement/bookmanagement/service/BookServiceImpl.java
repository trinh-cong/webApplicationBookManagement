package librarymanagement.bookmanagement.service;

import com.cloudinary.Cloudinary;
import librarymanagement.bookmanagement.model.Book;
import librarymanagement.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }


    @Override
    public void saveBook(Book book, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), Map.of());
                String imageUrl = (String) uploadResult.get("url");
                book.setImageUrl(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Giữ nguyên ảnh cũ khi không có ảnh mới được chọn
            Book existingBook = bookRepository.findById(book.getId()).orElse(null);
            if (existingBook != null) {
                book.setImageUrl(existingBook.getImageUrl());
            }
        }
        bookRepository.save(book);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> searchBooks(String title, Double price, String publisher, Pageable pageable) {
        if (price != null) {
            return bookRepository.findByTitleContainingAndPriceAndPublisher_NameContaining(title, price, publisher, pageable);
        } else {
            return bookRepository.findByTitleContainingAndPublisher_NameContaining(title, publisher, pageable);
        }
    }
    }

