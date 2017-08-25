package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Pyltsin on 25.08.2017.
 */
public class UserMapExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, User> userById = new HashMap<>();
        List<User> out = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            User user = userById.get(id);
            if (user == null) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Date date = rs.getDate("registered");
                boolean enabled = rs.getBoolean("enabled");
                int calories = rs.getInt("calories_per_day");
                String role = rs.getString("role");
                Role roleIn = Role.valueOf(role);
                Set<Role> roles = new HashSet<Role>();
                roles.add(roleIn);
                user = new User(id, name, email, password, calories, enabled, roles);
                userById.put(user.getId(), user);
                out.add(user);
            }

            String role = rs.getString("role");
            Role roleIn = Role.valueOf(role);
            user.getRoles().add(roleIn);
        }
        return out;
    }
}
