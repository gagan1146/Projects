package org.gagan.routematic_clone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Routes {
    @Id
    @GeneratedValue
    private UUID id;
    private String startLocation;
    private String endLocation;
    private int distance;
}