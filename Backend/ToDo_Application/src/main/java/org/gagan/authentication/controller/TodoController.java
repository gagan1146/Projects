package org.gagan.authentication.controller;

import org.gagan.authentication.model.Todo;
import org.gagan.authentication.model.UserModel;
import org.gagan.authentication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public String listTodos(HttpSession session, Model model) {
        UserModel user = (UserModel) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        List<Todo> todos = todoService.getTodosForUser(user.getEmail());
        model.addAttribute("todos", todos);
        return "todo";
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam String description, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        todoService.addTodo(description, user.getEmail());
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }

    @PostMapping("/toggle/{id}")
    public String toggleTodo(@PathVariable String id) {
        todoService.toggleCompleted(id);
        return "redirect:/todos";
    }
}