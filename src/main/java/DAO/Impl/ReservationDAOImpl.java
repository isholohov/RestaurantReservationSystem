package DAO.Impl;

import DAO.ReservationDAO;
import core.Reservation;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilia_Sholokhov on 10/10/15.
 */
public class ReservationDAOImpl implements ReservationDAO {
    public void addReservation(@NotNull Reservation reservation) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(reservation);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateReservation(@NotNull Reservation reservation) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(reservation);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Reservation getReservationById(@NotNull Integer id) throws SQLException {
        Reservation reservation = null;
        List reservations = getAllReservation();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservationFromList = (Reservation) reservations.get(i);
            if (reservationFromList.getIdReservation() == id) {
                reservation = reservationFromList;
            }
        }

        return reservation;
    }

    public List getAllReservationByIdRestaurant(@NotNull Integer id) throws SQLException {
        List allReservation = getAllReservation();
        List<Reservation> reservationsForRestaurant = new ArrayList<Reservation>();
        for (Object objReservation : allReservation) {
            Reservation reservation = (Reservation) objReservation;
            if (reservation.getIdRestaurant() == id) {
                reservationsForRestaurant.add(reservation);
            }
        }
        return reservationsForRestaurant;
    }

    public List getAllReservationByIdRegisteredUser(@NotNull Integer id) throws SQLException {
        List allReservation = getAllReservation();
        List<Reservation> reservationsForRegisteredUser = new ArrayList<Reservation>();
        for (Object objReservation : allReservation) {
            Reservation reservation = (Reservation) objReservation;
            if (reservation.getIdRegisteredUser() == id) {
                reservationsForRegisteredUser.add(reservation);
            }
        }
        return reservationsForRegisteredUser;
    }

    public List getAllReservation() throws SQLException {
        Session session = null;
        List reservations = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            reservations = session.createCriteria(Reservation.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return reservations;
    }

    public void deleteReservation(@NotNull Reservation reservation) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(reservation);
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
