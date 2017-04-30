/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Users;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import test.DbManager;

/**
 *
 * @author Akshay
 */
public class UserDB {
    
    public static Users getUser(String email, String password) throws ClassNotFoundException, SQLException {
	        
                DbManager db = new DbManager();
		Connection connection = db.getConnection();
		Users user = null;
                PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement("SELECT u.u_Name as u_name, u.u_id as u_id, r.role_name as role_name from Users u, roles r, role_user_relationship ru WHERE u.u_id = ru.u_id and ru.role_id = r.role_id and u.u_emailid = ? and u.u_password = ?");
	            ps.setString(1, email);
                    ps.setString(2, password);
	            rs = ps.executeQuery();
	            while (rs.next()) 
                    {
                        user = new Users();
	            	user.setUserEmail(email);
	            	user.setPassword(password);
                        user.setUserName(rs.getString("u_name"));
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
    
    
}
