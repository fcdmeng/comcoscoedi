package cosco.xml.service.packer.mapping;

import java.util.HashMap;
/**
 * 参文件FunctionCode.xsd
 * @author 返回功能代码
 *
 */
/**另一份说明
 *  2:增加
	3:删除
	5:修改
	9:主要数据
	0：其他数据
	11：指令通知

 */
public class FunctionCode {
	public HashMap FunctionCode(){
		HashMap<String, String> FunctionCode=new HashMap();
		FunctionCode.put("增加", "2");
		FunctionCode.put("删除", "3");
		FunctionCode.put("变更", "5");
		FunctionCode.put("一次申报", "9");
		FunctionCode.put("二次申报", "0");
		FunctionCode.put("回执", "11");
		return FunctionCode;
		}
	public String getFunctionCode(String FunctionCode){
		return (String)FunctionCode().get(FunctionCode);
	}
}

