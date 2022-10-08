package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {

        List<User> getUserList();
        void addUser(User user, List<Role> updatedRoles);
        void deleteUser(User user);
        void updateUser(User user, List<Role> roles);

        User getOneUser(Long id);

        User findByUsername(String username);
}
