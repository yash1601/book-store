package flip.entity;

import javax.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long collection_id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_price")
    private Integer total_price;

    @OneToMany(mappedBy="collection", cascade = CascadeType.PERSIST)
    private Set<Book> books = new HashSet<>();

    @Override
    public String toString() {
        return "Books:  [collection_id=" + collection_id + "|" + name + "|" + total_price+ "|" + books + "|";
    }
}
