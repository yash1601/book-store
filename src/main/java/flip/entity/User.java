package flip.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="users")
public class User {

    @Id
    private String user_id;

    @NonNull
    @Indexed(unique = true)
    private String name;

    private String email;

    @NonNull
    private String password;

    @DBRef
    private Collection userCollection = new Collection();
}
