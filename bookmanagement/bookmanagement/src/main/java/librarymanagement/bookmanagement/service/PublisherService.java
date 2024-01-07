package librarymanagement.bookmanagement.service;

import librarymanagement.bookmanagement.model.Publisher;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface PublisherService {

    List<Publisher> getAllPublishers();

    Publisher getPublisherById(Long id);

    void savePublisher(Publisher publisher);

    void deletePublisher(Long id);
}