package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public String getAuthorsPage(@RequestParam(required = false) String error, Model model){
        if (error != null) {
            model.addAttribute("error", error);
        }

        List<Author> authors = authorService.findAll();

        model.addAttribute("authors", authors);

        return "listAuthors";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id){
        authorService.delete(id);
        return "redirect:/authors";
    }

    @GetMapping("/author-form")
    public String getAuthorAddForm(){
        return "author-form";
    }

    @PostMapping("/add")
    public String addAuthor(@RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam String country,
                            @RequestParam String biography){

        authorService.create(name,surname,country,biography);
        return "redirect:/authors";
    }

    @GetMapping("/author-form/{id}")
    public String getAuthorEditForm(@PathVariable Long id, Model model){

        Author author = authorService.findById(id);

        model.addAttribute("author", author);
        model.addAttribute("name", author.getName());
        model.addAttribute("surname", author.getSurname());
        model.addAttribute("country", author.getCountry());
        model.addAttribute("biography", author.getBiography());

        return "author-form";
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography,Model model){
        authorService.update(id, name, surname, country, biography);
        return "redirect:/authors";
    }



}
