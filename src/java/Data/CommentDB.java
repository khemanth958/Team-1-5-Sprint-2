/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import test.DbManager;

/**
 *
 * @author Hemanth
 */
public class CommentDB 
{
    private static final DbManager db = new DbManager();
    private static final Connection connection = db.getConnection();
    
    public static ArrayList<Comment> getCommentList(int post_id) throws SQLException, ClassNotFoundException 
    {
        ArrayList<Comment> newCommentList = null;
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            newCommentList = new ArrayList<Comment>();
            ps = connection.prepareStatement("select comment_t as comment, u.u_name as uname, c.u_id as u_id from comments c, users u where u.u_id = c.u_id and c.post_id=?");
            ps.setInt(1, post_id);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Comment newComment = new Comment();
                newComment.setComment_text(rs.getString("comment"));
                newComment.setPost_id(post_id);
                newComment.setU_id(rs.getInt("u_id"));
                newComment.setU_name(rs.getString("uname"));
                newCommentList.add(newComment);
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            newCommentList = null;
        }
        return newCommentList;
    }
    
    public static int InsertComment(int post_id, int u_id, String comment_text) throws SQLException, ClassNotFoundException
    {
        PreparedStatement ps;
        int theCount = -1;
        try
        {
            ps = connection.prepareStatement("insert into comments(post_id, u_id, comment_t) values(?,?,?)");
            ps.setInt(1, post_id);
            ps.setInt(2, u_id);
            ps.setString(3, comment_text);
            theCount = ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            theCount = -1;
        }
        return theCount;
    }
    
}
