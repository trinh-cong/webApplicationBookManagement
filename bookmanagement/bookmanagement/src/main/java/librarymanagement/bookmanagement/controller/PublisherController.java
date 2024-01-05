package librarymanagement.bookmanagement.controller;

import librarymanagement.bookmanagement.model.Publisher;
import librarymanagement.bookmanagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public String listPublishers(Model model) {
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "publishers/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publishers/create";
    }

    @PostMapping("/create")
    public String createPublisher(@ModelAttribute("publisher") Publisher publisher) {
        publisherService.savePublisher(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Publisher publisher = publisherService.getPublisherById(id);
        model.addAttribute("publisher", publisher);
        return "publishers/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPublisher(@PathVariable("id") Long id, @ModelAttribute("publisher") Publisher publisher) {
        publisher.setId(id);
        publisherService.savePublisher(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(@PathVariable("id") Long id) {
        publisherService.deletePublisher(id);
        return "redirect:/publishers";
    }
}
