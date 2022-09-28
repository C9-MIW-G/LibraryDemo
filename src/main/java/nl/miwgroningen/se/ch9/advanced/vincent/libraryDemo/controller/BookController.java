package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.controller;

import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.Book;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository.AuthorRepository;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
@SuppressWarnings("SameReturnValue")
@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping({"/", "/book/overview"})
    protected String showBookOverview(Model model) {
        model.addAttribute("allBooks", bookRepository.findAll());
        return "bookOverview";
    }

    @GetMapping("/book/new")
    protected String showNewBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("allAuthors", authorRepository.findAll());
        return "bookForm";
    }

    @GetMapping("/book/update/{bookTitle}")
    protected String showUpdateBookForm(@PathVariable("bookTitle") String bookTitle, Model model) {
        Optional<Book> book = bookRepository.findByTitle(bookTitle);

        if (book.isEmpty()) {
            return "redirect:/book/overview";
        }

        model.addAttribute("book", book.get());
        model.addAttribute("allAuthors", authorRepository.findAll());
        return "bookForm";
    }

    @PostMapping("/book/new")
    protected String saveOrUpdateBook(@ModelAttribute("book") Book book, BindingResult result) {
        if (!result.hasErrors()) {
            bookRepository.save(book);
        }
        return "redirect:/book/overview";
    }
}
