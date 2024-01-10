package librarymanagement.bookmanagement.controller;

import librarymanagement.bookmanagement.model.Book;
import librarymanagement.bookmanagement.model.Publisher;
import librarymanagement.bookmanagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Pageable;

import java.util.List;

@Controller
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public String listPublishers(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = (Pageable) PageRequest.of(page, 5);
        Page<Publisher> publishers = publisherService.getAllPublishers((org.springframework.data.domain.Pageable) pageable);

        model.addAttribute("publishers", publishers.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", publishers.getTotalPages());

        return "publishers/list";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publishers/add";
    }

    @PostMapping("/add")
    public String addPublisher(@ModelAttribute("publisher") Publisher publisher, Model model) {
        // Kiểm tra xem tên nhà xuất bản có giá trị không
        if (publisher.getName() == null || publisher.getName().trim().isEmpty()) {
            model.addAttribute("error", "Publisher name cannot be empty");
            return "publishers/add";
        }

        // Kiểm tra xem tên nhà xuất bản đã tồn tại hay chưa
        if (publisherService.isPublisherNameExists(publisher.getName())) {
            // Tên nhà xuất bản đã tồn tại, gửi thông báo lỗi về giao diện
            model.addAttribute("error", "Publisher name already exists");
            return "publishers/add";
        }

        // Thêm nhà xuất bản vào cơ sở dữ liệu
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

    @GetMapping("/publisherDetails/{id}")
    public String showPublisherDetails(@PathVariable Long id, Model model) {
        try {
            Publisher publisher = publisherService.getPublisherById(id);

            List<Book> books = publisherService.getBooksByPublisherId(id);

            model.addAttribute("publisher", publisher);
            model.addAttribute("books", books);

            return "publishers/publisherDetails"; // Tên template Thymeleaf cho chi tiết nhà xuất bản
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
