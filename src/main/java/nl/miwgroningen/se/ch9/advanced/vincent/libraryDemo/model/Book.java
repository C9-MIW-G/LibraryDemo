package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private Long bookId;

    private String title;

    @ManyToMany
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private List<Copy> copies;

    public int getNumberOfAvailableCopies() {
        int count = 0;
        for (Copy copy : copies) {
            if (copy.getAvailable()) {
                count++;
            }
        }
        return count;
    }

    public String getAuthorDisplayNames() {
        return authors.stream().map(Author::getDisplayName).collect(Collectors.joining(", "));
    }
}
