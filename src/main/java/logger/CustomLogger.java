package logger;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomLogger {
    static final String loggerPath = "loggedComplaints.log";

    public static void logError(Throwable t) {
        setup();
        try {
            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date today = Calendar.getInstance().getTime();
            String todayAsString = df.format(today);
            FileWriter fw = new FileWriter(loggerPath,true);
            BufferedWriter writer = new BufferedWriter(fw);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string


            writer.write(todayAsString + "\n" + sStackTrace + "\n");



            writer.close();

            // need to write the stacktrace

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logError(String debugMessage) {
        setup();
        try {
            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date today = Calendar.getInstance().getTime();
            String todayAsString = df.format(today);
            FileWriter fw = new FileWriter(loggerPath,true);
            BufferedWriter writer = new BufferedWriter(fw);

            writer.write(todayAsString + "\n" + debugMessage + "\n" + "\n");
            writer.close();
            //System.out.println("Wrote things to the file!");
        } catch (Throwable t) {}
    }

    private static void setup() {
        // create a file if it doesn't exist
        try {
            File myObj = new File(loggerPath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                //System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
