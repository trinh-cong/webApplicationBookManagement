package com.example.bookmanagement.Controller;



import com.example.bookmanagement.Book;
import com.example.bookmanagement.Repository.BookRepository;
import com.example.bookmanagement.Service.BookService;
import com.example.bookmanagement.Service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("books")
public class BookController {
    private final BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/new")
    public String addBook(@ModelAttribute Book book, @RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = fileStorageService.storeFile(imageFile);
                book.setImageUrl(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PostMapping("/edit/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam("keyword") String keyword, Model model) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(keyword);
        model.addAttribute("books", books);
        return "book/list";
    }
    @GetMapping("/search-form")
    public String showSearchForm() {
        return "book/search";
    }
}








