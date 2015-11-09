package DAO.Impl;

import DAO.Factory;
import DAO.RegisteredUserDAO;
import core.RegisteredUser;
import core.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegisteredUserDAOImplTest {

    private RegisteredUserDAO registeredUserDAO;
    private ArrayList<RegisteredUser> registeredUsers = new ArrayList<RegisteredUser>();

    @Before
    public void before() throws Exception {
        registeredUserDAO = Factory.getRegisteredUserDAO();
    }

    @Test
    public void testRegisteredUserDAO() throws Exception {

        RegisteredUser registeredUser;
        String phoneNumber = "899955412";
        for (int i = 1; i <= 10; i++) {
            registeredUser = new RegisteredUser("login_" + i, "password_" + i, phoneNumber + i, "name_" + i);
            registeredUsers.add(registeredUser);

            try {
                registeredUserDAO.addRegisteredUser(registeredUser);
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }

        List<RegisteredUser> registeredUsersFromDB = (ArrayList<RegisteredUser>) registeredUserDAO.getAllRegisteredUser();

        for (RegisteredUser user : registeredUsers) {
            assertTrue(registeredUsersFromDB.contains(user));
        }

        RegisteredUser user = registeredUserDAO.getRegisteredUserById(registeredUsersFromDB.get(5).getIdRegisteredUser());
        registeredUsers.remove(user);

        for (RegisteredUser r : registeredUsers) {
            registeredUserDAO.deleteRegisteredUser(r);
        }

        registeredUserDAO.deleteRegisteredUser(user);
        assertFalse(registeredUserDAO.getAllRegisteredUser().contains(user));
    }

}