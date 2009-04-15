package cosco.vsagent.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class InsertAndSelect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/sms","root","root");
			sm = con.createStatement();
			
			
			sm.executeUpdate("delete from test_table");
			
			String s = "³Â¸Õ";
			sm.executeUpdate("insert into test_table (name0) values ('" + s + "')");
			sm.executeUpdate("insert into test_table (name1) values ('" + s + "')");
			sm.executeUpdate("insert into test_table (name2) values ('" + s + "')");
			sm.executeUpdate("insert into test_table (name3) values ('" + s + "')");
			// ¶ÁÈ¡¼ÇÂ¼
			rs = sm.executeQuery("select * from test_table");
			while (rs.next())
				System.out.println(rs.getInt("id") + "_" + rs.getString("name0") + "_" + rs.getString("name1") + "_" + rs.getString("name2") + "_" + rs.getString("name3"));
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (sm != null) {
				try {
					sm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				sm = null;
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				con = null;
			}
		}
		

	}

}
