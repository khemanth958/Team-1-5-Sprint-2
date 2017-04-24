/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.GroupDB;
import Model.Group;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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
public class GroupServlet extends HttpServlet {

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
        
        String group_name = request.getParameter("search_group");
        boolean flag = false;
        try
        {
            ArrayList Rows = new ArrayList();
            
            DbManager db = new DbManager();
            java.sql.Connection conn = db.getConnection();
            if(conn == null)
            {
                System.out.println("Connection not established");
            }
            else
            {
                System.out.println("Connection Established");
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM groups WHERE g_name like '%" + group_name + "%'");
                //pst.setString(1, group_name);
                //pst.setString(2, pass);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) 
                {
                    ArrayList row = new ArrayList();
                    for (int i = 1; i <= 3; i++) 
                    {
                        row.add(rs.getString(i));
                        System.out.println("row " + i);
                    }
                    Rows.add(row);
                    System.out.println("Group Found");
                    flag=true;
                }
            }
            
            if(flag == true)
            {
                {
                    request.setAttribute("ResultSet", Rows);
                    request.setAttribute("group_name", group_name);
                    RequestDispatcher rd = request.getRequestDispatcher("groupsearch.jsp");
                    rd.forward(request, response);   
                }
            }else
            {
                request.setAttribute("TheResultMessage", "No group Found");
                RequestDispatcher rd = request.getRequestDispatcher("groupsearch.jsp");
                rd.forward(request, response); 
            }
        }catch (SQLException e) {
            System.out.println(e);
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
            
        System.out.println("Reached Get Method");
            String action = request.getParameter("action");
            String userId = request.getParameter("userId");
            System.out.println(userId);
            //session.setAttribute("userId",userId);
            
            Group group = new Group();
            String groupName = request.getParameter("action");
            
            System.out.println(groupName);
            try {
                    List<Group> group2 = GroupDB.getGroup(groupName);;
                    System.out.println(group2.get(0).getGroupID());
                    request.setAttribute("groups", group2);
                    request.setAttribute("groupName", groupName);
                    RequestDispatcher rd = request.getRequestDispatcher("DisplayGroup.jsp");
                    rd.forward(request, response);
            } catch (ClassNotFoundException ex) {
                        Logger.getLogger(GroupServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(GroupServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
            
                
                
                
                /*
            HttpSession session = request.getSession();
            System.out.println("Reached Get mathod");
  
            Group group = new Group();
            String groupName = request.getParameter("search_group");
            
            System.out.println(groupName);
            group = GroupDB.getGroup(groupName);
            System.out.println(group.getGroupName());
            System.out.println(group.getGroupDescription());
            
            request.setAttribute("groups", group);
            RequestDispatcher rd = request.getRequestDispatcher("groups_1.jsp");
            rd.forward(request, response);
            
            

//Group tableGroupName =GroupDB.getGroup(groupName);
            
            
            
            //if(userType.equalsIgnoreCase("Participant")) {
            //session.setAttribute("theUser", user);
            //int participants = StudyDB.getParticipants(user.getEmail());
            //session.setAttribute("par", participants);
            //RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
            //rd.forward(request, response);
            //url = "/main.jsp";
            //} else if (userType.equalsIgnoreCase("Administrator")) {
            //session.setAttribute("theAdmin", user);
            //RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
            //rd.forward(request, response);
            //url = "/admin.jsp";
            //}
            //processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GroupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
        //String actions= request.getParameterValues("search_button");
        //System.out.println(actions);
        try {
          //  if(actions.equalsIgnoreCase("search_group")){
            HttpSession session = request.getSession();
            System.out.println("Reached Post mathod");
  
            Group group = new Group();
            String groupName = request.getParameter("search_group");
            
            System.out.println(groupName);
            if(groupName.isEmpty() || groupName.equalsIgnoreCase(" ")){
               List<Group> group1 = GroupDB.getGroups();
                request.setAttribute("groups", group1);
                System.out.println("Group Name is empty");
                for(int i =0;i<group1.size();i++){
                   
                    System.out.println(group1.get(i).getGroupID());
                }
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                
            }else if(!groupName.isEmpty()){
            List<Group> group2 = GroupDB.getGroup(groupName);
            //System.out.println(group.getGroupName());
            //System.out.println(group.getGroupDescription());
            
            request.setAttribute("groups", group2);
            RequestDispatcher rd = request.getRequestDispatcher("groups_1.jsp");
            rd.forward(request, response);
            
            }
             
            
            /* boolean flag = false;
            String g_id = request.getParameter("g_id");
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
            CallableStatement  myproc = conn.prepareCall("call Delete_Group1(?,?)");
            myproc.setString(1,g_id);
            myproc.registerOutParameter(2,Types.INTEGER);
            myproc.execute();
            int theCount = myproc.getInt(2);
            if (theCount == 0)
            {
            flag = true;
            }
            }
            }catch (SQLException e) {
            System.out.println(e);
            }
            finally
            {
            if(flag)
            response.getOutputStream().print("the group is deleted");
            }*/
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GroupServlet.class.getName()).log(Level.SEVERE, null, ex);
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
