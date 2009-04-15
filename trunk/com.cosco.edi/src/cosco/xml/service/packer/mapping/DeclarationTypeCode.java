package cosco.xml.service.packer.mapping;

import java.util.HashMap;

/**
 * MessageType �ο��ļ�DeclarationTypeCode.xsd
 * @author �������ʹ������ҵ������(1λ)+���䷽ʽ(1λ)+��ˮ��(2λ)
	����		����
	1101	����ԭʼ�յ�
	1201	����ԭʼ�յ�
	2101	����Ԥ��յ�
	2201	����Ԥ��յ�
	3101	���˳����˵�
	3102	���˷����˵�
	3201	���˳����˵�
	3202	���˷����˵�
	4101	���˳���װ��
	4201	���˳���װ��
	5101	���˽������
	5102	���˳������
	5201	���˽������
	5202	���˳������
	6101	���˽��ڷ���
	6102	���˽��ڷֲ�
	6201	���˽��ڷ���
	6202	���˽��ڷֲ�
	7101	���˳���װ���嵥
	8101	���˳��ڸĴ�
	8201	���˳��ڸĵ�
	9999	ָ��֪ͨ
 *
 */

public class DeclarationTypeCode {
	public HashMap DeclarationTypeCode(){
		HashMap<String, String> DeclarationTypeCode=new HashMap();
		DeclarationTypeCode.put("����ԭʼ�յ�", "1101");
		DeclarationTypeCode.put("����ԭʼ�յ�", "1201");
		DeclarationTypeCode.put("����Ԥ��յ�", "2101");
		DeclarationTypeCode.put("����Ԥ��յ�", "2201");
		DeclarationTypeCode.put("����Ԥ��յ�", "2201");
		DeclarationTypeCode.put("���˳����˵�", "3101");
		DeclarationTypeCode.put("���˷����˵�", "3102");
		DeclarationTypeCode.put("���˳����˵�", "3201");
		DeclarationTypeCode.put("���˷����˵�", "3202");
		DeclarationTypeCode.put("���˳���װ��", "4101");
		DeclarationTypeCode.put("���˳���װ��", "4201");
		DeclarationTypeCode.put("���˽������", "5101");
		DeclarationTypeCode.put("���˳������", "5102");
		DeclarationTypeCode.put("���˽������", "5201");
		DeclarationTypeCode.put("���˳������", "5202");
		DeclarationTypeCode.put("���˽��ڷ���", "6101");
		DeclarationTypeCode.put("���˽��ڷֲ�", "6102");
		DeclarationTypeCode.put("���˽��ڷ���", "6201");
		DeclarationTypeCode.put("���˳���װ���嵥", "7101");
		DeclarationTypeCode.put("���˳��ڸĴ�", "8101");
		DeclarationTypeCode.put("���˳��ڸĵ�", "8201");
		DeclarationTypeCode.put("ͨ��ָ��", "ORDER");

		return DeclarationTypeCode;
		}
	public String getDeclarationTypeCode(String DeclarationTypeCode){
		return (String)DeclarationTypeCode().get(DeclarationTypeCode);
	}
}
