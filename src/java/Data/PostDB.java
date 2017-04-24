/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Group;
import Model.Posts;
import Model.Users;
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
public class PostDB {
    
    public static ArrayList<Posts> getPosts(String groupName) throws ClassNotFoundException, SQLException {
	        
        Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssdi_project","root","root");
		
                PreparedStatement ps = null;
	        ResultSet rs = null;
                String userName1 = null;
	        ArrayList<Posts> postsList = new ArrayList<Posts>();
                try {
	            ps = connection.prepareStatement("select * from posts inner join post_user_group_relationship on posts.post_id = post_user_group_relationship.post_id inner join groups on groups.g_id = post_user_group_relationship.g_id where groups.g_name = ?");
	            ps.setString(1, groupName);
	            rs = ps.executeQuery();
	            while (rs.next()) {
                        //Group group = new Group();
                        System.out.println("Posts Found");
	            	Posts user = new Posts();
	            	user.setPostId(rs.getInt("post_id"));
	            	user.setUserPosts(rs.getString("post"));
	            	//user.setGroupDescription(rs.getString("g_description"));
	            	//user.setNumberOfGroupMembers(rs.getInt("g_group_members"));
                        /*ps = connection.prepareStatement("select u_name from users  where u_uid = ?");
                        ps.setInt(1, (rs.getInt("u_id")));
                        rs = ps.executeQuery();
	            	while(rs.next()){
                            userName1 = rs.getString("u_Name");
                        }
                        Users userName = new Users();
                        userName.setUserName(userName1);
                        */
                        postsList.add(user);
                        return postsList;
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
     
    
    
  /*  
public static List<Group> getPosts() throws ClassNotFoundException, SQLException {
	        Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssdi_project", "root", "root");
		
                PreparedStatement ps = null;
	        ResultSet rs = null;
//	       ArrayList<Post> postList = new ArrayList<Post>();
	        try {
	            ps = connection.prepareStatement("SELECT * from posts");
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


    */
    
    
}
