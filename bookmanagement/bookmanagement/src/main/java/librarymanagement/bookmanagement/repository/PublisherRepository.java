package librarymanagement.bookmanagement.repository;

import librarymanagement.bookmanagement.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    // Phương thức tìm kiếm nhà xuất bản theo tên
    Publisher findByName(String name);

    // Phương thức kiểm tra sự tồn tại của tên nhà xuất bản
    @Query("SELECT COUNT(p) > 0 FROM Publisher p WHERE LOWER(p.name) = LOWER(:name)")
    boolean existsByName(@Param("name") String name);
}