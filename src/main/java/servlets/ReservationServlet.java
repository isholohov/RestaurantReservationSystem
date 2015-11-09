package servlets;

import DAO.Factory;
import DAO.ReservationDAO;
import core.Reservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Ilia_Sholokhov on 12/10/15.
 */
public class ReservationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReservationDAO reservationDAO = Factory.getReservationDAO();
        Reservation reservation = null;
        Integer persons = null;
        Integer idRestaurant = null;
        Integer idRegisteredUser = null;
        Date date = null;
        Time time = null;
        if (request.getParameter("persons") != null) {
            persons = Integer.parseInt(request.getParameter("persons"));
        }
        if (request.getParameter("date") != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = new Date(simpleDateFormat.parse((request.getParameter("date"))).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("time") != null) {
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
            try {
                time = new Time(simpleTimeFormat.parse((request.getParameter("time"))).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("idRestaurant") != null) {
            idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
        }
        if (request.getParameter("idRegisteredUser") != null) {
            idRegisteredUser = Integer.parseInt(request.getParameter("idRegisteredUser"));
        }
        if (persons != null && date != null && idRegisteredUser != null && idRestaurant != null && time != null) {
            reservation = new Reservation(idRestaurant, idRegisteredUser, persons, date, time, (byte) 0);
        }
        if (reservation != null) {
            try {
                reservationDAO.addReservation(reservation);
                request.setAttribute("message", "You reserved a place. Waiting for confirmation");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/index").forward(request, response);
    }
}
