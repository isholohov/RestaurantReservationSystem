package DAO.Impl;

import DAO.Factory;
import DAO.RegisteredUserDAO;
import DAO.ReservationDAO;
import DAO.RestaurantDAO;
import core.RegisteredUser;
import core.Reservation;
import core.Restaurant;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class ReservationDAOImplTest {

    @Test
    public void testReservationDAO() throws Exception {

        ReservationDAO reservationDAO = Factory.getReservationDAO();
        RestaurantDAO restaurantDAO = Factory.getRestaurantDAO();
        RegisteredUserDAO registeredUserDAO = Factory.getRegisteredUserDAO();
        RegisteredUser user;
        Reservation reservation;
        ArrayList<RegisteredUser> registeredUsers = (ArrayList<RegisteredUser>) registeredUserDAO.getAllRegisteredUser();
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationDAO.getAllReservation();
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) restaurantDAO.getAllRestaurants();
        Random random = new Random();

        for (Restaurant restaurant : restaurants) {
            user = registeredUsers.get(random.nextInt(registeredUsers.size()));
            reservation = new Reservation(restaurant.getIdRestaurant(), user.getIdRegisteredUser(),
                    random.nextInt(4), new Date(115, 11, random.nextInt(30)),
                    new Time(10 + random.nextInt(10), random.nextInt(6) * 10, 0), (byte) random.nextInt(1));
            reservations.add(reservation);
            reservationDAO.addReservation(reservation);
        }

        reservation = reservations.get(random.nextInt(reservations.size()));
        reservationDAO.deleteReservation(reservation);
        reservations = (ArrayList<Reservation>) reservationDAO.getAllReservation();
        assertFalse(reservations.contains(reservation));

        int id = restaurants.get(random.nextInt(restaurants.size())).getIdRestaurant();
        ArrayList<Reservation> reservationsFromDB = (ArrayList<Reservation>) reservationDAO.getAllReservationByIdRestaurant(id);

        for (Reservation r : reservationsFromDB) {
            assertTrue(reservations.contains(r));
            reservationDAO.deleteReservation(r);
        }

        reservations = (ArrayList<Reservation>) reservationDAO.getAllReservation();

        for (Reservation r : reservationsFromDB) {
            assertNotEquals(r.getIdRestaurant(), id);
        }
    }
}