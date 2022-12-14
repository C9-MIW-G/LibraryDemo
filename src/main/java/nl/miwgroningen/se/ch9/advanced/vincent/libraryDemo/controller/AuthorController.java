package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.controller;

import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.Author;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/overview")
    protected String showAuthorOverview(Model model) {
        model.addAttribute("allAuthors", authorRepository.findAll());
        model.addAttribute("newAuthor", new Author());
        return "authorOverview";
    }

    @GetMapping("/detail/{authorId}")
    protected String showAuthorDetails(@PathVariable("authorId") Long authorId, Model model) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            model.addAttribute("author", author.get());
            model.addAttribute("allBooks", author.get().getAuthoredBooks());
            return "authorDetails";
        }
        return "redirect:/author";
    }

    @PostMapping("/new")
    protected String saveOrUpdateAuthor(@ModelAttribute("newAuthor") Author author, BindingResult result) {
        if (!result.hasErrors()) {
            authorRepository.save(author);
        }

        return "redirect:/author/overview";
    }
}
