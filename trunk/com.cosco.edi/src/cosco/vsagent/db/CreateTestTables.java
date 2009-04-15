package cosco.vsagent.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTestTables {
	public static void main(String[] args){
		Connection con = null;
		Statement sm = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","root");
			sm = con.createStatement();
			sm.addBatch("drop database if exists sms;");
			sm.addBatch("create database sms;");
			sm.addBatch("use sms;");
			sm.addBatch("drop table if exists test_table;");
			
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE test_table (");// ´´½¨test_table±í
			sb.append("  Id int(6) unsigned NOT NULL auto_increment,");
			sb.append("  name0 varchar(4) default NULL,");
			sb.append("  name1 varchar(4) default NULL,");
			sb.append("  name2 varchar(4) default NULL,");
			sb.append("  name3 varchar(4) default NULL,");
			sb.append("  PRIMARY KEY  (Id)");
			sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			sm.addBatch(sb.toString());
			sm.executeBatch();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (sm != null){
				try{sm.close();}catch(SQLException e){e.printStackTrace();}
				sm = null;
			}
			
			if (con != null){
				try{con.close();}catch(SQLException e){e.printStackTrace();}
				con = null;
			}
		}
	}

}
