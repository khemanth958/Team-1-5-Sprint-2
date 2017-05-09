/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.GroupDB;
import Data.UserDB;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import test.DbManager;
import Model.Group;

/**
 *
 * @author Akshay
 */
public class LoginValidation extends HttpServlet 
{
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
            throws ServletException, IOException, ClassNotFoundException, SQLException 
    {
     doPost(request,response);   
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
        Users currentUser = (Users)request.getSession().getAttribute("user");
        System.out.println("The user_id is -----------------"+currentUser.getUserId()+", role is---------"+currentUser.getRole());
        ArrayList<Group> listOfGroups = new ArrayList<Group>();
        try 
        {
            GroupDB newGroupDB = new GroupDB();
            listOfGroups = newGroupDB.getGroupsOfUser(currentUser.getRole(),currentUser.getUserId());
            request.setAttribute("groups", listOfGroups);
            RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
            rd.forward(request, response);
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
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
        response.setContentType("text/html;charset=UTF-8");
        try
        {
            /* TODO output your page here. You may use following sample code. */
            
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            boolean flag = false;
            ArrayList<Group> listOfGroups = null;
            UserDB newUserDB = new UserDB();
            Users newUser = newUserDB.getUser(email, pass);
            
            if (newUser != null) 
            {
                flag = true;
            }
            
            if (flag) 
            {
                GroupDB newGroupDB = new GroupDB();
                listOfGroups = newGroupDB.getGroupsOfUser(newUser.getRole(),newUser.getUserId());
                System.out.println(listOfGroups.get(1).getGroupName());
                HttpSession session = request.getSession();
                session.setAttribute("user", newUser);
                //session.setAttribute("role", newUser.getRole());
                request.setAttribute("groups", listOfGroups);
                request.setAttribute("msgForNotLogin", "Login Successfull");
                PrintWriter out = response.getWriter();
                out.write("Login successfull...");
                RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
                rd.forward(request, response);
                
            }
            else
            {
                
                System.out.println("Please enter correct credentials to login ");
                request.setAttribute("msgForNotLogin", "Password or username does not match. Please re-enter.");
                PrintWriter out = response.getWriter();
                out.write("Login unsuccessful...");
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
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
