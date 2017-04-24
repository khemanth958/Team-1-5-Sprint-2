/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.DbManager;

/**
 *
 * @author Viranchi
 */
public class UpdatePost extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) 
        {
            /* TODO output your page here. You may use following sample code. */
            String post_id = request.getParameter("post_id");
            String post_text = request.getParameter("post_text");
            String group_name = request.getParameter("group_name");
            int count = 0;
            try
            {
                DbManager db = new DbManager();
                java.sql.Connection conn = db.getConnection();
                if(conn == null)
                {
                    System.out.println("Connection not established");
                }
                else
                {
                    System.out.println("Connection Established");
                    PreparedStatement pst = conn.prepareStatement("update posts set post = ? where post_id = ?");
                    pst.setString(1, post_text);
                    pst.setString(2, post_id);
                    count = pst.executeUpdate(); 
                        
                }
            }
            catch (SQLException e) 
            {
                System.out.println(e);
            }
            if (count == 1) 
            {
                request.setAttribute("post_id", post_id);
                request.setAttribute("group_name", group_name);
                RequestDispatcher rd = request.getRequestDispatcher("/Uncc_49ers.jsp");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("messageForPost", "the post cannot be upgraded");
                RequestDispatcher rd = request.getRequestDispatcher("/UpdatePost.jsp");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
            throws ServletException, IOException 
    {
        String params = request.getParameter("post_id");
        
        String[] sentences = params.split(";");
        String post_id = sentences[0];
        String group_name = sentences[1].split("=")[1];
        String post_text = "Cannot Find the Post";
        try
        {
            DbManager db = new DbManager();
            java.sql.Connection conn = db.getConnection();
            if(conn == null)
            {
                System.out.println("Connection not established");
            }else
            {
                System.out.println("Connection Established");
                PreparedStatement pst = conn.prepareStatement("select post from posts where post_id = ?");
                pst.setString(1, post_id);
                ResultSet rs = pst.executeQuery();
                while(rs.next()) 
                {
                    post_text = rs.getString("post");
                } 
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
        finally
        {
            request.setAttribute("post_text", post_text);
            request.setAttribute("post_id", post_id);
            request.setAttribute("group_name", group_name);
            RequestDispatcher rd = request.getRequestDispatcher("/UpdatePost.jsp");
            rd.forward(request, response);
        }
        
        //    processRequest(request, response);
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
