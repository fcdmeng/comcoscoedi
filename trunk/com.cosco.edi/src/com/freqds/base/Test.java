package com.freqds.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test{

	/**
	 * @param args
	 */

		public static void main( String[] args ) {

		    try {
		       // Using Sybase jConnect 4.2
		       Class.forName("com.sybase.jdbc.SybDriver");

		       // Using Sybase jConnect 5.2
		       //Class.forName("com.sybase.jdbc2.jdbc.SybDriver");

		       Connection conn = DriverManager.getConnection("jdbc:sybase:Tds:172.20.40.25:8888/VSAGENTLIB?charset=eucgb", "sa", "freqds70");
		       Statement stmt = conn.createStatement( );
		       ResultSet rs = stmt.executeQuery( "SELECT cnname FROM basvslmsg");
		       while ( rs.next( ) ) {
		         System.out.println( rs.getString(1) );
		       }
		     } catch ( Exception e ) {
		    	 e.printStackTrace();		    			
		       System.out.println( "An exception occurred." );
		     }

		  }
		//==========================

}
