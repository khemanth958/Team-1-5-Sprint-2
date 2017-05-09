/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.CommentDB;
import Data.GroupDB;
import Model.Comment;
import Model.Group;
import Model.Posts;
import Model.Users;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import test.DbManager;

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
 * @author Hemanth Kumar
 */
public class CreateGroupServletTest {
    
    Group group = mock(Group.class);
    GroupDB groupDb = mock(GroupDB.class);
    
    
    public CreateGroupServletTest() {
    
    CreateGroupServlet Test = new CreateGroupServlet();
    }

    /**
     * Test of processRequest method, of class CreateGroupServlet.
     */
    /*
    @Test
    public void testProcessRequest() throws Exception {
        
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        
        Users newUser = mock(Users.class);
        
        CommentDB newCommentDB = mock(CommentDB.class);
        
        Comment newComment = mock(Comment.class);
        
        Group group = mock(Group.class);
        GroupDB groupDb = mock(GroupDB.class);
        
        group.setGroupID(10);
        group.setGroupName("testing");
        group.setGroupDescription("asdasd");
        group.setNumberOfGroupMembers(10);
        //int groupMembers = group.getNumberOfGroupMembers();
        when(request.getParameter("group_name")).thenReturn("testing");
        when(request.getParameter("group_description")).thenReturn("asdasd");
        when(request.getParameter("groupMembers")).thenReturn("10");
        when(groupDb.createGroup(group.getGroupName(), group.getGroupDescription(),group.getNumberOfGroupMembers())).thenReturn(1);
        when(request.getRequestDispatcher("GroupResult.jsp")).thenReturn(rd);
        when(request.getRequestDispatcher("create_group.jsp")).thenReturn(rd);
           
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
         
        new CreateGroupServlet().doPost(request, response);
        
        verify(rd).forward(request, response);
            
        String result = sw.getBuffer().toString().trim();
        System.out.println("Result: "+result);
                
       assertEquals("Group Created", result);
             
    } */
    
    /**
     * Test of processRequest method, of class CreateGroupServlet.
     */
    @Test
    public void testProcessRequest1() throws Exception {
        
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        
        Users newUser = mock(Users.class);
        
        CommentDB newCommentDB = mock(CommentDB.class);
        
        Comment newComment = mock(Comment.class);
        
        Group group = mock(Group.class);
        GroupDB groupDb = mock(GroupDB.class);
        
        group.setGroupID(10);
        group.setGroupName("testing");
        group.setGroupDescription("asdasd");
        group.setNumberOfGroupMembers(10);
        //int groupMembers = group.getNumberOfGroupMembers();
        when(request.getParameter("group_name")).thenReturn("testing");
        when(request.getParameter("group_description")).thenReturn("asdasd");
        when(request.getParameter("groupMembers")).thenReturn("10");
        when(groupDb.createGroup(group.getGroupName(), group.getGroupDescription(),group.getNumberOfGroupMembers())).thenReturn(1);
        when(request.getRequestDispatcher("GroupResult.jsp")).thenReturn(rd);
        when(request.getRequestDispatcher("create_group.jsp")).thenReturn(rd);
           
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
         
        new CreateGroupServlet().doPost(request, response);
        
        verify(rd).forward(request, response);
            
        String result = sw.getBuffer().toString().trim();
        System.out.println("Result: "+result);
                
       assertEquals("Group Could not be created", result);
             
    }

    
}
