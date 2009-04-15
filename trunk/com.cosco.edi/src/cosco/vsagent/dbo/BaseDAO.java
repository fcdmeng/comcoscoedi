package cosco.vsagent.dbo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.ResultSetDynaClass;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cosco.vsagent.db.ConnectManager;

public class BaseDAO {
	public void close(ResultSet rs){
		if (rs != null){
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			rs = null;
		}
	}
	
	public void close(Statement sm){
		if (sm != null){
			try{sm.close();}catch(SQLException e){e.printStackTrace();}
			sm = null;
		}
	}
	
	public void close (Connection con){
		
	}
	public List query(String sqlString, Class clazz){
		List beans = null;
		Connection conn = null;
		try{
			conn = ConnectManager.getServerConnectionNew();
			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, sqlString, new BeanListHandler(
					clazz));
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return beans;
	}
	public Object get(String sqlString, Class clazz){
		List beans = null;
		Object obj = null;
		Connection conn = null;
		
		try {
			conn = ConnectManager.getServerConnectionNew();
			QueryRunner qRunner = new QueryRunner();
			beans = (List)qRunner.query(conn, sqlString, new BeanListHandler(clazz));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtils.closeQuietly(conn);
		}
		if(beans != null && !beans.isEmpty()){
			obj = beans.get(0);
		}
		return obj;
	}
	public boolean update(String sqlString){
		boolean flag = false;
		Connection conn = null;
		
		int i;
		try {
			conn = ConnectManager.getServerConnectionNew();
			QueryRunner qRunner = new QueryRunner();
			i = qRunner.update(conn, sqlString);
			if(i > 0){
				flag = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtils.closeQuietly(conn);
		}
		
		return flag;
	}
	void test(){
		ArrayList results = new ArrayList(); // To hold copied list
		   ResultSetDynaClass rsdc = null;
		   DynaProperty properties[] = rsdc.getDynaProperties();
		   BasicDynaClass bdc =
		     new BasicDynaClass("foo", BasicDynaBean.class,
		                        rsdc.getDynaProperties());
		   Iterator rows = rsdc.iterator();
		   while (rows.hasNext()) {
		     DynaBean oldRow = (DynaBean) rows.next();
		     DynaBean newRow;
			try {
				newRow = bdc.newInstance();
				PropertyUtils.copyProperties(newRow, oldRow);
			    results.add(newRow);
			     
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		   } 
	}
//	void save();
//	void insert();
//	void update();
//	void retrieve();
//	void retrieve(String sql);

}
