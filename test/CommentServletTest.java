/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.CommentDB;
import Data.UserDB;
import Model.Comment;
import Model.Posts;
import Model.Users;
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
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.MockitoAnnotations;


/**
 *
 * @author Hemanth
 */
public class CommentServletTest {
    
       
    public CommentServletTest() {
    }
    
    
    @Before
    public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    }
    
    /**
     * Test of processRequest method, of class CommentServlet.
     */
    @Test
    public void testProcessRequestForInsertComment() throws Exception {
        
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        
        Users newUser = mock(Users.class);
        
        CommentDB newCommentDB = mock(CommentDB.class);
        
        Comment newComment = mock(Comment.class);
        
        Posts newPost = mock(Posts.class);
        
        
        newUser.setUserID(0);
        
        newPost.setPostId(0);
        
        newComment.setComment_text(null);
        
        
        System.out.println("The values are   " + newPost.getPostId() + newUser.getUserId()+ newComment.getComment_text());
        
        //when(request.getParameter("comment")).thenReturn("this is a test comment");
        //when(request.getParameter("post_id")).thenReturn("1");
        //when(request.getAttribute("user")).thenReturn("1,John Smith,password,johnsmith@gmail.com");
        when(request.getSession()).thenReturn(session);
        
        
          int  count = CommentDB.InsertComment(newPost.getPostId(), newUser.getUserId(), newComment.getComment_text());
          
          if(count<0)
          {
              fail();
          }
        
    }

    
    
  
}