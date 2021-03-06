package cosco.vsagent.mapping.ecd;

import java.util.Date;

/**
 * Ecdbksec generated by MyEclipse - Hibernate Tools
 */

public class Ecdbksec  implements java.io.Serializable {


    // Fields    

     private Integer mainkey;
     private Integer bkkey;
     private Integer vslvoykey;
     private String vslcname;
     private String vslename;
     private String voyage;
     private Date saildate;
     private String carrcode;
     private String carrename;
     private String blno;
     private String ldpcode;
     private String ldpename;
     private String dispcode;
     private String dispename;
     private String delpcode;
     private String delpename;
     private String ldpcus;
     private String ldpdist;

     private String vslkey_seq;
     private String blno_seq;

    // Constructors

    /** default constructor */
    public Ecdbksec() {
    }

	/** minimal constructor */
    public Ecdbksec(Integer mainkey) {
        this.mainkey = mainkey;
    }
    
    /** full constructor */
    public Ecdbksec(Integer mainkey, Integer bkkey, Integer vslvoykey, String vslcname, String vslename, String voyage, Date saildate, String carrcode, String carrename, String blno, String ldpcode, String ldpename, String dispcode, String dispename, String delpcode, String delpename, String ldpcus, String ldpdist) {
        this.mainkey = mainkey;
        this.bkkey = bkkey;
        this.vslvoykey = vslvoykey;
        this.vslcname = vslcname;
        this.vslename = vslename;
        this.voyage = voyage;
        this.saildate = saildate;
        this.carrcode = carrcode;
        this.carrename = carrename;
        this.blno = blno;
        this.ldpcode = ldpcode;
        this.ldpename = ldpename;
        this.dispcode = dispcode;
        this.dispename = dispename;
        this.delpcode = delpcode;
        this.delpename = delpename;
        this.ldpcus = ldpcus;
        this.ldpdist = ldpdist;
    }

   
    // Property accessors

    public Integer getMainkey() {
        return this.mainkey;
    }
    
    public void setMainkey(Integer mainkey) {
        this.mainkey = mainkey;
    }

    public Integer getBkkey() {
        return this.bkkey;
    }
    
    public void setBkkey(Integer bkkey) {
        this.bkkey = bkkey;
    }

    public Integer getVslvoykey() {
        return this.vslvoykey;
    }
    
    public void setVslvoykey(Integer vslvoykey) {
        this.vslvoykey = vslvoykey;
    }

    public String getVslcname() {
        return this.vslcname;
    }
    
    public void setVslcname(String vslcname) {
        this.vslcname = vslcname;
    }

    public String getVslename() {
        return this.vslename;
    }
    
    public void setVslename(String vslename) {
        this.vslename = vslename;
    }

    public String getVoyage() {
        return this.voyage;
    }
    
    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public Date getSaildate() {
        return this.saildate;
    }
    
    public void setSaildate(Date saildate) {
        this.saildate = saildate;
    }

    public String getCarrcode() {
        return this.carrcode;
    }
    
    public void setCarrcode(String carrcode) {
        this.carrcode = carrcode;
    }

    public String getCarrename() {
        return this.carrename;
    }
    
    public void setCarrename(String carrename) {
        this.carrename = carrename;
    }

    public String getBlno() {
        return this.blno;
    }
    
    public void setBlno(String blno) {
        this.blno = blno;
    }

    public String getLdpcode() {
        return this.ldpcode;
    }
    
    public void setLdpcode(String ldpcode) {
        this.ldpcode = ldpcode;
    }

    public String getLdpename() {
        return this.ldpename;
    }
    
    public void setLdpename(String ldpename) {
        this.ldpename = ldpename;
    }

    public String getDispcode() {
        return this.dispcode;
    }
    
    public void setDispcode(String dispcode) {
        this.dispcode = dispcode;
    }

    public String getDispename() {
        return this.dispename;
    }
    
    public void setDispename(String dispename) {
        this.dispename = dispename;
    }

    public String getDelpcode() {
        return this.delpcode;
    }
    
    public void setDelpcode(String delpcode) {
        this.delpcode = delpcode;
    }

    public String getDelpename() {
        return this.delpename;
    }
    
    public void setDelpename(String delpename) {
        this.delpename = delpename;
    }

    public String getLdpcus() {
        return this.ldpcus;
    }
    
    public void setLdpcus(String ldpcus) {
        this.ldpcus = ldpcus;
    }

    public String getLdpdist() {
        return this.ldpdist;
    }
    
    public void setLdpdist(String ldpdist) {
        this.ldpdist = ldpdist;
    }

	public String getVslkey_seq() {
		return vslkey_seq;
	}

	public void setVslkey_seq(String vslkey_seq) {
		this.vslkey_seq = vslkey_seq;
	}

	public String getBlno_seq() {
		return blno_seq;
	}

	public void setBlno_seq(String blno_seq) {
		this.blno_seq = blno_seq;
	}
   








}