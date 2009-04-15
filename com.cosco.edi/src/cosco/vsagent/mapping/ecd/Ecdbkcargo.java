package cosco.vsagent.mapping.ecd;



import cosco.vsagent.mapping.Cargo;



/**
 * Ecdbkcargo generated by MyEclipse - Hibernate Tools
 */

public class Ecdbkcargo  implements java.io.Serializable, Cargo {


    // Fields    

     private Integer mainkey;
     private Integer bkkey;
     private Integer vslvoykey;
     private String cargoname;
     private String typecode;
     private String typename;
     private Integer piece;
     private String pkgcode;
     private String pkgname;
     private Double ntweight;
     private Double gtweight;
     private String dgsign;
     private String dguncode;
     private String dgpageno;
     private String dgclass;
     private String dgflag;
     private String dgflash;
     private String marks;
     private String cargodesc;
     private String rfsign;
     private String rfunit;
     private String rffrom;
     private String rfto;
     private String remark;
     private String blpkgname;
     private String blgtweight;
     private String blmeasure;
     private Double measure;
     private String vslkey_seq;
     private String blno_seq;


    // Constructors

    /** default constructor */
    public Ecdbkcargo() {
    }

	/** minimal constructor */
    public Ecdbkcargo(Integer mainkey) {
        this.mainkey = mainkey;
    }
    
    /** full constructor */
    public Ecdbkcargo(Integer mainkey, Integer bkkey, Integer vslvoykey, String cargoname, String typecode, String typename, Integer piece, String pkgcode, String pkgname, Double ntweight, Double gtweight, String dgsign, String dguncode, String dgpageno, String dgclass, String dgflag, String dgflash, String marks, String cargodesc, String rfsign, String rfunit, String rffrom, String rfto, String remark, String blpkgname, String blgtweight, String blmeasure, Double measure) {
        this.mainkey = mainkey;
        this.bkkey = bkkey;
        this.vslvoykey = vslvoykey;
        this.cargoname = cargoname;
        this.typecode = typecode;
        this.typename = typename;
        this.piece = piece;
        this.pkgcode = pkgcode;
        this.pkgname = pkgname;
        this.ntweight = ntweight;
        this.gtweight = gtweight;
        this.dgsign = dgsign;
        this.dguncode = dguncode;
        this.dgpageno = dgpageno;
        this.dgclass = dgclass;
        this.dgflag = dgflag;
        this.dgflash = dgflash;
        this.marks = marks;
        this.cargodesc = cargodesc;
        this.rfsign = rfsign;
        this.rfunit = rfunit;
        this.rffrom = rffrom;
        this.rfto = rfto;
        this.remark = remark;
        this.blpkgname = blpkgname;
        this.blgtweight = blgtweight;
        this.blmeasure = blmeasure;
        this.measure = measure;
    }

   
    // Property accessors

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getMainkey()
	 */
    public Integer getMainkey() {
        return this.mainkey;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setMainkey(java.lang.Integer)
	 */
    public void setMainkey(Integer mainkey) {
        this.mainkey = mainkey;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getBkkey()
	 */
    public Integer getBkkey() {
        return this.bkkey;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setBkkey(java.lang.Integer)
	 */
    public void setBkkey(Integer bkkey) {
        this.bkkey = bkkey;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getVslvoykey()
	 */
    public Integer getVslvoykey() {
        return this.vslvoykey;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setVslvoykey(java.lang.Integer)
	 */
    public void setVslvoykey(Integer vslvoykey) {
        this.vslvoykey = vslvoykey;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getCargoname()
	 */
    public String getCargoname() {
        return this.cargoname;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setCargoname(java.lang.String)
	 */
    public void setCargoname(String cargoname) {
        this.cargoname = cargoname;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getTypecode()
	 */
    public String getTypecode() {
        return this.typecode;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setTypecode(java.lang.String)
	 */
    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getTypename()
	 */
    public String getTypename() {
        return this.typename;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setTypename(java.lang.String)
	 */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getPiece()
	 */
    public Integer getPiece() {
        return this.piece;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setPiece(java.lang.Integer)
	 */
    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getPkgcode()
	 */
    public String getPkgcode() {
        return this.pkgcode;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setPkgcode(java.lang.String)
	 */
    public void setPkgcode(String pkgcode) {
        this.pkgcode = pkgcode;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getPkgname()
	 */
    public String getPkgname() {
        return this.pkgname;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setPkgname(java.lang.String)
	 */
    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getNtweight()
	 */
    public Double getNtweight() {
        return this.ntweight;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setNtweight(java.lang.Double)
	 */
    public void setNtweight(Double ntweight) {
        this.ntweight = ntweight;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getGtweight()
	 */
    public Double getGtweight() {
        return this.gtweight;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setGtweight(java.lang.Double)
	 */
    public void setGtweight(Double gtweight) {
        this.gtweight = gtweight;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getDgsign()
	 */
    public String getDgsign() {
        return this.dgsign;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setDgsign(java.lang.String)
	 */
    public void setDgsign(String dgsign) {
        this.dgsign = dgsign;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getDguncode()
	 */
    public String getDguncode() {
        return this.dguncode;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setDguncode(java.lang.String)
	 */
    public void setDguncode(String dguncode) {
        this.dguncode = dguncode;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getDgpageno()
	 */
    public String getDgpageno() {
        return this.dgpageno;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setDgpageno(java.lang.String)
	 */
    public void setDgpageno(String dgpageno) {
        this.dgpageno = dgpageno;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getDgclass()
	 */
    public String getDgclass() {
        return this.dgclass;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setDgclass(java.lang.String)
	 */
    public void setDgclass(String dgclass) {
        this.dgclass = dgclass;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getDgflag()
	 */
    public String getDgflag() {
        return this.dgflag;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setDgflag(java.lang.String)
	 */
    public void setDgflag(String dgflag) {
        this.dgflag = dgflag;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getDgflash()
	 */
    public String getDgflash() {
        return this.dgflash;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setDgflash(java.lang.String)
	 */
    public void setDgflash(String dgflash) {
        this.dgflash = dgflash;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getMarks()
	 */
    public String getMarks() {
        return this.marks;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setMarks(java.lang.String)
	 */
    public void setMarks(String marks) {
        this.marks = marks;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getCargodesc()
	 */
    public String getCargodesc() {
        return this.cargodesc;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setCargodesc(java.lang.String)
	 */
    public void setCargodesc(String cargodesc) {
        this.cargodesc = cargodesc;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getRfsign()
	 */
    public String getRfsign() {
        return this.rfsign;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setRfsign(java.lang.String)
	 */
    public void setRfsign(String rfsign) {
        this.rfsign = rfsign;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getRfunit()
	 */
    public String getRfunit() {
        return this.rfunit;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setRfunit(java.lang.String)
	 */
    public void setRfunit(String rfunit) {
        this.rfunit = rfunit;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getRffrom()
	 */
    public String getRffrom() {
        return this.rffrom;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setRffrom(java.lang.String)
	 */
    public void setRffrom(String rffrom) {
        this.rffrom = rffrom;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getRfto()
	 */
    public String getRfto() {
        return this.rfto;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setRfto(java.lang.String)
	 */
    public void setRfto(String rfto) {
        this.rfto = rfto;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getRemark()
	 */
    public String getRemark() {
        return this.remark;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setRemark(java.lang.String)
	 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getBlpkgname()
	 */
    public String getBlpkgname() {
        return this.blpkgname;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setBlpkgname(java.lang.String)
	 */
    public void setBlpkgname(String blpkgname) {
        this.blpkgname = blpkgname;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getBlgtweight()
	 */
    public String getBlgtweight() {
        return this.blgtweight;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setBlgtweight(java.lang.String)
	 */
    public void setBlgtweight(String blgtweight) {
        this.blgtweight = blgtweight;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getBlmeasure()
	 */
    public String getBlmeasure() {
        return this.blmeasure;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setBlmeasure(java.lang.String)
	 */
    public void setBlmeasure(String blmeasure) {
        this.blmeasure = blmeasure;
    }

    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#getMeasure()
	 */
    public Double getMeasure() {
        return this.measure;
    }
    
    /* (non-Javadoc)
	 * @see cn.netstars.vsagent.mapping.ecd.IBkcargo#setMeasure(java.lang.Double)
	 */
    public void setMeasure(Double measure) {
        this.measure = measure;
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