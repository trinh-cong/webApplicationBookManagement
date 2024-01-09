package librarymanagement.bookmanagement.service;


import librarymanagement.bookmanagement.model.Book;
import librarymanagement.bookmanagement.model.Publisher;
import librarymanagement.bookmanagement.repository.BookRepository;
import librarymanagement.bookmanagement.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }


    @Override
    public Page<Publisher> getAllPublishers(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @Override
    public void savePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
    @Override
    public List<Book> getBooksByPublisherId(Long publisherId) {
        return bookRepository.findByPublisherId(publisherId);
    }


}