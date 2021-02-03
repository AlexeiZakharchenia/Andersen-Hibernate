package utils.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.Role;
import entity.User;
import utils.UserUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class UserUtilsImpl implements UserUtils {
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean isValid(String userName, String pass) {
        try {
            User user = userDao.getByName(userName);
            return user.getPassword().equals(pass);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean hasRole(String userName, String roleName) {
        try {
            User user = userDao.getByName(userName);
            return user.getRoles()
                    .stream()
                    .anyMatch(r -> r.getName().equals(roleName));
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<String> getAllOtherRoles(String userName, String excepted) {
        try{
            User user = userDao.getByName(userName);
            return user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .filter(r->!r.equals(excepted))
                    .collect(Collectors.toList());
        } catch (Exception e){
            return new LinkedList<>();
        }

    }
}
