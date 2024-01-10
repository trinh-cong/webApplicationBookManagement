package librarymanagement.bookmanagement.service;

import librarymanagement.bookmanagement.model.Book;
import librarymanagement.bookmanagement.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublisherService {
    List<Publisher> getAllPublishers();
    Page<Publisher> getAllPublishers(Pageable pageable);
    Publisher getPublisherById(Long id);
    void savePublisher(Publisher publisher);
    void deletePublisher(Long id);
    List<Book> getBooksByPublisherId(Long publisherId);

    // Thêm phương thức kiểm tra tên nhà xuất bản đã tồn tại hay chưa
    boolean isPublisherNameExists(String name);
}
