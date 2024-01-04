package librarymanagement.bookmanagement.controller;


import librarymanagement.bookmanagement.model.Publisher;
import librarymanagement.bookmanagement.service.BookService;
import librarymanagement.bookmanagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public String getAllPublishers(Model model) {
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publisher/list";
    }

    @GetMapping("/{id}")
    public String getPublisherById(@PathVariable Long id, Model model) {
        Publisher publisher = publisherService.getPublisherById(id);
        model.addAttribute("publisher", publisher);
        return "publisher/detail";
    }
}
