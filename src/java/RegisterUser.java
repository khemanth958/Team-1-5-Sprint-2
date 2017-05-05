/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class RegisterUser extends HttpServlet {

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
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                     
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            String confPass = request.getParameter("confirm_password");
            System.out.println(pass);
            System.out.println(confPass);
            if (!pass.equals(confPass)) 
            {
                    request.setAttribute("msg", "Password does not match");
                    RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
                    rd.forward(request, response);
            }
            else
            {
                boolean flag = false;
                try{
                DbManager db = new DbManager();
                java.sql.Connection conn = db.getConnection();
                if(conn == null)
                {
                System.out.println("Connection not established");
                }else
                {
                    PreparedStatement pst1 = conn.prepareStatement("select u_emailid from users where u_emailid = '"+email+"'");
                    ResultSet rs1 = pst1.executeQuery();
                    if (rs1.next())
                    {
                        request.setAttribute("msg", "Email already present");
                        RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
                        rd.forward(request, response);
                    }
                    else
                    {
                        //System.out.println("Connection Established");
            //                pst.setString(1, email);
            //                pst.setString(2, pass);
            //                pst.setString(3, name);
                        
                        Users user = new Users(name,email,pass);
 
                        System.out.println(user.getUserId());
                        PreparedStatement pst = conn.prepareStatement("insert into users(u_name,u_emailid,u_password) values('"+user.getUserName()+"','"+user.getUserEmail()+"','"+user.getPassword()+"')");
                        int result = pst.executeUpdate();   
                        int userId = user.getUserId();
                        PreparedStatement ps = conn.prepareStatement("insert into user_role_relationship values("+2+",'" + userId +"')");
                        if (result != 0) 
                        {
                            request.setAttribute("Result", "User registered successfully with emailId: "+email);
                        }
                        else
                        {
                            request.setAttribute("Result", "User could not be registered");
                        }
                    }
                }
                }catch (SQLException e) {
                    System.out.println(e);
                    request.setAttribute("Result", "User could not be registered cause of an exception");
                }
                finally
                {
                    RequestDispatcher rd = request.getRequestDispatcher("RegisterResult.jsp");
                    rd.forward(request, response);
                }
            }
        }
    }
    
//    response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RegisterUser</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RegisterUser at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

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
