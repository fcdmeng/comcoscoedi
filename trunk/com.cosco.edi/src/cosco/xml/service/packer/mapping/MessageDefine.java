package cosco.xml.service.packer.mapping;
/**�����ļ���MessageDefine.xsd
 * ���زյ�����
 */
import java.util.HashMap;

public class MessageDefine {
	public String getMessageDefine(String documentation){
		
		return (String) MessageDefine().get(documentation);
		
	}
	/**
	 * ��ʼ�����زյ�����Ҫ������
	 * @return
	 */
	public HashMap MessageDefine(){
		HashMap<String, String> FunctionCode=new HashMap();
		
		FunctionCode.put("����ԭʼ�յ�", "urn:wco:datamodel:standard:CN:1101:1");
		FunctionCode.put("����ԭʼ�յ�", "urn:wco:datamodel:standard:CN:1201:1");
		FunctionCode.put("����Ԥ��յ�", "urn:wco:datamodel:standard:CN:2101:1");
		FunctionCode.put("����Ԥ��յ�", "urn:wco:datamodel:standard:CN:2201:1");
		FunctionCode.put("���˳����˵�", "urn:wco:datamodel:standard:CN:3101:");
		FunctionCode.put("���˷����˵�", "urn:wco:datamodel:standard:CN:3102:1");
		FunctionCode.put("���˳����˵�", "urn:wco:datamodel:standard:CN:3201:1");
		FunctionCode.put("���˷����˵�", "urn:wco:datamodel:standard:CN:3202:1");
		FunctionCode.put("���˳���װ��", "urn:wco:datamodel:standard:CN:4101:1");
		FunctionCode.put("���˳���װ��", "urn:wco:datamodel:standard:CN:4201:1");
		FunctionCode.put("���˽������", "urn:wco:datamodel:standard:CN:5101:1");
		FunctionCode.put("���˳������", "urn:wco:datamodel:standard:CN:5102:1");
		FunctionCode.put("���˽������", "urn:wco:datamodel:standard:CN:5201:1");
		FunctionCode.put("���˳������", "urn:wco:datamodel:standard:CN:5202:1");
		FunctionCode.put("���˽��ڷ���", "urn:wco:datamodel:standard:CN:6101:1");
		FunctionCode.put("���˽��ڷֲ�", "urn:wco:datamodel:standard:CN:6102:1");
		FunctionCode.put("���˽��ڷ���", "urn:wco:datamodel:standard:CN:6201:1");
		FunctionCode.put("���˽��ڷֲ�", "urn:wco:datamodel:standard:CN:6202:1");
		FunctionCode.put("���˳���װ���嵥", "urn:wco:datamodel:standard:CN:7101:1");
		
		return FunctionCode;
		
	}
	
	
}
