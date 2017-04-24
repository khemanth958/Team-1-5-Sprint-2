/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.PostDB;
import Data.UserDB;
import Model.Posts;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        String text1 = request.getParameter("input1");
        String group_name = request.getParameter("group_name");
        System.out.println(group_name);
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
            HttpSession session = request.getSession();
            System.out.println("Connection Established");
            CallableStatement  myproc = conn.prepareCall("call Insert_Post(?,?,?,?)");
                myproc.setString(1,session.getAttribute("email").toString());
                myproc.setString(2,group_name);
                myproc.setString(3,text1);
                myproc.registerOutParameter(4,Types.INTEGER);
                myproc.execute();
                int theCount = myproc.getInt(4);
                System.out.println(theCount);
                if (theCount > 0) 
                {
                    flag = true;
                } 
            
        }
        }catch (SQLException e) {
            System.out.println(e);
        }
        if(flag==true)
                {
                    RequestDispatcher rd = request.getRequestDispatcher(group_name+".jsp");
                    rd.forward(request, response);   
                }
        else{
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
            throws ServletException, IOException {
            String action = request.getParameter("action");
        try {
            HttpSession session = request.getSession();
            //String groupName = request.getParameter("groupName");
            //System.out.println(groupName);
            String userEmail = (String) session.getAttribute("email");
            System.out.println(action);
            ArrayList<Posts> postList = new ArrayList<Posts>();
            postList = PostDB.getPosts(action);
            ArrayList<Posts> value = postList;
            //request.setAttribute("postList", postList);
            
            session.setAttribute("postList", postList);
            if(value == null){                
            request.setAttribute("PostError", "Sorry No Posts For this Group");
            request.setAttribute("groupName", action);
            RequestDispatcher rd = request.getRequestDispatcher("DisplayUserPost.jsp");
            rd.forward(request, response);
            }else{
            for(int i =0;i<postList.size();i++){
                   
                    System.out.println(postList.get(i).getPostId());
                }
            
            request.setAttribute("groupName", action);
            RequestDispatcher rd = request.getRequestDispatcher("DisplayUserPost.jsp");
            rd.forward(request, response);
            
            }
            
            
//RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
//rd.forward(request, response);
/*
Users user1 = new Users();
//String userType = user.getUserType();

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

*/

//processRequest(request, response);
        } catch (ClassNotFoundException ex) {
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
