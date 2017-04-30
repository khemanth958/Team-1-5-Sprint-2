/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.GroupDB;
import Data.PostDB;
import Data.UserDB;
import Model.Group;
import Model.Posts;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import test.DbManager;

/**
 *
 * @author Viranchi
 */
public class NewPostsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            Group newGroup = new Group();
            Posts newPost = new Posts();
            HttpSession session = request.getSession();
            Users newUser = (Users)session.getAttribute("user");
            
            boolean flag = false;
            
            newPost.setUserPosts(request.getParameter("input1"));
            newGroup.setGroupName(request.getParameter("group_name"));
            
            System.out.println(newGroup.getGroupName());
            
            try
            {
                System.out.println("Connection Established");
                int theCount = PostDB.InsertPost(newUser, newGroup, newPost);
                if (theCount == 1) 
                {
                    flag = true;
                }
            }
            catch (ClassNotFoundException | SQLException e)
            {
                System.out.println(e);
            }
            if(flag)
            {
                ArrayList<Posts> newListOfPost = new ArrayList<Posts>();
            //System.out.println(groupName);
                try 
                {
                    newGroup = GroupDB.getGroupAttributes(newGroup.getGroupName());
                    boolean isJoined = GroupDB.IsJoined(newGroup.getGroupName(), newUser.getUserId());
                    newListOfPost = PostDB.getAllPost(newGroup.getGroupName());
                    System.out.println(newGroup.getGroupID());
                    request.setAttribute("posts", newListOfPost);
                    request.setAttribute("isJoined", isJoined);
                    request.setAttribute("group", newGroup);
                    //request.setAttribute("groupName", groupName);
                    RequestDispatcher rd = request.getRequestDispatcher("DisplayGroup.jsp");
                    rd.forward(request, response);
                } catch (ClassNotFoundException ex) 
                {
                    Logger.getLogger(GroupServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) 
                {
                    Logger.getLogger(GroupServlet.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
            else
            {
            System.out.println("Please enter correct credentials to login ");
            }
            /* TODO output your page here. You may use following sample code. */
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String groupName = request.getParameter("action");
        try {
            HttpSession session = request.getSession();
            System.out.println(groupName);
            String userEmail = request.getParameter("email1");
            String userSessionEmail = (String) session.getAttribute("email");
            System.out.println(userEmail);
              Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssdi_project","root","root");
	        PreparedStatement ps = null;
	        ResultSet rs = null;
                String userName1 = null;
	        ArrayList<Posts> postsList = new ArrayList<Posts>();
                try {
	            ps = connection.prepareStatement("select p.post as post, p.post_id as post_id,u.u_id as u_id, u.u_name as uname from posts p, users u, post_user_group_relationship pug, groups g where p.post_id = pug.p_id and pug.u_id = u.u_id and pug.g_id = g.g_id and g.g_name = ? and u.u_emailid = ?");
	            ps.setString(1, groupName);
                    ps.setString(2, userEmail);
	            rs = ps.executeQuery();
	            while (rs.next()) {
                        //Group group = new Group();
                        System.out.println("Posts Found");
	            	Posts user = new Posts();
	            	user.setPostId(rs.getInt("post_id"));
	            	user.setUserPosts(rs.getString("post"));
	                postsList.add(user);
                    }
	        } catch (SQLException e) {
	            System.out.println(e);
	        } 
                session.setAttribute("postList", postsList);
                session.setAttribute("groupName",groupName);
                session.setAttribute("email",userEmail);
            for(int i =0;i<postsList.size();i++){
                 System.out.println(postsList.get(i).getPostId());
                 System.out.println(postsList.get(i).getPostId());
                }
            
            request.setAttribute("groupName", groupName);
            System.out.println(groupName);
            RequestDispatcher rd = request.getRequestDispatcher("DisplayUserPost.jsp");
            rd.forward(request, response);
           }
           catch (ClassNotFoundException ex) {
            request.setAttribute("PostError", "Sorry No Posts For this Group");
            RequestDispatcher rd = request.getRequestDispatcher("DisplayGroup.jsp");
            rd.forward(request, response);
            Logger.getLogger(NewPostsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            request.setAttribute("PostError", "Sorry No Posts For this Group");
            RequestDispatcher rd = request.getRequestDispatcher("DisplayGroup.jsp");
            rd.forward(request, response);
            Logger.getLogger(NewPostsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
