package dao;

import entity.Role;
import entity.User;

import java.util.List;

public interface UserDao {
    User get(Integer id);
    User getByName(String name);
    void save(User user);
    void delete(User user);
    void update(User user);
    List<User> getAll();
    List<User> getAllByRoleName(String roleName);
}
