/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.UserDB;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;
import test.DbManager;

/**
 *
 * @author Akshay
 */
public class LoginValidation extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        String role = null;
        String user = request.getParameter("email");
        String pass = request.getParameter("password");
        boolean flag;
        flag=false;
        try{
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        if(conn == null)
        {
	System.out.println("Connection not established");
        }else
        {
            System.out.println("Connection Established");
            PreparedStatement pst = conn.prepareStatement("select r.role_name as role, u.u_emailid as email, u.u_password as password from users u, roles r, role_user_relationship rup\n" +
"where r.role_id = rup.role_id and rup.u_id = u.u_id and u.u_emailid = ? and u.u_password = ?");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                role = rs.getString("role");
                System.out.println("Correct login credentials");
                flag=true;
            } 
            
        }
        }catch (SQLException e) {
            System.out.println(e);
        }
        if(flag==true)
                {
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("email", user);
                    session.setAttribute("password", pass);
                    session.setAttribute("role", role);
                    //RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                    //rd.forward(request, response);   
                    
                    Users user1 = new Users();
                    //String userType = user.getUserType();
                    if(role.equalsIgnoreCase("user")) {
			session.setAttribute("theUser", user);
			//int participants = StudyDB.getParticipants(user.getEmail());
			user1 = UserDB.getUser(user);
                        session.setAttribute("user", user1);
                        System.out.println(user1.getUserEmail());
                        RequestDispatcher rd = request.getRequestDispatcher("userpage.jsp");
                         rd.forward(request, response);
			
			} else if (role.equalsIgnoreCase("Admin")) {
			session.setAttribute("theAdmin", user);
                        RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                        rd.forward(request, response);
			}
                
                
                }
        else{
            System.out.println("Please enter correct credentials to login ");
            request.setAttribute("msgForNotLogin", "Password or username does not match. Please re-enter.");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }
}
    
    
    
    /*
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginValidation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginValidation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
*/
    
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginValidation.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginValidation.class.getName()).log(Level.SEVERE, null, ex);
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
