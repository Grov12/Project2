package sample;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;


public class DBHandler {
    private final String dbName;
    private final String user;
    private final String password;
    private final String connectionURL;

    public DBHandler() {
        Properties p = loadProperties();
        dbName = p.getProperty("dbName");
        user = p.getProperty("user");
        password = p.getProperty("password");
        connectionURL = "jdbc:mysql://localhost/" + dbName + "?user=" + user + "&password=" + password + "&useSSL=false";
    }

    private Properties loadProperties() {
        Properties appProp = new Properties();
        try(FileInputStream fis = new FileInputStream("DB.properties")) {
            appProp.load(fis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  appProp;
    }


    public void printAll(){
        try(Connection conn = DriverManager.getConnection(connectionURL)){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM team");

            while (rs.next()){
                System.out.printf("%s%n", rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
