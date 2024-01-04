package librarymanagement.bookmanagement.repository;

import librarymanagement.bookmanagement.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    // Các phương thức truy vấn dữ liệu nâng cao có thể được thêm ở đây
}
