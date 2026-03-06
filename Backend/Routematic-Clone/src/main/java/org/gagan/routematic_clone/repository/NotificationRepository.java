package org.gagan.routematic_clone.repository;

import org.gagan.routematic_clone.model.Notification;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface NotificationRepository extends Neo4jRepository<Notification, UUID> {
}
