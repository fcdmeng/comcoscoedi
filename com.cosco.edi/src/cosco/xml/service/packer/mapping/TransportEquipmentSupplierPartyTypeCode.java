package cosco.xml.service.packer.mapping;

import java.util.HashMap;
/**
 * ���ļ�TransportEquipmentSupplierPartyTypeCode.xsd
 * @author ���ع��ܴ���
 *
 */
/**
��װ�䣨������Դ����
 */
public class TransportEquipmentSupplierPartyTypeCode {
	public HashMap TransportEquipmentSupplierPartyTypeCode(){
		HashMap<String, String> TransportEquipmentSupplierPartyTypeCode=new HashMap();
		TransportEquipmentSupplierPartyTypeCode.put("�����Ա���", "1");
		TransportEquipmentSupplierPartyTypeCode.put("�������ṩ��", "2");
		TransportEquipmentSupplierPartyTypeCode.put("ƴ�����ṩ��", "3");
		TransportEquipmentSupplierPartyTypeCode.put("�������ṩ��", "4");
		TransportEquipmentSupplierPartyTypeCode.put("�������ṩ��", "5");
		return TransportEquipmentSupplierPartyTypeCode;
		}
	public String getTransportEquipmentSupplierPartyTypeCode(String TransportEquipmentSupplierPartyTypeCode){
		return (String)TransportEquipmentSupplierPartyTypeCode().get(TransportEquipmentSupplierPartyTypeCode);
	}
}

