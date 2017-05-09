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

public class UserDBTest {
    
    UserDB newUserDB = new UserDB();
    String email;
    String password;
    
    public UserDBTest() {
        this.email = "johnsmith@gmail.com";
        this.password = "password";
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
       
    // Test of getUser method nd add user, of class UserDB.
    
    @Test
    public void testGetUser() throws Exception, ClassNotFoundException, SQLException {
        //UserDB Userdb1 = new UserDB();
        Users User1 = new  Users();
        User1 = newUserDB.getUser(email, password);
        String nameexpected = "John smith";
        assertEquals(nameexpected,User1.getUserName());
    }
    
     @Test
    public void testGetUser1() throws Exception, ClassNotFoundException, SQLException {
        //UserDB Userdb1 = new UserDB();
        Users User1 = new  Users();
        User1 = newUserDB.getUser(email, password);
        String nameexpected = "John Smith";
        assertEquals(nameexpected,User1.getUserName());
    }
    
    @Test
    public void testaddUser() throws Exception, ClassNotFoundException, SQLException {
        //UserDB Userdb1 = new UserDB();
        Users user1 = new Users();
        user1.setUserName("Nisarg");
        user1.setUserEmail("nisarg@gmail.com");
        user1.setPassword("password");
        int User2 = newUserDB.addUser(user1);
        int numberexpected = 1;
         assertEquals(numberexpected, User2);      
        
    }
      
     @Test
    public void testaddUser1() throws Exception, ClassNotFoundException, SQLException {
        //UserDB Userdb1 = new UserDB();
        Users user1 = new  Users();
        user1.setUserID(1);
        user1.setUserName("John Smith");
        user1.setUserEmail("johnsmith@gmail.com");
        user1.setPassword("password");
        int User2 = UserDB.addUser(user1);
        int numberexpected = 0;
         assertEquals(numberexpected, User2);      
        
    }
    
}

