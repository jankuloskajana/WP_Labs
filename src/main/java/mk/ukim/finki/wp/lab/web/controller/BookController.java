package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public String getBooksPage(@RequestParam(required = false) String search,
                               @RequestParam(required = false) String error, Model model){
        if (error != null) {
            model.addAttribute("error", error);
        }

        List<Book> books;
        if ((search != null && !search.isEmpty())) {
            Double rating = null;
            String text = null;
            try {
                rating = Double.parseDouble(search);
            } catch (NumberFormatException e) {
                text = search;
            }
            books = bookService.searchBooks(text, rating);
        } else {
            books = bookService.listAll();
        }
        model.addAttribute("books", books);
        return "listBooks";

    }

    @GetMapping("/book-form")
    public String getAddBookForm(Model model){
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){

        bookService.create(title, genre, averageRating, authorId);

        return "redirect:/books";
    }

    @GetMapping("book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model){

        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){

        bookService.update(bookId, title, genre, averageRating, authorId);

        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.delete(id);
        return "redirect:/books";
    }

}
