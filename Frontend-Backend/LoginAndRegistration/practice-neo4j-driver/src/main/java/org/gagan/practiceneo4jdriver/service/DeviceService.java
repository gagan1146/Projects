package org.gagan.practiceneo4jdriver.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.QueryConfig;
import org.neo4j.driver.Result;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class DeviceService {

    private final Driver driver;

    public DeviceService(Driver driver) {
        this.driver = driver;
    }

    public String getPeople() {
        var result = driver.executableQuery("""
                        MATCH (p:Person)-[:KNOWS]->(:Person)
                        RETURN p.name AS name
                        """)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute();

        var records = result.records();
        records.forEach(r -> System.out.println(r.get("name").asString()));

        var summary = result.summary();
        System.out.printf("The query %s returned %d records in %d ms.%n",
                summary.query(), records.size(),
                summary.resultAvailableAfter(TimeUnit.MILLISECONDS));

        return "Data fetched Successfully....";
    }

    public String createTwoPerson() {
        var result = driver.executableQuery("""
                CREATE (a:Person {name: $name})
                CREATE (b:Person {name: $friendName})
                CREATE (a)-[:KNOWS]->(b)
                """)
                .withParameters(Map.of("name", "Alice", "friendName", "David"))
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute();

//        var summary = result.summary();
//        System.out.printf("Created %d nodes in %d ms.%n",
//                summary.counters().nodesCreated(),
//                summary.resultAvailableAfter(TimeUnit.MILLISECONDS));

        return "Created Data Successfully....";
    }
}
