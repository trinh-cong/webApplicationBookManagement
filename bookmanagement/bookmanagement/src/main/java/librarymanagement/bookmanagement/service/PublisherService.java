package librarymanagement.bookmanagement.service;

import librarymanagement.bookmanagement.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getAllPublishers();
    Publisher getPublisherById(Long id);
    void savePublisher(Publisher publisher);
    void deletePublisher(Long id);
}