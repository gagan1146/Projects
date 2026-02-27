package org.gagan.intern_assignment_backend.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Node
@Data
@Getter
@Setter
public class Shelf {
    @Id
    @GeneratedValue
    private UUID shelfId;
    private String shelfName;
    private String partName;

}
