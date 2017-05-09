package Data;

import Data.GroupDB;
import Model.Group;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kavya
 */
public class TestGroupDB1 {
    
    @Test
    public void testGroupAttributes() throws SQLException, ClassNotFoundException{
        GroupDB gp = new GroupDB();
        //PreparedStatement pstmt = gp.connection.prepareStatement("insert into groups values(?,?)");
        //pstmt.setString(1, "kavya");
        //pstmt.setString(2, "test desc");
        String groupname = "kavya";
        Group group = gp.getGroupAttributes(groupname);
        //assertTrue(group.getGroupName().equals("kavya"));
        assertEquals("kavya",group.getGroupName());
        
        
    }
    
    @Test
    public void testGroupIsJoined(){
        try {
            GroupDB gp = new GroupDB();
            PreparedStatement pstmt = gp.connection.prepareStatement("insert into groups values(?,?)");
            pstmt.setString(1, "group1");
            pstmt.setString(2, "test desc");
            
            PreparedStatement pstmt1 = gp.connection.prepareStatement("insert into group_user_relation values(?,?)");
            pstmt1.setInt(1, 1);
            pstmt1.setInt(2, 1);
            
            
            assertTrue(GroupDB.IsJoined("group1", 1));
           
        } catch (Exception ex) {
            Logger.getLogger(TestGroupDB1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
