package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {

        List<User> getUserList();
        void addUser(User user);
        void deleteUser(User user);
        void updateUser(User user);
        User findByUsername(String username);
        boolean checkIfUserExists(String username);
}
