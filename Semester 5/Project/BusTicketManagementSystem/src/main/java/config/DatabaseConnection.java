package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static final String URL="JDBC:mysql://localhost:3306/bus_ticket_db";
    public static final String USER="root";
    public static final String PASSWORD="";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );
        } catch (SQLException e){
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
}
