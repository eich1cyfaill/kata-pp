package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = null;
    private Session session = null;

    public UserDaoHibernateImpl() {

    }

    {
        sessionFactory = Util.buildSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), last_name VARCHAR(40), age TINYINT)").executeUpdate();
            session.getTransaction().commit();
        } catch (PersistenceException ignored) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (PersistenceException ignored) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.printf("User с именем - %s добавлен в базу данных \n", name);
        } catch (Throwable e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            list = session.createQuery("SELECT i FROM User i", User.class).getResultList();
            session.getTransaction().commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
        }
    }
}
