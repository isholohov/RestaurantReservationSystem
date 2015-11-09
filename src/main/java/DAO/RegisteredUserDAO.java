package DAO;

import core.RegisteredUser;

import java.sql.SQLException;
import java.util.List;

public interface RegisteredUserDAO {
    void addRegisteredUser(RegisteredUser registeredUser) throws SQLException;

    void updateRegisteredUser(RegisteredUser registeredUser) throws SQLException;

    RegisteredUser getRegisteredUserById(Integer id) throws SQLException;

    List getAllRegisteredUser() throws SQLException;

    void deleteRegisteredUser(RegisteredUser registeredUser) throws SQLException;
}
