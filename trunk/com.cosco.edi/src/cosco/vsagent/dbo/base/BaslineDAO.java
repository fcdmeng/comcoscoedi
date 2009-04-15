package cosco.vsagent.dbo.base;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cosco.vsagent.db.ConnectManager;
import cosco.vsagent.db.QueryInfo;
import cosco.vsagent.dbo.BaseDAO;
import cosco.vsagent.mapping.base.Basline;
import cosco.vsagent.util.UUIDGenerator;

public class BaslineDAO extends BaseDAO {
public List<Basline> getBasline(QueryInfo qi){
		
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			// 得到总记录数
			rs = sm.executeQuery("select count(*) from Basline");
			rs.next();
			qi.rsCount = rs.getInt(1);
			if (qi.rsCount == 0)// 等于0表示没有记录
				return Collections.emptyList();
			// 算出总页数
			if (qi.rsCount % qi.pageSize == 0)
				qi.pageCount = qi.rsCount / qi.pageSize;
			else
				qi.pageCount = (qi.rsCount / qi.pageSize) + 1;
			// 算出起始位置＝ (当前页号-1)*每页记录数
			int start = (qi.currentPage - 1) * qi.pageSize;
			rs = sm.executeQuery("select * from Basline limit " + start + "," + qi.pageSize);
			List<Basline> list = new ArrayList<Basline>(qi.pageSize);

			while (rs.next()) {
				list.add(Basline(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();
	}
	//取客户端数据
	public List<Basline> getBasline(String sql){
		return getBasline(sql, false);
	}
	/**
	 * 
	 * @param sql
	 * @param flag true 表示取服务器端数据，false 取客户端数据
	 * @return
	 */
	public List<Basline> getBasline(String sql, boolean flag){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			if (flag == true)
				con = ConnectManager.getServerConnection();
			else
				con = ConnectManager.getConnection();
			
			if(con == null) return Collections.emptyList();
			
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
			
			List<Basline> list = new ArrayList<Basline>();
			while(rs.next()){
				list.add(Basline(rs,flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public Basline findByLinecode(String linecode) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Basline where linecode ='"+linecode+"'");
			if(rs.next()){
				return Basline(rs);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	public Basline Basline(ResultSet rs){
		return Basline(rs, false);
	}
	public Basline Basline(ResultSet rs, boolean flag) {
		Basline basline = new Basline();
		try {
			
			basline.setLinename(rs.getString("linename")); 
			basline.setPortlist(rs.getString("portlist")); 
			basline.setOperator(rs.getString("operator")); 
			basline.setSchedule(rs.getString("schedule")); 
			basline.setLinetype(rs.getString("linetype")); 
			basline.setMakercode(rs.getString("makercode"));
			basline.setMaker(rs.getString("maker")); 
			basline.setMadetime(rs.getTimestamp("madetime")); 
			basline.setModicode(rs.getString("modicode")); 
			basline.setModifier(rs.getString("modifier"));
			basline.setModitime(rs.getTimestamp("moditime")); 
			basline.setLinecode(rs.getString("linecode")); 
			basline.setMainkey(rs.getInt("mainkey"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return basline;
	}
	public boolean inserBasline(Basline o) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			con = ConnectManager.getConnection();
			if (con == null) return false;
			
			if(findByLinecode(o.getLinecode())!=null) return false;
			
			String sql = "insert into Basline(linename,portlist,operator,schedule,linetype," +
					"makercode,maker,madetime,modicode,modifier,moditime,linecode,mainkey)" +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);
			
			
			setBasline(sm, o);
			sm.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			result = false;
	
		}finally{
			close(sm);close(rs);close(con);
		}
		return result;
	}
	private void setBasline(PreparedStatement sm, Basline o) throws SQLException {
		// TODO Auto-generated method stub
		sm.setString(1, o.getLinename());
		sm.setString(2, o.getPortlist());
		sm.setString(3, o.getOperator());
		sm.setString(4, o.getSchedule());
		sm.setString(5, o.getLinetype());
		sm.setString(6, o.getMakercode());
		sm.setString(7, o.getMaker());
		sm.setTimestamp(8,  o.getMadetime());
		sm.setString(9, o.getModicode());
		sm.setString(10, o.getModifier());
		sm.setTimestamp(11,  o.getModitime());
		sm.setString(12, o.getLinecode());
		sm.setInt(13, o.getMainkey());
		
		
		
	}
	public boolean removeBasline(Basline Basline) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			if(con == null) return false;
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from Basline where linecode='"+Basline.getLinecode()+"'");

			sm.executeBatch();
		    con.commit();
		    
		}catch (SQLException e){
			result = false;
			
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
		}finally{
			close(rs);close(sm);close(con);
		}
		return result;
	}

}
