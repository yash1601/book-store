package flip.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long book_id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "price")
    private Integer price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="collection_id", referencedColumnName = "collection_id")
    private Collection collection;

    public Book Book(String name, String author, String genre, int i) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.price = i;
        return this;
    }

    @Override
    public String toString() {
        return "=" + book_id + "|" + name + "|" + author + "|" + price + "|";
    }
}
