/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.CallableStatement;
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
public class JoinGroupServletTest {
    
    public JoinGroupServletTest() {
    }
    
    /**
     * Test of doPost method, of class JoinGroupServlet.
     */
    @Test
    public void testDoPostForValidJoin() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;

            String group_name = "Laser Tag Corps";
            String email = "janedoe@yahoo.com";           
            
            try 
            {
                DbManager db = new DbManager();
                java.sql.Connection conn = db.getConnection();
                if (conn == null) 
                {
                    System.out.println("Connection not established");
                } else 
                {
                    System.out.println("Connection Established");
                    CallableStatement myproc = conn.prepareCall("call Join_Group(?,?,?)");
                    myproc.setString(1, group_name);
                    myproc.setString(2, email);                
                    myproc.registerOutParameter(3, Types.INTEGER);
                    myproc.execute();
                    int theCount = myproc.getInt(3);
                    System.out.println(theCount);
                    assertEquals(1,theCount);
                }
            } catch (Exception ex) 
            {
                ex.printStackTrace();
            }
    }
    
    /**
     * Test of doPost method, of class JoinGroupServlet.
     */
    @Test
    public void testDoPostForInvalidJoin() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;

            String group_name = "Laser Tag Corps";
            String email = "janedoe@yahoo.com";           
            
            try 
            {
                DbManager db = new DbManager();
                java.sql.Connection conn = db.getConnection();
                if (conn == null) 
                {
                    System.out.println("Connection not established");
                } else 
                {
                    System.out.println("Connection Established");
                    CallableStatement myproc = conn.prepareCall("call Join_Group(?,?,?)");
                    myproc.setString(1, group_name);
                    myproc.setString(2, email);                
                    myproc.registerOutParameter(3, Types.INTEGER);
                    myproc.execute();
                    int theCount = myproc.getInt(3);
                    System.out.println(theCount);
                    assertEquals(0,theCount);
                }
            } catch (Exception ex) 
            {
                ex.printStackTrace();
            }
    }
    
    /**
     * Test of doPost method, of class JoinGroupServlet.
     */
    @Test
    public void testDoPostForException() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;

            String group_name = "xyz";
            String email = "xyz@gm.com";           
            
            try 
            {
                DbManager db = new DbManager();
                java.sql.Connection conn = db.getConnection();
                if (conn == null) 
                {
                    System.out.println("Connection not established");
                } else 
                {
                    System.out.println("Connection Established");
                    CallableStatement myproc = conn.prepareCall("call Join_Group(?,?,?)");
                    myproc.setString(1, group_name);
                    myproc.setString(2, email);                
                    myproc.registerOutParameter(3, Types.INTEGER);
                    myproc.execute();
                    int theCount = myproc.getInt(3);
                    System.out.println(theCount);
                    assertEquals(0,theCount);
                }
            } catch (Exception ex) 
            {
                ex.printStackTrace();
            }
    }
}
