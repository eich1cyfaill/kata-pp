package app.repository;

import app.model.User;


import java.util.List;

public interface UserDao {

    List<User> getUserList();
    void addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);

    User getOneUser(Long id);

}
