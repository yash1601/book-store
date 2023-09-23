package flip.entity;

import javax.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy="collection", cascade = CascadeType.ALL)
    private List<Book> books;

    @Override
    public String toString() {
        return "CarbonTrackerUserEntity [userId=" + collection_id + "|" + name + "|" + total_price+ "|" + "|";
    }
}
