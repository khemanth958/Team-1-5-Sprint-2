/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.junit.Assert.*;
import test.DbManager;

/**
 *
 * @author Hemanth Kumar
 */
public class NewPostsServletTest {
    
    public NewPostsServletTest() {
    }

    /**
     * Test of processRequest method, of class NewPostsServlet.
     */
    @Test
    public void testProcessRequest() throws Exception {
        boolean flag;
        flag=false;
        try{
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        if(conn == null)
        {
	System.out.println("Connection not established");
        }else
        {
            System.out.println("Connection Established");
            CallableStatement  myproc = conn.prepareCall("call Insert_Post(?,?,?,?)");
                myproc.setString(1,"testemail.uncc.edu");
                myproc.setString(2,"mygroup");
                myproc.setString(3,"the test group which will fail");
                myproc.registerOutParameter(4,Types.INTEGER);
                myproc.execute();
                int theCount = myproc.getInt(4);
                System.out.println(theCount);
                assertTrue(theCount > 1);   
        }
        }catch (SQLException e) {
            fail("Should Thorw Exception");
        }
            /* TODO output your page here. You may use following sample code. */
        }
    }
