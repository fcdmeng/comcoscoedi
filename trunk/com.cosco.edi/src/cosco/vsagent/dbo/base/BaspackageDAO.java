package cosco.vsagent.dbo.base;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cosco.vsagent.db.ConnectManager;
import cosco.vsagent.db.QueryInfo;
import cosco.vsagent.dbo.BaseDAO;
import cosco.vsagent.mapping.base.Baspackage;
import cosco.vsagent.util.UUIDGenerator;

public class BaspackageDAO extends BaseDAO {
public List<Baspackage> getBaspackage(QueryInfo qi){
		
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			// 得到总记录数
			rs = sm.executeQuery("select count(*) from Baspackage");
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
			rs = sm.executeQuery("select * from Baspackage limit " + start + "," + qi.pageSize);
			List<Baspackage> list = new ArrayList<Baspackage>(qi.pageSize);

			while (rs.next()) {
				list.add(Baspackage(rs));
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
	public List<Baspackage> getBaspackage(String sql){
		return getBaspackage(sql, false);
	}
	/**
	 * 
	 * @param sql
	 * @param flag true 表示取服务器端数据，false 取客户端数据
	 * @return
	 */
	public List<Baspackage> getBaspackage(String sql, boolean flag){
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
			
			List<Baspackage> list = new ArrayList<Baspackage>();
			while(rs.next()){
				list.add(Baspackage(rs,flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public Baspackage findByPkgcode(String pkgcode) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Baspackage where pkgcode ='"+pkgcode+"'");
			if(rs.next()){
				return Baspackage(rs);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	public Baspackage Baspackage(ResultSet rs){
		return Baspackage(rs, false);
	}
	public Baspackage Baspackage(ResultSet rs, boolean flag) {
		Baspackage baspackage = new Baspackage();
		try {
			baspackage.setMainkey(rs.getInt("mainkey"));
			baspackage.setPkgcode(rs.getString("pkgcode"));
			baspackage.setPkgname(rs.getString("pkgname")); 
			baspackage.setMakercode(rs.getString("makercode"));
			baspackage.setMaker(rs.getString("maker")); 
			baspackage.setMadetime(rs.getTimestamp("madetime")); 
			baspackage.setModicode(rs.getString("modicode")); 
			baspackage.setModifier(rs.getString("modifier"));
			baspackage.setModitime(rs.getTimestamp("moditime")); 
			if(flag == false){//服务器不存在此列
				baspackage.setSequence(rs.getString("sequence"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return baspackage;
	}
	public boolean inserBaspackage(Baspackage o) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			con = ConnectManager.getConnection();
			if (con == null) return false;
//			con.setAutoCommit(false);
			//重复
//			if(findByPkgcode(o.getPkgcode())!=null) return false;
			
			String sql = "insert into Baspackage(pkgcode,pkgname,makercode,maker,madetime," +
					"modicode,modifier,moditime,Sequence,mainkey)" +
					"values(?,?,?,?,?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);
			
//			System.out.println(o.getPkgname());
			setBaspackage(sm, o);
//			sm.executeBatch();
			sm.execute();
//			con.setAutoCommit(true);
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
	private void setBaspackage(PreparedStatement sm, Baspackage o) throws SQLException {
		// TODO Auto-generated method stub
		sm.setString(1, o.getPkgcode()==null?"":o.getPkgcode());
		sm.setString(2, o.getPkgname()==null?"":o.getPkgname());
		sm.setString(3, o.getMakercode()==null?"":o.getMakercode());
		sm.setString(4, o.getMaker()==null?"":o.getMaker());
		sm.setTimestamp(5,  o.getMadetime());
		sm.setString(6, o.getModicode()==null?"": o.getModicode());
		sm.setString(7, o.getModifier()==null?"":o.getModifier());
		sm.setTimestamp(8,  o.getModitime());
		if(o.getSequence() == null){
			//此处应该有个状态判断,判断何时产生主键
			String sequence = (String) new UUIDGenerator().generate();
			sm.setString(9,sequence);
			
		}else{
			sm.setString(9,o.getSequence());
		}
		sm.setInt(10, o.getMainkey());
		
	}
	public boolean removeBaspackage(Baspackage baspackage) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			if(con == null) return false;
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from Baspackage where Pkgcode='"+baspackage.getPkgcode()+"'");

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
