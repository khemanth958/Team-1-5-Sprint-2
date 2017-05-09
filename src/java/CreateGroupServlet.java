

import Data.GroupDB;
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
 * @author Hemanth
 */
public class CreateGroupServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       doPost(request,response);   
}

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         doPost(request,response);  
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        String groupName = request.getParameter("group_name");
        String groupDescription = request.getParameter("group_description");
        String groupMembers = request.getParameter("groupMembers");
        int numberOfMembers = Integer.parseInt(groupMembers);
        boolean flag;
        flag=false;
             
            //System.out.println("Connection Established");
            //PreparedStatement pst = conn.prepareStatement("insert into groups(g_name,g_description,g_group_members) values('"+ groupName + "','" + groupDescription + "','" + groupMembers + "')");
            //pst.executeUpdate();
            int result;
            result = GroupDB.createGroup(groupName, groupDescription, numberOfMembers);
                    if (result != 0) 
                    {
                        request.setAttribute("Result","Group Created");
                        System.out.println("Group created");
                        //PrintWriter out = response.getWriter();
                        out.write("Group Created");
                        RequestDispatcher rd = request.getRequestDispatcher("GroupResult.jsp");
                        rd.forward(request, response);  
                    }
                    else
                    {
                        request.setAttribute("Result", "Group Could not be created" + groupName);
                        out.write("Group Could not be created");
                        RequestDispatcher rd = request.getRequestDispatcher("create_group.jsp");
                        rd.forward(request, response);
                    }
                  } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }





    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
