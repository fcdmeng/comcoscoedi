package cosco.vsagent.dbo.ecd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cosco.vsagent.db.ConnectManager;
import cosco.vsagent.dbo.BaseDAO;
import cosco.vsagent.mapping.base.Basline;
import cosco.vsagent.mapping.ecd.Ecdbkcntr;

public class EcdbkcntrDAO extends BaseDAO {
	
	public List<Ecdbkcntr> getEcdbkcntr(String vslkey_seq, String blno_seq){
		return getEcdbkcntr(vslkey_seq, blno_seq, false);
	}
	/**
	 * 
	 * @param vslvoykey 航次主键
	 * @param blno_seq  提单主键
	 * @return
	 */
	public List<Ecdbkcntr> getEcdbkcntr(String vslkey_seq, String blno_seq, boolean flag){
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
			if (flag == true){
				rs = sm.executeQuery("select * from Ecdbkcntr where vslvoykey="+vslkey_seq+"  And bkkey="+blno_seq+"");
			}else{
				rs = sm.executeQuery("select * from Ecdbkcntr where vslkey_seq='"+vslkey_seq+"'  And blno_seq='"+blno_seq+"'");	
			}
			
			
			List<Ecdbkcntr> list = new ArrayList<Ecdbkcntr>();
			while(rs.next()){
				//Ecdbkcntr ecdbkcntr = new Ecdbkcntr();
				/**
				 * 主体还没有完成
				 */
				list.add(ecdbkcntr(rs, flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();

	}
	
	public Ecdbkcntr ecdbkcntr(ResultSet rs, boolean flag) {
		Ecdbkcntr ecdbkcntr = new Ecdbkcntr();
		try {
			ecdbkcntr.setBkkey(rs.getInt("bkkey"));
			ecdbkcntr.setVslvoykey(rs.getInt("vslvoykey"));
			
			if (flag == true){
				ecdbkcntr.setVslkey_seq(String.valueOf(rs.getInt("vslvoykey")));
				ecdbkcntr.setBlno_seq(String.valueOf(rs.getInt("bkkey")));
			}else{
				ecdbkcntr.setVslkey_seq(rs.getString("vslkey_seq"));
				ecdbkcntr.setBlno_seq(rs.getString("blno_seq"));
			}
			
			ecdbkcntr.setDockcode(rs.getString("dockcode"));
			ecdbkcntr.setSealno(rs.getString("sealno"));
			ecdbkcntr.setPkgcode(rs.getString("pkgcode"));
			ecdbkcntr.setPkgname(rs.getString("pkgname"));
			ecdbkcntr.setDockname(rs.getString("dockname"));
			ecdbkcntr.setPiece(rs.getInt("piece"));
			ecdbkcntr.setNtweight(rs.getDouble("ntweight"));
			ecdbkcntr.setTare(rs.getDouble("tare"));
			ecdbkcntr.setMeasure(rs.getDouble("measure"));
			ecdbkcntr.setFlesign(rs.getString("flesign"));
			ecdbkcntr.setSocsign(rs.getString("socsign"));
			ecdbkcntr.setValidsign(rs.getString("validsign"));
			ecdbkcntr.setRemark(rs.getString("remark"));
			ecdbkcntr.setLocation(rs.getString("location"));
			ecdbkcntr.setOverfront(rs.getString("overfront"));
			ecdbkcntr.setOverback(rs.getString("overback"));
			ecdbkcntr.setOverleft(rs.getString("overleft"));
			ecdbkcntr.setOverright(rs.getString("overright"));
			ecdbkcntr.setOverheight(rs.getString("overheight"));
			ecdbkcntr.setVent(rs.getString("vent"));
			ecdbkcntr.setVentunit(rs.getString("ventunit"));
			
			ecdbkcntr.setCntrcocode(rs.getString("cntrcocode"));
			ecdbkcntr.setCntrconame(rs.getString("cntrconame"));
			ecdbkcntr.setCntrno(rs.getString("cntrno"));
			ecdbkcntr.setCntrtype(rs.getString("cntrtype"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ecdbkcntr;
	}

}
