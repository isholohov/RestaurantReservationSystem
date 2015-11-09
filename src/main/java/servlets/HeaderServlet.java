package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ilia_Sholokhov on 06/10/15.
 */
public class HeaderServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usertype = null;
        if (request.getSession().getAttribute("usertype") != null) {
            usertype = request.getSession().getAttribute("usertype").toString();
        }


        if (usertype != null && usertype.equals("restaurant")) {
            request.getRequestDispatcher("headerrestaurant.jsp").include(request, response);
        } else if (usertype != null && usertype.equals("registereduser")) {
            request.getRequestDispatcher("headeruser.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("header.jsp").include(request, response);
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
