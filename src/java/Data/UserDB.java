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

/**
 *
 * @author Akshay
 */
public class UserDB {
    
    public static Users getUser(String email) throws ClassNotFoundException, SQLException {
	        Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssdi_project", "root", "root");
		
                PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement("SELECT * from Users WHERE u_emailid = ?");
	            ps.setString(1, email);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Users user = new Users();
	            	user.setUserEmail(rs.getString("u_emailid"));
	            	user.setPassword(rs.getString("u_password"));
                        user.setUserName(rs.getString("u_name"));
	            	user.setUserID(rs.getInt("u_id"));
	               return user;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
                    rs.close();
                    ps.close();
                    connection.close();
	        }
	        return null;
	    }
    
    
}
