package servlets;

import DAO.Factory;
import DAO.RegisteredUserDAO;
import DAO.RestaurantDAO;
import core.RegisteredUser;
import core.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ilia_Sholokhov on 08/10/15.
 */
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        ;
        String password = request.getParameter("password");
        ;
        List allRegisteredUsers = null;
        List allRestaurants = null;
        boolean isSuccessfulLogon = false;

        if (login != null && password != null && !login.equals("") && !password.equals("")) {
            if (request.getParameter("usertype").equals("user")) {
                RegisteredUserDAO registeredUserDAO = Factory.getRegisteredUserDAO();
                try {
                    allRegisteredUsers = registeredUserDAO.getAllRegisteredUser();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (allRegisteredUsers != null) {
                    for (Object objRegisteredUser : allRegisteredUsers) {
                        RegisteredUser registeredUser = (RegisteredUser) objRegisteredUser;
                        if (login.equals(registeredUser.getLogin()) && password.equals(registeredUser.getPassword())) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("registereduser", registeredUser);
                            session.setAttribute("usertype", "registereduser");
                            isSuccessfulLogon = true;

                        } else if (!isSuccessfulLogon) {
                            request.setAttribute("errorauthentication", "An authentication error has occured. Try again!");
                        }
                    }
                }
            } else if (request.getParameter("usertype").equals("restaurant")) {
                RestaurantDAO restaurantDAO = Factory.getRestaurantDAO();
                try {
                    allRestaurants = restaurantDAO.getAllRestaurants();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (allRestaurants != null) {
                    for (Object objRestaurant : allRestaurants) {
                        Restaurant restaurant = (Restaurant) objRestaurant;
                        if (login.equals(restaurant.getLogin()) && password.equals(restaurant.getPassword())) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("restaurant", restaurant);
                            session.setAttribute("usertype", "restaurant");
                            isSuccessfulLogon = true;
                        } else if (!isSuccessfulLogon) {
                            request.setAttribute("errorauthentication", "An authentication error has occured. Try again!");
                        }

                    }
                }
            }
        }
        request.getRequestDispatcher("/index").forward(request, response);
    }
}
