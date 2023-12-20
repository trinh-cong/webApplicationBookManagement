package com.example.bookmanagament.librarymanagement.controller;

import com.example.bookmanagament.librarymanagement.model.Book;
import com.example.bookmanagament.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, @RequestParam("file") MultipartFile file) {
        bookService.saveBook(book, file);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book, @RequestParam("file") MultipartFile file) {
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
