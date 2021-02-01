package dao.impl;

import dao.RoleDao;
import entity.Role;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sessionFactory.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.*;

public class RoleDaoImpl implements RoleDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Role get(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Role role = session.get(Role.class, id);
            session.getTransaction().commit();

            return role;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role getByName(String roleName) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Role role = (Role) session.createQuery("from Role r where r.name like :rlname")
                    .setParameter("rlname", roleName)
                    .getSingleResult();
            session.getTransaction().commit();
            return role;
        }catch (NoResultException e){
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Role role) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Role role) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Role persist = session.get(Role.class, role.getId());
            if (persist != null) {
                session.remove(persist);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Role persist = session.get(Role.class, role.getId());
            if (persist != null) {
                persist.setName(role.getName());
                session.update(persist);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Role> list = session.createQuery("from Role").getResultList();
            session.flush();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    @Override
    public List<Role> getAllByUserName(String userName) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Role> list = session.createQuery("select u.roles from User u where u.name like :usname")
                    .setParameter("usname", userName)
                    .list();
            session.getTransaction().commit();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new LinkedList<>();
    }
}
