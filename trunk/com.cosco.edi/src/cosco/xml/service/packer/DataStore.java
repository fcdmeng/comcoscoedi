package cosco.xml.service.packer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cosco.vsagent.dbo.base.BasvslvoyDAO;
import cosco.vsagent.mapping.base.Basvslvoy;
import cosco.vsagent.mapping.base.Basvvcarr;
import cosco.xml.dbo.XmlbkcargoDAO;
import cosco.xml.dbo.XmlbkcntrDAO;
import cosco.xml.dbo.XmlbkpartyDAO;
import cosco.xml.dbo.XmlbookingDAO;
import cosco.xml.mapping.Xmlbkcargo;
import cosco.xml.mapping.Xmlbkcntr;
import cosco.xml.mapping.Xmlbkparty;
import cosco.xml.mapping.Xmlbooking;

public class DataStore {
	private List<Xmlbooking> XMLbooking;
	Basvslvoy  basvslvoy;//船名航次信息
	Basvvcarr  basvvcarr;//承运人信息
	
	public List<Xmlbooking> getXmlbooking() {
		return XMLbooking;
	}

	public void setXmlbooking(List<Xmlbooking> xmlbooking) {
		XMLbooking = xmlbooking;
	}
	
	/**
	 * 检索数据库中的数据
	 */
	public void Retrieve(){
		
		XmlbookingDAO bookingDAO = new XmlbookingDAO();
		XmlbkpartyDAO bkpartyDAO = new XmlbkpartyDAO();
		XmlbkcntrDAO  bkcntrDAO = new XmlbkcntrDAO();
		XmlbkcargoDAO cargoDAO = new XmlbkcargoDAO();
		
		int bkmainkey;
		
		List<Xmlbooking> xmlbooking = bookingDAO.getXmlbooking("select * from Xmlbooking", true);
		List<Xmlbkparty> xmlbkparty = bkpartyDAO.getXmlbkparty("select * from Xmlbkparty", true);
		List<Xmlbkcntr>  xmlbkcntr = bkcntrDAO.getXmlbkcntr("select * from Xmlbkcntr", true);
		List<Xmlbkcargo> xmlbkcargo = cargoDAO.getXmlbkcargo("select * from Xmlbkcargo", true);
		
		XMLbooking = new ArrayList<Xmlbooking>();

		for(Iterator itbooking = xmlbooking.iterator(); itbooking.hasNext(); ) {
//		for(int i =0;i <xmlbooking.size();i++){
			Xmlbooking booking = (Xmlbooking) itbooking.next();
//			Xmlbooking booking = xmlbooking.get(i);
			bkmainkey = booking.getMainkey();
		
			Set<Xmlbkcargo> bkcargotmp = new HashSet<Xmlbkcargo>();
			for(Iterator itcargo = xmlbkcargo.iterator(); itcargo.hasNext(); ) {
				Xmlbkcargo cargo = (Xmlbkcargo) itcargo.next();
				if(bkmainkey == cargo.getXmlbkkey())
					bkcargotmp.add(cargo);
			}
			booking.setXmlbkcargo(bkcargotmp);
			
			Set<Xmlbkcntr> bkcntrtmp = new HashSet<Xmlbkcntr>();
			for(Iterator itbkcntr = xmlbkcntr.iterator(); itbkcntr.hasNext(); ) {
				Xmlbkcntr bkcntr = (Xmlbkcntr) itbkcntr.next();
				if(bkmainkey == bkcntr.getXmlbkkey())
					bkcntrtmp.add(bkcntr);
			}
			booking.setXmlbkcntr(bkcntrtmp);
			
			Set<Xmlbkparty> bkpartytmp = new HashSet<Xmlbkparty>();
			for(Iterator itbkparty = xmlbkparty.iterator(); itbkparty.hasNext(); ) {
				Xmlbkparty bkparty = (Xmlbkparty) itbkparty.next();
				if(bkmainkey == bkparty.getXmlbkkey())
					bkpartytmp.add(bkparty);
			}
			booking.setXmlbkparty(bkpartytmp);
			
			XMLbooking.add(booking);
			
		}
	}
	
	public static void main(String argv[]){
		DataStore ds = new DataStore();
		ds.Retrieve();
		List<Xmlbooking> xml = ds.getXmlbooking();
		for(Iterator it = xml.iterator();it.hasNext();){
			Xmlbooking booking = (Xmlbooking)it.next();
			System.out.println(booking.getAsblno());
			for(Xmlbkcntr cntr :booking.getXmlbkcntr())
				System.out.println(cntr.getCntrtype()+"=="+cntr.getCntrno());
			
		}
	}
}
