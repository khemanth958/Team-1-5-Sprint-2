/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Hemanth
 */
public class Comment 
{
    private int post_id;
    private int u_id;
    private String comment_text;
    private String u_name;

    /**
     * @return the post_id
     */
    public int getPost_id() {
        return post_id;
    }

    /**
     * @param post_id the post_id to set
     */
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    /**
     * @return the u_id
     */
    public int getU_id() {
        return u_id;
    }

    /**
     * @param u_id the u_id to set
     */
    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    /**
     * @return the comment_text
     */
    public String getComment_text() {
        return comment_text;
    }

    /**
     * @param comment_text the comment_text to set
     */
    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }
    
    public Comment()
    {
        super();
    }
    
    public Comment(int u_id, int post_id, String comment_text)
    {
        this.u_id = u_id;
        this.post_id = post_id;
        this.comment_text = comment_text;
    }

    /**
     * @return the u_name
     */
    public String getU_name() {
        return u_name;
    }

    /**
     * @param u_name the u_name to set
     */
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
}
