package cosco.vsagent.dbo.base;

import java.sql.Connection;
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
import cosco.vsagent.mapping.base.Baspaytype;

public class BaspaytypeDAO extends BaseDAO {
public List<Baspaytype> getBaspaytype(QueryInfo qi){
		
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			// 得到总记录数
			rs = sm.executeQuery("select count(*) from Baspaytype");
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
			rs = sm.executeQuery("select * from Baspaytype limit " + start + "," + qi.pageSize);
			List<Baspaytype> list = new ArrayList<Baspaytype>(qi.pageSize);

			while (rs.next()) {
				list.add(Baspaytype(rs));
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
	public List<Baspaytype> getBaspaytype(String sql){
		return getBaspaytype(sql, false);
	}
	/**
	 * 
	 * @param sql
	 * @param flag true 表示取服务器端数据，false 取客户端数据
	 * @return
	 */
	public List<Baspaytype> getBaspaytype(String sql, boolean flag){
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
			
			List<Baspaytype> list = new ArrayList<Baspaytype>();
			while(rs.next()){
				list.add(Baspaytype(rs,flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public Baspaytype findByPaycode(String paycode) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Baspaytype where paycode ='"+paycode+"'");
			if(rs.next()){
				return Baspaytype(rs);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	public Baspaytype Baspaytype(ResultSet rs){
		return Baspaytype(rs, false);
	}
	public Baspaytype Baspaytype(ResultSet rs, boolean flag) {
		Baspaytype baspaytype = new Baspaytype();
		try {
			baspaytype.setMainkey(rs.getInt("mainkey"));
			baspaytype.setPaycode(rs.getString("paycode"));
			baspaytype.setPayname(rs.getString("payname")); 
			baspaytype.setMakercode(rs.getString("makercode"));
			baspaytype.setMaker(rs.getString("maker")); 
			baspaytype.setMadetime(rs.getTimestamp("madetime")); 
			baspaytype.setModicode(rs.getString("modicode")); 
			baspaytype.setModifier(rs.getString("modifier"));
			baspaytype.setModitime(rs.getTimestamp("moditime")); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return baspaytype;
	}
	public boolean inserBaspaytype(Baspaytype o) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			con = ConnectManager.getConnection();
			con.setAutoCommit(false);
			if (con == null) return false;

			//重复
//			if(findByPkgcode(o.getPkgcode())!=null) return false;
			
			String sql = "insert into Baspaytype(payname," +
					"makercode,maker,madetime,modicode,modifier,moditime,paycode,mainkey)" +
					"values(?,?,?,?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);

			setBaspaytype(sm, o);

			sm.execute();
			con.setAutoCommit(true);
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
	private void setBaspaytype(PreparedStatement sm, Baspaytype o) throws SQLException {
		// TODO Auto-generated method stub
		sm.setString(1, o.getPayname()==null?"":o.getPayname());
		sm.setString(2, o.getMakercode()==null?"":o.getMakercode());
		sm.setString(3, o.getMaker()==null?"":o.getMaker());
		sm.setTimestamp(4,  o.getMadetime());
		sm.setString(5, o.getModicode()==null?"": o.getModicode());
		sm.setString(6, o.getModifier()==null?"":o.getModifier());
		sm.setTimestamp(7,  o.getModitime());
		sm.setString(8,  o.getPaycode());
		sm.setInt(9, o.getMainkey());
		
	}
	public boolean removeBaspaytype(Baspaytype Baspaytype) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			if(con == null) return false;
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from Baspaytype where Paycode='"+Baspaytype.getPaycode()+"'");

			sm.executeBatch();
		    con.commit();
		    con.setAutoCommit(true);
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
