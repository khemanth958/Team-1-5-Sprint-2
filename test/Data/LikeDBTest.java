/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hemanth
 */
public class LikeDBTest {
    
    LikeDB newLike;
    
    public LikeDBTest() {
        newLike = new LikeDB();
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of InsertORDeletePost method, of class LikeDB.
     */
    @Test
    public void testDeletePost() throws Exception {
        System.out.println("DeletePost");
        int post_id = 0;
        String role = "admin";
        String emailid = "johnsmith@gmail.com";
        int expResult = 0;
        int result = newLike.InsertORDeletePost(post_id, role, emailid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testInsertLikePost() throws Exception {
        System.out.println("InsertLikePost");
        int post_id = 1;
        String role = "user";
        String emailid = "johnsmith@gmail.com";
        int expResult = 1;
        int result = newLike.InsertORDeletePost(post_id, role, emailid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
