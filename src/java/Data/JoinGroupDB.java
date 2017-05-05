/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Group;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import test.DbManager;

/**
 *
 * @author Hemanth
 */
public class JoinGroupDB 
{
    private static final DbManager db = new DbManager();
    private static final Connection connection = db.getConnection();
    public static int JoinTheGroup(String groupName, String email) throws ClassNotFoundException, SQLException 
    {	
        CallableStatement myproc = null;
        int theCount = -1;
        try 
        {
            myproc = connection.prepareCall("call Join_Group(?,?,?)");
            myproc.setString(1, groupName);
            myproc.setString(2, email);                
            myproc.registerOutParameter(3, Types.INTEGER);
            myproc.execute();
            theCount = myproc.getInt(3);            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            theCount = -1;
        } 
        /*finally 
        {
            connection.close();
        }*/
	return theCount;
    }
}
