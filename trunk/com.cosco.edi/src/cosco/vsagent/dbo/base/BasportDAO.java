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
import cosco.vsagent.mapping.base.Basport;
import cosco.vsagent.util.UUIDGenerator;

public class BasportDAO extends BaseDAO {
public List<Basport> getBasport(QueryInfo qi){
		
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			// 得到总记录数
			rs = sm.executeQuery("select count(*) from Basport");
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
			rs = sm.executeQuery("select * from Basport limit " + start + "," + qi.pageSize);
			List<Basport> list = new ArrayList<Basport>(qi.pageSize);

			while (rs.next()) {
				list.add(Basport(rs));
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
	public List<Basport> getBasport(String sql){
		return getBasport(sql, false);
	}
	/**
	 * 
	 * @param sql
	 * @param flag true 表示取服务器端数据，false 取客户端数据
	 * @return
	 */
	public List<Basport> getBasport(String sql, boolean flag){
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
			
			List<Basport> list = new ArrayList<Basport>();
			while(rs.next()){
				list.add(Basport(rs,flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public Basport findByPortcode(String portcode) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Basport where portcode ='"+portcode+"'");
			if(rs.next()){
				return Basport(rs);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	
	public Basport findByLike(String ref) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Basport where portcode ='"+ref+"' Or cname='"+ref+"' Or ename='"+ref+"'");
			if(rs.next()){
				return Basport(rs);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	
	public Basport Basport(ResultSet rs){
		return Basport(rs, false);
	}
	
	public Basport Basport(ResultSet rs, boolean flag) {
		Basport basport = new Basport();
		try {
			basport.setMainkey(rs.getInt("mainkey"));
			basport.setPorttype(rs.getString("porttype")); 
			basport.setPortcode(rs.getString("portcode"));
			basport.setCname(rs.getString("cname")); 
			basport.setEname(rs.getString("ename")); 
			basport.setNationcode(rs.getString("nationcode"));
			basport.setIfbaseprot(rs.getString("ifbaseprot")); 
			basport.setMakercode(rs.getString("makercode"));
			basport.setMaker(rs.getString("maker")); 
			basport.setMadetime(rs.getDate("madetime")); 
			basport.setModicode(rs.getString("modicode")); 
			basport.setModifier(rs.getString("modifier"));
			basport.setModitime(rs.getDate("moditime")); 
			if(flag == false){//服务器不存在此列
				basport.setSequence(rs.getString("sequence"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return basport;
	}
	public boolean inserBasport(Basport o) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			con = ConnectManager.getConnection();
			if (con == null) return false;
			
			if(findByPortcode(o.getPortcode())!=null) return false;
			
			String sql = "insert into Basport(porttype,cname,ename,nationcode," +
					"ifbaseprot,makercode,maker,madetime," +
					"modicode,modifier,moditime,Sequence,portcode,mainkey)" +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);
			
			setBasport(sm, o);
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
	private void setBasport(PreparedStatement sm, Basport o) throws SQLException {
		// TODO Auto-generated method stub
		sm.setString(1, o.getPorttype());
		sm.setString(2, o.getCname());
		sm.setString(3, o.getEname());
		sm.setString(4, o.getNationcode());
		sm.setString(5, o.getIfbaseprot());
		sm.setString(6, o.getMakercode());
		sm.setString(7, o.getMaker());
		sm.setDate(8, (Date) o.getMadetime());
		sm.setString(9, o.getModicode());
		sm.setString(10, o.getModifier());
		sm.setDate(11, (Date) o.getModitime());
		if(o.getSequence() == null){
			//此处应该有个状态判断,判断何时产生主键
			String sequence = (String) new UUIDGenerator().generate();
			sm.setString(12,sequence);
			
		}else{
			sm.setString(12,o.getSequence());
		}
		sm.setString(13, o.getPortcode());
		sm.setInt(14, o.getMainkey());
		
		
		
	}
	public boolean removeBasport(Basport basport) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			if(con == null) return false;
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from Basport where Portcode='"+basport.getPortcode()+"'");

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
