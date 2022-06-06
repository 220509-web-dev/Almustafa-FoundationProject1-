package App;

import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static logger.CustomLogger.logError;

public class TestClass {
    public static void main(String[] args) {
        for (int i=2; i<300; i=i*i) iterate();



        }
    public static void iterate() {
        try {
            int i = 1/0;
        } catch (Throwable t) {
            logError(t);
        }

    }
}
