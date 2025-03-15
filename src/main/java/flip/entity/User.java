package flip.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

    @Id
    private long user_id;

    private String name;

    private String email;

    private String password;
}
