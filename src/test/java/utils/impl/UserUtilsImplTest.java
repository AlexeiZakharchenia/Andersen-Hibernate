package utils.impl;

import dao.UserDao;
import entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserUtilsImplTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserUtilsImpl userUtils = new UserUtilsImpl();

    @Before
    public void init(){
        User user = new User();
        user.setName("John");
        user.setPassword("qwerty");
        Mockito.when(userDao.getByName(ArgumentMatchers.anyString())).thenReturn(user);
    }

    @Test
    public void isValidTest(){
        Boolean result =  userUtils.isValid("John", "qwerty");
        Assert.assertTrue(result);
    }

    @Test
    public void isValidNullPasswordTest(){
        Boolean result =  userUtils.isValid("John", null);
        Assert.assertFalse(result);
    }
}