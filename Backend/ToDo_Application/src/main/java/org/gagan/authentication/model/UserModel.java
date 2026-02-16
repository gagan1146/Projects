package org.gagan.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Node
public class UserModel {
    private String name;
    @Id
    private String email;
    private String password;
}
