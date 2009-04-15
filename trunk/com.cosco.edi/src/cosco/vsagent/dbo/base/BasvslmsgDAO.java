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
import cosco.vsagent.mapping.base.Basvslmsg;
import cosco.vsagent.util.UUIDGenerator;


public class BasvslmsgDAO extends BaseDAO{
	
	
	public List<Basvslmsg> getBasvslmsg(QueryInfo qi){
		
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			// 得到总记录数
			rs = sm.executeQuery("select count(*) from Basvslmsg");
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
			rs = sm.executeQuery("select * from Basvslmsg limit " + start + "," + qi.pageSize);
			List<Basvslmsg> list = new ArrayList<Basvslmsg>(qi.pageSize);

			while (rs.next()) {
				list.add(Basvslmsg(rs));
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
	public List<Basvslmsg> getBasvslmsg(String sql){
		return getBasvslmsg(sql, false);
	}
	/**
	 * 
	 * @param sql
	 * @param flag true 表示取服务器端数据，false 取客户端数据
	 * @return
	 */
	public List<Basvslmsg> getBasvslmsg(String sql, boolean flag){
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
			
			List<Basvslmsg> list = new ArrayList<Basvslmsg>();
			while(rs.next()){
				list.add(Basvslmsg(rs));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public Basvslmsg findByVslcode(String vslcode) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from BasvslMsg where vslcode ='"+vslcode+"'");
			if(rs.next()){
				return Basvslmsg(rs);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	
	
	
	public boolean inserBasvslmsg(Basvslmsg basvslmsg) {
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			con = ConnectManager.getConnection();
			if (con == null) return false;
			
			if(findByVslcode(basvslmsg.getVslcode())!=null) return false;
			
			String sql = "insert into Basvslmsg(mainkey,shipcode,vslcode,enname,cnname,nationcode,nationenam,nationcnam,nationpcod,nationpnam,ownercode,ownername ," +
					"ownertype ,typecode ,typename,length,width,height,netton,grosston ,loadton ,fulldraft ,lightdraft,holdteu ,deckteu,packnum,bulknum,horsepower," +
					"speed ,hatchnum,email,faxno ,isscno,isscclass,remark,sanitation,plagueno,deptcode,deptname,tel,mobile,tlx,sumteu,selfagent,online,makercode,maker," +
					"madetime,modicode,modifier,moditime,linecode,linename,builddate,sequence)" +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);
			
			setBasvslmsg(sm, basvslmsg);
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
	public List<Basvslmsg> compare(List<Basvslmsg> clientmsg, List<Basvslmsg> servermsg){
		List<Basvslmsg> basvslmsg = new ArrayList<Basvslmsg>();
		boolean lb_exit;
		for(int i =0;i<clientmsg.size();i++){
			String vslcode = clientmsg.get(i).getVslcode();
			lb_exit = false;
			
			for(int k = 0; k<servermsg.size();k++){
				if(vslcode.equals(servermsg.get(i).getVslcode())){
					lb_exit = true;
				}
			}
			
			if(lb_exit == false){
				basvslmsg.add(clientmsg.get(i));
			}
			
		}
		
		for(int i =0;i<servermsg.size();i++){
			String vslcode = servermsg.get(i).getVslcode();
			lb_exit = false;
			
			for(int k = 0; k<clientmsg.size();k++){
				if(vslcode.equals(clientmsg.get(i).getVslcode())){
					lb_exit = true;
				}
			}
			
			//如果不存在,则新的
			if(lb_exit == false){
				basvslmsg.add(servermsg.get(i));
			}
			
		}
		
		
		if(basvslmsg.size()>0)
			return basvslmsg;
		
		return null;
	}
	
	public Basvslmsg compare(Basvslmsg basvslmsg1, Basvslmsg basvslmsg2){
		return null;
	}
	
	public boolean modifyBasvslmsg(Basvslmsg basvslmsg){
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			con.setAutoCommit(false);
			String sql = "update Basvslmsg " +
					"set enname=?,cnname=? WHERE  shipcode=? And vslcode=? ";
			sm = con.prepareStatement(sql);
			sm.setString(1, basvslmsg.getEnname());
			sm.setString(2, basvslmsg.getCnname());
//			sm.setString(3, basvslmsg.getSequence());
			sm.setString(3, basvslmsg.getShipcode());
			sm.setString(4, basvslmsg.getVslcode());
			sm.executeUpdate();
			con.setAutoCommit(true);
		}catch(SQLException e){
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
			return false;
		}finally{
			close(con);close(sm);close(rs);
		}
		return true;
	}

	
	public boolean removeBasvslmsg(Basvslmsg basvslmsg){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			if(con == null) return false;
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from Basvslmsg where vslcode='"+basvslmsg.getVslcode()+"'");
			/*if(user instanceof Teacher)
				sm.addBatch("delete from  iuser_course where iuser_id="+user.getId());
		    */
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
	public void setBasvslmsg(PreparedStatement sm, Basvslmsg basvslmsg) throws SQLException{
		sm.setInt(1,basvslmsg.getMainkey()==null?0:basvslmsg.getMainkey()) ;
		sm.setString(2,basvslmsg.getShipcode());
		sm.setString(3,basvslmsg.getVslcode());
		sm.setString(4,basvslmsg.getEnname());
		sm.setString(5,basvslmsg.getCnname());   
		sm.setString(6,basvslmsg.getNationcode()==null?"":basvslmsg.getNationcode());
		sm.setString(7,basvslmsg.getNationenam());
		sm.setString(8,basvslmsg.getNationcnam());    
		sm.setString(9,basvslmsg.getNationpcod()==null?"":basvslmsg.getNationpcod());
		sm.setString(10,basvslmsg.getNationcnam());
		sm.setString(11,basvslmsg.getOwnercode()==null?"":basvslmsg.getOwnercode());
		sm.setString(12,basvslmsg.getOwnername()==null?"":basvslmsg.getOwnername());
		sm.setString(13,basvslmsg.getOwnertype()); 
		sm.setString(14,basvslmsg.getTypecode()); 
		sm.setString(15,basvslmsg.getTypename());
		sm.setString(16,basvslmsg.getLength());
		sm.setString(17,basvslmsg.getWidth());
		sm.setString(18,basvslmsg.getHeight());
		sm.setDouble(19,basvslmsg.getNetton()==null?0.00:basvslmsg.getNetton());
		sm.setDouble(20,basvslmsg.getGrosston()==null?0.00:basvslmsg.getGrosston()); 
		sm.setDouble(21,basvslmsg.getLoadton()==null?0.00:basvslmsg.getLoadton()); 
		sm.setString(22,basvslmsg.getFulldraft()); 
		sm.setString(23,basvslmsg.getLightdraft());
		sm.setLong(24,basvslmsg.getHoldteu()==null?0:basvslmsg.getHoldteu()); 
		sm.setLong(25,basvslmsg.getDeckteu()==null?0:basvslmsg.getDeckteu());
		sm.setString(26,basvslmsg.getPacknum());
		sm.setString(27,basvslmsg.getBulknum());
		sm.setString(28,basvslmsg.getHorsepower());
		sm.setString(29,basvslmsg.getSpeed()); 
		sm.setString(30,basvslmsg.getHatchnum());
		sm.setString(31,basvslmsg.getEmail());
		sm.setString(32,basvslmsg.getFaxno()); 
		sm.setString(33,basvslmsg.getIsscno());
		sm.setString(34,basvslmsg.getIsscclass());
		sm.setString(35,basvslmsg.getRemark());
		sm.setString(36,basvslmsg.getSanitation());
		sm.setString(37,basvslmsg.getPlagueno());
		sm.setString(38,basvslmsg.getDeptcode()==null?"":basvslmsg.getDeptcode());
		sm.setString(39,basvslmsg.getDeptname()==null?"":basvslmsg.getDeptname());
		sm.setString(40,basvslmsg.getTel());     
		sm.setString(41,basvslmsg.getMobile());
		sm.setString(42,basvslmsg.getTlx());
		sm.setLong(43,basvslmsg.getSumteu()==null?0:basvslmsg.getSumteu()); 
		sm.setString(44,basvslmsg.getSelfagent());
		sm.setString(45,basvslmsg.getOnline());
		sm.setString(46,basvslmsg.getMakercode());
		sm.setString(47,basvslmsg.getMaker());
		sm.setTimestamp(48, basvslmsg.getMadetime());
		sm.setString(49,basvslmsg.getModicode()==null?"":basvslmsg.getModicode()  );
		sm.setString(50,basvslmsg.getModifier());
		sm.setTimestamp(51, basvslmsg.getModitime()); 
		sm.setString(52,basvslmsg.getLinecode()==null?"":basvslmsg.getLinecode());
		sm.setString(53,basvslmsg.getLinename());
		sm.setString(54,basvslmsg.getBuilddate());
		
		if(basvslmsg.getSequence() == null){
			//此处应该有个状态判断,判断何时产生主键
			String sequence = (String) new UUIDGenerator().generate();
			basvslmsg.setSequence(sequence);
			
		}else{
			basvslmsg.setSequence(basvslmsg.getSequence());
		}
			
		sm.setString(55,basvslmsg.getSequence());
		
		
		
	}
	public Basvslmsg Basvslmsg(ResultSet rs) throws SQLException{
		return Basvslmsg(rs,false);
	}
	public Basvslmsg Basvslmsg(ResultSet rs,boolean flag) throws SQLException{
		Basvslmsg basvslmsg = new Basvslmsg();
		basvslmsg.setMainkey(rs.getInt("mainkey")) ;
		basvslmsg.setShipcode(rs.getString("shipcode"));
		basvslmsg.setVslcode(rs.getString("vslcode"));
		basvslmsg.setEnname(rs.getString("enname"));
		basvslmsg.setCnname(rs.getString("cnname"));   
		basvslmsg.setNationcode(rs.getString("nationcode"));
		basvslmsg.setNationenam(rs.getString("nationenam"));
		basvslmsg.setNationcnam(rs.getString("nationcnam"));	    
		basvslmsg.setNationpcod(rs.getString("nationpcod"));
		basvslmsg.setNationpnam(rs.getString("nationpnam"));
		basvslmsg.setOwnercode(rs.getString("ownercode"));
		basvslmsg.setOwnername(rs.getString("ownername")); 
		basvslmsg.setOwnertype(rs.getString("ownertype")); 
		basvslmsg.setTypecode(rs.getString("typecode")); 
		basvslmsg.setTypename(rs.getString("typename"));
		basvslmsg.setLength(rs.getString("length"));
		basvslmsg.setWidth(rs.getString("width"));
		basvslmsg.setHeight(rs.getString("height"));
		basvslmsg.setNetton(rs.getDouble("netton"));
		basvslmsg.setGrosston(rs.getDouble("grosston")); 
		basvslmsg.setLoadton(rs.getDouble("loadton")); 
		basvslmsg.setFulldraft(rs.getString("fulldraft")); 
		basvslmsg.setLightdraft(rs.getString("lightdraft"));
		basvslmsg.setHoldteu(rs.getLong("holdteu")); 
		basvslmsg.setDeckteu(rs.getLong("deckteu"));
		basvslmsg.setPacknum(rs.getString("packnum"));
		basvslmsg.setBulknum(rs.getString("bulknum"));
		basvslmsg.setHorsepower(rs.getString("horsepower"));
		basvslmsg.setSpeed(rs.getString("speed")); 
		basvslmsg.setHatchnum(rs.getString("hatchnum"));
		basvslmsg.setEmail(rs.getString("email"));
		basvslmsg.setFaxno(rs.getString("faxno")); 
		basvslmsg.setIsscno(rs.getString("isscno"));
		basvslmsg.setIsscclass(rs.getString("isscclass"));
		basvslmsg.setRemark(rs.getString("remark"));
		basvslmsg.setSanitation(rs.getString("sanitation"));
		basvslmsg.setPlagueno(rs.getString("plagueno"));
		basvslmsg.setDeptcode(rs.getString("deptcode"));
		basvslmsg.setDeptname(rs.getString("deptname"));
		basvslmsg.setTel(rs.getString("tel"));     
		basvslmsg.setMobile(rs.getString("mobile"));
		basvslmsg.setTlx(rs.getString("tlx"));
		basvslmsg.setSumteu(rs.getLong("sumteu")); 
		basvslmsg.setSelfagent(rs.getString("selfagent"));
		basvslmsg.setOnline(rs.getString("online"));
		basvslmsg.setMakercode(rs.getString("makercode"));
		basvslmsg.setMaker(rs.getString("maker"));
		basvslmsg.setMadetime(rs.getTimestamp("madetime"));
		basvslmsg.setModicode(rs.getString("modicode"));  
		basvslmsg.setModifier(rs.getString("modifier"));
		basvslmsg.setModitime(rs.getTimestamp("moditime")); 
		basvslmsg.setLinecode(rs.getString("linecode"));
		basvslmsg.setLinename(rs.getString("linename"));
		basvslmsg.setBuilddate(rs.getString("builddate"));
		if(flag == false){
		   basvslmsg.setSequence(rs.getString("sequence"));
		}
		
		return basvslmsg;
	}
	
	

}
