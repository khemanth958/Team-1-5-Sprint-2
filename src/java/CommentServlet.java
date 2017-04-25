/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            String post_id = request.getParameter("post_id");
            String emailid = (String)request.getSession().getAttribute("email");
            int user_id = 0;
            int count = 0;
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
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
            if (count == 1) 
            {
                request.setAttribute("msgOfComment", "added the comment");
                RequestDispatcher rd = request.getRequestDispatcher("/comment1.jsp?post_id="+post_id);
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("msgOfComment", "Cant add the comment");
                RequestDispatcher rd = request.getRequestDispatcher("/comment1.jsp?post_id="+post_id);
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
