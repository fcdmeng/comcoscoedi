package cosco.xml.service.packer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cosco.xml.mapping.Xmlbkparty;
import cosco.xml.mapping.Xmlmapping;

public interface Xml {
	boolean Save2File(Document doc);//�õ��ļ���
	String getFName();//���ô�����������
	String getExtension();//���� �ļ���չ�� 
	boolean generaters(String partyCode);//���ɶ�����
	boolean generatersBooking();//�����ᵥ�����Ϣ
	Document ManifestHead();//����ͷ����
	void Declaration(Document doc, Element parentElement);//����������
	boolean Init();//��ʼ�����������
	boolean ExecutePack();//��������
	boolean Retrieve();//��ȡ����Ĳ������ᵥ��Ϣ
	String CodeConstrast();//ת��������
	boolean pack();//���ɱ�����
	Double getGrossVolumeMeasure();//���������
	void  setGrossVolumeMeasure(Double  GrossVolumeMeasure);
	void setMessageDefine(String MessageDefine);//���Ķ���
	String getMessageDefine();
	void setMessageType(String MessageType);//��������
	String getMessageType();
	void setMessageID(String MessageID);//���ı��
	String getMessageID();
	void setSenderID(String SenderID);//���ͷ�����
	String getSenderID();//���ͷ�����
	void setReceiverID(String ReceiverID);//���շ�����
	String getReceiverID();//���շ�����
	Xmlmapping getXmlmapping(String Type, String LocateCode);//������
	void setVersion(String Version);//���Ͱ汾
	String getVersion();
	void setFunctionCode(String FunctionCode);
	String getFunctionCode();//EDI���ܣ����ӡ�ɾ���������һ���걨�������걨
	void setSendTime(String SendTime);//����ʱ��
	String getSendTime();//���ķ���ʱ��
	void TransportEquipment(Document doc, Element parentElement,String blnokey);//�䵥��Ϣ
	void ConsignmentItem(Document doc, Element parentElement,String blnokey);//������Ϣ
	//------------
	//����XML�ļ�
	Element addElement(Document doc, Element parentElement, String nodeTitle);
	Element addElement(Document doc, Element parentElement, String nodeTitle, String nodeValue);
	Element nodeParty(Document doc, Element ParentElement, String nodeTitle, Xmlbkparty party);//������շ����˼�֪ͨ��
	//End By
}
