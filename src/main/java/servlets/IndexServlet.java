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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilia_Sholokhov on 27/09/15.
 */
public class IndexServlet extends HttpServlet {
    String page = "/index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List allRestaurants = null;
        RestaurantDAO restaurantDAO = Factory.getRestaurantDAO();
        try {
            allRestaurants = restaurantDAO.getAllRestaurants();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (allRestaurants != null) {
            request.setAttribute("allRestaurants", allRestaurants);
        }
        request.getRequestDispatcher(page).forward(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String[] valuesForFilter)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List allRestaurants = null;
        List<Restaurant> restaurantWithFilter = new ArrayList<Restaurant>();
        RestaurantDAO restaurantDAO = Factory.getRestaurantDAO();
        DishTagDAO dishTagDAO = Factory.getDishTagDAO();

        try {
            allRestaurants = restaurantDAO.getAllRestaurants();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (allRestaurants != null) {
            for (Object objRestaurant : allRestaurants) {
                Restaurant unchekedRestaurant = (Restaurant) objRestaurant;
                List dishTagsForRestaurant = null;
                try {
                    dishTagsForRestaurant = dishTagDAO.getAllDishTagsByIdRestaurant(unchekedRestaurant.getIdRestaurant());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (dishTagsForRestaurant != null) {
                    for (Object objDishTag : dishTagsForRestaurant) {
                        DishTag dishTag = (DishTag) objDishTag;
                        for (int i = 0; i < valuesForFilter.length; i++) {
                            if (dishTag.getDishTag().equals(valuesForFilter[i])) {
                                if (!restaurantWithFilter.contains(unchekedRestaurant)) {
                                    restaurantWithFilter.add(unchekedRestaurant);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (allRestaurants != null) {
            request.setAttribute("allRestaurants", restaurantWithFilter);
        }
        request.getRequestDispatcher(page).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] valuesForFilter = request.getParameterValues("dishTag");
        if (valuesForFilter != null) {
            processRequest(request, response, valuesForFilter);
        } else {
            processRequest(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] valuesForFilter = request.getParameterValues("dishTag");
        if (valuesForFilter != null) {
            processRequest(request, response, valuesForFilter);
        } else {
            processRequest(request, response);
        }
    }

}
