package librarymanagement.bookmanagement.controller;
import librarymanagement.bookmanagement.model.Book;
import librarymanagement.bookmanagement.model.Publisher;
import librarymanagement.bookmanagement.service.BookService;
import librarymanagement.bookmanagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public String listBooks(@RequestParam(defaultValue = "0") int page, Model model) {

        if (page < 0) {
            page = 0;
        }

        Pageable pageable = PageRequest.of(page, 5);
        Page<Book> bookPage = bookService.getAllBooks(pageable);

        if (page >= bookPage.getTotalPages()) {
            page = bookPage.getTotalPages() - 1;
        }

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        return "books/list";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("publishers", publishers);


        return "books/add";
    }


    @PostMapping("/add/save")
    public String createBook(@ModelAttribute("book") Book book,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {
        bookService.saveBook(book, imageFile);
        redirectAttributes.addFlashAttribute("message", "Book created successfully");
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("book", book);
        model.addAttribute("publishers", publishers);
        return "books/edit";
    }
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id,
                           @ModelAttribute("book") Book book,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           RedirectAttributes redirectAttributes) {
        bookService.saveBook(book, imageFile);
        redirectAttributes.addFlashAttribute("message", "Book updated successfully");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long   id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("message", "Book deleted successfully");
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam("title") String title,
                              @RequestParam(defaultValue = "0") int page,
                              Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Book> bookPage = bookService.searchBooks(title, pageable);
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("searchTitle", title);
         return  "/books/search";
    }

}
