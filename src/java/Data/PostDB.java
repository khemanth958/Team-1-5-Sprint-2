/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Group;
import Model.Like;
import Model.Posts;
import Model.Users;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import test.DbManager;

/**
 *
 * @author Akshay
 */
public class PostDB 
{
    private static final DbManager db = new DbManager();
    private static final Connection connection = db.getConnection();
    
    public static ArrayList<Posts> getPostsForUser(String groupName,String userEmail) throws ClassNotFoundException, SQLException 
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userName1 = null;
        ArrayList<Posts> postsList = new ArrayList<Posts>();
        try 
        {
            ps = connection.prepareStatement("select p.post as post_text, p.post_id as post_id,u.u_id as u_id, u.u_name as uname from posts p, users u, post_user_group_relationship pug, groups g where p.post_id = pug.p_id and pug.u_id = u.u_id and pug.g_id = g.g_id and g.g_name = ? and u.u_emailid =?");
            ps.setString(1, groupName);
            ps.setString(2, userEmail);
            rs = ps.executeQuery();
            while (rs.next()) 
            {
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
            }
        } catch (SQLException e) {
            System.out.println(e);
            postsList = null;
        } /*finally {
            connection.close();
        }*/
        return postsList;
    }
     
    public static ArrayList<Posts> getAllPost(String groupName) throws ClassNotFoundException, SQLException 
    {
        	
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userName1 = null;
        ArrayList<Posts> postsList = null;
        try 
        {
            postsList = new ArrayList<Posts>();
            ps = connection.prepareStatement("select p.post_id as post_id, p.post as post, u.u_id as u_id, u.u_name as u_name from posts p, post_user_group_relationship pug, users u, groups g\n" +
"where u.u_id = pug.u_id and p.post_id = pug.p_id and g.g_id = pug.g_id and g.g_name = ?");
            ps.setString(1, groupName);
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                //Group group = new Group();
                System.out.println("Posts Found");
                Posts newPost = new Posts();
                newPost.setPostId(rs.getInt("post_id"));
                newPost.setUserPosts(rs.getString("post"));
                newPost.setuId(rs.getInt("u_id"));
                newPost.setuName(rs.getString("u_name"));
                ArrayList<Like> newArrayList = getLikes(rs.getInt("post_id"));
                newPost.setListOfLikes(newArrayList);
                postsList.add(newPost);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } /*finally {
            rs.close();
            ps.close();
            connection.close();
        }*/
        return postsList;
    }
    
    private static ArrayList<Like> getLikes(int post_id) throws ClassNotFoundException, SQLException
    {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Like> likesList = null;
        try
        {
            likesList = new ArrayList<Like>();
            ps = connection.prepareStatement("select * from likes where post_id = ?");
            ps.setInt(1, post_id);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Like newLike = new Like();
                newLike.setLike_id(rs.getInt("like_id"));
                newLike.setPost_id(post_id);
                newLike.setU_id(rs.getInt("u_id"));
                likesList.add(newLike);
            }
        }catch(SQLException e)
        {
            System.out.println(e);
            likesList = null;
        }
        return likesList;
    }
    
    public static int DeletePost(int post_id) throws ClassNotFoundException, SQLException
    {
        CallableStatement  myproc = null;
        int theCount = -1;
        try {
            myproc = connection.prepareCall("call Delete_User_Post(?,?)");
            myproc.setInt(1, post_id);            
            myproc.registerOutParameter(2, Types.INTEGER);
            myproc.execute();
            theCount = myproc.getInt(2);
            System.out.println(theCount);
            
        } 
        catch (SQLException sQLException) 
        {
            System.out.println(sQLException);
        }
        return theCount;
    }
    
    public static int InsertPost(Users input_user, Group input_group, Posts input_post) throws ClassNotFoundException, SQLException
    {
        int theCount = -1;
        try
        {
            CallableStatement  myproc = connection.prepareCall("call Insert_Post(?,?,?,?)");
            myproc.setString(1, input_user.getUserEmail());
            myproc.setString(2, input_group.getGroupName());
            myproc.setString(3, input_post.getUserPosts());
            myproc.registerOutParameter(4,Types.INTEGER);
            myproc.execute();
            theCount = myproc.getInt(4);
            System.out.println(theCount); 
            
        }catch(Exception ex)
        {
            System.out.println(ex);
            theCount = -1;
        }
        return theCount;
    }
    
    public static Posts getPostsOnID(int post_id) throws SQLException, ClassNotFoundException
    {
        Posts newPost = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            newPost = new Posts();
            ps = connection.prepareStatement("select p.post as post_text, u.u_name as uname from posts p, users u, post_user_relationship pu where p.post_id = pu.post_id and pu.u_id = u.u_id and p.post_id=?");
            ps.setInt(1, post_id);
            rs = ps.executeQuery();
            while(rs.next())
            {
                newPost.setUserPosts(rs.getString("post_text"));
                newPost.setuName(rs.getString("uname"));
                newPost.setPostId(post_id);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            newPost = null;
        }
        return newPost;
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

select p.post_id, p.post, u.u_id, u.u_name from posts p, post_user_group_relationship pug, users u, groups g
where u.u_id = pug.u_id and p.post_id = pug.p_id and g.g_id = pug.g_id and g.g_name = ?

    */
    
    
}
