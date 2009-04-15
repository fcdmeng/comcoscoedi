package cosco.xml.service.packer.mapping;
/**参照文件：MessageDefine.xsd
 * 海关舱单类型
 */
import java.util.HashMap;

public class MessageDefine {
	public String getMessageDefine(String documentation){
		
		return (String) MessageDefine().get(documentation);
		
	}
	/**
	 * 初始化海关舱单所需要的类型
	 * @return
	 */
	public HashMap MessageDefine(){
		HashMap<String, String> FunctionCode=new HashMap();
		
		FunctionCode.put("海运原始舱单", "urn:wco:datamodel:standard:CN:1101:1");
		FunctionCode.put("空运原始舱单", "urn:wco:datamodel:standard:CN:1201:1");
		FunctionCode.put("海运预配舱单", "urn:wco:datamodel:standard:CN:2101:1");
		FunctionCode.put("空运预配舱单", "urn:wco:datamodel:standard:CN:2201:1");
		FunctionCode.put("海运出口运抵", "urn:wco:datamodel:standard:CN:3101:");
		FunctionCode.put("海运分流运抵", "urn:wco:datamodel:standard:CN:3102:1");
		FunctionCode.put("空运出口运抵", "urn:wco:datamodel:standard:CN:3201:1");
		FunctionCode.put("空运分流运抵", "urn:wco:datamodel:standard:CN:3202:1");
		FunctionCode.put("海运出口装载", "urn:wco:datamodel:standard:CN:4101:1");
		FunctionCode.put("空运出口装载", "urn:wco:datamodel:standard:CN:4201:1");
		FunctionCode.put("海运进口理货", "urn:wco:datamodel:standard:CN:5101:1");
		FunctionCode.put("海运出口理货", "urn:wco:datamodel:standard:CN:5102:1");
		FunctionCode.put("空运进口理货", "urn:wco:datamodel:standard:CN:5201:1");
		FunctionCode.put("空运出口理货", "urn:wco:datamodel:standard:CN:5202:1");
		FunctionCode.put("海运进口分流", "urn:wco:datamodel:standard:CN:6101:1");
		FunctionCode.put("海运进口分拨", "urn:wco:datamodel:standard:CN:6102:1");
		FunctionCode.put("空运进口分流", "urn:wco:datamodel:standard:CN:6201:1");
		FunctionCode.put("空运进口分拨", "urn:wco:datamodel:standard:CN:6202:1");
		FunctionCode.put("海运出口装箱清单", "urn:wco:datamodel:standard:CN:7101:1");
		
		return FunctionCode;
		
	}
	
	
}
