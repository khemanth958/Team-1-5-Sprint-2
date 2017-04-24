/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import test.DbManager;

/**
 *
 * @author Viranchi
 */
public class RegisterUserTest {
    
    public RegisterUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of processRequest method, of class RegisterUser.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String email = "johnsmith@gmail.com";
        //String password = "John";
        
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        PreparedStatement pst = conn.prepareStatement("Select u_emailid from users where u_emailid=? ");
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
            assertEquals(email,rs.getString("u_emailid"));
        }
        else
        {
            assertEquals(email,rs.getString("u_emailid"));
        }
    }

    /**
     * Test of doPost method, of class RegisterUser.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String email = "johnsmith123@gmail.com";
        //String password = "John";
        
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        PreparedStatement pst = conn.prepareStatement("Select u_emailid from users where u_emailid=? ");
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
            assertEquals(email,rs.getString("u_emailid"));
        }
        else
        {
            assertEquals(email,null);
        }
    }
    
}
