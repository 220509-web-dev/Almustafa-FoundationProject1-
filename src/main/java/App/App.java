package App;

import DAO.UserDAO;
import DAO.UserDAOPostgres;
import Entities.User;

public class App {
    public static void main(String[] args) {
        User getUserById;
        UserDAO userDAO = new UserDAOPostgres();
        User user = userDAO.getUserById(3);
        System.out.println(user.toString());
    }
}
