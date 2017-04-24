/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Group;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akshay
 */
public class UpdatePostServlets extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdatePostServlets</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePostServlets at " + request.getContextPath() + "</h1>");
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
        System.out.println("Reaached Get Method");
                ResultSet rs = null;
                PreparedStatement ps1 = null;
                ResultSet rs1 = null;
                String post1 = "Hi";
                String action = request.getParameter("action");
                String post = request.getParameter("showpost");
                System.out.println(action);
                System.out.println(post);
                request.setAttribute("groupName", action);
                request.setAttribute("showpost",post);
                RequestDispatcher rd = request.getRequestDispatcher("UpdatePost.jsp");
                rd.forward(request, response);
                /*
        try {
            Class.forName("com.mysql.jdbc.Driver");
+        } catch (ClassNotFoundException ex) {
+            Logger.getLogger(UpdatePostServlet.class.getName()).log(Level.SEVERE, null, ex);
+        }
+                Connection connection;
+                try {
+                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssdi_project", "root", "root");
+                    PreparedStatement   ps = connection.prepareStatement("update posts set post = ? where post='Hi I am at uncc archives' ");
+                    ps.setString(1, post1);
+                    int i = ps.executeUpdate();
+                    if(i==0){
+                    System.out.println("The post has been updated");
+                }else{
+                    System.out.println("Not updated");
+                }
+                   /* request.setAttribute("groupName", action);
+            RequestDispatcher rd = request.getRequestDispatcher("DisplayGroup.jsp");
+            rd.forward(request, response);
+                } catch (SQLException ex) {
+                    Logger.getLogger(UpdatePostServlet.class.getName()).log(Level.SEVERE, null, ex);
+                }
+                */

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
       Group group = new Group();
        
        String postEditText = request.getParameter("fname");
        String action = request.getParameter("groupname");
        String post = request.getParameter("oldpost");
       System.out.println(action);
             
        request.setAttribute("groupName", action);
         System.out.println(post);
        System.out.println(postEditText);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdatePostServlets.class.getName()).log(Level.SEVERE, null, ex);
        }
               Connection connection;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssdi_project", "root", "root");
                    PreparedStatement   ps = connection.prepareStatement("update posts set post = ? where post= ? ");
                    ps.setString(1, postEditText);
                    ps.setString(2,post);
                    int i = ps.executeUpdate();
                    if(i==0){
                    System.out.println("The post has been updated");
                }else{
                    System.out.println("Not updated");
                }
                    request.setAttribute("groupName", action);
                    RequestDispatcher rd = request.getRequestDispatcher("DisplayGroup.jsp");
                    rd.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UpdatePostServlets.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        
        //processRequest(request, response);
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
