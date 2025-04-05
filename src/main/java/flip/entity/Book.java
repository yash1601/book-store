package flip.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="books")
public class Book {

    @Id
    private String book_id;

    private String name;

    private String author;

    private String category;

    private Float price;

    private Integer sales;

    private Integer rating;

    @Override
    public String toString() {
        return "=" + book_id + "|" + name + "|" + author + "|" + price + "|";
    }
}
