/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import java.lang.String.split(String regex);
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
public class InserOrDeleteServlet extends HttpServlet {

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
            out.println("<title>Servlet InserOrDeleteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InserOrDeleteServlet at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException 
    {
        String post_id = request.getParameter("post_id");
        String role = request.getParameter("role");
        String user_id = request.getParameter("user_id");
        //java.lang.String.split(String regex):
        
        String[] sentences = post_id.split(";");
        System.out.println(sentences[0]);
        System.out.println(sentences[1]);
        System.out.println(sentences[2]);
        String roles[]=sentences[1].split("=");
        System.out.println(roles[1]);
        String user_ids[] = sentences[2].split("=");
        System.out.println(user_ids[1]);
        String user_role= roles[1];
        String email_id= user_ids[1];
        
        
        
        System.out.println(user_id);
        System.out.println(role);
        System.out.println(post_id);
        
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
                CallableStatement  myproc = conn.prepareCall("call Insert_or_Delete(?,?,?,?)");
                myproc.setString(1,user_role);
                myproc.setString(2,email_id);
                myproc.setString(3,sentences[0]);          
                myproc.registerOutParameter(4,Types.INTEGER);
                myproc.execute();
                int theCount = myproc.getInt(4);
                System.out.println(theCount);
                if (theCount == 0) 
                {
                    response.getOutputStream().print("Deleted");
                }
                else if(theCount == 1)
                {
                    response.getOutputStream().print("Liked");
                }
            }
        }catch (SQLException e) {
            System.out.println(e);
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
