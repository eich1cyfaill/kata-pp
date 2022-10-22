package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

@RestController
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping("/login.html")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/login-error.html")
    public ModelAndView loginError(Model model) {
        model.addAttribute("loginError", true);
        return new ModelAndView("login");
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("logout");
    }

    @GetMapping("/user")
    public ModelAndView getUserInfo(Principal principal, Model model) {
        model.addAttribute("principal", principal);
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("currentroles", SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(r -> r.toString().replaceAll("ROLE_", ""))
                .collect(Collectors.toList()));
        return new ModelAndView("user_info");
    }

    @GetMapping(value = "/")
    public ModelAndView printWelcome(Model model) {
        return new ModelAndView("index");
    }

    @GetMapping("/create")
    public ModelAndView createUserPage(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        model.addAttribute("currentroles", SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(r -> r.toString().replaceAll("ROLE_", ""))
                .collect(Collectors.toList()));
        return new ModelAndView("create-user");
    }

    @GetMapping("/admin")
    public ModelAndView getAdminPage(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        model.addAttribute("currentroles", SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(r -> r.toString().replaceAll("ROLE_", ""))
                .collect(Collectors.toList()));
        return new ModelAndView("admin");
    }

    @GetMapping("/api/users")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable("id") Long id) { return userService.getUser(id); }

    @PostMapping("/api/users")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/api/users")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        userService.deleteUser(user);
    }

    @GetMapping("/api/roles")
    public List<Role> getRoles() { return roleService.getAllRolesList(); }
}
