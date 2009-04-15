package cosco.xml.dbo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cosco.vsagent.db.ConnectManager;
import cosco.vsagent.dbo.BaseDAO;
import cosco.xml.mapping.Xmlbkcntr;

public class XmlbkcntrDAO extends BaseDAO {
	public List<Xmlbkcntr> getXmlbkcntr(String sql, boolean flag){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			if (flag == true)
				con = ConnectManager.getServerConnection();
			else
				con = ConnectManager.getConnection();
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
			
			if( con == null) return Collections.EMPTY_LIST;
			
			List<Xmlbkcntr> list = new ArrayList<Xmlbkcntr>();
			
			while(rs.next()){
				list.add(Xmlbkcntr(rs, flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(con);close(sm);close(rs);
		}
					
		return Collections.EMPTY_LIST;
	}

	private Xmlbkcntr Xmlbkcntr(ResultSet rs, boolean flag) {
		Xmlbkcntr bkcntr = new Xmlbkcntr();
		try{
			bkcntr.setMainkey(rs.getInt("mainkey"));
			bkcntr.setIeflag(rs.getString("ieflag"));
			bkcntr.setXmlbkkey(rs.getInt("xmlbkkey"));
			bkcntr.setVslvoykey(rs.getInt("vslvoykey"));
			bkcntr.setCntrno(rs.getString("cntrno"));
			bkcntr.setCntrtype(rs.getString("cntrtype"));
			bkcntr.setCntrcocode(rs.getString("cntrcocode"));
			bkcntr.setCntrconame(rs.getString("cntrconame"));
			bkcntr.setFlesign(rs.getString("flesign"));
			bkcntr.setSealnosh(rs.getString("sealnosh"));
			bkcntr.setSealnoca(rs.getString("sealnoca"));
			bkcntr.setSealnocu(rs.getString("sealnocu"));
			bkcntr.setSealnoto(rs.getString("sealnoto"));
			bkcntr.setSealnoaa(rs.getString("sealnoaa"));
			bkcntr.setSealnoac(rs.getString("sealnoac"));
			bkcntr.setSealnoab(rs.getString("sealnoab"));
			bkcntr.setSealnoun1(rs.getString("sealnoun1"));
			bkcntr.setSealnoun2(rs.getString("sealnoun2"));
		     
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bkcntr;
	}
	public static void main(String argv[]){
		XmlbkcntrDAO bkcntrDAO = new XmlbkcntrDAO();
		List<Xmlbkcntr> bkcntr = bkcntrDAO.getXmlbkcntr("select * from Xmlbkcntr", true);
		System.out.println(bkcntr.size());
		
		for(Iterator it = bkcntr.iterator(); it.hasNext(); ) {
			Xmlbkcntr tmp = (Xmlbkcntr) it.next();
			
			System.out.println(tmp.getCntrtype()+ tmp.getCntrno());
			
		}
	}
}