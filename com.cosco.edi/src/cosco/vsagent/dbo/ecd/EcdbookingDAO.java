package cosco.vsagent.dbo.ecd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cosco.vsagent.console.ConsoleCommand;
import cosco.vsagent.db.ConnectManager;
import cosco.vsagent.db.QueryInfo;
import cosco.vsagent.dbo.BaseDAO;
import cosco.vsagent.mapping.ecd.Ecdbkcargo;
import cosco.vsagent.mapping.ecd.Ecdbkcntr;
import cosco.vsagent.mapping.ecd.Ecdbkparty;
import cosco.vsagent.mapping.ecd.Ecdbooking;

public class EcdbookingDAO extends BaseDAO {
public List<Ecdbooking> getEcdbooking(QueryInfo qi, String vslkey){
		
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			// 得到总记录数
			rs = sm.executeQuery("select count(*) from Ecdbooking Where vslkey_seq = '"+vslkey+"'");
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
			rs = sm.executeQuery("select * from Ecdbooking Where vslkey_seq ='"+vslkey+"' limit " + start + "," + qi.pageSize);
			List<Ecdbooking> list = new ArrayList<Ecdbooking>(qi.pageSize);
			int i_f=0,i_m =0,i_l = 0;
			int mfcusign=0,mfblsign=0;
			
			while (rs.next()) {
				Ecdbooking o = Ecdbooking(rs);
				list.add(o);
				if(o.getMfblsign().equals("1")) mfblsign = mfblsign + 1;
				if(o.getMfcusign().equals("1")) mfcusign = mfcusign + 1;
				if(o.getBktype().equals("1")) i_f = i_f + 1;
				if(o.getBktype().equals("2")) i_m = i_m + 1;
				if(o.getBktype().equals("3")) i_l = i_l + 1;

			}
			
			String str = "";
			if(i_f>0) str = i_f +"票整箱 ";
			if(i_m>0) str += i_m +"票主票 ";
			if(i_l>0) str += i_l +"票分票 ";
			if(mfcusign>0) str += mfcusign +"海关舱单 ";
			if(mfblsign>0) str += mfblsign +"目的港舱单 ";
			new ConsoleCommand().logconsole(str, true);
			
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
	public List<Ecdbooking> getEcdbooking(String sql){
		return getEcdbooking(sql, false);
	}
	/**
	 * 
	 * @param sql
	 * @param flag true 表示取服务器端数据，false 取客户端数据
	 * @return
	 */
	public List<Ecdbooking> getEcdbooking(String sql, boolean flag){
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
			
			List<Ecdbooking> list = new ArrayList<Ecdbooking>();
			while(rs.next()){
				if(rs.getString("bachsign").equals("1")){
					System.out.println("退关提单号:"+rs.getString("blno")+"系统不做导入处理……");
					continue;
				}
				list.add(Ecdbooking(rs,flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public Ecdbooking findByBlno(String vslkey, String blno) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			
			con = ConnectManager.getConnection();
			
			if(con == null) return null;
			
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Ecdbooking where vslkey_seq='"+vslkey+"' And blno ='"+blno+"'");
			if(rs.next()){
				return Ecdbooking(rs);

			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return null;
	}
	public Ecdbooking Ecdbooking(ResultSet rs){
		return Ecdbooking(rs, false);
	}
	public Ecdbooking Ecdbooking(ResultSet rs, boolean flag) {
		Ecdbooking ecdbooking = new Ecdbooking();
		try {
			ecdbooking.setMainkey(rs.getInt("mainkey"));
			ecdbooking.setVslvoykey(rs.getInt("vslvoykey"));
			ecdbooking.setBlno(rs.getString("blno"));
			ecdbooking.setMainblno(rs.getString("mainblno"));
			ecdbooking.setBkno(rs.getString("bkno"));
			ecdbooking.setCarrcode(rs.getString("carrcode"));
			ecdbooking.setCarrename(rs.getString("carrename"));
			ecdbooking.setBkoffcode(rs.getString("bkoffcode"));
			ecdbooking.setBkoffcname(rs.getString("bkoffcname"));
			ecdbooking.setBkcode(rs.getString("bkcode"));
			ecdbooking.setBkcname(rs.getString("bkcname"));
			ecdbooking.setRcvpcode(rs.getString("rcvpcode"));
			ecdbooking.setRevpename(rs.getString("revpename"));
			ecdbooking.setLdpcode(rs.getString("ldpcode"));
			ecdbooking.setLdpename(rs.getString("ldpename"));
			ecdbooking.setTransport(rs.getString("transport"));
			ecdbooking.setTransename(rs.getString("transename"));
			ecdbooking.setDispcode(rs.getString("dispcode"));
			ecdbooking.setDispename(rs.getString("dispename"));
			ecdbooking.setDispcode(rs.getString("dispcode"));
			ecdbooking.setDispename(rs.getString("dispename"));
			ecdbooking.setDelpcode(rs.getString("delpcode"));
			ecdbooking.setDelpename(rs.getString("delpename"));
			ecdbooking.setCarrtype(rs.getString("carrtype"));
			ecdbooking.setPccode(rs.getString("pccode"));
			ecdbooking.setPcename(rs.getString("pcename"));
			ecdbooking.setBktype(rs.getString("bktype"));
			ecdbooking.setBachsign(rs.getString("bachsign"));
			ecdbooking.setSecvslsign(rs.getString("secvslsign"));
			ecdbooking.setMktcode(rs.getString("mktcode"));
			ecdbooking.setMarketer(rs.getString("marketer"));
			ecdbooking.setPreload(rs.getString("preload"));
			ecdbooking.setPrename(rs.getString("prename"));
			ecdbooking.setFactload(rs.getString("factload"));
			ecdbooking.setBlppat(rs.getString("blppat"));
			ecdbooking.setBlcpat(rs.getString("blcpat"));
			ecdbooking.setBlsignat(rs.getString("blsignat"));
			ecdbooking.setBlsigndate(rs.getString("blsigndate"));
			ecdbooking.setBkcount(rs.getString("bkcount"));
			ecdbooking.setMfcusign(rs.getString("mfcusign"));
			ecdbooking.setMfblsign(rs.getString("mfblsign"));
			ecdbooking.setRemark(rs.getString("remark"));
			ecdbooking.setBlldp(rs.getString("blldp"));
			ecdbooking.setBldelp(rs.getString("bldelp"));
			ecdbooking.setBldisp(rs.getString("bldisp"));
			ecdbooking.setCloseAfter("0");
			ecdbooking.setSign(rs.getString("sign") == null?"0":rs.getString("sign"));
			ecdbooking.setIssendedi(rs.getString("issendedi")== null?"0":rs.getString("issendedi"));
			ecdbooking.setIetype(rs.getString("ietype"));
			ecdbooking.setIscuspre(rs.getString("iscuspre")==null?"0":rs.getString("iscuspre"));
			ecdbooking.setPrename(rs.getString("prename"));
			if (flag == true){
				ecdbooking.setVslkey_seq(String.valueOf(rs.getInt("vslvoykey")));
				ecdbooking.setSequence(String.valueOf(rs.getInt("mainkey")));
			}else{
				ecdbooking.setVslkey_seq(rs.getString("vslkey_seq"));
				ecdbooking.setSequence(rs.getString("sequence"));
			}
			
			ecdbooking.setMakercode(rs.getString("makercode"));
			ecdbooking.setMaker(rs.getString("maker")); 
			ecdbooking.setMadetime(rs.getTimestamp("madetime")); 
			ecdbooking.setModicode(rs.getString("modicode")); 
			ecdbooking.setModifier(rs.getString("modifier"));
			ecdbooking.setModitime(rs.getTimestamp("moditime")); 
			
			ecdbooking.setCntr(new EcdbkcntrDAO().getEcdbkcntr(ecdbooking.getVslkey_seq(), ecdbooking.getSequence(), flag));
			ecdbooking.setParty(new EcdbkpartyDAO().getEcdbkparty(ecdbooking.getVslkey_seq(), ecdbooking.getSequence(), flag));
			ecdbooking.setCargo(new EcdbkcargoDAO().getEcdbkcargo(ecdbooking.getVslkey_seq(), ecdbooking.getSequence(), flag));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ecdbooking;
	}
	
	public boolean inserEcdbooking(Ecdbooking o) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement  sm = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			con = ConnectManager.getConnection();
			if (con == null) return false;
			
			con.setAutoCommit(false);
			
			
			//重复
			if(findByBlno(o.getVslkey_seq(),o.getBlno())!=null) return false;
			String sql;
			sql = "insert into Ecdbooking(mainkey,vslvoykey,blno,mainblno,bkno,carrcode,carrename," +
			"bkoffcode,bkoffcname,bkcode,bkcname,rcvpcode,revpename,ldpcode,ldpename,transport,transename," +
			"dispcode,dispename,delpcode,delpename,carrtype,pccode,pcename,bktype,bachsign,secvslsign," +
			"mktcode,marketer,preload,factload,blppat,blcpat,blsignat,blsigndate,bkcount,mfcusign,mfblsign," +
			"makercode,maker,madetime,modicode,modifier,moditime,remark,blldp,bldelp,bldisp,sign,issendedi," +
			"ietype,iscuspre,prename,close_After,is_web,vslkey_seq,sequence"+
			") values("+"?,?,?,?,?,?,?,?,?,?,?,?,?, ?,?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"+
			")";
			sm = con.prepareStatement(sql);
			sm.setInt(1, o.getMainkey());
			sm.setInt(2, o.getVslvoykey());
			sm.setString(3, o.getBlno());
			sm.setString(4, o.getMainblno());
			sm.setString(5, o.getBkno());
			sm.setString(6, o.getCarrcode());
			sm.setString(7, o.getCarrename());
			sm.setString(8, o.getBkoffcode());
			sm.setString(9, o.getBkoffcname());
			sm.setString(10, o.getBkcode());
			sm.setString(11, o.getBkcname());
			sm.setString(12, o.getRcvpcode());
			sm.setString(13, o.getRevpename());
			sm.setString(14, o.getLdpcode());
			sm.setString(15, o.getLdpename());
			sm.setString(16, o.getTransport());
			sm.setString(17, o.getTransename());
			sm.setString(18, o.getDispcode());
			sm.setString(19, o.getDispename());
			sm.setString(20, o.getDelpcode());
			sm.setString(21, o.getDelpename());
			sm.setString(22, o.getCarrtype());
			sm.setString(23, o.getPccode());
			sm.setString(24, o.getPcename());
			sm.setString(25, o.getBktype());
			sm.setString(26, o.getBachsign());
			sm.setString(27, o.getSecvslsign());
			sm.setString(28, o.getMktcode());
			sm.setString(29, o.getMarketer());
			sm.setString(30, o.getPreload());
			sm.setString(31, o.getFactload());
			sm.setString(32, o.getBlppat());
			sm.setString(33, o.getBlcpat());
			sm.setString(34, o.getBlsignat());
			sm.setString(35, o.getBlsigndate());
			sm.setString(36, o.getBkcount());
			sm.setString(37, o.getMfcusign());
			sm.setString(38, o.getMfblsign());
			sm.setString(39, o.getMakercode());
			sm.setString(40, o.getMaker());
			sm.setTimestamp(41, o.getMadetime());
			sm.setString(42, o.getModicode());
			sm.setString(43, o.getModifier());
			sm.setTimestamp(44, o.getModitime());
			sm.setString(45, o.getRemark());
			sm.setString(46, o.getBlldp());
			sm.setString(47, o.getBldelp());
			sm.setString(48, o.getBldisp());
			sm.setString(49, o.getSign());
			sm.setString(50, o.getIssendedi());
			sm.setString(51, o.getIetype());
			sm.setString(52, o.getIscuspre());
			sm.setString(53, o.getPrename());
			sm.setString(54, o.getCloseAfter());
			sm.setString(55, o.getIsweb());
			sm.setString(56, o.getVslkey_seq());
			sm.setString(57, o.getSequence());
			
			sm.executeUpdate();
			
			
			if (o.getCntr().size()>0){
				sql = "insert into Ecdbkcntr(mainkey,bkkey,vslvoykey,cntrtype,cntrno,cntrcocode,cntrconame," +
				"dockcode,dockname,sealno,piece,pkgcode,pkgname,ntweight,tare,measure,flesign,socsign," +
				"validsign,remark,location,overfront,overback,overleft,overright,overheight,vent," +
				"ventunit,vslkey_seq,blno_seq) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				sm = con.prepareStatement(sql);
				
				for (int i = 0; i< o.getCntr().size();i++){
					Ecdbkcntr cntr = o.getCntr().get(i);
					sm.setInt(1, cntr.getMainkey()==null?-9:cntr.getMainkey());
					sm.setInt(2, cntr.getBkkey());
					sm.setInt(3, cntr.getVslvoykey());
					sm.setString(4, cntr.getCntrtype());
					sm.setString(5, cntr.getCntrno());
					sm.setString(6, cntr.getCntrcocode());
					sm.setString(7, cntr.getCntrconame());
					sm.setString(8, cntr.getDockcode());
					sm.setString(9, cntr.getDockname());
					sm.setString(10, cntr.getSealno());
					sm.setInt(11, cntr.getPiece());
					sm.setString(12, cntr.getPkgcode());
					sm.setString(13, cntr.getPkgname());
					sm.setDouble(14, cntr.getNtweight());
					sm.setDouble(15, cntr.getTare());
					sm.setDouble(16, cntr.getMeasure());
					sm.setString(17, cntr.getFlesign());
					sm.setString(18, cntr.getSocsign());
					sm.setString(19, cntr.getValidsign());
					sm.setString(20, cntr.getRemark());
					sm.setString(21, cntr.getLocation());
					sm.setString(22, cntr.getOverfront());
					sm.setString(23, cntr.getOverback());
					sm.setString(24, cntr.getOverleft());
					sm.setString(25, cntr.getOverright());
					sm.setString(26, cntr.getOverheight());
					sm.setString(27, cntr.getVent());
					sm.setString(28, cntr.getVentunit());
					sm.setString(29, cntr.getVslkey_seq());
					sm.setString(30, cntr.getBlno_seq());
					
					sm.executeUpdate();
				}
				
				
			}
			
			if (o.getParty().size()>0){
				sql = "insert into Ecdbkparty(mainkey,bkkey,vslvoykey,partytype,partyname,partymsg,autoprime,vslkey_seq,blno_seq)" +
						" values(?,?,?,?,?,?,?,?,?)";
				sm = con.prepareStatement(sql);
				for (int i = 0; i< o.getParty().size();i++){
					Ecdbkparty party = o.getParty().get(i);
					sm.setInt(1, party.getMainkey()==null?-9:party.getMainkey());
					sm.setInt(2, party.getBkkey());
					sm.setInt(3, party.getVslvoykey());
					sm.setString(4, party.getPartytype());
					sm.setString(5, party.getPartyname());
					sm.setString(6, party.getPartymsg());
					sm.setString(7, party.getAutoprime());
					sm.setString(8, party.getVslkey_seq());
					sm.setString(9, party.getBlno_seq());
					
					sm.executeUpdate();
				}
				
				
			}
				
			if (o.getCargo().size()>0){
				sql = "insert into Ecdbkcargo(mainkey,bkkey,vslvoykey,cargoname,typecode,typename," +
				"piece,pkgcode,pkgname,measure,ntweight,gtweight,dgsign,dguncode,dgpageno," +
				"dgclass,dgflag,dgflash,marks,cargodesc,rfsign,rfunit,rffrom,rfto,remark," +
				"blpkgname,blgtweight,blmeasure,vslkey_seq,blno_seq) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					sm = con.prepareStatement(sql);
		
				for (int i = 0; i< o.getCargo().size();i++){
					Ecdbkcargo cargo = o.getCargo().get(i);
					sm.setInt(1, cargo.getMainkey()==null?-9:cargo.getMainkey());
					sm.setInt(2, cargo.getBkkey());
					sm.setInt(3, cargo.getVslvoykey());
					sm.setString(4, cargo.getCargoname());
					sm.setString(5, cargo.getTypecode());
					sm.setString(6, cargo.getTypename());
					sm.setInt(7, cargo.getPiece());
					sm.setString(8, cargo.getPkgcode());
					sm.setString(9, cargo.getPkgname());
					sm.setDouble(10, cargo.getMeasure());
					sm.setDouble(11, cargo.getNtweight());
					sm.setDouble(12, cargo.getGtweight());
					sm.setString(13, cargo.getDgsign());
					sm.setString(14, cargo.getDguncode());
					sm.setString(15, cargo.getDgpageno());
					sm.setString(16, cargo.getDgclass());
					sm.setString(17, cargo.getDgflag());
					sm.setString(18, cargo.getDgflash());
					sm.setString(19, cargo.getMarks());
					sm.setString(20, cargo.getCargodesc());
					sm.setString(21, cargo.getRfsign());
					sm.setString(22, cargo.getRfunit());
					sm.setString(23, cargo.getRffrom());
					sm.setString(24, cargo.getRfto());
					sm.setString(25, cargo.getRemark());
					sm.setString(26, cargo.getBlpkgname());
					sm.setString(27, cargo.getBlgtweight());
					sm.setString(28, cargo.getBlmeasure());
					sm.setString(29, cargo.getVslkey_seq());
					sm.setString(30, cargo.getBlno_seq());
					
					sm.executeUpdate();
				}
				
				
			}
			/*
			sql = "insert into Ecdbooking(mainkey,vslvoykey,blno,mainblno,bkno,carrcode,carrename," +
			"bkoffcode,bkoffcname,bkcode,bkcname,rcvpcode,revpename,ldpcode,ldpename,transport,transename," +
			"dispcode,dispename,delpcode,delpename,carrtype,pccode,pcename,bktype,bachsign,secvslsign," +
			"mktcode,marketer,preload,factload,blppat,blcpat,blsignat,blsigndate,bkcount,mfcusign,mfblsign," +
			"makercode,maker,madetime,modicode,modifier,moditime,remark,blldp,bldelp,bldisp,sign,issendedi," +
			"ietype,iscuspre,prename,close_After,is_web,vslkey_seq,sequence"+
			") values("+
			o.getMainkey()+","+o.getVslvoykey()+",'"+o.getBlno()+"','"+o.getMainblno()+"','"+o.getBkno()+"','"+o.getCarrcode()+"','"+o.getCarrename()+"','"+
			o.getBkoffcode()+"','"+o.getBkoffcname()+"','"+o.getBkcode()+"','"+o.getBkcname()+"','"+o.getRcvpcode()+"','"+o.getRevpename()+"','"+ o.getLdpcode()+"','"+o.getLdpename()+"','"+ o.getTransport()+"','"+ o.getTransename()+"','"+
			o.getDispcode()+"','"+o.getDispename()+"','"+o.getDelpcode()+"','"+o.getDelpename()+"','"+o.getCarrtype()+"','"+o.getPccode()+"','"+o.getPcename()+"','"+o.getBktype()+"','"+o.getBachsign()+"','"+o.getSecvslsign()+"','"+
			o.getMktcode()+"','"+o.getMarketer()+"','"+o.getPreload()+"','"+o.getFactload()+"','"+o.getBlppat()+"','"+o.getBlcpat()+"','"+o.getBlsignat()+"','"+o.getBlsigndate()+"','"+o.getBkcount()+"','"+o.getMfcusign()+"','"+o.getMfblsign()+"','"+
			o.getMakercode()+"','"+o.getMaker()+"','"+o.getMadetime()+"','"+o.getModicode()+"','"+o.getModifier()+"','"+o.getModitime()+"','"+o.getRemark()+"','"+o.getBlldp()+"','"+o.getBldelp()+"','"+o.getBldisp()+"','"+o.getSign()+"','"+o.getIssendedi()+"','"+
			o.getIetype()+"','"+o.getIscuspre()+"','"+o.getPrename()+"','"+o.getCloseAfter()+"','"+o.getIsweb()+"','"+o.getVslkey_seq()+"','"+o.getSequence()+"'"+
			")";
			
			
			//System.out.println(sql);
			sm.addBatch( sql );
			if (o.getCntr().size()>0){
				for (int i = 0; i< o.getCntr().size();i++){
					Ecdbkcntr cntr = o.getCntr().get(i);
					sm.addBatch("insert into Ecdbkcntr(mainkey,bkkey,vslvoykey,cntrtype,cntrno,cntrcocode,cntrconame," +
							"dockcode,dockname,sealno,piece,pkgcode,pkgname,ntweight,tare,measure,flesign,socsign," +
							"validsign,remark,location,overfront,overback,overleft,overright,overheight,vent," +
							"ventunit,vslkey_seq,blno_seq) values("+
							cntr.getMainkey()+","+cntr.getBkkey()+","+cntr.getVslvoykey()+",'"+cntr.getCntrtype()+"','"+cntr.getCntrno()+"','"+cntr.getCntrcocode()+"','"+cntr.getCntrconame()+"','"+
							cntr.getDockcode()+"','"+cntr.getDockname()+"','"+cntr.getSealno()+"',"+cntr.getPiece()+",'"+cntr.getPkgcode()+"','"+cntr.getPkgname()+"',"+cntr.getNtweight()+","+cntr.getTare()+","+cntr.getMeasure()+",'"+cntr.getFlesign()+"','"+cntr.getSocsign()+"','"+
							cntr.getValidsign()+"','"+cntr.getRemark()+"','"+cntr.getLocation()+"','"+cntr.getOverfront()+"','"+cntr.getOverback()+"','"+cntr.getOverleft()+"','"+cntr.getOverright()+"','"+cntr.getOverheight()+"','"+cntr.getVent()+"','"+
							cntr.getVentunit()+"','"+cntr.getVslvoykey()+"','"+cntr.getBlno_seq()+"'"+
							")");
				}
			}
			
			if (o.getParty().size()>0){
				for (int i = 0; i< o.getParty().size();i++){
					Ecdbkparty party = o.getParty().get(i);
					sm.addBatch("insert into Ecdbkparty(mainkey,bkkey,vslvoykey,partytype,partyname,partymsg,autoprime,vslkey_seq,blno_seq) values(" +
						party.getMainkey()+","+party.getBkkey()+","+party.getVslvoykey()+",'"+party.getPartytype()+"','"+party.getPartyname()+"','"+party.getPartymsg()+"','"+party.getAutoprime()+"','"+party.getVslkey_seq()+"','"+party.getBlno_seq()+"'"+	
					")");
				}
			}
			
			if (o.getCargo().size()>0){
				for (int i = 0; i< o.getCargo().size();i++){
					Ecdbkcargo cargo = o.getCargo().get(i);
					sql = "insert into Ecdbkcargo(mainkey,bkkey,vslvoykey,cargoname,typecode,typename," +
					"piece,pkgcode,pkgname,measure,ntweight,gtweight,dgsign,dguncode,dgpageno," +
					"dgclass,dgflag,dgflash,marks,cargodesc,rfsign,rfunit,rffrom,rfto,remark," +
					"blpkgname,blgtweight,blmeasure,vslkey_seq,blno_seq) values(" +
					cargo.getMainkey()+","+cargo.getBkkey()+","+cargo.getVslvoykey()+",'"+cargo.getCargoname()+"','"+cargo.getTypecode()+"','"+cargo.getTypename()+"',"+
					cargo.getPiece()+",'"+cargo.getPkgcode()+"','"+cargo.getPkgname()+"',"+cargo.getMeasure()+","+cargo.getNtweight()+","+cargo.getGtweight()+",'"+cargo.getDgsign()+"','"+cargo.getDguncode()+"','"+cargo.getDgpageno()+"','"+
					cargo.getDgclass()+"','"+cargo.getDgflag()+"','"+cargo.getDgflash()+"','"+cargo.getMarks()+"','"+cargo.getCargodesc()+"','"+cargo.getRfsign()+"','"+cargo.getRfunit()+"','"+cargo.getRffrom()+"','"+cargo.getRfto()+"','"+cargo.getRemark()+"','"+
					cargo.getBlpkgname()+"','"+cargo.getBlgtweight()+"','"+cargo.getBlmeasure()+"','"+cargo.getVslkey_seq()+"','"+cargo.getBlno_seq()+"'"+
					")";
					System.out.println(sql);
					sm.addBatch(sql);
				}
			}
			sm.executeBatch();
			*/
		    con.commit();
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
//	private void setBascountry(PreparedStatement sm, Bascountry o) throws SQLException {
//		// TODO Auto-generated method stub
//		sm.setString(1, o.getCname()==null?"":o.getCname());
//		sm.setString(2, o.getEname()==null?"":o.getEname());
//		sm.setString(3, o.getMakercode()==null?"":o.getMakercode());
//		sm.setString(4, o.getMaker()==null?"":o.getMaker());
//		sm.setTimestamp(5,  o.getMadetime());
//		sm.setString(6, o.getModicode()==null?"": o.getModicode());
//		sm.setString(7, o.getModifier()==null?"":o.getModifier());
//		sm.setTimestamp(8,  o.getModitime());
//		sm.setString(9,  o.getNationcode());
//		sm.setInt(10, o.getMainkey());
//		
//	}
	public boolean removeEcdbooking(Ecdbooking ecdbooking) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			if(con == null) return false;
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from Ecdbooking where vslkey_seq='"+ecdbooking.getVslkey_seq()+"' and blno ='"+ecdbooking.getBlno()+"'");
			sm.addBatch("delete from ecdbkcntr where vslkey_seq='"+ecdbooking.getVslkey_seq()+"' And blno_seq='"+ecdbooking.getSequence()+"'");
			sm.addBatch("delete from ecdbkparty where vslkey_seq='"+ecdbooking.getVslkey_seq()+"' And blno_seq='"+ecdbooking.getSequence()+"'");
			sm.addBatch("delete from ecdbkcargo where vslkey_seq='"+ecdbooking.getVslkey_seq()+"' And blno_seq='"+ecdbooking.getSequence()+"'");
			sm.addBatch("delete from ecdbksec where vslkey_seq='"+ecdbooking.getVslkey_seq()+"' And blno_seq='"+ecdbooking.getSequence()+"'");
			sm.executeBatch();
		    con.commit();
		    con.setAutoCommit(true);
		}catch (SQLException e){
			result = false;
			
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
		}finally{
			close(rs);close(sm);close(con);
		}
		return result;
	}
	
	public List<Ecdbooking> getAllEcdbooking(String vslkey_seq, String blno_seq) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.prepareStatement("select * from Ecdbooking where vslkey_seq='"+vslkey_seq+"' And blno_seq='"+blno_seq+"'");
			rs = sm.executeQuery();
			
			List<Ecdbooking> list = new ArrayList<Ecdbooking>();
			while(rs.next()){
				list.add(Ecdbooking(rs));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	

	public boolean modifyEcdbooking(Ecdbooking ecdbooking){
			Connection con = null;
			PreparedStatement sm = null;
			ResultSet rs = null;
			try{
				con = ConnectManager.getConnection();
				con.setAutoCommit(false);
				String sql = "update Ecdbooking " +
						"set enname=?,cnname=? WHERE  vslkey_seq=? And blno_seq=? ";
				sm = con.prepareStatement(sql);
				sm.setString(1, "");
				sm.executeUpdate();
				sm.execute();
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
}
