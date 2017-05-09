/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.GroupDB;
import Data.UserDB;
import Model.Users;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import static org.mockito.Mockito.*;
import javax.servlet.http.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
/**
 *
 * @author Viranchi
 */
public class LoginValidationTest extends Mockito{
    
    public LoginValidationTest() 
    {
        
    }
    
    @Before
    public void setUp() throws Exception {
     MockitoAnnotations.initMocks(this);
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of processRequest method, of class LoginValidation.
     */
    // Test with invalid credentials
    @Test
    public void testdoPost() throws Exception 
    {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        UserDB newUserDB = mock(UserDB.class);
        HttpSession session = mock(HttpSession.class);
        Users newUser = mock(Users.class);
        newUser.setUserEmail("johnsmith@gmail.com");
        newUser.setPassword("John");
        newUser.setRole("user");
        newUser.setUserID(1);
        newUser.setUserName("John Smith");
        String email = newUser.getUserEmail();
        String pass = newUser.getPassword();
            
        when(request.getParameter("email")).thenReturn("johnsmith@gmail.com");
        when(request.getParameter("password")).thenReturn("john");
        when(newUserDB.getUser(email, pass)).thenReturn(newUser);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/admin.jsp")).thenReturn(rd);
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(rd);
        //PrintWriter writer = new PrintWriter("somefile.txt");
        //when(response.getWriter()).thenReturn(writer);
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
         when(response.getWriter()).thenReturn(pw);
        
        new LoginValidation().doPost(request, response);

        //verify(session).setAttribute("user", newUser);
  
        verify(rd).forward(request, response);
            
        String result = sw.getBuffer().toString().trim();
        
        System.out.println(request.getAttribute("msgForNotLogin"));

        System.out.println("Result: "+result);

        assertEquals("Login successfull...", result);
        
            }
    
    // Testing with valid password for user
    @Test
    public void testdoPost1() throws Exception 
    {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        UserDB newUserDB = mock(UserDB.class);
        HttpSession session = mock(HttpSession.class);
        Users newUser = mock(Users.class);
        newUser.setUserEmail("johnsmith@gmail.com");
        newUser.setPassword("password");
        newUser.setRole("user");
        newUser.setUserID(1);
        newUser.setUserName("John Smith");
        String email = newUser.getUserEmail();
        String pass = newUser.getPassword();
           
        when(request.getParameter("email")).thenReturn("johnsmith@gmail.com");
        when(request.getParameter("password")).thenReturn("password");
        when(newUserDB.getUser(email, pass)).thenReturn(newUser);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/admin.jsp")).thenReturn(rd);
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(rd);
        //PrintWriter writer = new PrintWriter("somefile.txt");
        //when(response.getWriter()).thenReturn(writer);
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
         when(response.getWriter()).thenReturn(pw);
        
        new LoginValidation().doPost(request, response);

        //verify(session).setAttribute("user", newUser);
  
        verify(rd).forward(request, response);
            
        String result = sw.getBuffer().toString().trim();
        
        System.out.println(request.getAttribute("msgForNotLogin"));

        System.out.println("Result: "+result);

        assertEquals("Login successfull...", result);
        
            }

    /**
     * Test of doPost method, of class LoginValidation.
     */
    /*@Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String email = "johnsmith@gmail.com";
        String password = "Joe";
        
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        PreparedStatement pst = conn.prepareStatement("Select u_emailid,u_password from users where u_emailid=? and u_password=?");
        pst.setString(1, email);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
            assertEquals(email,rs.getString("u_emailid"));
            assertEquals(password,rs.getString("u_password"));
        }
        else
        {
            assertEquals(email,rs.getString("u_emailid"));
        }
        LoginValidation instance = new LoginValidation();
        instance.doPost(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
