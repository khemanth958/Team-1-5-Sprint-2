package test;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DbManager {
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://finalnbadproject.crjkhmwb7wlf.us-west-2.rds.amazonaws.com:3306/FinalNBadProject", "khemanth958", "Hemanthaws#958");
			return (Connection) conn;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}

