package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository;

import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
