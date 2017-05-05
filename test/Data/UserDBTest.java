/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Users;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Viranchi
 */
public class UserDBTest {
    String email;
    String password;
    
    public UserDBTest() {
        this.email = "johnsmith@gmail.com";
        this.password = "password";
    }
    
       
    // Test of getUser method nd add user, of class UserDB.
    
    @Test
    public void testGetUser() throws Exception, ClassNotFoundException, SQLException {
        //UserDB Userdb1 = new UserDB();
        Users User1 = new  Users();
        User1 = UserDB.getUser(email, password);
        String nameexpected = "John smith";
        assertEquals(nameexpected,User1.getUserName());
    }
    
     @Test
    public void testGetUser1() throws Exception, ClassNotFoundException, SQLException {
        //UserDB Userdb1 = new UserDB();
        Users User1 = new  Users();
        User1 = UserDB.getUser(email, password);
        String nameexpected = "John Smith";
        assertEquals(nameexpected,User1.getUserName());
    }
    
     @Test
    public void testaddUser() throws Exception, ClassNotFoundException, SQLException {
        //UserDB Userdb1 = new UserDB();
        Users User1 = new  Users(3,"Nisarg","nisarg@gmail.com","password");
        int User2 = UserDB.addUser(User1);
        int numberexpected = 1;
         assertEquals(numberexpected, User2);      
        
    }
      
     @Test
    public void testaddUser1() throws Exception, ClassNotFoundException, SQLException {
        //UserDB Userdb1 = new UserDB();
        Users User1 = new  Users(1,"John Smith","johnsmith@gmail.com","password");
        int User2 = UserDB.addUser(User1);
        int numberexpected = 0;
         assertEquals(numberexpected, User2);      
        
    }  
        
        
        
        /*Class.forName("com.mysql.jdbc.Driver");
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
        assertNotEquals("administr@xyz.com",user.getUserEmail());         */
	    

    
    
    }
