package org.gagan.intern_assignment_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;

//@Node
//@Data
@Getter
@Setter
public class LoginResponse {
    String username;
    String password;
}
