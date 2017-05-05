/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import test.DbManager;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 *
 * @author Hemanth
 */
public class CommentServletTest {
    @Mock
 HttpServletRequest request;
 @Mock
 HttpServletResponse response;
 @Mock
 HttpSession session;

 @Mock
 RequestDispatcher rd;
        
    public CommentServletTest() {
    }
    
    
    @Before
    protected void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    }
    
    /**
     * Test of processRequest method, of class CommentServlet.
     */
    @Test
    public void testProcessRequestForValidUser() throws Exception {
        when(request.getParameter("comment")).thenReturn("this is a test comment");
        
        
        
        
        
        
        System.out.println("processRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String comment_text = "comment here";
        String post_id = "9";
        String emailid = "johnsmith@gmail.com";
        int user_id = 0;
        int count = 0;            
            DbManager db = new DbManager();
            java.sql.Connection conn = db.getConnection();
            if(conn == null)
            {
            System.out.println("Connection not established");
            }else
            {
                System.out.println("Connection Established");
                PreparedStatement pst = conn.prepareStatement("select u_id from users where u_emailid = ?");
                pst.setString(1, emailid);
                ResultSet rs = pst.executeQuery();
                while(rs.next()) 
                {
                    user_id = rs.getInt("u_id");
                }
                
                assertEquals(1,user_id);
            }
    }

    /**
     * Test of processRequest method, of class CommentServlet.
     */
    @Test
    public void testProcessRequestForComment() throws Exception {
        System.out.println("processRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String comment_text = "comment here";
        String post_id = "9";
        String emailid = "johnsmith@gmail.com";
        int user_id = 0;
        int count = 0;            
            DbManager db = new DbManager();
            java.sql.Connection conn = db.getConnection();
            if(conn == null)
            {
            System.out.println("Connection not established");
            }else
            {
                System.out.println("Connection Established");
                PreparedStatement pst = conn.prepareStatement("select u_id from users where u_emailid = ?");
                pst.setString(1, emailid);
                ResultSet rs = pst.executeQuery();
                while(rs.next()) 
                {
                    user_id = rs.getInt("u_id");
                }

                if (user_id != 0) 
                {
                    PreparedStatement pst1 = conn.prepareStatement("insert into comments(post_id, u_id, comment_t) values("+post_id+","+user_id+",?)");
                    pst1.setString(1, comment_text);
                    count = pst1.executeUpdate();
                }

            }             
            assertEquals(1,count);
    }
    
    /**
     * Test of processRequest method, of class CommentServlet.
     */
    @Test
    public void testProcessRequestForInvalidUser() throws Exception {
        System.out.println("processRequest");
        String emailid = "atestemail@gmail.com";
        int user_id = 0;          
            DbManager db = new DbManager();
            java.sql.Connection conn = db.getConnection();
            if(conn == null)
            {
            System.out.println("Connection not established");
            }else
            {
                System.out.println("Connection Established");
                PreparedStatement pst = conn.prepareStatement("select u_id from users where u_emailid = ?");
                pst.setString(1, emailid);
                ResultSet rs = pst.executeQuery();
                while(rs.next()) 
                {
                    user_id = rs.getInt("u_id");
                }
                
                assertNotEquals(1,user_id);
            }
    }
    
}
