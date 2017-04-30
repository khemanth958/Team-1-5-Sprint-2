/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import test.DbManager;

/**
 *
 * @author Viranchi
 */
public class LikeDB 
{
    private static final DbManager db = new DbManager();
    private static final Connection connection = db.getConnection();
    public static int InsertORDeletePost(int post_id, String role, String emailid) throws ClassNotFoundException, SQLException
    {
        CallableStatement  myproc = null;
        int theCount = 0;
        try {
            myproc = connection.prepareCall("call Insert_or_Delete(?,?,?,?)");
            myproc.setString(1, role);
            myproc.setString(2, emailid);
            myproc.setInt(3, post_id);            
            myproc.registerOutParameter(4, Types.INTEGER);
            myproc.execute();
            theCount = myproc.getInt(4);
        } 
        catch (SQLException sQLException) 
        {
            System.out.println(sQLException);
            theCount = -1;
        }
        return theCount;
    }
}
