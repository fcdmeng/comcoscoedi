package cosco.xml.mapping;



/**
 * Xmlbkcargo generated by MyEclipse - Hibernate Tools
 */

public class Xmlbkcargo  implements java.io.Serializable {


    // Fields    

     private Integer mainkey;
     private String bcflag;
     private String ieflag;
     private Integer xmlbkkey;
     private Integer vslvoykey;
     private Integer seqno;
     private Integer piece;
     private String pkgcode;
     private String pkgname;
     private String marks;
     private String cargodesc;
     private String dgsign;
     private String undgcode;
     private String custtcode;
     private String adddesc;
     private Double gtweight;
     private String remark;


    // Constructors

    /** default constructor */
    public Xmlbkcargo() {
    }

	/** minimal constructor */
    public Xmlbkcargo(Integer mainkey) {
        this.mainkey = mainkey;
    }
    
    /** full constructor */
    public Xmlbkcargo(Integer mainkey, String bcflag, String ieflag, Integer xmlbkkey, Integer vslvoykey, Integer seqno, Integer piece, String pkgcode, String pkgname, String marks, String cargodesc, String dgsign, String undgcode, String custtcode, String adddesc, Double gtweight, String remark) {
        this.mainkey = mainkey;
        this.bcflag = bcflag;
        this.ieflag = ieflag;
        this.xmlbkkey = xmlbkkey;
        this.vslvoykey = vslvoykey;
        this.seqno = seqno;
        this.piece = piece;
        this.pkgcode = pkgcode;
        this.pkgname = pkgname;
        this.marks = marks;
        this.cargodesc = cargodesc;
        this.dgsign = dgsign;
        this.undgcode = undgcode;
        this.custtcode = custtcode;
        this.adddesc = adddesc;
        this.gtweight = gtweight;
        this.remark = remark;
    }

   
    // Property accessors

    public Integer getMainkey() {
        return this.mainkey;
    }
    
    public void setMainkey(Integer mainkey) {
        this.mainkey = mainkey;
    }

    public String getBcflag() {
        return this.bcflag;
    }
    
    public void setBcflag(String bcflag) {
        this.bcflag = bcflag;
    }

    public String getIeflag() {
        return this.ieflag;
    }
    
    public void setIeflag(String ieflag) {
        this.ieflag = ieflag;
    }

    public Integer getXmlbkkey() {
        return this.xmlbkkey;
    }
    
    public void setXmlbkkey(Integer xmlbkkey) {
        this.xmlbkkey = xmlbkkey;
    }

    public Integer getVslvoykey() {
        return this.vslvoykey;
    }
    
    public void setVslvoykey(Integer vslvoykey) {
        this.vslvoykey = vslvoykey;
    }

    public Integer getSeqno() {
        return this.seqno;
    }
    
    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }

    public Integer getPiece() {
        return this.piece;
    }
    
    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public String getPkgcode() {
        return this.pkgcode;
    }
    
    public void setPkgcode(String pkgcode) {
        this.pkgcode = pkgcode;
    }

    public String getPkgname() {
        return this.pkgname;
    }
    
    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    public String getMarks() {
        return this.marks;
    }
    
    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getCargodesc() {
        return this.cargodesc;
    }
    
    public void setCargodesc(String cargodesc) {
        this.cargodesc = cargodesc;
    }

    public String getDgsign() {
        return this.dgsign;
    }
    
    public void setDgsign(String dgsign) {
        this.dgsign = dgsign;
    }

    public String getUndgcode() {
        return this.undgcode;
    }
    
    public void setUndgcode(String undgcode) {
        this.undgcode = undgcode;
    }

    public String getCusttcode() {
        return this.custtcode;
    }
    
    public void setCusttcode(String custtcode) {
        this.custtcode = custtcode;
    }

    public String getAdddesc() {
        return this.adddesc;
    }
    
    public void setAdddesc(String adddesc) {
        this.adddesc = adddesc;
    }

    public Double getGtweight() {
        return this.gtweight;
    }
    
    public void setGtweight(Double gtweight) {
        this.gtweight = gtweight;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}