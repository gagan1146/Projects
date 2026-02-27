package org.gagan.intern_assignment_backend.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Data
@Getter
@Setter
public class User {
    String username;
    @Id
    String email;
    String password;
}
