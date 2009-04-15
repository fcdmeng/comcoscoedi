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
import cosco.xml.mapping.Xmlbkcargo;
import cosco.xml.mapping.Xmlbkparty;

public class XmlbkcargoDAO extends BaseDAO {
	public List<Xmlbkcargo> getXmlbkcargo(String sql, boolean flag){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			if (flag == true)
				con = ConnectManager.getServerConnection();
			else
				con = ConnectManager.getConnection();
			
			if (con == null) return Collections.EMPTY_LIST;
			
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
			
			List<Xmlbkcargo> list = new ArrayList<Xmlbkcargo>();
			while(rs.next()){
				list.add(Xmlbkcargo(rs, flag));
			}
			return list;
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.EMPTY_LIST;
	}

	private Xmlbkcargo Xmlbkcargo(ResultSet rs, boolean flag) {
		// TODO Auto-generated method stub
		Xmlbkcargo bkcargo = new Xmlbkcargo();
		try {
			bkcargo.setMainkey(rs.getInt("mainkey"));
			bkcargo.setBcflag(rs.getString("bcflag"));
			bkcargo.setIeflag(rs.getString("ieflag"));
			bkcargo.setXmlbkkey(rs.getInt("xmlbkkey"));
			bkcargo.setVslvoykey(rs.getInt("vslvoykey"));
			bkcargo.setSeqno(rs.getInt("seqno"));
			bkcargo.setPiece(rs.getInt("piece"));
			bkcargo.setPkgcode(rs.getString("pkgcode"));
			bkcargo.setPkgname(rs.getString("pkgname"));
			bkcargo.setMarks(rs.getString("marks"));
			bkcargo.setDgsign(rs.getString("dgsign"));
			bkcargo.setUndgcode(rs.getString("undgcode"));
			bkcargo.setCusttcode(rs.getString("custtcode"));
			bkcargo.setAdddesc(rs.getString("adddesc"));
			bkcargo.setGtweight(rs.getDouble("gtweight"));
			bkcargo.setRemark(rs.getString("remark"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bkcargo;
	}
	
	public static void main(String argv[]){
		XmlbkcargoDAO cargoDAO = new XmlbkcargoDAO();
		List<Xmlbkcargo> bkcargo = cargoDAO.getXmlbkcargo("select * from Xmlbkcargo", true);
		System.out.println(bkcargo.size());
		
		for(Iterator it = bkcargo.iterator(); it.hasNext(); ) {
			Xmlbkcargo tmp = (Xmlbkcargo) it.next();
			System.out.println(tmp.getMarks());
			
		}
	}
}