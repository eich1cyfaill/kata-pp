package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/api/users")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable("id") Long id) { return userService.getUser(id); }

    @PostMapping("/api/users")
    public void addUser(@RequestBody User user) {
        user.setRoles(user.getRoles().stream().map(r -> roleService.findByName(r.getName())).toList());
        userService.addUser(user);
    }

    @PutMapping("/api/users")
    public void updateUser(@RequestBody User user) {
        user.setRoles(user.getRoles().stream().map(r -> roleService.findByName(r.getName())).toList());
        userService.updateUser(user);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        user.setRoles(null);
        userService.deleteUser(user);
    }

    @GetMapping("/api/roles")
    public List<Role> getRoles() { return roleService.getAllRolesList(); }


}
