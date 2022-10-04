package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue
    private Long authorId;

    private String firstName;
    private String infixName = "";
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> authoredBooks;

    public String getDisplayName() {
        return String.format("%s %s, %s", infixName, lastName, firstName);
    }
}
