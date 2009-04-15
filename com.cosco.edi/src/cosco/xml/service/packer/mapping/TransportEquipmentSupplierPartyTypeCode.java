package cosco.xml.service.packer.mapping;

import java.util.HashMap;
/**
 * 参文件TransportEquipmentSupplierPartyTypeCode.xsd
 * @author 返回功能代码
 *
 */
/**
集装箱（器）来源代码
 */
public class TransportEquipmentSupplierPartyTypeCode {
	public HashMap TransportEquipmentSupplierPartyTypeCode(){
		HashMap<String, String> TransportEquipmentSupplierPartyTypeCode=new HashMap();
		TransportEquipmentSupplierPartyTypeCode.put("货主自备箱", "1");
		TransportEquipmentSupplierPartyTypeCode.put("承运人提供箱", "2");
		TransportEquipmentSupplierPartyTypeCode.put("拼箱人提供箱", "3");
		TransportEquipmentSupplierPartyTypeCode.put("拆箱人提供箱", "4");
		TransportEquipmentSupplierPartyTypeCode.put("第三方提供箱", "5");
		return TransportEquipmentSupplierPartyTypeCode;
		}
	public String getTransportEquipmentSupplierPartyTypeCode(String TransportEquipmentSupplierPartyTypeCode){
		return (String)TransportEquipmentSupplierPartyTypeCode().get(TransportEquipmentSupplierPartyTypeCode);
	}
}

