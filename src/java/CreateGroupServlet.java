

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
 * @author Akshay
 */
public class CreateGroupServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        String groupName = request.getParameter("group_name");
        String groupDescription = request.getParameter("group_description");
        String groupMembers = request.getParameter("groupMembers");
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
            //System.out.println("Connection Established");
            PreparedStatement pst = conn.prepareStatement("insert into groups(g_name,g_description,g_group_members) values('"+ groupName + "','" + groupDescription + "','" + groupMembers + "')");
            //pst.executeUpdate();
            int result = pst.executeUpdate();
                    if (result != 0) 
                    {
                        request.setAttribute("Result"," Group Created ");
                    }
                    else
                    {
                        request.setAttribute("Result", "Group Could not be created" + groupName);
                    }
                  }
        }catch (SQLException e) {
            System.out.println(e);
        }finally
                {
                    RequestDispatcher rd = request.getRequestDispatcher("GroupResult.jsp");
                    rd.forward(request, response);
                }
    }
}

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
