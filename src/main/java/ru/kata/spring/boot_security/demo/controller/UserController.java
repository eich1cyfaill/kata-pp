package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/user")
    public String getUserInfo(Principal principal, Model model) {
        model.addAttribute("principal", principal);
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("currentroles", SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(r -> r.toString().replaceAll("ROLE_", ""))
                .collect(Collectors.toList()));
        return "user_info";
    }

    @GetMapping(value = "/")
    public String printWelcome(Model model) {
        return "index";
    }

    @GetMapping("/create")
    public String createUserPage(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        model.addAttribute("currentroles", SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(r -> r.toString().replaceAll("ROLE_", ""))
                .collect(Collectors.toList()));
        return "create-user";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        model.addAttribute("currentroles", SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(r -> r.toString().replaceAll("ROLE_", ""))
                .collect(Collectors.toList()));
        return "admin";
    }
}
