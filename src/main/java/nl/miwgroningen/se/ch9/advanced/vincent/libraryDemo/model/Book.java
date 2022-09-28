package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private Set<Author> authors;

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
        StringBuilder stringBuilder = new StringBuilder();
        for (Author author : authors) {
            stringBuilder.append(author.getDisplayName()).append(", ");
        }
        return stringBuilder.toString();
    }
}
