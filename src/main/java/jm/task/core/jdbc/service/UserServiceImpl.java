package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        dao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        List<User> list = dao.getAllUsers();
        return list;
    }

    public void cleanUsersTable() {
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        dao.cleanUsersTable();
    }
}
