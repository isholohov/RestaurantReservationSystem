package DAO.Impl;

import DAO.Factory;
import DAO.RegisteredUserDAO;
import DAO.ReservationDAO;
import DAO.RestaurantDAO;
import core.RegisteredUser;
import core.Reservation;
import core.Restaurant;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class ReservationDAOImplTest {
    private ArrayList<RegisteredUser> registeredUsers;
    private ArrayList<Restaurant> restaurants;
    private ArrayList<Reservation> reservations;
    private ReservationDAO reservationDAO;

    @Before
    public void before() {
        reservationDAO = Factory.getReservationDAO();
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
        reservations = new ArrayList<Reservation>();
    }

    @Test
    public void testReservationDAO() throws Exception {
        Reservation reservation;

        Random random = new Random();

        for (Restaurant restaurant : restaurants) {
            for (RegisteredUser registeredUser : registeredUsers) {
                reservation = new Reservation(restaurant.getIdRestaurant(), registeredUser.getIdRegisteredUser(),
                        random.nextInt(4), new Date(115, 11, random.nextInt(30)),
                        new Time(10 + random.nextInt(10), random.nextInt(6) * 10, 0), (byte) random.nextInt(1));
                reservations.add(reservation);
                try {
                    reservationDAO.addReservation(reservation);
                } catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                }

            }
        }


        reservation = reservations.get(random.nextInt(reservations.size()));
        reservationDAO.deleteReservation(reservation);
        reservations.remove(reservation);
        assertFalse(reservations.contains(reservation));

        int id = restaurants.get(random.nextInt(restaurants.size())).getIdRestaurant();
        ArrayList<Reservation> reservationsFromDBByIdRestaurant = (ArrayList<Reservation>) reservationDAO.getAllReservationByIdRestaurant(id);

        for (Reservation r : reservationsFromDBByIdRestaurant) {
            assertTrue(r.getIdRestaurant() == id);
        }

        for (Reservation r : reservations) {
            reservationDAO.deleteReservation(r);
        }
    }
}