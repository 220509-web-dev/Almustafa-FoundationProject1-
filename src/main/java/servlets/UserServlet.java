package servlets;

import DAO.UserDAO;
import Entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
