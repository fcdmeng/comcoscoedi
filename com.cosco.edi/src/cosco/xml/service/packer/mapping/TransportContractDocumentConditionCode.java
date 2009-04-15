package cosco.xml.service.packer.mapping;

import java.util.HashMap;

public class TransportContractDocumentConditionCode {
	public String getTransportTypeCode(String documentation){
		return (String) TransportContractDocumentConditionCode().get(documentation);
	}
	/**
	 * 运输方式代码
	 * @return
	 */
	public HashMap TransportContractDocumentConditionCode(){
		HashMap<String, String> TransportContractDocumentConditionCode=new HashMap();
		
		TransportContractDocumentConditionCode.put("港到港", "10");//port to port
		TransportContractDocumentConditionCode.put("门到门", "27");//door to door
		TransportContractDocumentConditionCode.put("门到点", "28");//door to pier
		TransportContractDocumentConditionCode.put("点到门", "29");//pier to door
		TransportContractDocumentConditionCode.put("点到点", "30");//pier to pier
	
		
		
		return TransportContractDocumentConditionCode;
		
	}
	
	public static void main(String argv[]){
		String ls = new TransportContractDocumentConditionCode().getTransportTypeCode("");
		System.out.println(ls);
	}
}
