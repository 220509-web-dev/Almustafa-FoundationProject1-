package App;

import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestClass {
    public static void main(String[] args) {
        for (int i=2; i<300; i=i*i) System.out.println(i);



        }
    public static void iterate() {
        try {
            System.out.println("iterated");
            Connection conn = ConnectionFactory.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
