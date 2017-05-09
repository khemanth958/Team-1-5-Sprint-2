

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Group;
import Model.Posts;
import Model.Users;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kavya
 */
public class PostDBTest {
    
    PostDB newPostDB;
    
    public PostDBTest() {
        newPostDB = new PostDB();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getPostsForUser method, of class PostDB.
     */
    @Test
    public void testGetPostsForUser() throws Exception {
        System.out.println("getPostsForUser");
        String groupName = "Uncc_49ers";
        String userEmail = "johnsmith@gmail.com";
        ArrayList<Posts> result = newPostDB.getPostsForUser(groupName, userEmail);
        assertNotNull(result);
    }
    
    @Test
    public void testGetPostsForUser1() throws Exception {
        System.out.println("getPostsForUser");
        String groupName = "Uncc_49ers";
        String userEmail = "vinu0404@gmail.com";
        ArrayList<Posts> result = newPostDB.getPostsForUser(groupName, userEmail);
        assertNull(result);
    }

    /**
     * Test of getAllPost method, of class PostDB.
     */
    @Test
    public void testGetAllPost() throws Exception {
        System.out.println("getAllPost");
        String groupName = "XYZ";
        ArrayList<Posts> expResult = null;
        ArrayList<Posts> result = newPostDB.getAllPost(groupName);
        assertEquals(expResult, result);
    }

    /**
     * Test of DeletePost method, of class PostDB.
     */
    @Test
    public void testDeletePost() throws Exception {
        System.out.println("DeletePost");
        int post_id = 0;
        int expResult = 0;
        int result = newPostDB.DeletePost(post_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of InsertPost method, of class PostDB.
     */
    @Test
    public void testInsertPost() throws Exception {
        System.out.println("InsertPost");
        Users input_user = new Users();
        Group input_group = new Group();
        Posts input_post = new Posts();
        
        input_user.setUserEmail("johnsmith@gmail.com");
        input_group.setGroupName("Uncc_49ers");
        input_post.setUserPosts("Hi this is a test post");
        
        int expResult = 1;
        int result = newPostDB.InsertPost(input_user, input_group, input_post);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of InsertPost method, of class PostDB.
     */
    @Test
    public void testInsertPost1() throws Exception {
        System.out.println("InsertPost");
        Users input_user = new Users();
        Group input_group = new Group();
        Posts input_post = new Posts();
        
        input_group.setGroupName("Uncc_49ers");
        input_post.setUserPosts("Hi this is a test post");
        
        int expResult = -1;
        int result = newPostDB.InsertPost(input_user, input_group, input_post);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPostsOnID method, of class PostDB.
     */
    @Test
    public void testGetPostsOnID() throws Exception {
        System.out.println("getPostsOnID");
        int post_id = 0;
        Posts expResult = null;
        Posts result = newPostDB.getPostsOnID(post_id);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPostsOnID method, of class PostDB.
     */
    @Test
    public void testGetPostsOnID2() throws Exception {
        System.out.println("getPostsOnID");
        int post_id = 1;
        Posts result = newPostDB.getPostsOnID(post_id);
        assertNotNull(result);
    }
    
}
