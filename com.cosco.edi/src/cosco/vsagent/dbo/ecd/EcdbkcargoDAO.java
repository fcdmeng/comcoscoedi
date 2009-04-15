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
import cosco.vsagent.mapping.ecd.Ecdbkcargo;

public class EcdbkcargoDAO extends BaseDAO {

	public List<Ecdbkcargo> getEcdbkcargo(String vslkey_seq, String blno_seq,
			boolean flag) {
		// TODO Auto-generated method stub
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
				rs = sm.executeQuery("select * from Ecdbkcargo where vslvoykey="+vslkey_seq+"  And bkkey="+blno_seq+"");
			}else{
				rs = sm.executeQuery("select * from Ecdbkcargo where vslkey_seq='"+vslkey_seq+"'  And blno_seq='"+blno_seq+"'");
			}
			
			
			List<Ecdbkcargo> list = new ArrayList<Ecdbkcargo>();
			while(rs.next()){
				/**
				 * 主体还没有完成
				 */
				list.add(ecdbkcargo(rs, flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();


	}

	private Ecdbkcargo ecdbkcargo(ResultSet rs, boolean flag) {
		// TODO Auto-generated method stub
		Ecdbkcargo ecdbkcargo = new Ecdbkcargo();
		try {
			ecdbkcargo.setBkkey(rs.getInt("bkkey"));
			ecdbkcargo.setVslvoykey(rs.getInt("vslvoykey"));
			
			if (flag == true){
				ecdbkcargo.setVslkey_seq(String.valueOf(rs.getInt("vslvoykey")));
				ecdbkcargo.setBlno_seq(String.valueOf(rs.getInt("bkkey")));
			}
			
			ecdbkcargo.setCargoname(rs.getString("cargoname"));
			ecdbkcargo.setTypecode(rs.getString("typecode"));
			ecdbkcargo.setTypename(rs.getString("typename"));
			ecdbkcargo.setPiece(rs.getInt("piece"));
			ecdbkcargo.setPkgcode(rs.getString("pkgcode"));
			ecdbkcargo.setPkgname(rs.getString("pkgname"));
			ecdbkcargo.setNtweight(rs.getDouble("ntweight"));
			ecdbkcargo.setGtweight(rs.getDouble("gtweight"));
			ecdbkcargo.setMeasure(rs.getDouble("measure"));
			ecdbkcargo.setDgsign(rs.getString("dgsign"));
			ecdbkcargo.setDguncode(rs.getString("dguncode"));
			ecdbkcargo.setDgpageno(rs.getString("dgpageno"));
			
			ecdbkcargo.setDgclass(rs.getString("dgclass"));
			ecdbkcargo.setDgflag(rs.getString("dgflag"));
			ecdbkcargo.setDgflash(rs.getString("dgflash"));
			ecdbkcargo.setMarks(rs.getString("marks"));
			ecdbkcargo.setCargodesc(rs.getString("cargodesc"));
			ecdbkcargo.setRfsign(rs.getString("rfsign"));
			ecdbkcargo.setRfunit(rs.getString("rfunit"));
			ecdbkcargo.setRffrom(rs.getString("rffrom"));
			ecdbkcargo.setRfto(rs.getString("rfto"));
			ecdbkcargo.setRemark(rs.getString("remark"));
			ecdbkcargo.setBlpkgname(rs.getString("blpkgname"));
			ecdbkcargo.setBlgtweight(rs.getString("blgtweight"));
			ecdbkcargo.setBlmeasure(rs.getString("blmeasure"));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ecdbkcargo;

	}

}
