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
import cosco.vsagent.mapping.ecd.Ecdbkparty;

public class EcdbkpartyDAO extends BaseDAO {
	/**
	 * 
	 * @param vslvoykey 航次主键
	 * @param blno_seq  提单主键
	 * @return
	 */
	public List<Ecdbkparty> getEcdbkparty(String vslkey_seq, String blno_seq, boolean flag){
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
//			 order by 'CN','SH','N1'
			if (flag == true){
				rs = sm.executeQuery("select * from Ecdbkparty where vslvoykey="+vslkey_seq+"  And bkkey="+blno_seq+"");
			}else{
				rs = sm.executeQuery("select * from Ecdbkparty where vslkey_seq='"+vslkey_seq+"'  And blno_seq='"+blno_seq+"'");	
			}
			
			
			List<Ecdbkparty> list = new ArrayList<Ecdbkparty>();
			while(rs.next()){
				/**
				 * 主体还没有完成
				 */
				list.add(ecdbkparty(rs, flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();

	}
	
	public Ecdbkparty ecdbkparty(ResultSet rs, boolean flag) {
		Ecdbkparty ecdbkparty = new Ecdbkparty();
		try {
			ecdbkparty.setBkkey(rs.getInt("bkkey"));
			ecdbkparty.setVslvoykey(rs.getInt("vslvoykey"));
			
			if (flag == true){
				ecdbkparty.setVslkey_seq(String.valueOf(rs.getInt("vslvoykey")));
				ecdbkparty.setBlno_seq(String.valueOf(rs.getInt("bkkey")));
			}
			
			ecdbkparty.setPartytype(rs.getString("partytype"));
			ecdbkparty.setPartyname(rs.getString("partyname"));
			ecdbkparty.setPartymsg(rs.getString("partymsg"));
			ecdbkparty.setAutoprime(rs.getString("autoprime")==null?"0":rs.getString("autoprime"));
		     
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ecdbkparty;
	}

}
