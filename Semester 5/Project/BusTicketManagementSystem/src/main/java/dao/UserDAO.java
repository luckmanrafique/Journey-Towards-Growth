package dao;
import config.DatabaseConnection;
import model.User;
import util.PasswordUtil;

import javax.xml.crypto.Data;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean createUser(
        String username,
        String password,
        String fullname,
        String role
    ){
        String sql= """
                INSERT INTO users(username,password_hash,full_name,role)
                VALUES(?,?,?,?)
                """;
        String hashedPassword=PasswordUtil.hashPassword(password);
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1,username);
            ps.setString(2,hashedPassword);
            ps.setString(3,fullname);
            ps.setString(4,role);

            return ps.executeUpdate()>0;
        }catch (SQLException e){
            System.out.println("User creation failed!");
            e.printStackTrace();
            return false;
        }
    }

    public User authenticate(String username,String password){
        String sql= """
                SELECT user_id,username,password_hash,full_name,role
                FROM users
                WHERE username=? AND is_active=1
                """;
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,username);

            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    String storedHash=rs.getString("password_hash");
                    if(PasswordUtil.verifyPassword(password,storedHash)){
                        return new User(
                                rs.getInt("user_id"),
                                rs.getString("username"),
                                rs.getString("full_name"),
                                rs.getString("role")
                        );
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("Authentication failed!");
            e.printStackTrace();
        }
        return null;
    }
    public boolean usernameExists(String username){
        String sql="SELECT user_id from users WHERE username=?";
        try(Connection con= DatabaseConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,username);
            try(ResultSet rs=ps.executeQuery()){
                return rs.next();
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
