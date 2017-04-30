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
import java.sql.*;
import test.DbManager;

/**
 *
 * @author Akshay
 */
public class GroupDB 
{
    private static final DbManager db = new DbManager();
    private static final Connection connection = db.getConnection();
    
    public static Group getGroupAttributes(String groupName) throws ClassNotFoundException, SQLException 
    {	        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Group groupAttributes = null;
        try 
        {
            groupAttributes = new Group();
            ps = connection.prepareStatement("SELECT * from groups WHERE g_name = ?");
            ps.setString(1, groupName);
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                //Group group = new Group();
                System.out.println("Group Found");
                groupAttributes.setGroupID(rs.getInt("g_id"));
                groupAttributes.setGroupName(rs.getString("g_name"));
                groupAttributes.setGroupDescription(rs.getString("g_description"));
                groupAttributes.setNumberOfGroupMembers(rs.getInt("g_group_members"));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            groupAttributes = null;
        } 
        /*finally 
        {
            connection.close();
        }*/
	return groupAttributes;
    }
        
    public static ArrayList<Group> getGroupsOfUser(String role_name, int u_id) throws ClassNotFoundException, SQLException 
    {
	PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Group> groupList = null;

        try 
        {
            groupList = new ArrayList<Group>();
            if ("admin".equals(role_name.toLowerCase())) 
            {
                ps = connection.prepareStatement("SELECT * from groups");
            }
            else
            {
                ps = connection.prepareStatement("select * from groups g, group_user_relationship gu where gu.g_id = g.g_id and gu.u_id = ?");
                ps.setInt(1, u_id);
            }

            rs = ps.executeQuery();
            while (rs.next()) 
            {
                Group newGroup = new Group();
                newGroup.setGroupID(rs.getInt("g_id"));
                newGroup.setGroupName(rs.getString("g_name"));
                newGroup.setGroupDescription(rs.getString("g_description"));
                newGroup.setNumberOfGroupMembers(rs.getInt("g_group_members"));
                System.out.println("The id found are :" + newGroup.getGroupID());
                groupList.add(newGroup);           
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } /*finally {
            connection.close();
        }*/
	return groupList;
    }

    public static boolean IsJoined(String group_Name, int u_id) throws ClassNotFoundException, SQLException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isJoined = false;
        try 
        {
            ps = connection.prepareStatement("select u_id from group_user_relationship where u_id = ? and g_id = (select g_id from groups where g_name = ?)");
            ps.setInt(1, u_id);
            ps.setString(2, group_Name);
            rs = ps.executeQuery();
            if (rs.next()) 
            {
                isJoined = true;
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            isJoined = false;
        } 
        /*finally 
        {
            connection.close();
        }*/
        return isJoined;
    }
    
    public static ArrayList<Group> getStringBasedGroups(String inputString) throws ClassNotFoundException, SQLException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Group> groupList = null;
        try 
        {
            groupList = new ArrayList<Group>();
            if (inputString.isEmpty() || inputString.equalsIgnoreCase(" "))
                ps = connection.prepareStatement("select * from groups");
            else
                ps = connection.prepareStatement("select * from groups where g_name like '%"+inputString+"%'");
            
            rs = ps.executeQuery();
            while(rs.next())
            {
                Group newGroup = new Group();
                newGroup.setGroupID(rs.getInt("g_id"));
                newGroup.setGroupName(rs.getString("g_name"));
                newGroup.setGroupDescription(rs.getString("g_description"));
                newGroup.setNumberOfGroupMembers(rs.getInt("g_group_members"));
                groupList.add(newGroup);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            groupList = null;
        } 
        /*finally 
        {
            connection.close();
        }*/
        return groupList;
    }
    
}
