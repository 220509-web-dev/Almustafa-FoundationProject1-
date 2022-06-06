package DAO;

import Entities.User;

import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static logger.CustomLogger.logError;

public class UserDAOPostgres implements UserDAO{

    @Override
    public User createUser(User user) {

        try{Connection conn = ConnectionFactory.getInstance().getConnection();
            String sql = "insert into football_app.app_users values (default,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());


            ps.execute();

            // getting the generated primary key value
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");

            user.setUserid(generatedId);// the book id changing for 0 to a non zero values means that it is saved
            return user;
        } catch (SQLException e) {
            logError(e);
            e.printStackTrace();
        } catch (Throwable t) {
            logError(t);
            t.printStackTrace();
        }
        return null;
    }

    @Override
    public  User getUserById(int id) {
        // try with resources. Automatically closes the connection once the try block finishes

        try{Connection conn = ConnectionFactory.getInstance().getConnection();
            String sql = "select * from football_app.app_users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(); // JDBC actually interacts with the DB

            //Get First Record
            rs.next();

            // Create a book and set the values of that book to the information in the result set
            User user = new User();

            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));

            return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();

        }

        String sql = "select * from football_app.app_users where username = ?";
        return null;
    }

    @Override
    public List<User> getAllUsers(){

        try{Connection conn = ConnectionFactory.getInstance().getConnection();
            String sql = "select * from football_app.app_users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<User>();

            while (rs.next()){
                User user = new User();
                user.setUserid(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
            return users;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {

        try{Connection conn = ConnectionFactory.getInstance().getConnection();
            String sql = "update football_app.app_users set username = ?, set password = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUserid());

            ps.execute();

            return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUserById(int id) {

        try{Connection conn = ConnectionFactory.getInstance().getConnection();
            String sql = "delete from football_app.app_users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}