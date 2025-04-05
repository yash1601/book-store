package flip.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="collections")
public class Collection {

    @Id
    private String collection_id;

    private String name;

    private Integer total_price;

    @DBRef
    private Set<Book> books = new HashSet<>();

    @Override
    public String toString() {
        return "Books:  [collection_id=" + collection_id + "|" + name + "|" + total_price;
    }
}
