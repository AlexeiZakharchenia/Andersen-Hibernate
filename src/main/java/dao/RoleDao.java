package dao;

import entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Role get(Integer id);
    Role getByName(String name);
    void save(Role role);
    void delete(Role role);
    void update(Role role);
    List<Role> getAll();
    List<Role> getAllByUserName(String username);
}
