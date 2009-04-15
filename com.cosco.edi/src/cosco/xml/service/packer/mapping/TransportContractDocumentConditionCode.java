package cosco.xml.service.packer.mapping;

import java.util.HashMap;

public class TransportContractDocumentConditionCode {
	public String getTransportTypeCode(String documentation){
		return (String) TransportContractDocumentConditionCode().get(documentation);
	}
	/**
	 * ���䷽ʽ����
	 * @return
	 */
	public HashMap TransportContractDocumentConditionCode(){
		HashMap<String, String> TransportContractDocumentConditionCode=new HashMap();
		
		TransportContractDocumentConditionCode.put("�۵���", "10");//port to port
		TransportContractDocumentConditionCode.put("�ŵ���", "27");//door to door
		TransportContractDocumentConditionCode.put("�ŵ���", "28");//door to pier
		TransportContractDocumentConditionCode.put("�㵽��", "29");//pier to door
		TransportContractDocumentConditionCode.put("�㵽��", "30");//pier to pier
	
		
		
		return TransportContractDocumentConditionCode;
		
	}
	
	public static void main(String argv[]){
		String ls = new TransportContractDocumentConditionCode().getTransportTypeCode("");
		System.out.println(ls);
	}
}
