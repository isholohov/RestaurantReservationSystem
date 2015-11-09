package DAO.Impl;

import DAO.*;
import core.RegisteredUser;
import core.Reservation;
import core.Restaurant;
import core.Review;
import org.junit.Before;
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
    private ArrayList<RegisteredUser> registeredUsers;
    private ArrayList<Restaurant> restaurants;
    private ArrayList<Review> reviews;
    private ReviewDAO reviewDAO;

    @Before
    public void before() {
        reviewDAO = Factory.getReviewDAO();
        registeredUsers = new ArrayList<RegisteredUser>();
        RegisteredUser registeredUser;
        String phoneNumber = "899955412";
        for (int i = 1; i <= 10; i++) {
            registeredUser = new RegisteredUser("login_" + i, "password_" + i, phoneNumber + i, "name_" + i);
            registeredUsers.add(registeredUser);
        }
        restaurants = new ArrayList<Restaurant>();
        Restaurant restaurant;
        for (int i = 1; i <= 20; i++) {
            restaurant = new Restaurant("login_" + i, "password_" + i, "restaurant_" + i, "address_" + i,
                    "shortDescription_" + i, "fullDescription_" + i, "phoneNumber_" + i, i * (11 + i),
                    "openingHours_" + i, "closestStation" + i, i / 5 + 1);
            restaurants.add(restaurant);
        }
        reviews = new ArrayList<Review>();
    }


    @Test
    public void testReviewDAO() throws Exception {
        Review review;
        Random random = new Random();
        String comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse quam nibh, pellentesque at venenatis eget, sagittis sit amet tellus. Donec volutpat quis felis sed dignissim. Etiam blandit vel velit eget varius.";


        for (Restaurant restaurant : restaurants) {
            for (RegisteredUser registeredUser : registeredUsers) {
                review = new Review(restaurant.getIdRestaurant(), registeredUser.getIdRegisteredUser(), comment,
                        random.nextInt(4) + 1, new Date(115, 11, random.nextInt(30)));
                reviews.add(review);
                reviewDAO.addReview(review);
            }
        }

        review = reviews.get(random.nextInt(reviews.size()));
        reviewDAO.deleteReview(review);
        reviews.remove(review);
        assertFalse(reviews.contains(review));

        int id = restaurants.get(random.nextInt(restaurants.size())).getIdRestaurant();
        ArrayList<Review> reviewsFromDBByIdRestaurant = (ArrayList<Review>) reviewDAO.getAllReviewByIdRestaurant(id);

        for (Review r : reviewsFromDBByIdRestaurant) {
            assertTrue(r.getIdRestaurant() == id);
        }

        for (Review r : reviews) {
            reviewDAO.deleteReview(r);
        }
    }
}