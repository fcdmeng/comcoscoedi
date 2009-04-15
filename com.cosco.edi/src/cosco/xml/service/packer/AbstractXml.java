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
	private String BorderTransportMeans = "";// 船名
	private String JourneyID = "";// 航次

	private String FunctionCode;// 功能代码
	private String SenderID = "2000102";// 发送方代码
	private String ReceiverID = "CNCustoms";// 接收方代码
	private String Version = "1.0";// 报文版本
	private String MessageDefine;// 报文定义
	private String MessageType;// 报文类型
	private String MessageID = "ChinaInboundMainData";// 报文编号
	private String SendTime;// 报文发送时间
	private String OperationType = "SEND";// 发送还是接收
	private String SystemId = "ICD";// 系统标示
	private String separator = System.getProperty("file.separator");
	
	private String EdiFullName;// EDI生成的文件全名，包括路径
	private String EdiFileName;//文件名，仅名称
	DataStore dataStore = new DataStore();// 当前要生成的EDI
	List<Xmlmapping> xmlmapping;//对照码表
	// dataStore//提单信息表
	Basvslvoy basvslvoy;// 船名航次信息
	Basvvcarr basvvcarr;// 承运人信息
	String Values = null;// 临时用的值
	String ValuesCode;
	String ValuesName;
	Xmlbooking xmlbooking;// 提单表
	Xmlbkcargo xmlcargo;// 货物表
	Xmlbkcntr xmlcntr;// 箱信息
	Xmlbkparty xmlparty;// 参与方信息

	private Double GrossVolumeMeasure;// 货物体积

	Document doc = null;
	Element root = null;

//	public AbstractXml() {
		/*setMessageDefine("海运原始舱单");
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		this.setSendTime(tempDate.format(new java.util.Date()));*/
//		AbstractXml("海运原始舱单");
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
			
			// 构建文件存放路径
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
		 * 海关报文命名规则如下： CN_[name] _1p0_[sender_code]_[send_time].xml CN代表中国海关，
		 * [sender_code]代表发送方代码， [send_time]代表发送时间，精确到毫秒，采用yyyyMMddhhmmssfff格式，
		 * “1p0”中的“1”代表报文格式的主版本号、“0”代表副版本号， 具体[name]名称详见《中国海关进出境舱单报文类型编码表》
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

		Element Head = doc.createElement("Head");// 头部分
		root.appendChild(Head);

		addElement(doc, Head, "MessageID", this.getMessageID());// 报文编号
		addElement(doc, Head, "FunctionCode", this.getFunctionCode());// 报文功能代码

		addElement(doc, Head, "MessageType", getMessageType());// 报文类型代码
		addElement(doc, Head, "SenderID", getSenderID());// 发送方代码
		addElement(doc, Head, "ReceiverID", getReceiverID());// 接收方代码
		addElement(doc, Head, "SendTime", getSendTime());// 发送时间

		addElement(doc, Head, "Version", getVersion());// 报文版本

		return doc;
	}

	public String getExtension() {
		// TODO Auto-generated method stub
		return ".xml";
	}

	/**
	 * 生成EDI功能代码
	 */
	public String getFunctionCode() {
		// TODO Auto-generated method stub
		if (FunctionCode == null || FunctionCode.equals("")) {
			FunctionCode = "一次申报";
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
		// if (MessageDefine == null) MessageDefine ="海运原始舱单";
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

	// 转换对照码操作
	public String CodeConstrast() {
		return null;
	}

	// 打包主体对象
	public boolean ExecutePack() {
		if (Init() == false)
			return false;// 初始化参数表
		if (Retrieve() == false)
			return false;// 读取业务数据及所需要的对照表具体信息
		if (pack() == false)
			return false;// 打包
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
		
		//读取对照码
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

			Element Consignee = addElement(doc, ParentElement, nodeTitle);// 收货人信息
			addElement(doc, Consignee, "ID", ValuesCode);// 收货人代码

			ValuesName = StringUtils.trimToEmpty(party.getPartymsg());
			if (ValuesName.length() > 0)
				addElement(doc, Consignee, "Name", ValuesName);// 收货人名称
			Values = StringUtils.trimToEmpty(party.getPtline());
			if (Values.length() > 0) {
				Element Address = addElement(doc, Consignee, "Address"); // 收货人地址信息
				addElement(doc, Address, "Line", Values);// 详细地址

				Values = StringUtils.trimToEmpty(party.getPtcity());
				if (Values.length() > 0)
					addElement(doc, Address, "CityName", Values);// 城市名称
				Values = StringUtils.trimToEmpty(party.getPtentid());
				if (Values.length() > 0)
					addElement(doc, Address, "CountrySubEntityID", Values);// 省份代码

				Values = StringUtils.trimToEmpty(party.getPtentname());
				if (Values.length() > 0)
					addElement(doc, Address, "CountrySubEntityName", Values);// 省份名称
				Values = StringUtils.trimToEmpty(party.getPtpostid());
				if (Values.length() > 0)
					addElement(doc, Address, "PostcodeID", Values);// 邮政编码
				Values = StringUtils.trimToEmpty(party.getPtcountry());
				addElement(doc, Address, "CountryCode", Values);// 国家代码
			}
			// 1-3
			Values = StringUtils.trimToEmpty(party.getPtctcm1());
			if (Values.length() > 0) {

				Element CCommunication = addElement(doc, Consignee,
						"Communication"); // 收货人联系方式
				addElement(doc, CCommunication, "ID", Values);// 收货人联系号码
				Values = StringUtils.trimToEmpty(party.getPtctty1());
				if (Values.length() > 0)
					addElement(doc, CCommunication, "TypeID", Values);// 通讯方式类别代码

			}
			Values = StringUtils.trimToEmpty(party.getPtctcm2());
			if (Values.length() > 0) {

				Element CCommunication = addElement(doc, Consignee,
						"Communication"); // 收货人联系方式
				addElement(doc, CCommunication, "ID", Values);// 收货人联系号码
				Values = StringUtils.trimToEmpty(party.getPtctty2());
				if (Values.length() > 0)
					addElement(doc, CCommunication, "TypeID", Values);// 通讯方式类别代码

			}
			Values = StringUtils.trimToEmpty(party.getPtctcm3());
			if (Values.length() > 0) {

				Element CCommunication = addElement(doc, Consignee,
						"Communication"); // 收货人联系方式
				addElement(doc, CCommunication, "ID", Values);// 收货人联系号码
				Values = StringUtils.trimToEmpty(party.getPtctty3());
				if (Values.length() > 0)
					addElement(doc, CCommunication, "TypeID", Values);// 通讯方式类别代码

			}
			//收货人具体联系信息
			if(nodeTitle.equals("CN")){
				Values = StringUtils.trimToEmpty(party.getContname());
				if (Values.length() > 0) {
					Element Contact = addElement(doc, Consignee, "Contact"); // 收货具体联系人信息
					addElement(doc, Contact, "Name", Values);// 具体联系人名称

					// 1-3
					Values = StringUtils.trimToEmpty(party.getCtctcm1());
					if (Values.length() > 0) {
						Element ContactCommunication = addElement(doc, Contact,
								"Communication");// 具体联系人联系方式
						addElement(doc, ContactCommunication, "ID", Values);// 具体联系人联系号码
						Values = StringUtils.trimToEmpty(party.getCtctty1());
						if (Values.length() > 0)
							addElement(doc, ContactCommunication, "TypeID", Values);// 通讯方式类别代码
					}

					// 1-3
					Values = StringUtils.trimToEmpty(party.getCtctcm2());
					if (Values.length() > 0) {
						Element ContactCommunication = addElement(doc, Contact,
								"Communication");// 具体联系人联系方式
						addElement(doc, ContactCommunication, "ID", Values);// 具体联系人联系号码
						Values = StringUtils.trimToEmpty(party.getCtctty2());
						if (Values.length() > 0)
							addElement(doc, ContactCommunication, "TypeID", Values);// 通讯方式类别代码
					}

					// 1-3
					Values = StringUtils.trimToEmpty(party.getCtctcm3());
					if (Values.length() > 0) {
						Element ContactCommunication = addElement(doc, Contact,
								"Communication");// 具体联系人联系方式
						addElement(doc, ContactCommunication, "ID", Values);// 具体联系人联系号码
						Values = StringUtils.trimToEmpty(party.getCtctty3());
						if (Values.length() > 0)
							addElement(doc, ContactCommunication, "TypeID", Values);// 通讯方式类别代码
					}

				}
			}
			
			return Consignee;
		}
		return null;
	}

	/**
	 * EDI文件目录存放路径
	 * 
	 * @return
	 */
	public String getEdiPath() {
		String path = new Activator().getPluginPath() + this.separator + "EDI";

		String month = new SimpleDateFormat("yyyy-MM").format(new Date());
		path = path + separator + month;// 月份
		path = path + separator + SystemId;// 系统ＩＤ，进口还是出口
		path = path + separator + OperationType;// 发送还是接收
		path = path + separator
				+ Context.getInstance().getCurrentUser().getName();// 当前用户
		path = path + separator + BorderTransportMeans + " " + JourneyID;// 船名加航次
		path = path + separator;
		File dir = (new File(path)).getAbsoluteFile();
		if (dir.exists() == false) {
			if (dir.mkdirs() == false)
				return "创建目录失败";
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
