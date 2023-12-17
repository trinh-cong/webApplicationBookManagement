package com.example.bookmanagament.librarymanagement.data;

import com.example.bookmanagament.librarymanagement.Repository.BookRepository;
import com.example.bookmanagament.librarymanagement.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public BookInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        // Khởi tạo dữ liệu đầu sách mẫu
//        Book book1 = new Book();
//        book1.setTitle("Sample Book 1");
//        book1.setPrice(19.99);
//        book1.setDescription("This is a sample book.");
//        book1.setPublisher("Sample Publisher 1");
//        book1.setImageUrl("../../../../../../uploads/book3.jpg");  // Đường dẫn ảnh tương ứng
//
//        Book book2 = new Book();
//        book2.setTitle("Sample Book 2");
//        book2.setPrice(29.99);
//        book2.setDescription("Another sample book.");
//        book2.setPublisher("Sample Publisher 2");
//        book2.setImageUrl("../../../../../../uploads/book4.jpg");  // Đường dẫn ảnh tương ứng

        // Lưu vào cơ sở dữ liệu
//        bookRepository.save(book1);
//        bookRepository.save(book2);
    }
}
