package cosco.xml.service.packer.mapping;

import java.util.HashMap;

/**
 * MessageType 参考文件DeclarationTypeCode.xsd
 * @author 报文类型代码规则：业务类型(1位)+运输方式(1位)+流水号(2位)
	编码		含义
	1101	海运原始舱单
	1201	空运原始舱单
	2101	海运预配舱单
	2201	空运预配舱单
	3101	海运出口运抵
	3102	海运分流运抵
	3201	空运出口运抵
	3202	空运分流运抵
	4101	海运出口装载
	4201	空运出口装载
	5101	海运进口理货
	5102	海运出口理货
	5201	空运进口理货
	5202	空运出口理货
	6101	海运进口分流
	6102	海运进口分拨
	6201	空运进口分流
	6202	空运进口分拨
	7101	海运出口装箱清单
	8101	海运出口改船
	8201	空运出口改单
	9999	指令通知
 *
 */

public class DeclarationTypeCode {
	public HashMap DeclarationTypeCode(){
		HashMap<String, String> DeclarationTypeCode=new HashMap();
		DeclarationTypeCode.put("海运原始舱单", "1101");
		DeclarationTypeCode.put("空运原始舱单", "1201");
		DeclarationTypeCode.put("海运预配舱单", "2101");
		DeclarationTypeCode.put("空运预配舱单", "2201");
		DeclarationTypeCode.put("空运预配舱单", "2201");
		DeclarationTypeCode.put("海运出口运抵", "3101");
		DeclarationTypeCode.put("海运分流运抵", "3102");
		DeclarationTypeCode.put("空运出口运抵", "3201");
		DeclarationTypeCode.put("空运分流运抵", "3202");
		DeclarationTypeCode.put("海运出口装载", "4101");
		DeclarationTypeCode.put("空运出口装载", "4201");
		DeclarationTypeCode.put("海运进口理货", "5101");
		DeclarationTypeCode.put("海运出口理货", "5102");
		DeclarationTypeCode.put("空运进口理货", "5201");
		DeclarationTypeCode.put("空运出口理货", "5202");
		DeclarationTypeCode.put("海运进口分流", "6101");
		DeclarationTypeCode.put("海运进口分拨", "6102");
		DeclarationTypeCode.put("空运进口分流", "6201");
		DeclarationTypeCode.put("海运出口装箱清单", "7101");
		DeclarationTypeCode.put("海运出口改船", "8101");
		DeclarationTypeCode.put("空运出口改单", "8201");
		DeclarationTypeCode.put("通关指令", "ORDER");

		return DeclarationTypeCode;
		}
	public String getDeclarationTypeCode(String DeclarationTypeCode){
		return (String)DeclarationTypeCode().get(DeclarationTypeCode);
	}
}
