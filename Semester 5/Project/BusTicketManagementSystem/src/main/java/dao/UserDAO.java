package dao;
import config.DatabaseConnection;
import model.User;
import util.PasswordUtil;
import java.util.ArrayList;
import java.util.List;
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
    public List<User> getAllStaff(){
        List<User> staffList= new ArrayList<>();
        String sql= """
                SELECT user_id,username,full_name,role
                FROM users
                WHERE role='STAFF'
                ORDER BY user_id DESC
                """;
        try(Connection connection=DatabaseConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery()){
            while (resultSet.next()){
                staffList.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("full_name"),
                        resultSet.getString("role")
                ));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return staffList;
    }
    public boolean updateStaff(int userId,String username, String fullname){
        String sql= """
                UPDARE users
                SET username=? , full name= ? 
                WHERE user_id=? AND role='STAFF'
                """;
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,username);
            ps.setString(2,fullname);
            ps.setInt(3,userId);
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean setStaffActive(int userId,boolean active){
        String sql= """
                UPDATE users
                SET is_active=?
                WHERE user_id=? AND role='STAFF'
                """;
        try(Connection c=DatabaseConnection.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setBoolean(1,active);
            ps.setInt(2, userId);
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean resetStaffPassword(int userId, String password){
        String sql= """
                UPDATE users
                SET password_hash=?
                WHERE user_id=? AND role='STAFF'
                """;
        String hashedPassword=PasswordUtil.hashPassword(password);
        try(Connection c=DatabaseConnection.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,hashedPassword);
            ps.setInt(2,userId);
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean usernameExistsForOtherUser(String username,int userid){
        String sql= """
                SELECT user_id
                FROM users
                WHERE username=? AND user_id!=?
                """;
        try(Connection c=DatabaseConnection.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,username);
            ps.setInt(2,userid);
            try(ResultSet rs=ps.executeQuery()){
                return rs.next();
            }
        }catch (SQLException e){
            e.printStackTrace();
            return true;
        }
    }
    public boolean isStaffActive(int userid){
        String sql= """
                SELECT is_active
                FROM users
                Where user_id=? AND role = 'STAFF'
                """;
        try(Connection c=DatabaseConnection.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,userid);
            try(ResultSet rs=ps.executeQuery()){
                return rs.next() &&   rs.getBoolean("is_active");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
