package librarymanagement.bookmanagement.repository;

import librarymanagement.bookmanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Các phương thức truy vấn dữ liệu nâng cao có thể được thêm ở đây
}
