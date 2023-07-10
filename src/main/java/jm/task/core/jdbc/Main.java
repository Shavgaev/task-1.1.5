package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        byte age = 10;
        UserServiceImpl userService = new UserServiceImpl();
        //userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Zhenya","Shavgaev", age );
        userService.saveUser("ivan","ivanov", (byte) 25);
        userService.saveUser("Petr","Petrov", (byte) 43);
        userService.saveUser("Semen","Losov", (byte) 16);

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

        // реализуйте алгоритм здесь
    }
}