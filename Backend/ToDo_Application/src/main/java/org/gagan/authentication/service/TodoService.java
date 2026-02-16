package org.gagan.authentication.service;

import org.gagan.authentication.model.Todo;
import org.gagan.authentication.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodosForUser(String userEmail) {
        return todoRepository.findByUserEmail(userEmail);
    }

    public Todo addTodo(String description, String userEmail) {
        Todo todo = new Todo(UUID.randomUUID().toString(), description, false, userEmail);
        return todoRepository.save(todo);
    }

    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }

    public void toggleCompleted(String id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
    }
}