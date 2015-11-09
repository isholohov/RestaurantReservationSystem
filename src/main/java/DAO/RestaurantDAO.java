package DAO;

import core.Restaurant;

import java.sql.SQLException;
import java.util.List;

public interface RestaurantDAO {
    public void addRestaurant(Restaurant restaurant) throws SQLException;

    public void updateRestaurant(Restaurant restaurant) throws SQLException;

    public Restaurant getRestaurantById(Integer id) throws SQLException;

    public List getAllRestaurants() throws SQLException;

    public void deleteRestaurant(Restaurant restaurant) throws SQLException;
}
