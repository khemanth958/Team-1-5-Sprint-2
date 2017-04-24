/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Group;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akshay
 */
public class GroupDB {
    
    public static ArrayList<Group> getGroup(String groupName) throws ClassNotFoundException, SQLException {
	        
        Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssdi_project","root","root");
		
                PreparedStatement ps = null;
	        ResultSet rs = null;
	        ArrayList<Group> groupList = new ArrayList<Group>();
                try {
	            ps = connection.prepareStatement("SELECT * from groups WHERE g_name = ?");
	            ps.setString(1, groupName);
	            rs = ps.executeQuery();
	            while (rs.next()) {
                        //Group group = new Group();
                        System.out.println("Group Found");
	            	Group user = new Group();
	            	user.setGroupID(rs.getInt("g_id"));
	            	user.setGroupName(rs.getString("g_name"));
	            	user.setGroupDescription(rs.getString("g_description"));
	            	user.setNumberOfGroupMembers(rs.getInt("g_group_members"));
	            	groupList.add(user);
                        return groupList;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
                    rs.close();
                    ps.close();
                    connection.close();
	        }
	        return null;
	    }
     
    
    
    
public static List<Group> getGroups() throws ClassNotFoundException, SQLException {
	        Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssdi_project", "root", "root");
		
                PreparedStatement ps = null;
	        ResultSet rs = null;
	       ArrayList<Group> groupList = new ArrayList<Group>();
	        try {
	            ps = connection.prepareStatement("SELECT * from groups");
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Group group = new Group();
	               	group.setGroupID(rs.getInt("g_id"));
	            	group.setGroupName(rs.getString("g_name"));
	            	group.setGroupDescription(rs.getString("g_description"));
	            	group.setNumberOfGroupMembers(rs.getInt("g_group_members"));
                        System.out.println(group.getGroupID());
	                groupList.add(group);
                        
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            rs.close();
                    ps.close();
                    connection.close();
	        }
	        return groupList;
	    }


}
