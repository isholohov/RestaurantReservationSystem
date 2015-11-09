package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ilia_Sholokhov on 06/10/15.
 */
public class RegistrationDispatcherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("usertype");
        if (userType != null) {
            if (userType.equals("user")) {
                request.getRequestDispatcher("/reguser.jsp").forward(request, response);
            } else if (userType.equals("restaurant")) {
                request.getRequestDispatcher("/regrest.jsp").forward(request, response);
            }
        }
    }
}
