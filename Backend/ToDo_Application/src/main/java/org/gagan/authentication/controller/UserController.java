package org.gagan.authentication.controller;

import jakarta.servlet.http.HttpSession;
import org.gagan.authentication.model.Todo;
import org.gagan.authentication.model.UserModel;
import org.gagan.authentication.service.TodoService;
import org.gagan.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String mainPage(){
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserModel user = (UserModel) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        List<Todo> todos = todoService.getTodosForUser(user.getEmail());
        model.addAttribute("todos", todos);
        return "home";
    }


    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") UserModel user, Model model) {
        ResponseEntity<UserModel> user1 = userService.signup(user.getEmail(), user.getName(), user.getPassword());
        if (user1.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("message", "Signup successful!");
            return "login";
        } else {
            model.addAttribute("error", "Signup failed: " + user1.getStatusCode());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        ResponseEntity<UserModel> response = userService.login(email, password);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            session.setAttribute("loggedInUser", response.getBody()); // store user in session
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
