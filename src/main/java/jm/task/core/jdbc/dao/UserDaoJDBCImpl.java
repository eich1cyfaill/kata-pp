package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = null;

    public UserDaoJDBCImpl() {

    }

    {
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), last_name VARCHAR(40), age TINYINT)");
            connection.commit();
        } catch (SQLSyntaxErrorException ignored) {
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException exp2) {
                exp2.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute("DROP TABLE IF EXISTS users");
            connection.commit();
        } catch (SQLSyntaxErrorException ignored) {
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exp2) {
                exp2.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, last_name, age) VALUES (?, ?, ?)")) {
            connection.setAutoCommit(false);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            connection.commit();
            System.out.printf("User с именем - %s добавлен в базу данных \n", name);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exp2) {
                exp2.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            connection.setAutoCommit(false);
            statement.setLong(1, id);
            statement.execute();
            connection.commit();
        } catch (Throwable ignored) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            connection.commit();
            while (result.next()) {
                list.add(new User(result.getString("name"), result.getString("last_name"), result.getByte("age")) {
                    {
                        this.setId(result.getLong("id"));
                    }
                });
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exp2) {
                exp2.printStackTrace();
            }
            throw new RuntimeException(e);
        }

        return list;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users")) {
            connection.setAutoCommit(false);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exp2) {
                exp2.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }
}
