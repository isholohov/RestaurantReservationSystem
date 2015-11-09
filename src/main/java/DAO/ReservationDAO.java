package DAO;

import core.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO {
    public void addReservation(Reservation reservation) throws SQLException;

    public void updateReservation(Reservation reservation) throws SQLException;

    public Reservation getReservationById(Integer id) throws SQLException;

    public List getAllReservationByIdRestaurant(Integer id) throws SQLException;

    public List getAllReservationByIdRegisteredUser(Integer id) throws SQLException;

    public List getAllReservation() throws SQLException;

    public void deleteReservation(Reservation reservation) throws SQLException;
}
