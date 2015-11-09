package DAO.Impl;

import DAO.RegisteredUserDAO;
import core.RegisteredUser;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisteredUserDAOImpl implements RegisteredUserDAO {

    public void addRegisteredUser(@NotNull RegisteredUser registeredUser) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(registeredUser);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public void updateRegisteredUser(@NotNull RegisteredUser registeredUser) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(registeredUser);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public RegisteredUser getRegisteredUserById(@NotNull Integer id) throws SQLException {
        RegisteredUser registeredUser = null;
        List registeredUsers = getAllRegisteredUser();

        for (int i = 0; i < registeredUsers.size(); i++) {
            RegisteredUser registeredUserFromList = (RegisteredUser) registeredUsers.get(i);
            if (registeredUserFromList.getIdRegisteredUser() == id) {
                registeredUser = registeredUserFromList;
            }
        }

        return registeredUser;
    }

    public List getAllRegisteredUser() throws SQLException {
        Session session = null;
        List registeredUsers = new ArrayList<RegisteredUser>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            registeredUsers = session.createCriteria(RegisteredUser.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return registeredUsers;
    }

    public void deleteRegisteredUser(@NotNull RegisteredUser registeredUser) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(registeredUser);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
