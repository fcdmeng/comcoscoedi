package cosco.xml.service.packer.mapping;

import java.util.HashMap;
/**
 * ���ļ�FunctionCode.xsd
 * @author ���ع��ܴ���
 *
 */
/**
��������0
��������1
		
 */
public class TransportSplitCode {
	public HashMap TransportSplitCode(){
		HashMap<String, String> transportSplitCode=new HashMap();
		transportSplitCode.put("������", "0");
		transportSplitCode.put("����", "1");
		
		return transportSplitCode;
		}
	public String getTransportSplitCode(String TransportSplitCode){
		String Values = TransportSplitCode;
		if(Values.equals("1") || Values.equals("0")) return TransportSplitCode;
		
		return (String)TransportSplitCode().get(TransportSplitCode);
	}
}

