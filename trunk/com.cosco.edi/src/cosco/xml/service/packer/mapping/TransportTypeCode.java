package cosco.xml.service.packer.mapping;

import java.util.HashMap;

public class TransportTypeCode {
	public String getTransportTypeCode(String documentation){
		return (String) TransportTypeCode().get(documentation);
	}
	/**
	 * ���䷽ʽ����
	 * @return
	 */
	public HashMap TransportTypeCode(){
		HashMap<String, String> TransportTypeCode=new HashMap();
		
		TransportTypeCode.put("��������", "1");
		TransportTypeCode.put("��·����", "2");
		TransportTypeCode.put("��·����", "3");
		TransportTypeCode.put("��������", "4");
		TransportTypeCode.put("��������", "5");
		TransportTypeCode.put("��ʽ����", "6");
		TransportTypeCode.put("�ܵ�����", "7");
		TransportTypeCode.put("�ں�����", "8");
		TransportTypeCode.put("δ֪�����䷽ʽ", "9");
		
		
		return TransportTypeCode;
		
	}
}
