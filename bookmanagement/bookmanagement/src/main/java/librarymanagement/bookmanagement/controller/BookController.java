package librarymanagement.bookmanagement.controller;
import librarymanagement.bookmanagement.model.Book;
import librarymanagement.bookmanagement.model.Publisher;
import librarymanagement.bookmanagement.service.BookService;
import librarymanagement.bookmanagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public String getAllBooks(Model model, Pageable pageable) {
        Page<Book> books = bookService.getAllBooksPaged(pageable);
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute @Valid Book book, BindingResult bindingResult,
                          @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("publishers", publisherService.getAllPublishers());
            return "book/add";
        }

        bookService.saveBook(book, file);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "book/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute @Valid Book book, BindingResult bindingResult,
                           @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("publishers", publisherService.getAllPublishers());
            return "book/edit";
        }

        bookService.saveBook(book, file);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Book> books = (keyword != null && !keyword.isEmpty()) ? bookService.searchBooks(keyword) : bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }
}

