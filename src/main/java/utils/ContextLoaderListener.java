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
import servlets.UserNameServlet;
import servlets.UserServlet;

public class ContextLoaderListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[LOG] - Context Loader Listener has been initialized at - " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        UserDAOPostgres userDAOPostgres = new UserDAOPostgres();
        UserServlet userServlet = new UserServlet(mapper, userDAOPostgres);
        UserNameServlet userNameServlet = new UserNameServlet(mapper, userDAOPostgres);


        ServletContext context = sce.getServletContext();


        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("UserNameServlet", userNameServlet).addMapping("/username/*");
    }
}
