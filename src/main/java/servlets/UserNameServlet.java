package servlets;

import DAO.UserDAOPostgres;
import Entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static services.GetUserByUsername.getUserByUsername;

public class UserNameServlet extends HttpServlet {


    private final ObjectMapper mapper;
    private final UserDAOPostgres userDAOPostgres;


    public UserNameServlet(ObjectMapper mapper, UserDAOPostgres userDAOPostgres) {
        this.mapper = mapper;
        this.userDAOPostgres = userDAOPostgres;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NullPointerException {
        String username = req.getParameter("username");

        if (username != null) {
            System.out.println("Looks like you want to get a user by their username");
            User user = new User();
            String message;
            try {
                user = getUserByUsername(username);
            } catch (SQLException e) {
                resp.setStatus(500);
                message = "Database error";
                // map a return statement ot JSON with ObjectMapper and return it
                resp.getWriter().write(mapper.writeValueAsString(message)); // parse into JSON?
                return;


            }
        }
    }
}
