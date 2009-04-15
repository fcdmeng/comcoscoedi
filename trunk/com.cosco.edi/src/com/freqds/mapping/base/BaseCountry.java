package com.freqds.mapping.base;

import java.util.Date;

public class BaseCountry  implements java.io.Serializable {
	private String country_code;
	private String country_cname;
	private String country_ename;
	private String act;
	private String memo;
	private String insert_usercode;
	private Date insert_time;
	
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getCountry_cname() {
		return country_cname;
	}
	public void setCountry_cname(String country_cname) {
		this.country_cname = country_cname;
	}
	public String getCountry_ename() {
		return country_ename;
	}
	public void setCountry_ename(String country_ename) {
		this.country_ename = country_ename;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getInsert_usercode() {
		return insert_usercode;
	}
	public void setInsert_usercode(String insert_usercode) {
		this.insert_usercode = insert_usercode;
	}
	public Date getInsert_time() {
		return insert_time;
	}
	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

}
