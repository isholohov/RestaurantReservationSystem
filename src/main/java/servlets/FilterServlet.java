package servlets;

import DAO.DishTagDAO;
import DAO.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Ilia_Sholokhov on 05/10/15.
 */
public class FilterServlet extends HttpServlet {
    String page = "/filter.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DishTagDAO dishTagDAO = Factory.getDishTagDAO();
        Set setDishTags = null;
        try {
            setDishTags = dishTagDAO.getSetDishTags();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (setDishTags != null) {
            request.setAttribute("setDishTags", setDishTags);
        }
        request.getRequestDispatcher(page).include(request, response);
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
