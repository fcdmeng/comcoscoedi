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
import cosco.vsagent.mapping.base.Basvslvoy;


public class BasvslvoyDAO extends BaseDAO{
	
	
	public List<Basvslvoy> getBasvslvoy(QueryInfo qi){
		
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			// 得到总记录数
			rs = sm.executeQuery("select count(*) from Basvslvoy");
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
			rs = sm.executeQuery("select * from Basvslvoy order by madetime Desc limit " + start + "," + qi.pageSize);
			List<Basvslvoy> list = new ArrayList<Basvslvoy>(qi.pageSize);

			while (rs.next()) {
				list.add(Basvslvoy(rs));
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
	public List<Basvslvoy> getBasvslvoy(String sql){
		return getBasvslvoy(sql, false);
	}
	/**
	 * 
	 * @param sql
	 * @param flag true 表示取服务器端数据，false 取客户端数据
	 * @return
	 */
	public List<Basvslvoy> getBasvslvoy(String sql, boolean flag){
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
			
			List<Basvslvoy> list = new ArrayList<Basvslvoy>();
			while(rs.next()){
				list.add(Basvslvoy(rs));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public Basvslvoy findByVslvoy(String vslcode, String voyage, String IE) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Basvslvoy where vslcode ='"+vslcode+"' And voyage ='"+voyage+"' And IE ='"+IE+"'");
			if(rs.next()){
				return Basvslvoy(rs);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	
	public Basvslvoy findByMainkey(int mainkey) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getServerConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Basvslvoy where mainkey = "+String.valueOf(mainkey));
			
			if(rs.next()){
				return Basvslvoy(rs,true);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	
	
	public boolean inserBasvslvoy(Basvslvoy basvslvoy) {
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			con = ConnectManager.getConnection();
			if (con == null) return false;
			
			if(findByVslvoy(basvslvoy.getVslcode(),basvslvoy.getVoyage(), basvslvoy.getIe())!=null){
				System.out.println(basvslvoy.getIe().equals("E")?"出口":"进口"+" 船名:"+basvslvoy.getVslcname()+" 航次"+basvslvoy.getVoyage()+"==========已经存在");
				return false;
			}
			
			String sql = "insert into Basvslvoy(vslkey,vslcode,vslcname,vslename,voyage ," +
					"ie,cuscode,linename,locksign,secvslsign,tradetype,makercode,maker,madetime,modicode,modifier,moditime," +
					"linecode,custcode,custcname,linetype,saildate,arrdate,arrtime,sailtime,ownagent,cntronly,needclose,transid,vslldkey,sequence,mainkey)" +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);
			
			setBasvslvoy(sm, basvslvoy);
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
	/**
	 * 取出本地存在的，服务器不存在，或是服务器存在，不地不存在
	 * 如果存在，则取出不一致的
	 * @param clientmsg
	 * @param servermsg
	 * @return
	 */
//	public List<Basvslvoy> compare(List<Basvslvoy> clientmsg, List<Basvslvoy> servermsg){
//		List<Basvslvoy> Basvslvoy = new ArrayList<Basvslvoy>();
//		boolean lb_exit;
//		for(int i =0;i<clientmsg.size();i++){
//			String vslcode = clientmsg.get(i).getVslcode();
//			lb_exit = false;
//			
//			for(int k = 0; k<servermsg.size();k++){
//				if(vslcode.equals(servermsg.get(i).getVslcode())){
//					lb_exit = true;
//				}
//			}
//			
//			if(lb_exit == false){
//				Basvslvoy.add(clientmsg.get(i));
//			}
//			
//		}
//		
//		for(int i =0;i<servermsg.size();i++){
//			String vslcode = servermsg.get(i).getVslcode();
//			lb_exit = false;
//			
//			for(int k = 0; k<clientmsg.size();k++){
//				if(vslcode.equals(clientmsg.get(i).getVslcode())){
//					lb_exit = true;
//				}
//			}
//			
//			//如果不存在,则新的
//			if(lb_exit == false){
//				Basvslvoy.add(servermsg.get(i));
//			}
//			
//		}
//		
//		
//		if(Basvslvoy.size()>0)
//			return Basvslvoy;
//		
//		return null;
//	}
//	
//	public Basvslvoy compare(Basvslvoy Basvslvoy1, Basvslvoy Basvslvoy2){
//		return null;
//	}
//	
	public boolean modifyBasvslvoy(Basvslvoy basvslvoy){
//		Connection con = null;
//		PreparedStatement sm = null;
//		ResultSet rs = null;
//		try{
//			con = ConnectManager.getConnection();
//			con.setAutoCommit(false);
//			String sql = "update Basvslvoy " +
//					"set enname=?,cnname=? WHERE  shipcode=? And vslcode=? ";
//			sm = con.prepareStatement(sql);
//			sm.setString(1, Basvslvoy.getEnname());
//			sm.setString(2, Basvslvoy.getCnname());
////			sm.setString(3, Basvslvoy.getSequence());
//			sm.setString(3, Basvslvoy.getShipcode());
//			sm.setString(4, Basvslvoy.getVslcode());
//			sm.executeUpdate();
//			con.setAutoCommit(true);
//		}catch(SQLException e){
//			e.printStackTrace();
//			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
//			return false;
//		}finally{
//			close(con);close(sm);close(rs);
//		}
		return true;
	}

	
	public boolean removeBasvslvoy(Basvslvoy basvslvoy){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			if(con == null) return false;
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from Basvslvoy where mainkey='"+basvslvoy.getMainkey()+"'");
			sm.addBatch("delete from basvvcarr where Sequence_fk='"+basvslvoy.getMainkey()+"'");

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
	public void setBasvslvoy(PreparedStatement sm, Basvslvoy basvslvoy) throws SQLException{
		sm.setInt(1,basvslvoy.getVslkey() == null?0:basvslvoy.getVslkey());
		sm.setString(2, basvslvoy.getVslcode());
		sm.setString(3, basvslvoy.getVslcname());
		sm.setString(4, basvslvoy.getVslename());
		sm.setString(5, basvslvoy.getVoyage());
		sm.setString(6, basvslvoy.getIe());
		sm.setString(7, basvslvoy.getCuscode());
		sm.setString(8, basvslvoy.getLinename());
		sm.setString(9, basvslvoy.getLocksign());
		sm.setString(10, basvslvoy.getSecvslsign());
		sm.setString(11, basvslvoy.getTradetype());
		sm.setString(12, basvslvoy.getMakercode());
		sm.setString(13, basvslvoy.getMaker());
		sm.setTimestamp(14, basvslvoy.getMadetime());
		sm.setString(15, basvslvoy.getModicode());
		sm.setString(16, basvslvoy.getModifier());
		sm.setTimestamp(17, basvslvoy.getModitime());
		sm.setString(18, basvslvoy.getLinecode());
		sm.setString(19, basvslvoy.getCustcode());
		sm.setString(20, basvslvoy.getCustcname());
		sm.setString(21, basvslvoy.getLinetype());
		sm.setDate(22, (Date) basvslvoy.getSaildate());
		sm.setDate(23, (Date) basvslvoy.getArrdate());
		sm.setTime(24, basvslvoy.getArrtime());
		sm.setTime(25, basvslvoy.getSailtime());
		sm.setString(26, basvslvoy.getOwnagent());
		sm.setString(27, basvslvoy.getCntronly());
		sm.setString(28, basvslvoy.getNeedclose());
		sm.setString(29, basvslvoy.getTransid());
		sm.setLong(30, basvslvoy.getVslldkey()==null?0:basvslvoy.getVslldkey());
		if (basvslvoy.getSequence() == null || basvslvoy.getSequence().equals(""))
		{
			sm.setString(31, basvslvoy.getMainkey().toString());
		}else{
			sm.setString(31, basvslvoy.getSequence());
		}
			
		
		sm.setInt(32, basvslvoy.getMainkey());
		
		
	}
	public Basvslvoy Basvslvoy(ResultSet rs) throws SQLException{
		return Basvslvoy(rs,false);
	}
	public Basvslvoy Basvslvoy(ResultSet rs,boolean flag) throws SQLException{
		Basvslvoy basvslvoy = new Basvslvoy();
		basvslvoy.setMainkey(rs.getInt("mainkey")) ;
//		basvslvoy.setShipcode(rs.getString("shipcode"));
		basvslvoy.setVslcode(rs.getString("vslcode"));
		basvslvoy.setVslcname(rs.getString("vslcname"));
		basvslvoy.setVslename(rs.getString("vslename"));   
		basvslvoy.setVoyage(rs.getString("voyage"));
		basvslvoy.setIe(rs.getString("ie"));
		basvslvoy.setCuscode(rs.getString("cuscode"));	    
		basvslvoy.setCustcname(rs.getString("custcname"));
		basvslvoy.setLinecode(rs.getString("linecode"));
		basvslvoy.setLinename(rs.getString("linename"));
		basvslvoy.setCustcode(rs.getString("custcode"));
		basvslvoy.setCustcname(rs.getString("custcname"));
		basvslvoy.setLinetype(rs.getString("linetype"));
		basvslvoy.setSaildate(rs.getDate("saildate"));
		basvslvoy.setSailtime(rs.getTime("sailtime"));
		basvslvoy.setArrdate(rs.getDate("arrdate"));
		basvslvoy.setArrtime(rs.getTime("arrtime"));
		basvslvoy.setMakercode(rs.getString("makercode"));
		basvslvoy.setMaker(rs.getString("maker"));
		basvslvoy.setMadetime(rs.getTimestamp("madetime"));
		basvslvoy.setModicode(rs.getString("modicode"));  
		basvslvoy.setModifier(rs.getString("modifier"));
		basvslvoy.setModitime(rs.getTimestamp("moditime")); 
		basvslvoy.setLinecode(rs.getString("linecode"));
		basvslvoy.setLinename(rs.getString("linename"));

		if(flag == true){
			basvslvoy.setSequence(String.valueOf(rs.getInt("mainkey")));
		}else{
			basvslvoy.setSequence(rs.getString("sequence"));
		}
			
			
			
		return basvslvoy;
	}
	
	

}
