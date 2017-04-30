/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Viranchi
 */
public class Like 
{
    private int like_id;
    private int post_id;
    private int u_id;

    /**
     * @return the like_id
     */
    public int getLike_id() {
        return like_id;
    }

    /**
     * @param like_id the like_id to set
     */
    public void setLike_id(int like_id) {
        this.like_id = like_id;
    }

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
    
    public Like(int u_id, int like_id, int post_id)
    {
        this.like_id = like_id;
        this.post_id = post_id;
        this.u_id = u_id;
    }
    
    public Like()
    {
        super();
    }
    
}
