package ua.pp.darknsoft.dao;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.pp.darknsoft.entity.User;

import java.util.List;

/**
 * Created by Dark on 11.01.2017.
 */
public interface UserDao {
    void createUser(User user);

    List<User> findUserByName(String username);

    void updateUser(User user);

    @PreAuthorize(value = "authenticated")
    void deleteUser(String username);
}
