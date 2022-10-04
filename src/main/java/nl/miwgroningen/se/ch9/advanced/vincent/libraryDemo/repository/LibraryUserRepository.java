package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository;

import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
    Optional<LibraryUser> findByUsername(String username);
}
