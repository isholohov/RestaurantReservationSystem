package DAO.Impl;

import DAO.Factory;
import DAO.RestaurantDAO;
import core.DishTag;
import core.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RestaurantDAOImplTest {

    private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
    private RestaurantDAO restaurantDAO;

    @Before
    public void before() {
        restaurantDAO = Factory.getRestaurantDAO();
    }

    @Test
    public void testRestaurantDAO() throws Exception {

        Restaurant restaurant;

        for (int i = 1; i <= 20; i++) {
            restaurant = new Restaurant("login_" + i, "password_" + i, "restaurant_" + i, "address_" + i,
                    "shortDescription_" + i, "fullDescription_" + i, "phoneNumber_" + i, i * (11 + i),
                    "openingHours_" + i, "closestStation" + i, i / 5 + 1);
            restaurants.add(restaurant);

            try {
                restaurantDAO.addRestaurant(restaurant);
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }

        ArrayList<Restaurant> restaurantsFromDB = (ArrayList<Restaurant>) restaurantDAO.getAllRestaurants();

        for (Restaurant r : restaurants) {
            assertTrue(restaurantsFromDB.contains(r));
        }

        Restaurant rest = restaurantDAO.getRestaurantById(restaurantsFromDB.get(5).getIdRestaurant());
        restaurantDAO.deleteRestaurant(rest);
//        Thread.sleep(30000);
        assertFalse(restaurantDAO.getAllRestaurants().contains(rest));
    }

    @After
    public void after() throws Exception {
        for (Restaurant restaurant : restaurants) {
            restaurantDAO.deleteRestaurant(restaurant);
        }
    }
}