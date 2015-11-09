package DAO.Impl;

import DAO.DishTagDAO;
import core.DishTag;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ilia_Sholokhov on 05/10/15.
 */
public class DishTagDAOImpl implements DishTagDAO {

    public void addDishTag(@NotNull DishTag dishTag) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(dishTag);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateDishTag(@NotNull DishTag dishTag) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(dishTag);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List getAllDishTagsByIdRestaurant(@NotNull Integer id) throws SQLException {
        List dishTags = getAllDishTags();
        List<DishTag> dishTagsForRestaurant = new ArrayList<DishTag>();
        for (Object objDishTag : dishTags) {
            DishTag dishTag = (DishTag) objDishTag;
            if (dishTag.getIdRestaurant() == id) {
                dishTagsForRestaurant.add(dishTag);
            }
        }
        return dishTagsForRestaurant;
    }

    public List getAllDishTags() throws SQLException {
        Session session = null;
        List dishTags = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            dishTags = session.createCriteria(DishTag.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return dishTags;
    }

    public Set getSetDishTags() throws SQLException {
        List dishTags = getAllDishTags();
        Set<String> setDishTags = new HashSet<String>();
        for (Object objDishTag : dishTags) {
            DishTag dishTag = (DishTag) objDishTag;
            setDishTags.add(dishTag.getDishTag());
        }
        return setDishTags;
    }

    public DishTag deleteDishTag(@NotNull DishTag dishTag) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(dishTag);
            session.getTransaction().commit();
        } catch (Exception e) {
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return dishTag;
    }
}
