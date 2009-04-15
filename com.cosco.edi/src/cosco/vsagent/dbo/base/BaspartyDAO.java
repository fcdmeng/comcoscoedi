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
import cosco.vsagent.mapping.base.Basparty;
import cosco.vsagent.mapping.base.Basvslmsg;

public class BaspartyDAO extends BaseDAO {
public List<Basparty> getBasparty(QueryInfo qi){
		
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			// 得到总记录数
			rs = sm.executeQuery("select count(*) from Basparty");
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
			rs = sm.executeQuery("select * from Basparty limit " + start + "," + qi.pageSize);
			List<Basparty> list = new ArrayList<Basparty>(qi.pageSize);

			while (rs.next()) {
				list.add(Basparty(rs));
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
	public List<Basparty> getBasparty(String sql){
		return getBasparty(sql, false);
	}
	/**
	 * 
	 * @param sql
	 * @param flag true 表示取服务器端数据，false 取客户端数据
	 * @return
	 */
	public List<Basparty> getBasparty(String sql, boolean flag){
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
			
			List<Basparty> list = new ArrayList<Basparty>();
			while(rs.next()){
				list.add(Basparty(rs,flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public Basparty findByPartycode(String partycode) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Basparty where partycode ='"+partycode+"'");
			if(rs.next()){
				return Basparty(rs);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	public Basparty Basparty(ResultSet rs){
		return Basparty(rs, false);
	}
	public Basparty Basparty(ResultSet rs, boolean flag) {
		Basparty basparty = new Basparty();
		try {
			
			basparty.setCname(rs.getString("cname")); 
			basparty.setEname(rs.getString("ename")); 
			basparty.setFund(rs.getString("fund")); 
			basparty.setAddress(rs.getString("address")); 
			basparty.setPostal(rs.getString("postal")); 
			basparty.setContacter(rs.getString("contacter")); 
			basparty.setTelephone(rs.getString("telephone"));
			basparty.setFax(rs.getString("fax"));
			basparty.setTelephone(rs.getString("telephone"));
			basparty.setEmail(rs.getString("email"));
			basparty.setDockcode(rs.getString("dockcode"));
			basparty.setDockname(rs.getString("dockname"));
			basparty.setRemark(rs.getString("remark"));
			basparty.setIfcosco(rs.getString("ifcosco"));
			basparty.setMgrcode(rs.getString("mgrcode"));
			basparty.setMgrdesc(rs.getString("mgrdesc"));
			basparty.setMemocode(rs.getString("memocode"));
			basparty.setTransway(rs.getString("transway"));
			basparty.setVslmgrcode(rs.getString("vslmgrcode"));
			basparty.setCbrname(rs.getString("cbrname"));
			basparty.setEbrname(rs.getString("ebrname"));
			basparty.setNationcode(rs.getString("nationcode"));
			basparty.setNationname(rs.getString("nationname"));
			basparty.setDeptcode(rs.getString("deptcode"));
			basparty.setDeptname(rs.getString("deptname"));
			basparty.setVslmgrname(rs.getString("vslmgrname"));
			basparty.setIfaccount(rs.getString("ifaccount"));
			basparty.setEffect(rs.getString("effect"));
			basparty.setKeeper(rs.getString("keeper"));
			basparty.setIs_Fleet(rs.getString("is_Fleet"));
			basparty.setPartycode(rs.getString("partycode"));
			basparty.setMakercode(rs.getString("makercode"));
			basparty.setMaker(rs.getString("maker")); 
			basparty.setMadetime(rs.getTimestamp("madetime")); 
			basparty.setModicode(rs.getString("modicode")); 
			basparty.setModifier(rs.getString("modifier"));
			basparty.setModitime(rs.getTimestamp("moditime")); 
			
			basparty.setMainkey(rs.getInt("mainkey"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return basparty;
	}
	public boolean inserBasparty(Basparty o) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			con = ConnectManager.getConnection();
			if (con == null) return false;
			con.setAutoCommit(false);
			if(findByPartycode(o.getPartycode())!=null) return false;
			
			String sql = "insert into Basparty(cname,ename,fund,address,postal,contacter," +
					"telephone,fax,telex,email,dockcode,dockname,makercode,maker,madetime,modicode,modifier," +
					"moditime,remark,ifcosco,mgrcode,mgrdesc,memocode,transway,vslmgrcode,cbrname,ebrname,nationcode," +
					"nationname,deptcode,deptname,vslmgrname,ifaccount,effect,keeper,is_Fleet," +
					"partycode,mainkey)" +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);
			
			
			setBasparty(sm, o);
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
	private void setBasparty(PreparedStatement sm, Basparty o) throws SQLException {
		// TODO Auto-generated method stub
		sm.setString(1, o.getCname());
		sm.setString(2, o.getEname());
		sm.setString(3, o.getFund());
		sm.setString(4, o.getAddress());
		sm.setString(5, o.getPostal());
		sm.setString(6, o.getContacter());
		sm.setString(7, o.getTelephone());
		sm.setString(8,  o.getFax());
		sm.setString(9, o.getTelex());
		sm.setString(10, o.getEmail());
		sm.setString(11,  o.getDockcode());
		sm.setString(12, o.getDockname());
		sm.setString(13, o.getMakercode());
		sm.setString(14, o.getMaker());
		sm.setTimestamp(15,  o.getMadetime());
		sm.setString(16, o.getModicode());
		sm.setString(17, o.getModifier());
		sm.setTimestamp(18,  o.getModitime());
		sm.setString(19, o.getRemark());
		sm.setString(20, o.getIfcosco());
		sm.setString(21, o.getMgrcode());
		sm.setString(22, o.getMgrdesc());
		sm.setString(23, o.getMemocode());
		sm.setString(24, o.getTransway());
		sm.setString(25, o.getVslmgrcode());
		sm.setString(26,  o.getCbrname());
		sm.setString(27, o.getEbrname());
		sm.setString(28, o.getNationcode());
		sm.setString(29,  o.getNationname());
		sm.setString(30, o.getDeptcode());
		sm.setString(31, o.getDeptname());
		sm.setString(32, o.getVslmgrname());
		sm.setString(33, o.getIfaccount());
		sm.setString(34, o.getEffect());
		sm.setString(35,  o.getKeeper());
		sm.setString(36, o.getIs_Fleet());
		sm.setString(37, o.getPartycode());
		sm.setInt(38, o.getMainkey());
		
		
		
	}
	public boolean removeBasparty(Basparty basparty) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			if(con == null) return false;
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from Basparty where partycode='"+basparty.getPartycode()+"'");

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
	
	public boolean modifyBasparty(Basparty basparty){
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		
		return false;
	}

}
