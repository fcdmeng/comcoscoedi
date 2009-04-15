package cosco.vsagent.mapping;



public interface Cargo {

	public Integer getMainkey();

	public void setMainkey(Integer mainkey);

	public Integer getBkkey();

	public void setBkkey(Integer bkkey);

	public Integer getVslvoykey();

	public void setVslvoykey(Integer vslvoykey);

	public String getCargoname();

	public void setCargoname(String cargoname);

	public String getTypecode();

	public void setTypecode(String typecode);

	public String getTypename();

	public void setTypename(String typename);

	public Integer getPiece();

	public void setPiece(Integer piece);

	public String getPkgcode();

	public void setPkgcode(String pkgcode);

	public String getPkgname();

	public void setPkgname(String pkgname);

	public Double getNtweight();

	public void setNtweight(Double ntweight);

	public Double getGtweight();

	public void setGtweight(Double gtweight);

	public String getDgsign();

	public void setDgsign(String dgsign);

	public String getDguncode();

	public void setDguncode(String dguncode);

	public String getDgpageno();

	public void setDgpageno(String dgpageno);

	public String getDgclass();

	public void setDgclass(String dgclass);

	public String getDgflag();

	public void setDgflag(String dgflag);

	public String getDgflash();

	public void setDgflash(String dgflash);

	public String getMarks();

	public void setMarks(String marks);

	public String getCargodesc();

	public void setCargodesc(String cargodesc);

	public String getRfsign();

	public void setRfsign(String rfsign);

	public String getRfunit();

	public void setRfunit(String rfunit);

	public String getRffrom();

	public void setRffrom(String rffrom);

	public String getRfto();

	public void setRfto(String rfto);

	public String getRemark();

	public void setRemark(String remark);

	//public String getBlpkgname();

	//public void setBlpkgname(String blpkgname);

	//public String getBlgtweight();

	//public void setBlgtweight(String blgtweight);

	//public String getBlmeasure();

	//public void setBlmeasure(String blmeasure);

	public Double getMeasure();

	public void setMeasure(Double measure);

}