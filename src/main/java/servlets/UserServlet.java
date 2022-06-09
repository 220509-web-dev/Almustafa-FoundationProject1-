package servlets;

import DAO.UserDAO;
import Entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.exceptions.InvalidUserException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static services.GetUserById.getUserById;

public class UserServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final UserDAO userDAO;
    private String logstring;


    public UserServlet(ObjectMapper objectMapper, UserDAO userDAO) {
        this.objectMapper = objectMapper;
        this.userDAO = userDAO;
    }

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - User Servlet has been created.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if(id != null) {
            System.out.println("Looks like you want to get a user by their ID");
            User user = new User();
            String message;
            try {
                user = getUserById(id);
            } catch (SQLException e) {
                resp.setStatus(500);
                message = "Database error";
                // map a return statement ot JSON with ObjectMapper and return it
                resp.getWriter().write(objectMapper.writeValueAsString(message)); // parse into JSON?
                return;
            }  catch (NumberFormatException e) {
                resp.setStatus(400);
                message = "Invalid input";
                // map a return statement ot JSON with ObjectMapper and return it
                resp.getWriter().write(objectMapper.writeValueAsString(message)); // parse into JSON?
                return;
            } catch (InvalidUserException e) {
                resp.setStatus(400);
                message = "User not found";
                // map a return statement ot JSON with ObjectMapper and return it
                resp.getWriter().write(objectMapper.writeValueAsString(message)); // parse into JSON?
                return;
            } catch (Throwable t) {
                resp.setStatus(500);
                message = "Some error occurred";
                // map a return statement ot JSON with ObjectMapper and return it
                resp.getWriter().write(objectMapper.writeValueAsString(message)); // parse into JSON?
                return;
            }
            resp.setStatus(200);
            resp.getWriter().write(objectMapper.writeValueAsString(user));
            return;
        }


        List<User> userList = userDAO.getAllUsers();

        String result = objectMapper.writeValueAsString(userList);
        resp.setContentType("application/json");
        resp.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
