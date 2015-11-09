package servlets;

import DAO.Factory;
import DAO.RegisteredUserDAO;
import core.RegisteredUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ilia_Sholokhov on 07/10/15.
 */
public class RegistrationUserServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phonenumber");
        String name = request.getParameter("name");

        RegisteredUserDAO registeredUserDAO = Factory.getRegisteredUserDAO();
        List allRegisteredUsers = null;

        try {
            allRegisteredUsers = registeredUserDAO.getAllRegisteredUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (allRegisteredUsers != null) {
            for (Object objRegisteredUser : allRegisteredUsers) {
                RegisteredUser registeredUser = (RegisteredUser) objRegisteredUser;
                if (login.equals(registeredUser.getLogin())) {
                    request.setAttribute("error", "That username is taken. Try again!");
                    request.getRequestDispatcher("/reguser.jsp").forward(request, response);
                }
            }
        }


        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setLogin(login);
        registeredUser.setPassword(password);
        registeredUser.setPhoneNumber(phoneNumber);
        registeredUser.setName(name);
        try {
            registeredUserDAO.addRegisteredUser(registeredUser);
        } catch (SQLException e) {
            e.printStackTrace();
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
