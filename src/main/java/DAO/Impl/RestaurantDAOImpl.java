package DAO.Impl;

import DAO.RestaurantDAO;
import core.Restaurant;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

public class RestaurantDAOImpl implements RestaurantDAO {

    public void addRestaurant(@NotNull Restaurant restaurant) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(restaurant);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateRestaurant(@NotNull Restaurant restaurant) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(restaurant);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Restaurant getRestaurantById(@NotNull Integer id) throws SQLException {
        Restaurant restaurant = null;
        List restaurants = getAllRestaurants();

        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant restaurantFromList = (Restaurant) restaurants.get(i);
            if (restaurantFromList.getIdRestaurant() == id) {
                restaurant = restaurantFromList;
            }
        }

        return restaurant;
    }

    public List getAllRestaurants() throws SQLException {
        Session session = null;
        List restaurants = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            restaurants = session.createCriteria(Restaurant.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return restaurants;
    }

    public void deleteRestaurant(@NotNull Restaurant restaurant) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(restaurant);
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
