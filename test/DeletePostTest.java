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
public class DeletePostTest 
{
    
    public DeletePostTest() 
    {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of processRequest method, of class DeletePost.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        
        String post_id = "9";
        
            DbManager db = new DbManager();
            java.sql.Connection conn = db.getConnection();
            if(conn == null)
            {
                System.out.println("Connection not established");
            }else
            {
                System.out.println("Connection Established");
                CallableStatement  myproc = conn.prepareCall("call Delete_User_Post(?,?)");
                myproc.setString(1,post_id);          
                myproc.registerOutParameter(2,Types.INTEGER);
                myproc.execute();
                int theCount = myproc.getInt(2);
                System.out.println(theCount);
                assertEquals(1,theCount);
            }
    }

    /**
     * Test of doPost method, of class DeletePost.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        DeletePost instance = new DeletePost();
        instance.doPost(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    
}
