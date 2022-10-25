package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;


@RestController
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/api/principal")
    public Principal getPrincipal(Principal principal) {
        return principal;
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
