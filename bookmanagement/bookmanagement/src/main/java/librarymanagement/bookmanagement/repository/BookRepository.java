package librarymanagement.bookmanagement.repository;

import librarymanagement.bookmanagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContainingAndPublisher_NameContaining(String title, String publisher, Pageable pageable);

    List<Book> findByPublisherId(Long publisherId);
    Page<Book> findByTitleContainingAndPriceAndPublisher_NameContaining(String title, Double price, String publisher, Pageable pageable);

}
