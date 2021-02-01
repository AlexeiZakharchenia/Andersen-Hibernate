package dao;

import entity.Role;

import java.util.List;

public interface RoleDao {
    Role get(Integer id);
    Role getByName(String roleName);
    void save(Role role);
    void delete(Role role);
    void update(Role role);
    List<Role> getAll();
    List<Role> getAllByUserName(String userName);
}
