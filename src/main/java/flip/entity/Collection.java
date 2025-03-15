package flip.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Collection {

    @Id
    private long collection_id;

    private String name;

    private Integer total_price;
//
//    @OneToMany(mappedBy="collection", cascade = CascadeType.PERSIST)
//    private Set<Book> books = new HashSet<>();

    @Override
    public String toString() {
        return "Books:  [collection_id=" + collection_id + "|" + name + "|" + total_price;
    }
}
