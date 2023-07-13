package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    User user = new User();

    public void createUsersTable() {
        String creatSQL = (
                "CREATE TABLE IF NOT EXISTS users (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(50) NOT NULL, " +
                        "lastname VARCHAR(50) NOT NULL, " +
                        "age TINYINT NOT NULL)");
        try {
            try (Statement statement = Util.getConnection().createStatement()) {
                statement.executeUpdate(creatSQL);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropSQL = "DROP TABLE IF EXISTS Users";
        try {
            try (Statement statement = Util.getConnection().createStatement()) {
                statement.executeUpdate(dropSQL);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSql = "insert into Users (Name, LastName, Age)  values( ?, ? , ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(saveSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeSql = "delete from Users where id = ? ";
        try {
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(removeSql)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String getSql = "select * from Users";
        List<User> list = new ArrayList<>();
        try (PreparedStatement ps = Util.getConnection().prepareStatement(getSql)) {
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("Id"));
                user.setName(rs.getString("Name"));
                user.setLastName(rs.getString("LastName"));
                user.setAge(rs.getByte("Age"));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        String cleanSql = "TRUNCATE TABLE users";
        try (PreparedStatement ps = Util.getConnection().prepareStatement(cleanSql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}





