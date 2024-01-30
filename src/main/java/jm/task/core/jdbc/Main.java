package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl(new UserDaoJDBCImpl());

    private static final User u1 = new User("Gleb", "Simonov", (byte) 24);
    private static final User u2 = new User("Ivan", "Ivanov", (byte) 25);
    private static final User u3 = new User("Petr", "Pentov", (byte) 26);
    private static final User u4 = new User("Pop", "Popov", (byte) 100);

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser(u1.getName(), u1.getLastName(), u1.getAge());
        userService.saveUser(u2.getName(), u2.getLastName(), u2.getAge());
        userService.saveUser(u3.getName(), u3.getLastName(), u3.getAge());
        userService.saveUser(u4.getName(), u4.getLastName(), u4.getAge());
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
