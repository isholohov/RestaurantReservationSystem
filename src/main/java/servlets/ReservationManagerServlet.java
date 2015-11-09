package servlets;

import DAO.Factory;
import DAO.ReservationDAO;
import core.RegisteredUser;
import core.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ilia_Sholokhov on 13/10/15.
 */
public class ReservationManagerServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usertype = null;
        ReservationDAO reservationDAO = Factory.getReservationDAO();
        List reservations = null;
        if (request.getSession().getAttribute("usertype") != null) {
            usertype = request.getSession().getAttribute("usertype").toString();
        }

        if (usertype != null && usertype.equals("restaurant")) {
            Restaurant restaurant = null;
            if (request.getSession().getAttribute("restaurant") != null) {
                restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
            }
            try {
                if (restaurant != null) {
                    reservations = reservationDAO.getAllReservationByIdRestaurant(restaurant.getIdRestaurant());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (reservations != null) {
                request.setAttribute("reservations", reservations);
            }
            request.getRequestDispatcher("/reservationmanager.jsp").forward(request, response);
        } else if (usertype != null && usertype.equals("registereduser")) {
            RegisteredUser registeredUser = null;
            if (request.getSession().getAttribute("registereduser") != null) {
                registeredUser = (RegisteredUser) request.getSession().getAttribute("registereduser");
            }
            try {
                if (registeredUser != null) {
                    reservations = reservationDAO.getAllReservationByIdRegisteredUser(registeredUser.getIdRegisteredUser());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (reservations != null) {
                request.setAttribute("reservations", reservations);
            }
            request.getRequestDispatcher("/usersreservation.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
