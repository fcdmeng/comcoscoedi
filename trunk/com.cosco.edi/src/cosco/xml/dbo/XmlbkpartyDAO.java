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
import cosco.xml.mapping.Xmlbkparty;

public class XmlbkpartyDAO extends BaseDAO {
	public List<Xmlbkparty> getXmlbkparty(String sql, boolean flag){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			if(flag = true)
				con = ConnectManager.getServerConnection();
			else
				con = ConnectManager.getConnection();
			
			if (con == null) return Collections.EMPTY_LIST;
			
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
			List<Xmlbkparty> list = new ArrayList<Xmlbkparty>();
			while(rs.next()){
				list.add(Xmlbkparty(rs, flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(con);close(sm);close(rs);
		}
		
		return Collections.EMPTY_LIST;
	}

	private Xmlbkparty Xmlbkparty(ResultSet rs, boolean flag) {
		Xmlbkparty bkparty = new Xmlbkparty();
		try{
			bkparty.setMainkey(rs.getInt("mainkey"));
			bkparty.setBcflag(rs.getString("bcflag"));
			bkparty.setIeflag(rs.getString("ieflag"));
			bkparty.setXmlbkkey(rs.getInt("xmlbkkey"));
			bkparty.setVslvoykey(rs.getInt("vslvoykey"));
			bkparty.setPartytype(rs.getString("partytype"));
			bkparty.setPartyname(rs.getString("partyname"));
			bkparty.setPartyid(rs.getString("partyid"));
			bkparty.setPartymsg(rs.getString("partymsg"));
			bkparty.setPtline(rs.getString("ptline"));
			bkparty.setPtcity(rs.getString("ptcity"));
			bkparty.setPtentid(rs.getString("ptentid"));
			bkparty.setPtentname(rs.getString("ptentname"));
			bkparty.setPtpostid(rs.getString("ptpostid"));
			bkparty.setPtcountry(rs.getString("ptcountry"));
			bkparty.setPtctcm1(rs.getString("ptctcm1"));
			bkparty.setPtctty1(rs.getString("ptctty1"));
			bkparty.setPtctcm1(rs.getString("ptctcm2"));
			bkparty.setPtctty1(rs.getString("ptctty2"));
			bkparty.setPtctcm1(rs.getString("ptctcm3"));
			bkparty.setPtctty1(rs.getString("ptctty3"));
			bkparty.setContname(rs.getString("contname"));
			bkparty.setCtctcm1(rs.getString("ctctcm1"));
			bkparty.setCtctty1(rs.getString("ctctty1"));
			bkparty.setCtctcm1(rs.getString("ctctcm2"));
			bkparty.setCtctty1(rs.getString("ctctty2"));
			bkparty.setCtctcm1(rs.getString("ctctcm3"));
			bkparty.setCtctty1(rs.getString("ctctty3"));
		     
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bkparty;
	}
	public static void main(String argv[]){
		XmlbkpartyDAO bkpartyDAO = new XmlbkpartyDAO();
		List<Xmlbkparty> bkparty = bkpartyDAO.getXmlbkparty("select * from Xmlbkparty", true);
		System.out.println(bkparty.size());
		
		for(Iterator it = bkparty.iterator(); it.hasNext(); ) {
			Xmlbkparty tmp = (Xmlbkparty) it.next();
			System.out.print(tmp.getPartytype()+"======");
			System.out.println(tmp.getPartymsg());
			
		}
	}
}