package cosco.xml.service.packer.mapping;

import java.util.HashMap;

public class SealAgencyCode {
	public String getTransportTypeCode(String documentation){
		return (String) SealAgencyCode().get(documentation);
	}
	/**
	 * ���䷽ʽ����
	 * @return
	 */
	public HashMap SealAgencyCode(){
		HashMap<String, String> SealAgencyCode=new HashMap();
		
		SealAgencyCode.put("ƴ����", "AA");
		SealAgencyCode.put("δ֪", "AB");
		SealAgencyCode.put("����", "AC");
		SealAgencyCode.put("������", "CA");
		SealAgencyCode.put("����", "CU");
		SealAgencyCode.put("������", "SH");
		SealAgencyCode.put("��ͷ", "TO");
	
		
		
		return SealAgencyCode;
		
	}
}
