package flip.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {

    @Id
    private long book_id;

    private String name;

    private String author;

    private String category;

    private Float price;

    private Integer sales;

    private Integer rating;

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="collection_id", referencedColumnName = "collection_id")
//    private Collection collection;

    @Override
    public String toString() {
        return "=" + book_id + "|" + name + "|" + author + "|" + price + "|";
    }
}
