package cosco.vsagent.mapping;



import java.sql.Timestamp;

public interface Booking {

	public Integer getMainkey();

	public void setMainkey(Integer mainkey);

	public Integer getVslvoykey();

	public void setVslvoykey(Integer vslvoykey);

	public String getBlno();

	public void setBlno(String blno);

	public String getMainblno();

	public void setMainblno(String mainblno);

	public String getBkno();

	public void setBkno(String bkno);

	public String getCarrcode();

	public void setCarrcode(String carrcode);

	public String getCarrename();

	public void setCarrename(String carrename);

	//public String getBkoffcode();

	//public void setBkoffcode(String bkoffcode);

	//public String getBkoffcname();

	//public void setBkoffcname(String bkoffcname);

	//public String getBkcode();

	//public void setBkcode(String bkcode);

	//public String getBkcname();

	//public void setBkcname(String bkcname);

	public String getRcvpcode();

	public void setRcvpcode(String rcvpcode);

	public String getRevpename();

	public void setRevpename(String revpename);

	public String getLdpcode();

	public void setLdpcode(String ldpcode);

	public String getLdpename();

	public void setLdpename(String ldpename);

	public String getTransport();

	public void setTransport(String transport);

	public String getTransename();

	public void setTransename(String transename);

	public String getDispcode();

	public void setDispcode(String dispcode);

	public String getDispename();

	public void setDispename(String dispename);

	public String getDelpcode();

	public void setDelpcode(String delpcode);

	public String getDelpename();

	public void setDelpename(String delpename);

	public String getCarrtype();

	public void setCarrtype(String carrtype);

	public String getPccode();

	public void setPccode(String pccode);

	public String getPcename();

	public void setPcename(String pcename);

	public String getBktype();

	public void setBktype(String bktype);

	public String getBachsign();

	public void setBachsign(String bachsign);

	///public String getSecvslsign();

	//public void setSecvslsign(String secvslsign);

	//public String getMktcode();

	//public void setMktcode(String mktcode);

	//public String getMarketer();

	//public void setMarketer(String marketer);

	//public String getPreload();

	//public void setPreload(String preload);

	public String getFactload();

	public void setFactload(String factload);

	public String getBlppat();

	public void setBlppat(String blppat);

	public String getBlcpat();

	public void setBlcpat(String blcpat);

	public String getBlsignat();

	public void setBlsignat(String blsignat);

	public String getBlsigndate();

	public void setBlsigndate(String blsigndate);

	public String getBkcount();

	public void setBkcount(String bkcount);

	public String getMfcusign();

	public void setMfcusign(String mfcusign);

	public String getMfblsign();

	public void setMfblsign(String mfblsign);

	public String getMakercode();

	public void setMakercode(String makercode);

	public String getMaker();

	public void setMaker(String maker);

	public Timestamp getMadetime();

	public void setMadetime(Timestamp madetime);

	public String getModicode();

	public void setModicode(String modicode);

	public String getModifier();

	public void setModifier(String modifier);

	public Timestamp getModitime();

	public void setModitime(Timestamp moditime);

	public String getRemark();

	public void setRemark(String remark);

	//public String getBlldp();

	//public void setBlldp(String blldp);

	//public String getBldelp();	//出口中存在  进口不存在

	//public void setBldelp(String bldelp);

	//public String getBldisp();

	//public void setBldisp(String bldisp);

	public String getSign();

	public void setSign(String sign);

	//public String getIssendedi();

	//public void setIssendedi(String issendedi);

	public String getIetype();

	public void setIetype(String ietype);

	public String getIscuspre();

	public void setIscuspre(String iscuspre);

	//public String getPrename();

	//public void setPrename(String prename);

	//public String getCloseAfter();

	//public void setCloseAfter(String closeAfter);

}