package cosco.xml.service.packer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import cosco.vsagent.app.Activator;
import cosco.vsagent.dbo.base.BasvslvoyDAO;
import cosco.vsagent.mapping.base.Basvslvoy;
import cosco.vsagent.mapping.base.Basvvcarr;
import cosco.vsagent.system.Context;
import cosco.xml.dbo.XmlmappingDAO;
import cosco.xml.mapping.Xmlbkcargo;
import cosco.xml.mapping.Xmlbkcntr;
import cosco.xml.mapping.Xmlbkparty;
import cosco.xml.mapping.Xmlbooking;
import cosco.xml.mapping.Xmlmapping;
import cosco.xml.service.packer.mapping.DeclarationTypeCode;
import cosco.xml.service.packer.mapping.FunctionCode;
import cosco.xml.service.packer.mapping.MessageDefine;
import cosco.xml.service.packer.mapping.MessageID;

public class AbstractXml implements Xml {
	private String BorderTransportMeans = "";// ����
	private String JourneyID = "";// ����

	private String FunctionCode;// ���ܴ���
	private String SenderID = "2000102";// ���ͷ�����
	private String ReceiverID = "CNCustoms";// ���շ�����
	private String Version = "1.0";// ���İ汾
	private String MessageDefine;// ���Ķ���
	private String MessageType;// ��������
	private String MessageID = "ChinaInboundMainData";// ���ı��
	private String SendTime;// ���ķ���ʱ��
	private String OperationType = "SEND";// ���ͻ��ǽ���
	private String SystemId = "ICD";// ϵͳ��ʾ
	private String separator = System.getProperty("file.separator");
	
	private String EdiFullName;// EDI���ɵ��ļ�ȫ��������·��
	private String EdiFileName;//�ļ�����������
	DataStore dataStore = new DataStore();// ��ǰҪ���ɵ�EDI
	List<Xmlmapping> xmlmapping;//�������
	// dataStore//�ᵥ��Ϣ��
	Basvslvoy basvslvoy;// ����������Ϣ
	Basvvcarr basvvcarr;// ��������Ϣ
	String Values = null;// ��ʱ�õ�ֵ
	String ValuesCode;
	String ValuesName;
	Xmlbooking xmlbooking;// �ᵥ��
	Xmlbkcargo xmlcargo;// �����
	Xmlbkcntr xmlcntr;// ����Ϣ
	Xmlbkparty xmlparty;// ���뷽��Ϣ

	private Double GrossVolumeMeasure;// �������

	Document doc = null;
	Element root = null;

//	public AbstractXml() {
		/*setMessageDefine("����ԭʼ�յ�");
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		this.setSendTime(tempDate.format(new java.util.Date()));*/
//		AbstractXml("����ԭʼ�յ�");
//	}
	

	public AbstractXml(String MessageDefine) {
		setMessageDefine(MessageDefine);
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		this.setSendTime(tempDate.format(new java.util.Date()));
	}

	public String getJourneyID() {
		return JourneyID;
	}

	public void setJourneyID(String journeyID) {
		JourneyID = journeyID;
	}

	public String getBorderTransportMeans() {
		return BorderTransportMeans;
	}

	public void setBorderTransportMeans(String borderTransportMeans) {
		BorderTransportMeans = borderTransportMeans;
	}

	public void Declaration(Document doc, Element parentElement) {
		// TODO Auto-generated method stub

	}

	public boolean Save2File(Document doc) {
		// TODO Auto-generated method stub
		try {
			String fileName = getFName();
			this.setEdiFileName(fileName + getExtension());
			
			// �����ļ����·��
			fileName = getEdiPath() + fileName;
			
			FileOutputStream outStream = new FileOutputStream(fileName
					+ getExtension());
			OutputStreamWriter outWriter = new OutputStreamWriter(outStream,
					"UTF-8");

			((XmlDocument) doc).write(outWriter, "UTF-8");
			outWriter.close();
			outStream.close();
			this.setEdiFullName(fileName+ getExtension());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean generaters(String partyCode) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean generatersBooking() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFName() {
		/**
		 * ���ر��������������£� CN_[name] _1p0_[sender_code]_[send_time].xml CN�����й����أ�
		 * [sender_code]�����ͷ����룬 [send_time]������ʱ�䣬��ȷ�����룬����yyyyMMddhhmmssfff��ʽ��
		 * ��1p0���еġ�1�������ĸ�ʽ�����汾�š���0�������汾�ţ� ����[name]����������й����ؽ������յ��������ͱ����
		 */

		String fileName = this.getSendTime();
		fileName = "CN_" + getMessageType() + "_1p0_" + getSenderID()
				+ fileName;
		return fileName;
	}

	public Element addElement(Document doc, Element parentElement,
			String nodeTitle) {
		Element element = doc.createElement(nodeTitle);
		parentElement.appendChild(element);
		return element;
	}

	public Element addElement(Document doc, Element parentElement,
			String nodeTitle, String nodeValue) {
		Element element = doc.createElement(nodeTitle);
		parentElement.appendChild(element);
		Text text = doc.createTextNode(nodeValue);
		element.appendChild(text);
		return element;
	}

	public void ConsignmentItem(Document doc, Element parentElement,
			String blnokey) {
		// TODO Auto-generated method stub
		return;
	}

	public void TransportEquipment(Document doc, Element parentElement,
			String blnokey) {
		// TODO Auto-generated method stub
		return;
	}

	public Document ManifestHead() {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		doc = db.newDocument();
		root = doc.createElement("Manifest");

		root.setAttribute("xmlns", new MessageDefine().getMessageDefine(this
				.getMessageDefine()));
		// root.setAttribute("xmlns:xsi",
		// "http://www.w3.org/2001/XMLSchema-instance");
		doc.appendChild(root);

		Element Head = doc.createElement("Head");// ͷ����
		root.appendChild(Head);

		addElement(doc, Head, "MessageID", this.getMessageID());// ���ı��
		addElement(doc, Head, "FunctionCode", this.getFunctionCode());// ���Ĺ��ܴ���

		addElement(doc, Head, "MessageType", getMessageType());// �������ʹ���
		addElement(doc, Head, "SenderID", getSenderID());// ���ͷ�����
		addElement(doc, Head, "ReceiverID", getReceiverID());// ���շ�����
		addElement(doc, Head, "SendTime", getSendTime());// ����ʱ��

		addElement(doc, Head, "Version", getVersion());// ���İ汾

		return doc;
	}

	public String getExtension() {
		// TODO Auto-generated method stub
		return ".xml";
	}

	/**
	 * ����EDI���ܴ���
	 */
	public String getFunctionCode() {
		// TODO Auto-generated method stub
		if (FunctionCode == null || FunctionCode.equals("")) {
			FunctionCode = "һ���걨";
		}

		return new FunctionCode().getFunctionCode(FunctionCode);
	}

	public void setFunctionCode(String FunctionCode) {
		// TODO Auto-generated method stub
		this.FunctionCode = FunctionCode;
	}

	public String getReceiverID() {
		// TODO Auto-generated method stub
		return ReceiverID;
	}

	public String getSenderID() {
		// TODO Auto-generated method stub
		return SenderID;
	}

	public void setReceiverID(String ReceiverID) {
		// TODO Auto-generated method stub
		this.ReceiverID = ReceiverID;
	}

	public void setSenderID(String SenderID) {
		// TODO Auto-generated method stub
		this.SenderID = SenderID;
	}

	public String getVersion() {
		// TODO Auto-generated method stub
		return Version;
	}

	public void setVersion(String Version) {
		// TODO Auto-generated method stub
		this.Version = Version;

	}

	public String getMessageDefine() {
		// TODO Auto-generated method stub
		// if (MessageDefine == null) MessageDefine ="����ԭʼ�յ�";
		return MessageDefine;
	}

	public void setMessageDefine(String MessageDefine) {
		// TODO Auto-generated method stub
		this.MessageDefine = MessageDefine;
	}

	public String getMessageType() {
		// TODO Auto-generated method stub
		this.MessageType = new DeclarationTypeCode()
				.getDeclarationTypeCode(getMessageDefine());
		return MessageType;
	}

	public void setMessageType(String MessageType) {
		// TODO Auto-generated method stub
		this.MessageType = MessageType;
	}

	public String getMessageID() {
		// TODO Auto-generated method stub
		return new MessageID().getMessageID(MessageID);
	}

	public void setMessageID(String MessageID) {
		// TODO Auto-generated method stub
		this.MessageID = MessageID;
	}

	public void setSendTime(String SendTime) {
		// TODO Auto-generated method stub
		this.SendTime = SendTime;
	}

	public String getSendTime() {
		return this.SendTime;
	}

	// ת�����������
	public String CodeConstrast() {
		return null;
	}

	// ����������
	public boolean ExecutePack() {
		if (Init() == false)
			return false;// ��ʼ��������
		if (Retrieve() == false)
			return false;// ��ȡҵ�����ݼ�����Ҫ�Ķ��ձ������Ϣ
		if (pack() == false)
			return false;// ���
		return true;
	}

	public boolean Init() {
		// TODO Auto-generated method stub
		BasvslvoyDAO basvslvoyDAO = new BasvslvoyDAO();
		// BasvvcarrDAO basvvcarrDAO = new BasvvcarrDAO();

		basvslvoy = basvslvoyDAO.findByMainkey(216984);
		if (basvslvoy != null) {
			this.setJourneyID(basvslvoy.getVoyage());
			this.setBorderTransportMeans(basvslvoy.getVslcname());

		}
		
		//��ȡ������
		 xmlmapping  = new XmlmappingDAO().getXmlmapping("",true);
		
		// basvvcarr
		return true;
	}

	public boolean Retrieve() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean pack() {
		// TODO Auto-generated method stub
		return true;
	}

	public DataStore getDataStore() {
		return dataStore;
	}

	public void setDataStore(DataStore dataStore) {
		this.dataStore = dataStore;
	}

	public Double getGrossVolumeMeasure() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setGrossVolumeMeasure(Double GrossVolumeMeasure) {
		// TODO Auto-generated method stub
		this.GrossVolumeMeasure = GrossVolumeMeasure;
	}

	public void setGrossVolumeMeasure(Set<Xmlbkcargo> xmlbkcargo) {
		// TODO Auto-generated method stub
		Double Measure = 0.00;
		if (xmlbkcargo == null) {
			Measure = 0.00;
		} else {

			for (Xmlbkcargo cargo : xmlbkcargo) {
				// Double tmp = cargo.get
			}

		}

		setGrossVolumeMeasure(Measure);

	}

	public Element nodeParty(Document doc, Element ParentElement,
			String nodeTitle, Xmlbkparty party) {
		ValuesCode = StringUtils.trimToEmpty(party.getPartyid());
		if (ValuesCode.length() > 0) {

			Element Consignee = addElement(doc, ParentElement, nodeTitle);// �ջ�����Ϣ
			addElement(doc, Consignee, "ID", ValuesCode);// �ջ��˴���

			ValuesName = StringUtils.trimToEmpty(party.getPartymsg());
			if (ValuesName.length() > 0)
				addElement(doc, Consignee, "Name", ValuesName);// �ջ�������
			Values = StringUtils.trimToEmpty(party.getPtline());
			if (Values.length() > 0) {
				Element Address = addElement(doc, Consignee, "Address"); // �ջ��˵�ַ��Ϣ
				addElement(doc, Address, "Line", Values);// ��ϸ��ַ

				Values = StringUtils.trimToEmpty(party.getPtcity());
				if (Values.length() > 0)
					addElement(doc, Address, "CityName", Values);// ��������
				Values = StringUtils.trimToEmpty(party.getPtentid());
				if (Values.length() > 0)
					addElement(doc, Address, "CountrySubEntityID", Values);// ʡ�ݴ���

				Values = StringUtils.trimToEmpty(party.getPtentname());
				if (Values.length() > 0)
					addElement(doc, Address, "CountrySubEntityName", Values);// ʡ������
				Values = StringUtils.trimToEmpty(party.getPtpostid());
				if (Values.length() > 0)
					addElement(doc, Address, "PostcodeID", Values);// ��������
				Values = StringUtils.trimToEmpty(party.getPtcountry());
				addElement(doc, Address, "CountryCode", Values);// ���Ҵ���
			}
			// 1-3
			Values = StringUtils.trimToEmpty(party.getPtctcm1());
			if (Values.length() > 0) {

				Element CCommunication = addElement(doc, Consignee,
						"Communication"); // �ջ�����ϵ��ʽ
				addElement(doc, CCommunication, "ID", Values);// �ջ�����ϵ����
				Values = StringUtils.trimToEmpty(party.getPtctty1());
				if (Values.length() > 0)
					addElement(doc, CCommunication, "TypeID", Values);// ͨѶ��ʽ������

			}
			Values = StringUtils.trimToEmpty(party.getPtctcm2());
			if (Values.length() > 0) {

				Element CCommunication = addElement(doc, Consignee,
						"Communication"); // �ջ�����ϵ��ʽ
				addElement(doc, CCommunication, "ID", Values);// �ջ�����ϵ����
				Values = StringUtils.trimToEmpty(party.getPtctty2());
				if (Values.length() > 0)
					addElement(doc, CCommunication, "TypeID", Values);// ͨѶ��ʽ������

			}
			Values = StringUtils.trimToEmpty(party.getPtctcm3());
			if (Values.length() > 0) {

				Element CCommunication = addElement(doc, Consignee,
						"Communication"); // �ջ�����ϵ��ʽ
				addElement(doc, CCommunication, "ID", Values);// �ջ�����ϵ����
				Values = StringUtils.trimToEmpty(party.getPtctty3());
				if (Values.length() > 0)
					addElement(doc, CCommunication, "TypeID", Values);// ͨѶ��ʽ������

			}
			//�ջ��˾�����ϵ��Ϣ
			if(nodeTitle.equals("CN")){
				Values = StringUtils.trimToEmpty(party.getContname());
				if (Values.length() > 0) {
					Element Contact = addElement(doc, Consignee, "Contact"); // �ջ�������ϵ����Ϣ
					addElement(doc, Contact, "Name", Values);// ������ϵ������

					// 1-3
					Values = StringUtils.trimToEmpty(party.getCtctcm1());
					if (Values.length() > 0) {
						Element ContactCommunication = addElement(doc, Contact,
								"Communication");// ������ϵ����ϵ��ʽ
						addElement(doc, ContactCommunication, "ID", Values);// ������ϵ����ϵ����
						Values = StringUtils.trimToEmpty(party.getCtctty1());
						if (Values.length() > 0)
							addElement(doc, ContactCommunication, "TypeID", Values);// ͨѶ��ʽ������
					}

					// 1-3
					Values = StringUtils.trimToEmpty(party.getCtctcm2());
					if (Values.length() > 0) {
						Element ContactCommunication = addElement(doc, Contact,
								"Communication");// ������ϵ����ϵ��ʽ
						addElement(doc, ContactCommunication, "ID", Values);// ������ϵ����ϵ����
						Values = StringUtils.trimToEmpty(party.getCtctty2());
						if (Values.length() > 0)
							addElement(doc, ContactCommunication, "TypeID", Values);// ͨѶ��ʽ������
					}

					// 1-3
					Values = StringUtils.trimToEmpty(party.getCtctcm3());
					if (Values.length() > 0) {
						Element ContactCommunication = addElement(doc, Contact,
								"Communication");// ������ϵ����ϵ��ʽ
						addElement(doc, ContactCommunication, "ID", Values);// ������ϵ����ϵ����
						Values = StringUtils.trimToEmpty(party.getCtctty3());
						if (Values.length() > 0)
							addElement(doc, ContactCommunication, "TypeID", Values);// ͨѶ��ʽ������
					}

				}
			}
			
			return Consignee;
		}
		return null;
	}

	/**
	 * EDI�ļ�Ŀ¼���·��
	 * 
	 * @return
	 */
	public String getEdiPath() {
		String path = new Activator().getPluginPath() + this.separator + "EDI";

		String month = new SimpleDateFormat("yyyy-MM").format(new Date());
		path = path + separator + month;// �·�
		path = path + separator + SystemId;// ϵͳ�ɣģ����ڻ��ǳ���
		path = path + separator + OperationType;// ���ͻ��ǽ���
		path = path + separator
				+ Context.getInstance().getCurrentUser().getName();// ��ǰ�û�
		path = path + separator + BorderTransportMeans + " " + JourneyID;// �����Ӻ���
		path = path + separator;
		File dir = (new File(path)).getAbsoluteFile();
		if (dir.exists() == false) {
			if (dir.mkdirs() == false)
				return "����Ŀ¼ʧ��";
		}
		return path;
	}

	

	public String getEdiFullName() {
		return EdiFullName;
	}

	public void setEdiFullName(String ediFullName) {
		EdiFullName = ediFullName;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getEdiFileName() {
		return EdiFileName;
	}

	public void setEdiFileName(String ediFileName) {
		EdiFileName = ediFileName;
	}
/**
 * 
 */
	public Xmlmapping getXmlmapping(String Type, String LocateCode) {
		// TODO Auto-generated method stub
		if(xmlmapping != null && Type != null && LocateCode != null){
			for(int i = 0 ;i<xmlmapping.size();i++){
				Xmlmapping tmp = (Xmlmapping)xmlmapping.get(i);
				if (tmp.getMaptpcode().equals(Type) && tmp.getLccode().equals(LocateCode)){
					return tmp;
				}
			}
		}
		return null;
	}

}
