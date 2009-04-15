package cosco.xml.service.packer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cosco.xml.mapping.Xmlbkparty;
import cosco.xml.mapping.Xmlmapping;

public interface Xml {
	boolean Save2File(Document doc);//得到文件名
	String getFName();//利用船名航次生成
	String getExtension();//返回 文件扩展名 
	boolean generaters(String partyCode);//生成对照码
	boolean generatersBooking();//生成提单相关信息
	Document ManifestHead();//报文头部分
	void Declaration(Document doc, Element parentElement);//报文体内容
	boolean Init();//初始化对照码对象
	boolean ExecutePack();//创建主体
	boolean Retrieve();//读取所需的参数及提单信息
	String CodeConstrast();//转换对照码
	boolean pack();//生成报文体
	Double getGrossVolumeMeasure();//货物总体积
	void  setGrossVolumeMeasure(Double  GrossVolumeMeasure);
	void setMessageDefine(String MessageDefine);//报文定义
	String getMessageDefine();
	void setMessageType(String MessageType);//报文类型
	String getMessageType();
	void setMessageID(String MessageID);//报文编号
	String getMessageID();
	void setSenderID(String SenderID);//发送方代码
	String getSenderID();//发送方代码
	void setReceiverID(String ReceiverID);//接收方代码
	String getReceiverID();//接收方代码
	Xmlmapping getXmlmapping(String Type, String LocateCode);//对照码
	void setVersion(String Version);//发送版本
	String getVersion();
	void setFunctionCode(String FunctionCode);
	String getFunctionCode();//EDI功能：增加、删除、变更、一次申报、二次申报
	void setSendTime(String SendTime);//发送时间
	String getSendTime();//报文发送时间
	void TransportEquipment(Document doc, Element parentElement,String blnokey);//箱单信息
	void ConsignmentItem(Document doc, Element parentElement,String blnokey);//货物信息
	//------------
	//操作XML文件
	Element addElement(Document doc, Element parentElement, String nodeTitle);
	Element addElement(Document doc, Element parentElement, String nodeTitle, String nodeValue);
	Element nodeParty(Document doc, Element ParentElement, String nodeTitle, Xmlbkparty party);//处理参收发货人及通知人
	//End By
}
