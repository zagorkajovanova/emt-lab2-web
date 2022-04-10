package mk.ukim.finki.emt.lab2application.model;
import lombok.Data;
import javax.persistence.*;
import mk.ukim.finki.emt.lab2application.model.enums.Category;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @OneToOne
    private Author author;

    private Integer availableCopies;

    public Book(){

    }
    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
