package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Pyltsin on 20.07.2017.
 */
public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(1, "Mike", "a@a", "1234", Role.ROLE_USER),
            new User(4, "Mike", "a@a", "1234", Role.ROLE_USER),
            new User(2, "Like", "b@a", "12345", Role.ROLE_USER),
            new User(3, "Pol", "b@a", "12345", Role.ROLE_USER, Role.ROLE_ADMIN)
            );
}
