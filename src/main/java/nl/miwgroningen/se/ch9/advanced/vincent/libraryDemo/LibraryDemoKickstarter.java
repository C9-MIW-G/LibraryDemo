package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo;

import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.Author;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.Book;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.LibraryUser;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository.AuthorRepository;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository.BookRepository;
import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository.LibraryUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
@SpringBootApplication
public class LibraryDemoKickstarter implements CommandLineRunner {

    private final LibraryUserRepository libraryUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public LibraryDemoKickstarter(LibraryUserRepository libraryUserRepository, PasswordEncoder passwordEncoder,
                                  AuthorRepository authorRepository, BookRepository bookRepository) {
        this.libraryUserRepository = libraryUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (libraryUserRepository.findByUsername("admin").isEmpty()) {
            // create default admin
            LibraryUser admin = new LibraryUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("changeThisNow"));
            libraryUserRepository.save(admin);

//            // create author
//            Author patrick = new Author();
//            patrick.setFirstName("Patrick");
//            patrick.setLastName("Rothfuss");
//            authorRepository.save(patrick);
//
//            // create book
//            Book book = new Book();
//            book.setTitle("The Name of the Wind");
//            book.getAuthors().add(patrick);
//            bookRepository.save(book);
        }
    }
}
