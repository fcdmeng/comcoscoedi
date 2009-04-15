package cosco.xml.dbo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cosco.vsagent.db.ConnectManager;
import cosco.vsagent.dbo.BaseDAO;
import cosco.xml.mapping.Xmlbooking;

public class XmlbookingDAO  extends BaseDAO{
	/**
	 * 
	 * @param sql 查询语句
	 * @param flag读取数据方向,true读取服务器端数据，false读取客户端数据
	 * @return
	 */
	public List<Xmlbooking> getXmlbooking(String sql, boolean flag){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			if(flag == true)
				con = ConnectManager.getServerConnection();
			else
				con = ConnectManager.getConnection();
			
			if(con == null) return Collections.EMPTY_LIST;
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
			
			List<Xmlbooking> list = new ArrayList<Xmlbooking>();
			while(rs.next()){
				list.add(Xmlbooking(rs, flag));
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		
		return Collections.EMPTY_LIST;
	}

	private Xmlbooking Xmlbooking(ResultSet rs, boolean flag) {
		// TODO Auto-generated method stub
		Xmlbooking xmlbooking = new Xmlbooking();
		try{
			xmlbooking.setMainkey(rs.getInt("mainkey"));
			xmlbooking.setBcflag(rs.getString("bcflag"));
			xmlbooking.setIeflag(rs.getString("ieflag"));
			xmlbooking.setVslvoykey(rs.getInt("vslvoykey"));
			xmlbooking.setBlno(rs.getString("blno"));
			xmlbooking.setCarrcode(rs.getString("carrcode"));
			xmlbooking.setCarrename(rs.getString("carrename"));
			xmlbooking.setPccode(rs.getString("pccode"));
			xmlbooking.setPcename(rs.getString("pcename"));
			xmlbooking.setBktype(rs.getString("bktype"));
			xmlbooking.setAmdcode1(rs.getString("amdcode1"));
			xmlbooking.setAmdcode2(rs.getString("amdcode2"));
			xmlbooking.setAmdcode3(rs.getString("amdcode3"));
			xmlbooking.setCarrtype(rs.getString("carrtype"));
			xmlbooking.setDeconsid(rs.getString("deconsid"));
			xmlbooking.setAsblno(rs.getString("asblno"));
			xmlbooking.setGvmeasure(rs.getDouble("gvmeasure"));
			xmlbooking.setCvamount(rs.getDouble("cvamount"));
			xmlbooking.setCvacurr(rs.getString("cvacurr"));
			xmlbooking.setLdpcode(rs.getString("ldpcode"));
			xmlbooking.setLdpdate(rs.getDate("ldpdate"));
			xmlbooking.setDispcode(rs.getString("dispcode"));
			xmlbooking.setDispdate(rs.getDate("dispdate"));
			xmlbooking.setDeconsid(rs.getString("deconsid"));
			xmlbooking.setDelpcode(rs.getString("delpcode"));
			xmlbooking.setTransfrm(rs.getString("transfrm"));
			xmlbooking.setTransend(rs.getString("transend"));
			xmlbooking.setRtcountry(rs.getString("rtcountry"));
			xmlbooking.setConsignp(rs.getString("consignp"));
			xmlbooking.setCuststs(rs.getString("custsts"));
			xmlbooking.setSplitflag(rs.getString("splitflag"));
			xmlbooking.setFrtpayer(rs.getString("frtpayer"));
			xmlbooking.setPiece(rs.getInt("piece"));
			xmlbooking.setPkgcode(rs.getString("pkgcode"));
			xmlbooking.setGtweight(rs.getDouble("gtweight"));
			xmlbooking.setPcdocid(rs.getString("pcdocid"));
			xmlbooking.setPcdoctype(rs.getString("pcdoctype"));
			xmlbooking.setDdline(rs.getString("ddline"));
			xmlbooking.setDdcity(rs.getString("ddcity"));
			xmlbooking.setDdentid(rs.getString("ddentid"));
			xmlbooking.setDdentname(rs.getString("ddentname"));
			xmlbooking.setDdpostid(rs.getString("ddpostid"));
			xmlbooking.setDdcountry(rs.getString("ddcountry"));
			xmlbooking.setHandcode(rs.getString("handcode"));
			xmlbooking.setMcarrid(rs.getString("mcarrid"));
			xmlbooking.setMcarrcm1(rs.getString("mcarrcm1"));
			xmlbooking.setMcarrty1(rs.getString("mcarrty1"));
			xmlbooking.setMcarrcm2(rs.getString("mcarrcm2"));
			xmlbooking.setMcarrty1(rs.getString("mcarrty2"));
			xmlbooking.setMcarrcm2(rs.getString("mcarrcm3"));
			xmlbooking.setMcarrty1(rs.getString("mcarrty3"));
			xmlbooking.setDgctname(rs.getString("dgctname"));
			xmlbooking.setDgctcm1(rs.getString("dgctcm1"));
			xmlbooking.setDgctty1(rs.getString("dgctty1"));
			xmlbooking.setDgctcm1(rs.getString("dgctcm2"));
			xmlbooking.setDgctty1(rs.getString("dgctty2"));
			xmlbooking.setDgctcm1(rs.getString("dgctcm3"));
			xmlbooking.setDgctty1(rs.getString("dgctty3"));
			xmlbooking.setCustpcode(rs.getString("custpcode"));
			xmlbooking.setRemark(rs.getString("remark"));
			xmlbooking.setMakercode(rs.getString("makercode"));
			xmlbooking.setMaker(rs.getString("maker"));
			xmlbooking.setMadetime(rs.getTimestamp("madetime"));
			xmlbooking.setModicode(rs.getString("modicode"));
			xmlbooking.setModifier(rs.getString("modifier"));
			xmlbooking.setModitime(rs.getTimestamp("moditime"));
			xmlbooking.setApfirnums(rs.getInt("apfirnums"));
			xmlbooking.setApsecnums(rs.getInt("apsecnums"));
			xmlbooking.setApmodnums(rs.getInt("apmodnums"));
			xmlbooking.setApdelnums(rs.getInt("apdelnums"));
			xmlbooking.setXml2pre(rs.getString("xml2pre"));
//			List   list   =   new   ArrayList(new   Hashset());  
			List list = new XmlbkcargoDAO().getXmlbkcargo("select * from xmlbkcargo where xmlbkkey="+xmlbooking.getMainkey().toString(),true);
			Set   set   =   new   HashSet(list);
			xmlbooking.setXmlbkcargo( set );
			
			list = new XmlbkcntrDAO().getXmlbkcntr("select * from Xmlbkcntr where xmlbkkey="+xmlbooking.getMainkey().toString(),true);
			set   =   new   HashSet(list);
			xmlbooking.setXmlbkcntr(set);
			
			list = new XmlbkpartyDAO().getXmlbkparty("select * from xmlbkParty where xmlbkkey="+xmlbooking.getMainkey().toString(),true);
			set   =   new   HashSet(list);
			xmlbooking.setXmlbkparty(set);

		}catch(SQLException e){
			e.printStackTrace();
		}
		return xmlbooking;
	}
	
	public static void main(String argv[]){
		XmlbookingDAO bookingDAO = new XmlbookingDAO();
		List<Xmlbooking> booking = bookingDAO.getXmlbooking("select * from Xmlbooking", true);
		System.out.println(booking.size());
		
		for(Iterator it = booking.iterator(); it.hasNext(); ) {
			Xmlbooking tmp = (Xmlbooking) it.next();
			System.out.println(tmp.getAsblno());
			
		}
	}
}