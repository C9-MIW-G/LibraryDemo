package nl.miwgroningen.se.ch9.advanced.vincent.libraryDemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
@Entity @Getter @Setter
public class Book {
    @Id
    @GeneratedValue
    private Long bookId;

    private String title;
    private String author;
}
