/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Comment;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import test.DbManager;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Hemanth Kumar
 */
public class CommentDBtest {
    int post_id;
    int noofcomments;
    
    
    public CommentDBtest() {
        this.post_id=22;
        
    }

    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
     
    
    
     @Test
    public void testgetcommentlist() throws Exception, ClassNotFoundException, SQLException {
    DbManager db = new DbManager();
    Connection connection = db.getConnection();
        int expectednoofcomments=2;
        noofcomments = CommentDB.getCommentList(post_id).size();
        
        assertEquals(expectednoofcomments,noofcomments);
    }
    
    @Test
    public void testgetcommentlist1() throws Exception, ClassNotFoundException, SQLException {
    DbManager db = new DbManager();
    Connection connection = db.getConnection();
        int expectednoofcomments=1;
        noofcomments = CommentDB.getCommentList(post_id).size();
        
        assertEquals(expectednoofcomments,noofcomments);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    

    
    
}
