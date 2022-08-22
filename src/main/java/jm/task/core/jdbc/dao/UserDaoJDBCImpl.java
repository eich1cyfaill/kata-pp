package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), last_name VARCHAR(40), age TINYINT)");

        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("DROP TABLE IF EXISTS users");

        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, last_name, age) VALUES (?, ?, ?)")) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.execute();

            System.out.printf("User с именем - %s добавлен в базу данных \n", name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")
        ) {

            statement.setLong(1, id);

            statement.execute();

        } catch (Throwable ignored) {

        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery("SELECT * FROM users");

            while(result.next()) {
                User user = new User(result.getString("name"), result.getString("last_name"), result.getByte("age"));
                user.setId(result.getLong("id"));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users")) {

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
