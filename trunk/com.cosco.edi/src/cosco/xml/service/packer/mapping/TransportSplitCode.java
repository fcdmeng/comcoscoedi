package cosco.xml.service.packer.mapping;

import java.util.HashMap;
/**
 * 参文件FunctionCode.xsd
 * @author 返回功能代码
 *
 */
/**
不分批　0
分批　　1
		
 */
public class TransportSplitCode {
	public HashMap TransportSplitCode(){
		HashMap<String, String> transportSplitCode=new HashMap();
		transportSplitCode.put("不分批", "0");
		transportSplitCode.put("分批", "1");
		
		return transportSplitCode;
		}
	public String getTransportSplitCode(String TransportSplitCode){
		String Values = TransportSplitCode;
		if(Values.equals("1") || Values.equals("0")) return TransportSplitCode;
		
		return (String)TransportSplitCode().get(TransportSplitCode);
	}
}

