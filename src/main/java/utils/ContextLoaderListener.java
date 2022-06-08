package utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import java.time.LocalDateTime;

import DAO.UserDAO;
import DAO.UserDAOPostgres;
import Entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import servlets.UserServlet;

public class ContextLoaderListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[LOG] - Context Loader Listener has been initialized at - " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        UserDAO userDAO = new UserDAOPostgres();
        UserServlet userServlet = new UserServlet(mapper, userDAO);


        ServletContext context = sce.getServletContext();

        ServletRegistration.Dynamic servletRegistration = context.addServlet("UserServlet", userServlet);
        servletRegistration.addMapping("/users/*");

    }
}
