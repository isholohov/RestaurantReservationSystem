package servlets;

import DAO.DishTagDAO;
import DAO.Factory;
import DAO.RestaurantDAO;
import core.DishTag;
import core.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ilia_Sholokhov on 08/10/15.
 */
public class RegistrationRestaurantServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String closestStation = request.getParameter("closeststation");
        String phoneNumber = request.getParameter("phonenumber");
        String openingHours = request.getParameter("openinghours");
        Double averageBill = Double.parseDouble(request.getParameter("averagebill"));
        String shortDescription = request.getParameter("shortdescription");
        String fullDescription = request.getParameter("fulldescription");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        RestaurantDAO restaurantDAO = Factory.getRestaurantDAO();
        DishTagDAO dishTagDAO = Factory.getDishTagDAO();
        List allRestaurants = null;

        try {
            allRestaurants = restaurantDAO.getAllRestaurants();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (allRestaurants != null) {
            for (Object objRestaurant : allRestaurants) {
                Restaurant restaurant = (Restaurant) objRestaurant;
                if (login.equals(restaurant.getLogin())) {
                    request.setAttribute("error", "That username is taken. Try again!");
                    request.getRequestDispatcher("/regrest.jsp").forward(request, response);
                }
            }
        }


        Restaurant restaurant = new Restaurant(login, password, name, address, shortDescription, fullDescription, phoneNumber, averageBill, openingHours, closestStation, 0);
        try {
            restaurantDAO.addRestaurant(restaurant);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DishTag dishTag1 = new DishTag(restaurant.getIdRestaurant(), request.getParameter("dishtag1"));
        DishTag dishTag2 = new DishTag(restaurant.getIdRestaurant(), request.getParameter("dishtag2"));
        DishTag dishTag3 = new DishTag(restaurant.getIdRestaurant(), request.getParameter("dishtag3"));
        if (!dishTag1.getDishTag().equals("") && !dishTag2.getDishTag().equals("") && !dishTag3.getDishTag().equals("")) {
            try {
                dishTagDAO.addDishTag(dishTag1);
                dishTagDAO.addDishTag(dishTag2);
                dishTagDAO.addDishTag(dishTag3);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("message", "Registration successful");
        request.getRequestDispatcher("/index").forward(request, response);
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
