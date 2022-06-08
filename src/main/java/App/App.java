package App;

import DAO.UserDAO;
import DAO.UserDAOPostgres;
import Entities.User;

import java.util.List;

public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOPostgres();
//        User user = userDAO.getUserById(3);
//
//        List<User> userList = userDAO.getAllUsers();
//        System.out.println(userList);

        User test = userDAO.getUserByUsername("fwefew");
        System.out.println(test);
    }
}
