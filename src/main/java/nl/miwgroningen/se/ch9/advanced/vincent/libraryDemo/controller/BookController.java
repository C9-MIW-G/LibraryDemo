package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.controller;

import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.Book;
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
@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping({"/", "/book/overview"})
    protected String showBookOverview(Model model) {
        model.addAttribute("allBooks", bookRepository.findAll());
        return "bookOverview";
    }

    @GetMapping("/book/new")
    protected String showNewBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookForm";
    }

    @GetMapping("/book/update/{bookId}")
    protected String showUpdateBookForm(@PathVariable("bookId") Long bookId, Model model) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isEmpty()) {
            return "redirect:/book/overview";
        }

        model.addAttribute("book", book.get());
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
