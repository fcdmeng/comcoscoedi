package cosco.vsagent.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.eclipse.jface.preference.IPreferenceStore;

import cosco.vsagent.app.Activator;
import cosco.vsagent.preferences.DBPreferencePage;


public class ConnectManager {
	private static Connection con;
	private static Connection servercon;
	private ConnectManager(){}
	
	//��������
	public static Connection getConnection() throws SQLException {
		if (con != null && !con.isClosed())// ��Ϊ����û�ر�
			return con;
		
		IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
		String className = ps.getString(DBPreferencePage.CLASSNAME_KEY);
		String url = ps.getString(DBPreferencePage.URL_KEY);
		String username = ps.getString(DBPreferencePage.USERNAME_KEY);
		String password = ps.getString(DBPreferencePage.PASSWORD_KEY);
		
		// TODO ���ڽ���Щ������Ϊ�ɽ�����������
//		String className = "com.mysql.jdbc.Driver";
//		String url = "jdbc:mysql://localhost/sms";
//		String username = "root";
//		String password = "root";
		// ����һ�����ݿ�����
		//try {
			//Class.forName(className);
			DbUtils.loadDriver(className);
//			url="jdbc:mysql://localhost:3306/sms?user=root&password=root&characterEncoding=utf-8"; 
//			con = DriverManager.getConnection(url);
			con = DriverManager.getConnection(url, username, password);

		/*} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		return con;
	}
	public static void closeConn(Connection conn) throws SQLException{
		DbUtils.close(conn);
	}
	
	public static void closeConnection(){
		if (con == null) return;
		try{
			con.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		con = null;
	}
	/**
	 * �µ��������ݿ�ķ���
	 * @return
	 */
	
	//������������
	public static Connection getServerConnection() throws SQLException{
		if (servercon != null && !servercon.isClosed())// ��Ϊ����û�ر�
			return servercon;
		
		try {
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			servercon = DriverManager.getConnection("jdbc:as400://172.40.66.208:6789/VSAGENTLIB", "QPGMR", "PQPGMR");
//			 Statement s = servercon.createStatement();
//			 ResultSet rs = s.executeQuery("SELECT * FROM VSAGENTLIB.BASVSLMSG");
//			 
//			 while(rs.next()){
//				 System.out.println(rs.getString("enname"));
//			 }

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return servercon;
		
	}
	
	public static Connection getServerConnectionNew(){
//		Connection conn = null;
		String jdbcURL = "com.ibm.as400.access.AS400JDBCDriver";
		String jdbcDriver = "jdbc:as400://172.40.66.208:6789/VSAGENTLIB";
		String user ="QPGMR";
		String password ="PQPGMR";
		
		try {
			DbUtils.loadDriver(jdbcDriver);
			con = DriverManager.getConnection(jdbcURL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally{
			DbUtils.closeQuietly(con);
		}*/
		
		return con;
	}

}
