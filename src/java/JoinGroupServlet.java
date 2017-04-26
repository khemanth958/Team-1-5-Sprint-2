/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.DbManager;

/**
 *
 * @author Viranchi
 */
public class JoinGroupServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet JoinGroupServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet JoinGroupServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        
            String parameters = request.getParameter("group_name");
            String[] sentences = parameters.split(";");
            System.out.println(sentences[0]);
            String group_name = sentences[0];
            String email = sentences[1].split("=")[1];           
            
            try 
            {
                DbManager db = new DbManager();
                java.sql.Connection conn = db.getConnection();
                if (conn == null) 
                {
                    System.out.println("Connection not established");
                } else 
                {
                    System.out.println("Connection Established");
                    CallableStatement myproc = conn.prepareCall("call Join_Group(?,?,?)");
                    myproc.setString(1, group_name);
                    myproc.setString(2, email);                
                    myproc.registerOutParameter(3, Types.INTEGER);
                    myproc.execute();
                    int theCount = myproc.getInt(3);
                    System.out.println(theCount);
                    if (theCount == 0) 
                    {
                        response.getOutputStream().print("Cant join. Member Limit reached");
                    } else if (theCount == 1) 
                    {
                        response.getOutputStream().print("Joined");
                    }
                }
            } catch (Exception ex) 
            {
                ex.printStackTrace();
            }
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
