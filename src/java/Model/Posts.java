/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Hemanth
 */
public class Posts implements Serializable{
    private int postId;
    private String userPosts;
    private ArrayList<Like> listOfLikes;
    private String uName;
    private int uId; 
            
    public Posts(int postId,String userPosts){
        this.postId = postId;
        this.userPosts = userPosts;
    }

    public Posts() {
    super();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * @return the postID
     */
    public int getPostId() {
        return postId;
    }

    /**
     * @param postID the postID to set
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * @return the userPosts
     */
    public String getUserPosts() {
        return userPosts;
    }

    /**
     * @param userPosts the userPosts to set
     */
    public void setUserPosts(String userPosts) {
        this.userPosts = userPosts;
    }

    /**
     * @return the listOfLikes
     */
    public ArrayList<Like> getListOfLikes() {
        return listOfLikes;
    }

    /**
     * @param listOfLikes the listOfLikes to set
     */
    public void setListOfLikes(ArrayList<Like> listOfLikes) {
        this.listOfLikes = listOfLikes;
    }

    /**
     * @return the uName
     */
    public String getuName() {
        return uName;
    }

    /**
     * @param uName the uName to set
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * @return the uId
     */
    public int getuId() {
        return uId;
    }

    /**
     * @param uId the uId to set
     */
    public void setuId(int uId) {
        this.uId = uId;
    }


}
