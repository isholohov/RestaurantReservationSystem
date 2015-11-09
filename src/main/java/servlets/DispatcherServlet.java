package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ilia_Sholokhov on 06/10/15.
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("action");
        if (submit != null) {
            if (submit.equals("Login")) {
                request.getRequestDispatcher("authentication").forward(request, response);
            } else if (submit.equals("Sign up")) {
                request.getRequestDispatcher("/registrationdispatcher").forward(request, response);
            }
        }
    }
}
