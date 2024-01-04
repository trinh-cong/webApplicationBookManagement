package librarymanagement.bookmanagement.service;


import librarymanagement.bookmanagement.model.Publisher;
import librarymanagement.bookmanagement.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAllPublishers() {
        return null;
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return null;
    }

    @Override
    public void savePublisher(Publisher publisher) {

    }

    @Override
    public void deletePublisher(Long id) {

    }

    // Implement các phương thức từ PublisherService
}