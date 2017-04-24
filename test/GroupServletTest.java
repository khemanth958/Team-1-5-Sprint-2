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
 * @author Kavya
 */
public class GroupServletTest {
    
    public GroupServletTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of processRequest method, of class GroupServlet.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String name = "Uncc_49ers";;
        String partOfname = "Uncc_49";
        
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        PreparedStatement pst = conn.prepareStatement("Select g_name from groups where g_name like '%" + partOfname +"%'");
        //pst.setString(1,name);
        ResultSet rs = pst.executeQuery();
        //name = rs.getString("g_name");
        if (rs.next())
        {            
            assertEquals(name,rs.getString("g_name"));
        }
        else
        {
            assertEquals(name, null);
        }
        
        //GroupServlet instance = new GroupServlet();
        //instance.processRequest(request, response);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of doPost method, of class GroupServlet.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String name = "Uncc_49ers";;
        String partOfname = "laser";
        
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        PreparedStatement pst = conn.prepareStatement("Select g_name from groups where g_name like '%" + partOfname +"%'");
        //pst.setString(1,name);
        ResultSet rs = pst.executeQuery();
        //name = rs.getString("g_name");
        if (rs.next())
        {            
            assertEquals(name,rs.getString("g_name"));
        }
        else
        {
            assertEquals(name, null);
        }
    }
}
