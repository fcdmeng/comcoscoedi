package cosco.vsagent.mapping.base;

import java.util.Date;


/**
 * Bascntr generated by MyEclipse - Hibernate Tools
 */

public class Bascntr  implements java.io.Serializable {


    // Fields    

     private Integer mainkey;
     private String sizetype;
     private String cntrname;
     private Double cntrtare;
     private String makercode;
     private String maker;
     private Date madetime;
     private String modicode;
     private String modifier;
     private Date moditime;
     private String Sequence;

    // Constructors

    public String getSequence() {
		return Sequence;
	}

	public void setSequence(String sequence) {
		Sequence = sequence;
	}

	/** default constructor */
    public Bascntr() {
    }

	/** minimal constructor */
    public Bascntr(Integer mainkey) {
        this.mainkey = mainkey;
    }
    
    /** full constructor */
    public Bascntr(Integer mainkey, String sizetype, String cntrname, Double cntrtare, String makercode, String maker, Date madetime, String modicode, String modifier, Date moditime) {
        this.mainkey = mainkey;
        this.sizetype = sizetype;
        this.cntrname = cntrname;
        this.cntrtare = cntrtare;
        this.makercode = makercode;
        this.maker = maker;
        this.madetime = madetime;
        this.modicode = modicode;
        this.modifier = modifier;
        this.moditime = moditime;
    }

   
    // Property accessors

    public Integer getMainkey() {
        return this.mainkey;
    }
    
    public void setMainkey(Integer mainkey) {
        this.mainkey = mainkey;
    }

    public String getSizetype() {
        return this.sizetype;
    }
    
    public void setSizetype(String sizetype) {
        this.sizetype = sizetype;
    }

    public String getCntrname() {
        return this.cntrname;
    }
    
    public void setCntrname(String cntrname) {
        this.cntrname = cntrname;
    }

    public Double getCntrtare() {
        return this.cntrtare;
    }
    
    public void setCntrtare(Double cntrtare) {
        this.cntrtare = cntrtare;
    }

    public String getMakercode() {
        return this.makercode;
    }
    
    public void setMakercode(String makercode) {
        this.makercode = makercode;
    }

    public String getMaker() {
        return this.maker;
    }
    
    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Date getMadetime() {
        return this.madetime;
    }
    
    public void setMadetime(Date madetime) {
        this.madetime = madetime;
    }

    public String getModicode() {
        return this.modicode;
    }
    
    public void setModicode(String modicode) {
        this.modicode = modicode;
    }

    public String getModifier() {
        return this.modifier;
    }
    
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModitime() {
        return this.moditime;
    }
    
    public void setModitime(Date moditime) {
        this.moditime = moditime;
    }
   








}