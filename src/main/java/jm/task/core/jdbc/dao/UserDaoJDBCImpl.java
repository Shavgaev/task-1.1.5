package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        connection = getConnection();
    }

    public void createUsersTable() {

        try {
            connection.setAutoCommit(false);
            try (PreparedStatement createTableStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(50) NOT NULL, " +
                            "lastname VARCHAR(50) NOT NULL, " +
                            "age TINYINT NOT NULL)")) {
                createTableStatement.execute();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement dropTableStatement = connection.prepareStatement(
                    "DROP TABLE IF EXISTS users")) {
                dropTableStatement.execute();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement saveUserStatement = connection.prepareStatement(
                    "INSERT INTO users ( name, lastname, age) VALUES (?, ?, ?)")) {
                saveUserStatement.setString(1, name);
                saveUserStatement.setString(2, lastName);
                saveUserStatement.setByte(3, age);
                saveUserStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement removeUserStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE id = ?")) {
                removeUserStatement.setLong(1, id);
                removeUserStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List getAllUsers() {
        List users = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement getAllUsersStatement = connection.prepareStatement(
                    "SELECT * FROM users")) {
                ResultSet resultSet = getAllUsersStatement.executeQuery();
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastname");
                    byte age = resultSet.getByte("age");
                    users.add(new User(name, lastName, age));
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement cleanTableStatement = connection.prepareStatement(
                    "TRUNCATE TABLE users")) {
                cleanTableStatement.execute();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}





