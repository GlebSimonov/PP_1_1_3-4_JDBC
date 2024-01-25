package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserServiceImpl userService = new UserServiceImpl();
    public static void main(String[] args){
        userService.createUsersTable();
        userService.saveUser("Gleb", "Simonov", (byte) 24);
        userService.saveUser("Ivan", "Ivanov", (byte) 25);
        userService.saveUser("Petr", "Pentov", (byte) 26);
        userService.saveUser("Pop", "Popov", (byte) 100);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
