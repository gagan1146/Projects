package org.gagan.authentication.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Todo")
public class Todo {
    @Id
    private String id;
    private String description;
    private boolean completed;
    private String userEmail;

    public Todo() {}

    public Todo(String id, String description, boolean completed, String userEmail) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.userEmail = userEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}