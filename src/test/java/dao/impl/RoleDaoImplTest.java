package dao.impl;

import dao.RoleDao;
import entity.Role;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertTrue(roleDao.getAll().size() > 0);
    }

    @Test
    public void getAllByUserName(){
        Assert.assertTrue(roleDao.getAllByUserName("Tom").size() > 0);
    }
}