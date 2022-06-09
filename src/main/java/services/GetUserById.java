package services;

import DAO.UserDAO;
import DAO.UserDAOPostgres;
import Entities.User;
import utils.exceptions.InvalidUserException;

import java.sql.SQLException;

public class GetUserById {
    public static User getUserById(String id_maybe) throws SQLException {
        int id;
        User user = new User();
        try {
            id = Integer.parseInt(id_maybe);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

        try {
            UserDAO userDAO = new UserDAOPostgres();
            user = userDAO.getUserById(id);
        } catch (Throwable e) {
            throw new SQLException();
        }
        if (user == null) {
            throw new InvalidUserException();
        }
        return user;
    }
}
