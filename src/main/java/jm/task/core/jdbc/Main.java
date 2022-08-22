package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Rostislav", "Komarov", (byte) 20);
        service.saveUser("Second", "Name", (byte) 21);
        service.saveUser("Third", "Surname", (byte) 22);
        service.saveUser("Fourth", "User", (byte) 23);
        List<User> list = service.getAllUsers();
        for (User user:
             list) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
