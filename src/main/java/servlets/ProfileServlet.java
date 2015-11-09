package servlets;

import DAO.Factory;
import DAO.RestaurantDAO;
import DAO.ReviewDAO;
import core.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ProfileServlet extends HttpServlet {
    String page = "/profile.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Restaurant restaurant = null;
        List allReviewForRestaurant = null;
        Integer id = Integer.parseInt(request.getParameter("id"));
        RestaurantDAO restaurantDAO = Factory.getRestaurantDAO();
        ReviewDAO reviewDAO = Factory.getReviewDAO();
        try {
            restaurant = restaurantDAO.getRestaurantById(id);
            allReviewForRestaurant = reviewDAO.getAllReviewByIdRestaurant(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (restaurant != null) {
            request.setAttribute("restaurant", restaurant);
        }
        if (allReviewForRestaurant != null) {
            request.setAttribute("reviews", allReviewForRestaurant);
        }
        request.getRequestDispatcher(page).forward(request, response);
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