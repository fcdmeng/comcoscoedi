package cosco.vsagent.mapping.ecd;

/**
 * Ecdbkpcntr generated by MyEclipse - Hibernate Tools
 */

public class Ecdbkpcntr  implements java.io.Serializable {


    // Fields    

     private Integer mainkey;
     private Integer bkkey;
     private Integer vslvoykey;
     private String cntrtype;
     private Short cntrcount;


    // Constructors

    /** default constructor */
    public Ecdbkpcntr() {
    }

	/** minimal constructor */
    public Ecdbkpcntr(Integer mainkey) {
        this.mainkey = mainkey;
    }
    
    /** full constructor */
    public Ecdbkpcntr(Integer mainkey, Integer bkkey, Integer vslvoykey, String cntrtype, Short cntrcount) {
        this.mainkey = mainkey;
        this.bkkey = bkkey;
        this.vslvoykey = vslvoykey;
        this.cntrtype = cntrtype;
        this.cntrcount = cntrcount;
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

    public String getCntrtype() {
        return this.cntrtype;
    }
    
    public void setCntrtype(String cntrtype) {
        this.cntrtype = cntrtype;
    }

    public Short getCntrcount() {
        return this.cntrcount;
    }
    
    public void setCntrcount(Short cntrcount) {
        this.cntrcount = cntrcount;
    }
   








}