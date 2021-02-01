package dao.impl;

import dao.RoleDao;
import entity.Role;
import entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class RoleDaoImplTest {
    RoleDao roleDao = new RoleDaoImpl();


    @Test
    public void saveDeleteAndGet() {
        Role role = new Role(null,"newRole");
        roleDao.save(role);
        Role persist = roleDao.getByName("newRole");
        Assert.assertNotNull(persist);
        roleDao.delete(persist);
        Assert.assertNull(roleDao.getByName("newRole"));
    }


    @Test
    public void update() {
        Role role = new Role(null,"newRole");
        roleDao.save(role);
        Role persist = roleDao.getByName("newRole");
        persist.setName("noNewRole");
        roleDao.update(persist);
        Assert.assertNotNull(roleDao.getByName("noNewRole"));
        roleDao.delete(persist);
    }

    @Test
    public void getAll() {
        System.out.println(roleDao.getAll());
    }

    @Test
    public void getAllByUserName(){
      roleDao.getAllByUserName(new String("Tom")).forEach(System.out::println);
    }
}