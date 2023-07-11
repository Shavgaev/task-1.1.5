package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        byte age = 10;
        UserService userService = new UserServiceImpl();
//        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Zhenya","Shavgaev", age );
        userService.saveUser("ivan","ivanov", (byte) 25);
        userService.saveUser("Petr","Petrov", (byte) 43);
        userService.saveUser("Semen","Losov", (byte) 16);
        List <User> list = userService.getAllUsers();
        for (User user:list) {
            System.out.println(user);

        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}

