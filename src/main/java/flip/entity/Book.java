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

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Integer price;

    @Column(name = "sales")
    private Integer sales;

    @Column(name = "rating")
    private Integer rating;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="collection_id", referencedColumnName = "collection_id")
    private Collection collection;

    public Book(long book_id, String name, String author, String category, Integer price, Integer sales, Integer rating) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.price = price;
        this.sales = sales;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "=" + book_id + "|" + name + "|" + author + "|" + price + "|";
    }
}
