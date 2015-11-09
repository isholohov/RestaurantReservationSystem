package servlets;

import DAO.Factory;
import DAO.ReservationDAO;
import core.Reservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Ilia_Sholokhov on 13/10/15.
 */
public class AcceptReservationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReservationDAO reservationDAO = Factory.getReservationDAO();
        Integer idReservation = null;
        if (request.getParameter("idReservation") != null) {
            idReservation = Integer.parseInt(request.getParameter("idReservation"));
        }
        Reservation reservation = null;
        try {
            reservation = reservationDAO.getReservationById(idReservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (reservation != null) {
            reservation.setIsAccepted((byte) 1);
        }
        try {
            reservationDAO.updateReservation(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/reservationmanager").forward(request, response);
    }
}
