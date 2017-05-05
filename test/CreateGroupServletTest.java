/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import static org.junit.Assert.*;
import test.DbManager;

/**
 *
 * @author Hemanth Kumar
 */
public class CreateGroupServletTest {
    
    public CreateGroupServletTest() {
    
    CreateGroupServlet Test = new CreateGroupServlet();
    
    
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    /**
     * Test of processRequest method, of class CreateGroupServlet.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        //HttpServletRequest request = null;
        //HttpServletResponse response = null;
        CreateGroupServlet instance = new CreateGroupServlet();
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        assertEquals(null,conn);   
        //instance.processRequest(request, response);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of doGet method, of class CreateGroupServlet.
     
    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        CreateGroupServlet instance = new CreateGroupServlet();
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        assertEquals(null,conn);
        instance.doGet(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doPost method, of class CreateGroupServlet.
     
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        CreateGroupServlet instance = new CreateGroupServlet();
        instance.doPost(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServletInfo method, of class CreateGroupServlet.
     
    @Test
    public void testGetServletInfo() {
        System.out.println("getServletInfo");
        CreateGroupServlet instance = new CreateGroupServlet();
        String expResult = "";
        String result = instance.getServletInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
