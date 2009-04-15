package cosco.xml.service.packer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XmlPacker {
	public void builderTest(){
		/**
		 * 生成测试海关格式EDI
		 */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = null;
		doc = db.newDocument();
		Element root = doc.createElement("Manifest");
		root.setAttribute("xmlns", "urn:Declaration:datamodel:standard:CN:[Name]:1");
		root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		doc.appendChild(root);
		
		Element Head = doc.createElement("Head");//头部分
		root.appendChild(Head);
		addElement(doc,Head,"MessageID","");//报文编号
		addElement(doc,Head,"FunctionCode","");//报文功能代码
		addElement(doc,Head,"MessageType","");//报文类型代码
		addElement(doc,Head,"SenderID","");//发送方代码
		addElement(doc,Head,"ReceiverID","");//接收方代码
		addElement(doc,Head,"SendTime","");//发送时间
		addElement(doc,Head,"Version","");//报文版本
		
		Element Declaration = doc.createElement("Declaration");//报文体
		root.appendChild(Declaration);
		
		Element RepresentativePerson = addElement(doc,Declaration,"RepresentativePerson");//舱单传输人信息
		addElement(doc,RepresentativePerson,"Name","");//舱单传输人名称
		
		Element ExitCustomsOffice = addElement(doc,Declaration,"ExitCustomsOffice");//运输工具离境地海关信息
		addElement(doc,ExitCustomsOffice,"ID","");//运输工具离境地海关代码
		
		Element Agent = addElement(doc,Declaration,"Agent");//运输工具代理企业信息
		addElement(doc,Agent,"ID","");//运输工具代理企业代码
		
		Element Carrier = addElement(doc,Declaration,"Carrier");//承运人信息
		addElement(doc,Carrier,"ID","");//承运人代码
		
		Element BorderTransportMeans = addElement(doc,Declaration,"BorderTransportMeans");//运输工具信息
		addElement(doc,BorderTransportMeans,"JourneyID","");//航次航班编号
		addElement(doc,BorderTransportMeans,"TypeCode","");//运输方式代码
		addElement(doc,BorderTransportMeans,"ID","");//运输工具代码
		addElement(doc,BorderTransportMeans,"Name","");//运输工具名称
		addElement(doc,BorderTransportMeans,"FirstArrivalLocationID","");//运输工具抵达关境内第一个目的港代码
		addElement(doc,BorderTransportMeans,"ArrivalDateTime","");//运输工具抵达关境内第一个目的港的日期和时间
		addElement(doc,BorderTransportMeans,"DepartureDateTime","");//运输工具启运日期和时间
		
		//提单信息
		for (int i = 0; i <1; i++){
			Element Consignment = addElement(doc,Declaration,"Consignment");//一票提单的开始
			Element TransportContractDocument = addElement(doc,Consignment,"TransportContractDocument");//运输合同信息
			addElement(doc,TransportContractDocument,"ID","");//总提单号
			//1-3
			addElement(doc,TransportContractDocument,"ChangeReasonCode","");//更改原因代码
			addElement(doc,TransportContractDocument,"ConditionCode","");//运输条款代码
			Element Deconsolidator = addElement(doc,TransportContractDocument,"Deconsolidator");//拆箱信息
			addElement(doc,Deconsolidator,"ID","");//拆箱人代码
			
			
			Element AssociatedTransportDocument = addElement(doc,Consignment,"AssociatedTransportDocument");//运输合同附加信息
			addElement(doc,AssociatedTransportDocument,"ID","");//分提运单号
			
			addElement(doc,Consignment,"GrossVolumeMeasure", "");//货物体积
			Element ValueAmount = addElement(doc,Consignment,"ValueAmount");//托运货物价值
			addElement(doc,ValueAmount,"CurrencyTypeCode", "");//金额类型代码
			
			Element LoadingLocation = addElement(doc,Consignment,"LoadingLocation");//装货地信息
			addElement(doc,LoadingLocation,"ID", "");//装货地代码
			addElement(doc,LoadingLocation,"LoadingDate", "");//货物装载运输工具时间
			
			Element UnloadingLocation = addElement(doc,Consignment,"UnloadingLocation");//卸货地信息
			addElement(doc,UnloadingLocation,"ID", "");//卸货地代码
			addElement(doc,UnloadingLocation,"ArrivalDate", "");//到达卸货地日期
			
			Element GoodsReceiptPlace = addElement(doc,Consignment,"GoodsReceiptPlace");//收货地信息
			addElement(doc,GoodsReceiptPlace,"ID", "");//收货地点代码
			addElement(doc,GoodsReceiptPlace,"NAME", "");//收货地点名称
			
			Element TranshipmentLocation = addElement(doc,Consignment,"TranshipmentLocation");//中转启运地信息
			addElement(doc,TranshipmentLocation,"ID", "");//中转启运地点代码
	
			Element TransitDestination = addElement(doc,Consignment,"TransitDestination");//中转目的地信息
			addElement(doc,TransitDestination,"ID", "");//中转目的地点代码
			
			Element RoutingCountryCode = addElement(doc,Consignment,"RoutingCountryCode","");//途经的国家代码
			
			Element GoodsConsignedPlace = addElement(doc,Consignment,"GoodsConsignedPlace");//货物托运地信息
			addElement(doc,GoodsConsignedPlace,"ID", "");//货物托运的地点或者国家代码
			
			Element CustomsStatusCode = addElement(doc,Consignment,"CustomsStatusCode","");//货物海关状态代码
			Element TransportSplitIndicator = addElement(doc,Consignment,"TransportSplitIndicator","");//承运人货物分批到/发货标识
			
			Element FreightPayment = addElement(doc,Consignment,"FreightPayment");//运费支付信息
			addElement(doc,FreightPayment,"MethodCode", "");//运费支付方代码
			
			Element ConsignmentPackaging = addElement(doc,Consignment,"ConsignmentPackaging");//提运单包装信息
			addElement(doc,ConsignmentPackaging,"QuantityQuantity", "");//托运货物件数
			addElement(doc,ConsignmentPackaging,"TypeCode", "");//包装种类代码
			
			Element TotalGrossMassMeasure = addElement(doc,Consignment,"TotalGrossMassMeasure","");//货物总毛重
			
			Element PreviousCustomsDocument= addElement(doc,Consignment,"PreviousCustomsDocument");//前一海关单证信息
			addElement(doc,PreviousCustomsDocument,"ID", "");//前一海关单证号
			addElement(doc,PreviousCustomsDocument,"TypeCode", "");//前一海关单证类型代码
			
			Element DeliveryDestination= addElement(doc,Consignment,"DeliveryDestination");//货物交付信息
			addElement(doc,DeliveryDestination,"Line", "");//货物交付目的地地址
			addElement(doc,DeliveryDestination,"CityName", "");//城市名称
			addElement(doc,DeliveryDestination,"CountrySubEntityID", "");//省份代码
			addElement(doc,DeliveryDestination,"CountrySubEntityName", "");//省份名称
			addElement(doc,DeliveryDestination,"PostcodeID", "");//邮政编码
			addElement(doc,DeliveryDestination,"CountryCode", "");//国家代码
			
			Element Handling= addElement(doc,Consignment,"Handling");//码头作业信息
			addElement(doc,Handling,"InstructionsCodeCode", "");//码头作业指令代码
			
			Element IntermediateCarrier= addElement(doc,Consignment,"IntermediateCarrier");//中间承运人信息
			addElement(doc,IntermediateCarrier,"ID", "");//中间承运人标识
			//1-3
			Element Communication = addElement(doc,IntermediateCarrier,"Communication");//中间承运人联系方式
			addElement(doc,Communication,"ID", "");//中间承运人联系号码
			addElement(doc,Communication,"TypeID", "");//通讯方式类别代码
			
			Element Consignee = addElement(doc,Consignment,"Consignee");//收货人信息
			addElement(doc,Consignee,"ID", "");//收货人代码
			addElement(doc,Consignee,"Name", "");//收货人名称
			
			Element Address = addElement(doc,Consignee,"Address"); //收货人地址信息
			addElement(doc,Address,"Line", "");//详细地址
			addElement(doc,Address,"CityName", "");//城市名称
			addElement(doc,Address,"CountrySubEntityID", "");//省份代码
			addElement(doc,Address,"CountrySubEntityName", "");//省份名称
			addElement(doc,Address,"PostcodeID", "");//邮政编码
			addElement(doc,Address,"CountryCode", "");//国家代码
			//1-3
			Element CCommunication = addElement(doc,Consignee,"Communication"); //收货人联系方式
			addElement(doc,CCommunication,"ID", "");//收货人联系号码
			addElement(doc,CCommunication,"TypeID", "");//通讯方式类别代码
			
			Element Contact = addElement(doc,Consignee,"Contact"); //收货具体联系人信息
			addElement(doc,Contact,"Name", "");//具体联系人名称
			//1-3
			Element ContactCommunication = addElement(doc,Contact,"Communication");//具体联系人联系方式
			addElement(doc,ContactCommunication,"ID", "");//具体联系人联系号码
			addElement(doc,ContactCommunication,"TypeID", "");//通讯方式类别代码
			
			Element Consignor = addElement(doc,Consignee,"Consignor"); //发货人信息
			addElement(doc,Consignor,"ID", "");//发货人代码
			addElement(doc,Consignor,"Name", "");//发货人名称
			Element ConsignorAddress = addElement(doc,Consignor,"Address"); //发货人地址信息
			addElement(doc,ConsignorAddress,"Line", "");//详细地址
			addElement(doc,ConsignorAddress,"CityName", "");//城市名称
			addElement(doc,ConsignorAddress,"CountrySubEntityID", "");//省份代码
			addElement(doc,ConsignorAddress,"CountrySubEntityName", "");//省份名称
			addElement(doc,ConsignorAddress,"PostcodeID", "");//邮政编码
			addElement(doc,ConsignorAddress,"CountryCode", "");//国家代码
			
			Element ConsignorCommunication = addElement(doc,Consignor,"Communication"); //发货人联系方式
			//1-3
			addElement(doc,ConsignorCommunication,"ID", "");//联系号码
			addElement(doc,ConsignorCommunication,"TypeID", "");//通讯方式类别代码
			
			//
			Element NotifyParty = addElement(doc,Consignee,"Consignor"); //通知人信息
			addElement(doc,NotifyParty,"ID", "");//通知人代码
			addElement(doc,NotifyParty,"Name", "");//（通知人名称
			Element NotifyPartyAddress = addElement(doc,Consignor,"Address"); //通知人地址
			addElement(doc,NotifyPartyAddress,"Line", "");//详细地址
			addElement(doc,NotifyPartyAddress,"CityName", "");//城市名称
			addElement(doc,NotifyPartyAddress,"CountrySubEntityID", "");//省份代码
			addElement(doc,NotifyPartyAddress,"CountrySubEntityName", "");//省份名称
			addElement(doc,NotifyPartyAddress,"PostcodeID", "");//邮政编码
			addElement(doc,NotifyPartyAddress,"CountryCode", "");//国家代码
			//1-3
			Element NotifyPartyCommunication = addElement(doc,NotifyParty,"Communication"); //通知人联系方式
			addElement(doc,NotifyPartyCommunication,"ID", "");//联系号码
			addElement(doc,NotifyPartyCommunication,"TypeID", "");//通讯方式类别代码
			
			Element  UNDGContact = addElement(doc,Consignee,"UNDGContact"); //危险品联系人信息
			addElement(doc,UNDGContact,"Name", "");//危险品联系人姓名
			//1-3
			Element UNDGContactCommunication = addElement(doc,UNDGContact,"Communication");//危险品联系方式
			addElement(doc,UNDGContactCommunication,"ID", "");//联系号码
			addElement(doc,UNDGContactCommunication,"TypeID", "");//联系方式类别代码
			
			//添加集装箱信息
			Element  TransportEquipment = addElement(doc,Consignee,"TransportEquipment"); 
			TransportEquipment(doc,TransportEquipment,"");
			
			//添加货物信息
			Element  ConsignmentItem = addElement(doc,Consignee,"ConsignmentItem"); 
			ConsignmentItem(doc, ConsignmentItem, "");
			//2008-12-05
			Element  AdditionalInformation = addElement(doc,Consignee,"AdditionalInformation");//备注 
			addElement(doc,AdditionalInformation,"Content", "");
			
		}
		
		try {
			FileOutputStream outStream = new FileOutputStream("xml.xml");
			OutputStreamWriter outWriter = new OutputStreamWriter(outStream);
			
			((XmlDocument)doc).write(outWriter,"UTF-8");
			outWriter.close();
			outStream.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 集装箱信息
	 * @param doc
	 * @param parentElement
	 * @param blnokey 提单主键
	 */
	public void TransportEquipment(Document doc, Element parentElement,String blnokey){
		//n-9999
		Element  EquipmentIdentification = addElement(doc,parentElement,"EquipmentIdentification");//集装箱编号信息
		addElement(doc,EquipmentIdentification,"ID", "");//集装箱编号
		
		addElement(doc,parentElement,"CharacteristicCode","");//集装箱尺寸和类型
		addElement(doc,parentElement,"SupplierPartyTypeCode","");//集装箱来源代码
		addElement(doc,parentElement,"FullnessCode","");//重箱或者空箱标识代码
		//n-9
		Element  SealID = addElement(doc,parentElement,"FullnessCode");//封志号码、类型和施加封志人
		addElement(doc,SealID,"AgencyCode","");//施加封志人
	}
	/**
	 * 托运货物详细信息
	 * @param doc
	 * @param parentElement
	 * @param blnokey提单主键
	 */
	public void ConsignmentItem(Document doc, Element parentElement,String blnokey){
		//n-9999
		addElement(doc,parentElement,"SequenceNumeric","");//托运货物序号
		Element ConsignmentItemPackaging = addElement(doc,parentElement,"ConsignmentItemPackaging");//托运货物详细包装信息
		addElement(doc,ConsignmentItemPackaging,"QuantityQuantity","");//货物详细件数
		addElement(doc,ConsignmentItemPackaging,"TypeCode","");//包装种类代码
		addElement(doc,ConsignmentItemPackaging,"MarksNumbers","");//唛头
		
		Element Commodity = addElement(doc,parentElement,"Commodity");//货物简要信息
		addElement(doc,Commodity,"CargoDescription","");//货物简要描述
		addElement(doc,Commodity,"UNDGCode","");//危险品编号
		addElement(doc,Commodity,"TariffClassificationCode","");//海关税则编号
		
		Element AdditionalInformation = addElement(doc,parentElement,"AdditionalInformation");//补充信息
		addElement(doc,AdditionalInformation,"Content","");//货物描述补充信息
		
		Element GoodsMeasure = addElement(doc,parentElement,"GoodsMeasure");//货物毛重
		addElement(doc,GoodsMeasure,"GrossMassMeasure","");//货物毛重
		//n-9999
		Element  EquipmentIdentification = addElement(doc,parentElement,"EquipmentIdentification");//集装箱编号信息
		addElement(doc,EquipmentIdentification,"ID","");//集装箱编号
		
		Element  CustomsProcedure = addElement(doc,parentElement,"CustomsProcedure");//海关手续信息
		addElement(doc,CustomsProcedure,"CurrentCode","");//海关手续代码
		//n-99
		Element  UCR = addElement(doc,parentElement,"UCR");//唯一托运信息
		addElement(doc,UCR,"ID","");//唯一托运编号
		
		Element  Origin = addElement(doc,parentElement,"Origin");//原产国信息
		addElement(doc,Origin,"OriginCountryCode","");//原产国代码
		
	}
	
	public Element addElement(Document doc, Element parentElement, String nodeTitle){
		Element element = doc.createElement(nodeTitle);
		parentElement.appendChild(element);
		return element;
	}
	public Element addElement(Document doc, Element parentElement, String nodeTitle, String nodeValue){
		Element element = doc.createElement(nodeTitle);
		parentElement.appendChild(element);
		Text text = doc.createTextNode(nodeValue);
		element.appendChild(text);
		return element;
	}
	
	public static void main(String argv[]){
		XmlPacker tt = new XmlPacker();
		tt.builderTest();
	}

}
