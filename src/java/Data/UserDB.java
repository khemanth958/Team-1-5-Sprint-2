/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Users;
import Util.PasswordUtil;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import test.DbManager;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import test.DbManager;

/**
 *
 * @author Hemanth
 */
public class UserDB {
    
    public Users getUser(String email, String password) throws ClassNotFoundException, SQLException {
	        
                DbManager db = new DbManager();
		Connection connection = db.getConnection();
		Users user = null;


/**
 *
 * @author Hemanth
 */
                PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement("SELECT u.u_Name as u_Name, u.u_id as u_id, r.role_name as role_name from users u, roles r, role_user_relationship ru WHERE u.u_id = ru.u_id and ru.role_id = r.role_id and u.u_emailid = ? and u.u_password = ?");
	            ps.setString(1, email);
                    ps.setString(2, password);
	            rs = ps.executeQuery();
	            while (rs.next()) 
                    {
                        user = new Users();
	            	user.setUserEmail(email);
	            	user.setPassword(password);
                        user.setUserName(rs.getString("u_Name"));
                        user.setUserID(rs.getInt("u_id"));
                        user.setRole(rs.getString("role_name"));
	            }
                    
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
                    connection.close();
                }
                return user;
	    }
    
    public static int addUser(Users user) throws SQLException {
	        DbManager db = new DbManager();
		Connection connection = db.getConnection();
	        PreparedStatement ps = null;

	        try {
	            ps = connection.prepareStatement("Insert into users(u_emailid,u_password,u_Name) value (?,?,? )");
	            ps.setString(1, user.getUserEmail());
	            ps.setString(2, user.getPassword());
	            ps.setString(3, user.getUserName());
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            connection.close();
	        }
	    }

    
}
