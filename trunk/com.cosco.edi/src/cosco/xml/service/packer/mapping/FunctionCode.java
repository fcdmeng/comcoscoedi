package cosco.xml.service.packer.mapping;

import java.util.HashMap;
/**
 * ���ļ�FunctionCode.xsd
 * @author ���ع��ܴ���
 *
 */
/**��һ��˵��
 *  2:����
	3:ɾ��
	5:�޸�
	9:��Ҫ����
	0����������
	11��ָ��֪ͨ

 */
public class FunctionCode {
	public HashMap FunctionCode(){
		HashMap<String, String> FunctionCode=new HashMap();
		FunctionCode.put("����", "2");
		FunctionCode.put("ɾ��", "3");
		FunctionCode.put("���", "5");
		FunctionCode.put("һ���걨", "9");
		FunctionCode.put("�����걨", "0");
		FunctionCode.put("��ִ", "11");
		return FunctionCode;
		}
	public String getFunctionCode(String FunctionCode){
		return (String)FunctionCode().get(FunctionCode);
	}
}

