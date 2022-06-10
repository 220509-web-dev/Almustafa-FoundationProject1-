package services;

import DAO.UserDAO;
import DAO.UserDAOPostgres;
import Entities.User;
import utils.exceptions.InvalidUserException;

import java.sql.SQLException;

public class GetUserByUsername {
    public static User getUserByUsername (String username) throws SQLException {

        User user = new User();
        try {
            UserDAOPostgres userDAOPostgres = new UserDAOPostgres();
            user = userDAOPostgres.getUserByUsername(username);
            return user;
        } catch (Throwable e) {
            throw new SQLException();
        }

         }
}
