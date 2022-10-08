package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.Query;
import java.util.List;

public interface UserDao {

    List<User> getUserList();
    void addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);

    User getOneUser(Long id);

    boolean checkIfUserExists(String username);
    User findByUsername(String username);

}
