package servlets;

import DAO.Factory;
import DAO.RestaurantDAO;
import DAO.ReviewDAO;
import core.Restaurant;
import core.Review;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ilia_Sholokhov on 12/10/15.
 */
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewDAO reviewDAO = Factory.getReviewDAO();
        Integer idRestaurant = null;
        Integer idRegisteredUser = null;
        String comment = null;
        Integer rating = null;
        Date date = new Date(new java.util.Date().getTime());
        if (request.getParameter("idRestaurant") != null) {
            idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
        }
        if (request.getParameter("idRegisteredUser") != null) {
            idRegisteredUser = Integer.parseInt(request.getParameter("idRegisteredUser"));
        }
        if (request.getParameter("comment") != null) {
            comment = request.getParameter("comment");
        }
        if (request.getParameter("rating") != null) {
            rating = Integer.parseInt(request.getParameter("rating"));
        }

        if (idRegisteredUser != null && idRestaurant != null && comment != null && rating != null) {
            Review review = new Review(idRestaurant, idRegisteredUser, comment, rating, date);
            try {
                reviewDAO.addReview(review);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        RestaurantDAO restaurantDAO = Factory.getRestaurantDAO();
        Restaurant restaurant = null;
        List reviews = null;
        try {
            reviews = reviewDAO.getAllReviewByIdRestaurant(idRestaurant);
            restaurant = restaurantDAO.getRestaurantById(idRestaurant);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Double sumOfRating = 0.0;
        Double averageRating = 0.0;

        if (reviews != null) {
            for (Object objReview : reviews) {
                Review review = (Review) objReview;
                sumOfRating += review.getRating();
            }
            averageRating = sumOfRating / reviews.size();
        }

        if (restaurant != null) {
            restaurant.setRating(averageRating);
        }

        try {
            restaurantDAO.updateRestaurant(restaurant);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("message", "Thank for a review.");
        request.getRequestDispatcher("/index").forward(request, response);

    }
}
