package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.repository;

import nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Long> {
}
