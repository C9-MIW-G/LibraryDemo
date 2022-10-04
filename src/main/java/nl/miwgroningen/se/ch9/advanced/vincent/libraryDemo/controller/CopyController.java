package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.controller;

import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.Book;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.Copy;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository.BookRepository;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository.CopyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
@Controller
@RequestMapping("/copy")
public class CopyController {

    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;

    public CopyController(BookRepository bookRepository, CopyRepository copyRepository) {
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
    }

    @GetMapping("/new/{bookId}")
    protected String createNewCopy(@PathVariable("bookId") Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isPresent()) {
            Copy copy = new Copy();
            copy.setBook(book.get());
            copyRepository.save(copy);
        }

        return "redirect:/book/overview";
    }

    @GetMapping("/delete/{copyId}")
    protected String deleteCopy(@PathVariable("copyId") Long copyId) {
        Optional<Copy> copy = copyRepository.findById(copyId);

        if (copy.isEmpty()) {
            return "redirect:/book/overview";
        } else {
            copyRepository.delete(copy.get());
            return String.format("redirect:/book/id/%d", copy.get().getBook().getBookId());
        }
    }
}
