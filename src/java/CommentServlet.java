/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.CommentDB;
import Data.PostDB;
import Model.Comment;
import Model.Posts;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Hemanth
 */
public class CommentServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String comment_text = request.getParameter("comment");
            int post_id = Integer.parseInt(request.getParameter("post_id"));
            Users user = (Users)request.getSession().getAttribute("user");
            int user_id = user.getUserId();
            
            Posts thePost = new Posts();
            ArrayList<Comment> commentList = new ArrayList<Comment>();
            
            int count = 0;
            try
            {             
                if (user_id != 0) 
                {
                    count = CommentDB.InsertComment(post_id, user_id, comment_text);
                }
                thePost = PostDB.getPostsOnID(post_id);
                commentList = CommentDB.getCommentList(post_id);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
            if (count == 1) 
            {
                request.setAttribute("msgOfComment", "added the comment");
                request.setAttribute("thePost", thePost);
                request.setAttribute("commentList", commentList);
                RequestDispatcher rd = request.getRequestDispatcher("comment1.jsp");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("msgOfComment", "Cant add the comment");
                request.setAttribute("thePost", thePost);
                request.setAttribute("commentList", commentList);
                RequestDispatcher rd = request.getRequestDispatcher("comment1.jsp");
                rd.forward(request, response);
            }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        System.out.println("Reached the CommentServlet Get Method");
        
        String group_name = request.getParameter("group_name");
        int post_Id = Integer.parseInt(request.getParameter("post_id"));
        Posts thePost = new Posts();
        ArrayList<Comment> commentList = new ArrayList<Comment>();;
        try 
        {   
            thePost = PostDB.getPostsOnID(post_Id);
            commentList = CommentDB.getCommentList(post_Id);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!(thePost.getuName().isEmpty())) 
        {
            request.setAttribute("thePost", thePost);
            request.setAttribute("commentList", commentList);
            request.setAttribute("group_name", group_name);
            RequestDispatcher rd = request.getRequestDispatcher("comment1.jsp");
            rd.forward(request, response);
        }
        else
        {   
            request.setAttribute("errorMsg", "no such post found");
            RequestDispatcher rd = request.getRequestDispatcher("comment1.jsp");
            rd.forward(request, response);
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
