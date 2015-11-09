package DAO.Impl;

import DAO.Factory;
import DAO.RegisteredUserDAO;
import DAO.RestaurantDAO;
import DAO.ReviewDAO;
import core.RegisteredUser;
import core.Restaurant;
import core.Review;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by GlAz on 19.10.2015.
 */
public class ReviewDAOImplTest {

    @Ignore
    @Test
    public void testReviewDAO() throws Exception {

        ReviewDAO reviewDAO = Factory.getReviewDAO();
        RestaurantDAO restaurantDAO = Factory.getRestaurantDAO();
        RegisteredUserDAO registeredUserDAO = Factory.getRegisteredUserDAO();
        RegisteredUser user;
        Review review;
        ArrayList<RegisteredUser> registeredUsers = (ArrayList<RegisteredUser>) registeredUserDAO.getAllRegisteredUser();
        ArrayList<Review> reviews = (ArrayList<Review>) reviewDAO.getAllReview();
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) restaurantDAO.getAllRestaurants();
        Random random = new Random();
        String comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse quam nibh, pellentesque at venenatis eget, sagittis sit amet tellus. Donec volutpat quis felis sed dignissim. Etiam blandit vel velit eget varius.";


        for (Restaurant restaurant : restaurants) {
            user = registeredUsers.get(random.nextInt(registeredUsers.size()));
            review = new Review(restaurant.getIdRestaurant(), user.getIdRegisteredUser(), comment,
                    random.nextInt(4) + 1, new Date(115, 11, random.nextInt(30)));
            reviews.add(review);
            reviewDAO.addReview(review);
        }

        review = reviews.get(random.nextInt(reviews.size()));
        reviewDAO.deleteReview(review);
        reviews = (ArrayList<Review>) reviewDAO.getAllReview();
        assertFalse(reviews.contains(review));

        int id = restaurants.get(random.nextInt(restaurants.size())).getIdRestaurant();
        ArrayList<Review> reviewsFromDB = (ArrayList<Review>) reviewDAO.getAllReviewByIdRestaurant(id);

        for (Review r : reviewsFromDB) {
            assertTrue(reviews.contains(r));
            reviewDAO.deleteReview(r);
        }

        reviews = (ArrayList<Review>) reviewDAO.getAllReview();

        for (Review r : reviewsFromDB) {
            assertNotEquals(r.getIdRestaurant(), id);
        }
    }
}