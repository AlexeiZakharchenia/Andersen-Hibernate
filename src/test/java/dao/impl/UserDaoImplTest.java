package dao.impl;

import dao.RoleDao;
import dao.UserDao;
import entity.Role;
import entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class UserDaoImplTest {
    UserDao userDao = new UserDaoImpl();
    RoleDao roleDao = new RoleDaoImpl();

    @Test
    public void saveDeleteAndGet() {
        List<Role> list = new LinkedList<>();
        System.out.println(roleDao.get(2));
        list.add(roleDao.get(2));
        User user = new User(null, "Johan", 19, list);
        userDao.save(user);
        User persist = userDao.get(user.getId());
        Assert.assertNotNull(persist);
        System.out.println(persist);
        userDao.delete(persist);
        Assert.assertNull(userDao.get(persist.getId()));
    }

    @Test
    public void getByName(){
        System.out.println(userDao.getByName("Tom"));
    }

    @Test
    public void update() {
        User user = userDao.get(5);
        user.setName("Alexei");
        user.setAge(20);

        userDao.update(user);

        Assert.assertEquals(user, userDao.get(5));
    }

    @Test
    public void getAll() {
        System.out.println(userDao.getAll());
    }

    @Test
    public void getAllByRole() {
        userDao.getAllByRoleName("admin").forEach(System.out::println);
    }
}