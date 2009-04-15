package cosco.xml.service.packer.mapping;

import java.util.HashMap;

public class SealAgencyCode {
	public String getTransportTypeCode(String documentation){
		return (String) SealAgencyCode().get(documentation);
	}
	/**
	 * 运输方式代码
	 * @return
	 */
	public HashMap SealAgencyCode(){
		HashMap<String, String> SealAgencyCode=new HashMap();
		
		SealAgencyCode.put("拼箱人", "AA");
		SealAgencyCode.put("未知", "AB");
		SealAgencyCode.put("检疫", "AC");
		SealAgencyCode.put("承运人", "CA");
		SealAgencyCode.put("海关", "CU");
		SealAgencyCode.put("发货人", "SH");
		SealAgencyCode.put("码头", "TO");
	
		
		
		return SealAgencyCode;
		
	}
}
