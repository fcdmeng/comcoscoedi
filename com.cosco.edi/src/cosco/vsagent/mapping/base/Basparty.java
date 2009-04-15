package cosco.vsagent.mapping.base;

import java.sql.Timestamp;
import java.util.Date;


/**
 * Basparty generated by MyEclipse - Hibernate Tools
 */

public class Basparty  implements java.io.Serializable {


    // Fields    

     private Integer mainkey;
     private String partycode;
     private String cname;
     private String ename;
     private String fund;
     private String address;
     private String postal;
     private String contacter;
     private String telephone;
     private String fax;
     private String telex;
     private String email;
     private String dockcode;
     private String dockname;
     private String makercode;
     private String maker;
     private Timestamp madetime;
     private String modicode;
     private String modifier;
     private Timestamp moditime;
     private String remark;
     private String ifcosco;
     private String mgrcode;
     private String mgrdesc;
     private String memocode;
     private String transway;
     private String vslmgrcode;
     private String cbrname;
     private String ebrname;
     private String nationcode;
     private String nationname;
     private String deptcode;
     private String deptname;
     private String vslmgrname;
     private String ifaccount;
     private String effect;
     private String keeper;
     private String is_Fleet;


    
    // Constructors

    

	/** default constructor */
    public Basparty() {
    }

	/** minimal constructor */
    public Basparty(Integer mainkey) {
        this.mainkey = mainkey;
    }
    
    public Basparty(String partycode){
    	this.partycode = partycode;
    	
    }
    
    /** full constructor */
    public Basparty(Integer mainkey, String partycode, String cname, String ename, String fund, String address, String postal, String contacter, String telephone, String fax, String telex, String email, String dockcode, String dockname, String makercode, String maker, Timestamp madetime, String modicode, String modifier, Timestamp moditime, String remark, String ifcosco, String mgrcode, String mgrdesc, String memocode, String transway, String vslmgrcode, String cbrname, String ebrname, String nationcode, String nationname, String deptcode, String deptname, String vslmgrname, String ifaccount, String effect, String keeper, String is_Fleet) {
        this.mainkey = mainkey;
        this.partycode = partycode;
        this.cname = cname;
        this.ename = ename;
        this.fund = fund;
        this.address = address;
        this.postal = postal;
        this.contacter = contacter;
        this.telephone = telephone;
        this.fax = fax;
        this.telex = telex;
        this.email = email;
        this.dockcode = dockcode;
        this.dockname = dockname;
        this.makercode = makercode;
        this.maker = maker;
        this.madetime = madetime;
        this.modicode = modicode;
        this.modifier = modifier;
        this.moditime = moditime;
        this.remark = remark;
        this.ifcosco = ifcosco;
        this.mgrcode = mgrcode;
        this.mgrdesc = mgrdesc;
        this.memocode = memocode;
        this.transway = transway;
        this.vslmgrcode = vslmgrcode;
        this.cbrname = cbrname;
        this.ebrname = ebrname;
        this.nationcode = nationcode;
        this.nationname = nationname;
        this.deptcode = deptcode;
        this.deptname = deptname;
        this.vslmgrname = vslmgrname;
        this.ifaccount = ifaccount;
        this.effect = effect;
        this.keeper = keeper;
        this.is_Fleet = is_Fleet;
    }

   
    // Property accessors

    public Integer getMainkey() {
        return this.mainkey;
    }
    
    public void setMainkey(Integer mainkey) {
        this.mainkey = mainkey;
    }

    public String getPartycode() {
        return this.partycode;
    }
    
    public void setPartycode(String partycode) {
        this.partycode = partycode;
    }

    public String getCname() {
        return this.cname;
    }
    
    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEname() {
        return this.ename;
    }
    
    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getFund() {
        return this.fund;
    }
    
    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return this.postal;
    }
    
    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getContacter() {
        return this.contacter;
    }
    
    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelex() {
        return this.telex;
    }
    
    public void setTelex(String telex) {
        this.telex = telex;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDockcode() {
        return this.dockcode;
    }
    
    public void setDockcode(String dockcode) {
        this.dockcode = dockcode;
    }

    public String getDockname() {
        return this.dockname;
    }
    
    public void setDockname(String dockname) {
        this.dockname = dockname;
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

    public Timestamp getMadetime() {
        return this.madetime;
    }
    
    public void setMadetime(Timestamp madetime) {
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

    public Timestamp getModitime() {
        return this.moditime;
    }
    
    public void setModitime(Timestamp moditime) {
        this.moditime = moditime;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIfcosco() {
        return this.ifcosco;
    }
    
    public void setIfcosco(String ifcosco) {
        this.ifcosco = ifcosco;
    }

    public String getMgrcode() {
        return this.mgrcode;
    }
    
    public void setMgrcode(String mgrcode) {
        this.mgrcode = mgrcode;
    }

    public String getMgrdesc() {
        return this.mgrdesc;
    }
    
    public void setMgrdesc(String mgrdesc) {
        this.mgrdesc = mgrdesc;
    }

    public String getMemocode() {
        return this.memocode;
    }
    
    public void setMemocode(String memocode) {
        this.memocode = memocode;
    }

    public String getTransway() {
        return this.transway;
    }
    
    public void setTransway(String transway) {
        this.transway = transway;
    }

    public String getVslmgrcode() {
        return this.vslmgrcode;
    }
    
    public void setVslmgrcode(String vslmgrcode) {
        this.vslmgrcode = vslmgrcode;
    }

    public String getCbrname() {
        return this.cbrname;
    }
    
    public void setCbrname(String cbrname) {
        this.cbrname = cbrname;
    }

    public String getEbrname() {
        return this.ebrname;
    }
    
    public void setEbrname(String ebrname) {
        this.ebrname = ebrname;
    }

    public String getNationcode() {
        return this.nationcode;
    }
    
    public void setNationcode(String nationcode) {
        this.nationcode = nationcode;
    }

    public String getNationname() {
        return this.nationname;
    }
    
    public void setNationname(String nationname) {
        this.nationname = nationname;
    }

    public String getDeptcode() {
        return this.deptcode;
    }
    
    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getDeptname() {
        return this.deptname;
    }
    
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getVslmgrname() {
        return this.vslmgrname;
    }
    
    public void setVslmgrname(String vslmgrname) {
        this.vslmgrname = vslmgrname;
    }

    public String getIfaccount() {
        return this.ifaccount;
    }
    
    public void setIfaccount(String ifaccount) {
        this.ifaccount = ifaccount;
    }

    public String getEffect() {
        return this.effect;
    }
    
    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getKeeper() {
        return this.keeper;
    }
    
    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    public String getIs_Fleet() {
        return this.is_Fleet;
    }
    
    public void setIs_Fleet(String is_Fleet) {
        this.is_Fleet = is_Fleet;
    }
   








}