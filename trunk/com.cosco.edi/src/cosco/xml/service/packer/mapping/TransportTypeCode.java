package cosco.xml.service.packer.mapping;

import java.util.HashMap;

public class TransportTypeCode {
	public String getTransportTypeCode(String documentation){
		return (String) TransportTypeCode().get(documentation);
	}
	/**
	 * 运输方式代码
	 * @return
	 */
	public HashMap TransportTypeCode(){
		HashMap<String, String> TransportTypeCode=new HashMap();
		
		TransportTypeCode.put("海上运输", "1");
		TransportTypeCode.put("铁路运输", "2");
		TransportTypeCode.put("公路运输", "3");
		TransportTypeCode.put("航空运输", "4");
		TransportTypeCode.put("邮政运输", "5");
		TransportTypeCode.put("多式联运", "6");
		TransportTypeCode.put("管道运输", "7");
		TransportTypeCode.put("内河运输", "8");
		TransportTypeCode.put("未知的运输方式", "9");
		
		
		return TransportTypeCode;
		
	}
}
