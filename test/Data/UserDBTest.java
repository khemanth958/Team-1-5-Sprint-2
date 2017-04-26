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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Viranchi
 */
public class UserDBTest {
    
    public UserDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getUser method, of class UserDB.
     */
    @Test
    public void testGetUser() throws Exception, ClassNotFoundException, SQLException {
	        Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssdi_mock", "root", "root");
		
                PreparedStatement ps = null;
	        ResultSet rs = null;
	            ps = connection.prepareStatement("SELECT * from Users WHERE u_emailid = ?");
	            ps.setString(1, "administr@xyz.com");
	            rs = ps.executeQuery();
                    Users user = new Users();
	            while (rs.next()) {
	            	user = new Users();
	            	user.setUserEmail(rs.getString("u_emailid"));
	            	user.setPassword(rs.getString("u_password"));
                        user.setUserName(rs.getString("u_name"));
	            	user.setUserID(rs.getInt("u_id"));
	            }
	        assertNotEquals("administr@xyz.com",user.getUserEmail());
	    }
    }
