/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
public class InserOrDeleteServletTest {
    
    public InserOrDeleteServletTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of processRequest method, of class InserOrDeleteServlet.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        
        String post_id = "9";
        String user_role= "users";
        String email_id= "johnsmith@gmail.com";
        
        
            DbManager db = new DbManager();
            java.sql.Connection conn = db.getConnection();
            if(conn == null)
            {
                System.out.println("Connection not established");
            }else
            {
                System.out.println("Connection Established");
                CallableStatement  myproc = conn.prepareCall("call Insert_or_Delete(?,?,?,?)");
                myproc.setString(1,user_role);
                myproc.setString(2,email_id);
                myproc.setString(3,post_id);          
                myproc.registerOutParameter(4,Types.INTEGER);
                myproc.execute();
                int theCount = myproc.getInt(4);
                System.out.println(theCount);
                assertEquals(1,theCount);
            }
    }

    /**
     * Test of doPost method, of class InserOrDeleteServlet.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("processRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        
        String post_id = "8";
        String user_role= "admin";
        String email_id= "vinu0404@gmail.com";
        
        
            DbManager db = new DbManager();
            java.sql.Connection conn = db.getConnection();
            if(conn == null)
            {
                System.out.println("Connection not established");
            }else
            {
                System.out.println("Connection Established");
                CallableStatement  myproc = conn.prepareCall("call Insert_or_Delete(?,?,?,?)");
                myproc.setString(1,user_role);
                myproc.setString(2,email_id);
                myproc.setString(3,post_id);          
                myproc.registerOutParameter(4,Types.INTEGER);
                myproc.execute();
                int theCount = myproc.getInt(4);
                System.out.println(theCount);
                assertEquals(0,theCount);
            }
    }
    
}
