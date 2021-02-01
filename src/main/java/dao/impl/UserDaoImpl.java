package dao.impl;


import dao.UserDao;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sessionFactory.HibernateUtil;

import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public User get(Integer id) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user =  session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getByName(String name) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> list = session
                    .createQuery("select distinct u from User u " +
                            "left join fetch u.roles rl where u.name like :usname")
                    .setParameter("usname", name)
                    .list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e){
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    @Override
    public void save(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User persist = session.get(User.class, user.getId());
            if(persist != null){
                session.remove(persist);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User persist = session.get(User.class, user.getId());
            if(persist != null){
                persist.setName(user.getName());
                persist.setAge(user.getAge());
                persist.setRoles(user.getRoles());
                session.update(persist);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> list =  session.createQuery("from User u left join fetch u.roles").getResultList();
            session.flush();
            session.getTransaction().commit();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    @Override
    public List<User> getAllByRoleName(String roleName) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> list =  session.createQuery("from User u left join fetch u.roles as rl " +
                    "where rl.name like :name")
                    .setParameter("name", roleName)
                    .list();
            session.getTransaction().commit();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new LinkedList<>();
    }
}
