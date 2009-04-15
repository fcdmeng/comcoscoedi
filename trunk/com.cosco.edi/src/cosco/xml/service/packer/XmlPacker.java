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
		 * ���ɲ��Ժ��ظ�ʽEDI
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
		
		Element Head = doc.createElement("Head");//ͷ����
		root.appendChild(Head);
		addElement(doc,Head,"MessageID","");//���ı��
		addElement(doc,Head,"FunctionCode","");//���Ĺ��ܴ���
		addElement(doc,Head,"MessageType","");//�������ʹ���
		addElement(doc,Head,"SenderID","");//���ͷ�����
		addElement(doc,Head,"ReceiverID","");//���շ�����
		addElement(doc,Head,"SendTime","");//����ʱ��
		addElement(doc,Head,"Version","");//���İ汾
		
		Element Declaration = doc.createElement("Declaration");//������
		root.appendChild(Declaration);
		
		Element RepresentativePerson = addElement(doc,Declaration,"RepresentativePerson");//�յ���������Ϣ
		addElement(doc,RepresentativePerson,"Name","");//�յ�����������
		
		Element ExitCustomsOffice = addElement(doc,Declaration,"ExitCustomsOffice");//���乤���뾳�غ�����Ϣ
		addElement(doc,ExitCustomsOffice,"ID","");//���乤���뾳�غ��ش���
		
		Element Agent = addElement(doc,Declaration,"Agent");//���乤�ߴ�����ҵ��Ϣ
		addElement(doc,Agent,"ID","");//���乤�ߴ�����ҵ����
		
		Element Carrier = addElement(doc,Declaration,"Carrier");//��������Ϣ
		addElement(doc,Carrier,"ID","");//�����˴���
		
		Element BorderTransportMeans = addElement(doc,Declaration,"BorderTransportMeans");//���乤����Ϣ
		addElement(doc,BorderTransportMeans,"JourneyID","");//���κ�����
		addElement(doc,BorderTransportMeans,"TypeCode","");//���䷽ʽ����
		addElement(doc,BorderTransportMeans,"ID","");//���乤�ߴ���
		addElement(doc,BorderTransportMeans,"Name","");//���乤������
		addElement(doc,BorderTransportMeans,"FirstArrivalLocationID","");//���乤�ߵִ�ؾ��ڵ�һ��Ŀ�ĸ۴���
		addElement(doc,BorderTransportMeans,"ArrivalDateTime","");//���乤�ߵִ�ؾ��ڵ�һ��Ŀ�ĸ۵����ں�ʱ��
		addElement(doc,BorderTransportMeans,"DepartureDateTime","");//���乤���������ں�ʱ��
		
		//�ᵥ��Ϣ
		for (int i = 0; i <1; i++){
			Element Consignment = addElement(doc,Declaration,"Consignment");//һƱ�ᵥ�Ŀ�ʼ
			Element TransportContractDocument = addElement(doc,Consignment,"TransportContractDocument");//�����ͬ��Ϣ
			addElement(doc,TransportContractDocument,"ID","");//���ᵥ��
			//1-3
			addElement(doc,TransportContractDocument,"ChangeReasonCode","");//����ԭ�����
			addElement(doc,TransportContractDocument,"ConditionCode","");//�����������
			Element Deconsolidator = addElement(doc,TransportContractDocument,"Deconsolidator");//������Ϣ
			addElement(doc,Deconsolidator,"ID","");//�����˴���
			
			
			Element AssociatedTransportDocument = addElement(doc,Consignment,"AssociatedTransportDocument");//�����ͬ������Ϣ
			addElement(doc,AssociatedTransportDocument,"ID","");//�����˵���
			
			addElement(doc,Consignment,"GrossVolumeMeasure", "");//�������
			Element ValueAmount = addElement(doc,Consignment,"ValueAmount");//���˻����ֵ
			addElement(doc,ValueAmount,"CurrencyTypeCode", "");//������ʹ���
			
			Element LoadingLocation = addElement(doc,Consignment,"LoadingLocation");//װ������Ϣ
			addElement(doc,LoadingLocation,"ID", "");//װ���ش���
			addElement(doc,LoadingLocation,"LoadingDate", "");//����װ�����乤��ʱ��
			
			Element UnloadingLocation = addElement(doc,Consignment,"UnloadingLocation");//ж������Ϣ
			addElement(doc,UnloadingLocation,"ID", "");//ж���ش���
			addElement(doc,UnloadingLocation,"ArrivalDate", "");//����ж��������
			
			Element GoodsReceiptPlace = addElement(doc,Consignment,"GoodsReceiptPlace");//�ջ�����Ϣ
			addElement(doc,GoodsReceiptPlace,"ID", "");//�ջ��ص����
			addElement(doc,GoodsReceiptPlace,"NAME", "");//�ջ��ص�����
			
			Element TranshipmentLocation = addElement(doc,Consignment,"TranshipmentLocation");//��ת���˵���Ϣ
			addElement(doc,TranshipmentLocation,"ID", "");//��ת���˵ص����
	
			Element TransitDestination = addElement(doc,Consignment,"TransitDestination");//��תĿ�ĵ���Ϣ
			addElement(doc,TransitDestination,"ID", "");//��תĿ�ĵص����
			
			Element RoutingCountryCode = addElement(doc,Consignment,"RoutingCountryCode","");//;���Ĺ��Ҵ���
			
			Element GoodsConsignedPlace = addElement(doc,Consignment,"GoodsConsignedPlace");//�������˵���Ϣ
			addElement(doc,GoodsConsignedPlace,"ID", "");//�������˵ĵص���߹��Ҵ���
			
			Element CustomsStatusCode = addElement(doc,Consignment,"CustomsStatusCode","");//���ﺣ��״̬����
			Element TransportSplitIndicator = addElement(doc,Consignment,"TransportSplitIndicator","");//�����˻��������/������ʶ
			
			Element FreightPayment = addElement(doc,Consignment,"FreightPayment");//�˷�֧����Ϣ
			addElement(doc,FreightPayment,"MethodCode", "");//�˷�֧��������
			
			Element ConsignmentPackaging = addElement(doc,Consignment,"ConsignmentPackaging");//���˵���װ��Ϣ
			addElement(doc,ConsignmentPackaging,"QuantityQuantity", "");//���˻������
			addElement(doc,ConsignmentPackaging,"TypeCode", "");//��װ�������
			
			Element TotalGrossMassMeasure = addElement(doc,Consignment,"TotalGrossMassMeasure","");//������ë��
			
			Element PreviousCustomsDocument= addElement(doc,Consignment,"PreviousCustomsDocument");//ǰһ���ص�֤��Ϣ
			addElement(doc,PreviousCustomsDocument,"ID", "");//ǰһ���ص�֤��
			addElement(doc,PreviousCustomsDocument,"TypeCode", "");//ǰһ���ص�֤���ʹ���
			
			Element DeliveryDestination= addElement(doc,Consignment,"DeliveryDestination");//���ｻ����Ϣ
			addElement(doc,DeliveryDestination,"Line", "");//���ｻ��Ŀ�ĵص�ַ
			addElement(doc,DeliveryDestination,"CityName", "");//��������
			addElement(doc,DeliveryDestination,"CountrySubEntityID", "");//ʡ�ݴ���
			addElement(doc,DeliveryDestination,"CountrySubEntityName", "");//ʡ������
			addElement(doc,DeliveryDestination,"PostcodeID", "");//��������
			addElement(doc,DeliveryDestination,"CountryCode", "");//���Ҵ���
			
			Element Handling= addElement(doc,Consignment,"Handling");//��ͷ��ҵ��Ϣ
			addElement(doc,Handling,"InstructionsCodeCode", "");//��ͷ��ҵָ�����
			
			Element IntermediateCarrier= addElement(doc,Consignment,"IntermediateCarrier");//�м��������Ϣ
			addElement(doc,IntermediateCarrier,"ID", "");//�м�����˱�ʶ
			//1-3
			Element Communication = addElement(doc,IntermediateCarrier,"Communication");//�м��������ϵ��ʽ
			addElement(doc,Communication,"ID", "");//�м��������ϵ����
			addElement(doc,Communication,"TypeID", "");//ͨѶ��ʽ������
			
			Element Consignee = addElement(doc,Consignment,"Consignee");//�ջ�����Ϣ
			addElement(doc,Consignee,"ID", "");//�ջ��˴���
			addElement(doc,Consignee,"Name", "");//�ջ�������
			
			Element Address = addElement(doc,Consignee,"Address"); //�ջ��˵�ַ��Ϣ
			addElement(doc,Address,"Line", "");//��ϸ��ַ
			addElement(doc,Address,"CityName", "");//��������
			addElement(doc,Address,"CountrySubEntityID", "");//ʡ�ݴ���
			addElement(doc,Address,"CountrySubEntityName", "");//ʡ������
			addElement(doc,Address,"PostcodeID", "");//��������
			addElement(doc,Address,"CountryCode", "");//���Ҵ���
			//1-3
			Element CCommunication = addElement(doc,Consignee,"Communication"); //�ջ�����ϵ��ʽ
			addElement(doc,CCommunication,"ID", "");//�ջ�����ϵ����
			addElement(doc,CCommunication,"TypeID", "");//ͨѶ��ʽ������
			
			Element Contact = addElement(doc,Consignee,"Contact"); //�ջ�������ϵ����Ϣ
			addElement(doc,Contact,"Name", "");//������ϵ������
			//1-3
			Element ContactCommunication = addElement(doc,Contact,"Communication");//������ϵ����ϵ��ʽ
			addElement(doc,ContactCommunication,"ID", "");//������ϵ����ϵ����
			addElement(doc,ContactCommunication,"TypeID", "");//ͨѶ��ʽ������
			
			Element Consignor = addElement(doc,Consignee,"Consignor"); //��������Ϣ
			addElement(doc,Consignor,"ID", "");//�����˴���
			addElement(doc,Consignor,"Name", "");//����������
			Element ConsignorAddress = addElement(doc,Consignor,"Address"); //�����˵�ַ��Ϣ
			addElement(doc,ConsignorAddress,"Line", "");//��ϸ��ַ
			addElement(doc,ConsignorAddress,"CityName", "");//��������
			addElement(doc,ConsignorAddress,"CountrySubEntityID", "");//ʡ�ݴ���
			addElement(doc,ConsignorAddress,"CountrySubEntityName", "");//ʡ������
			addElement(doc,ConsignorAddress,"PostcodeID", "");//��������
			addElement(doc,ConsignorAddress,"CountryCode", "");//���Ҵ���
			
			Element ConsignorCommunication = addElement(doc,Consignor,"Communication"); //��������ϵ��ʽ
			//1-3
			addElement(doc,ConsignorCommunication,"ID", "");//��ϵ����
			addElement(doc,ConsignorCommunication,"TypeID", "");//ͨѶ��ʽ������
			
			//
			Element NotifyParty = addElement(doc,Consignee,"Consignor"); //֪ͨ����Ϣ
			addElement(doc,NotifyParty,"ID", "");//֪ͨ�˴���
			addElement(doc,NotifyParty,"Name", "");//��֪ͨ������
			Element NotifyPartyAddress = addElement(doc,Consignor,"Address"); //֪ͨ�˵�ַ
			addElement(doc,NotifyPartyAddress,"Line", "");//��ϸ��ַ
			addElement(doc,NotifyPartyAddress,"CityName", "");//��������
			addElement(doc,NotifyPartyAddress,"CountrySubEntityID", "");//ʡ�ݴ���
			addElement(doc,NotifyPartyAddress,"CountrySubEntityName", "");//ʡ������
			addElement(doc,NotifyPartyAddress,"PostcodeID", "");//��������
			addElement(doc,NotifyPartyAddress,"CountryCode", "");//���Ҵ���
			//1-3
			Element NotifyPartyCommunication = addElement(doc,NotifyParty,"Communication"); //֪ͨ����ϵ��ʽ
			addElement(doc,NotifyPartyCommunication,"ID", "");//��ϵ����
			addElement(doc,NotifyPartyCommunication,"TypeID", "");//ͨѶ��ʽ������
			
			Element  UNDGContact = addElement(doc,Consignee,"UNDGContact"); //Σ��Ʒ��ϵ����Ϣ
			addElement(doc,UNDGContact,"Name", "");//Σ��Ʒ��ϵ������
			//1-3
			Element UNDGContactCommunication = addElement(doc,UNDGContact,"Communication");//Σ��Ʒ��ϵ��ʽ
			addElement(doc,UNDGContactCommunication,"ID", "");//��ϵ����
			addElement(doc,UNDGContactCommunication,"TypeID", "");//��ϵ��ʽ������
			
			//��Ӽ�װ����Ϣ
			Element  TransportEquipment = addElement(doc,Consignee,"TransportEquipment"); 
			TransportEquipment(doc,TransportEquipment,"");
			
			//��ӻ�����Ϣ
			Element  ConsignmentItem = addElement(doc,Consignee,"ConsignmentItem"); 
			ConsignmentItem(doc, ConsignmentItem, "");
			//2008-12-05
			Element  AdditionalInformation = addElement(doc,Consignee,"AdditionalInformation");//��ע 
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
	 * ��װ����Ϣ
	 * @param doc
	 * @param parentElement
	 * @param blnokey �ᵥ����
	 */
	public void TransportEquipment(Document doc, Element parentElement,String blnokey){
		//n-9999
		Element  EquipmentIdentification = addElement(doc,parentElement,"EquipmentIdentification");//��װ������Ϣ
		addElement(doc,EquipmentIdentification,"ID", "");//��װ����
		
		addElement(doc,parentElement,"CharacteristicCode","");//��װ��ߴ������
		addElement(doc,parentElement,"SupplierPartyTypeCode","");//��װ����Դ����
		addElement(doc,parentElement,"FullnessCode","");//������߿����ʶ����
		//n-9
		Element  SealID = addElement(doc,parentElement,"FullnessCode");//��־���롢���ͺ�ʩ�ӷ�־��
		addElement(doc,SealID,"AgencyCode","");//ʩ�ӷ�־��
	}
	/**
	 * ���˻�����ϸ��Ϣ
	 * @param doc
	 * @param parentElement
	 * @param blnokey�ᵥ����
	 */
	public void ConsignmentItem(Document doc, Element parentElement,String blnokey){
		//n-9999
		addElement(doc,parentElement,"SequenceNumeric","");//���˻������
		Element ConsignmentItemPackaging = addElement(doc,parentElement,"ConsignmentItemPackaging");//���˻�����ϸ��װ��Ϣ
		addElement(doc,ConsignmentItemPackaging,"QuantityQuantity","");//������ϸ����
		addElement(doc,ConsignmentItemPackaging,"TypeCode","");//��װ�������
		addElement(doc,ConsignmentItemPackaging,"MarksNumbers","");//��ͷ
		
		Element Commodity = addElement(doc,parentElement,"Commodity");//�����Ҫ��Ϣ
		addElement(doc,Commodity,"CargoDescription","");//�����Ҫ����
		addElement(doc,Commodity,"UNDGCode","");//Σ��Ʒ���
		addElement(doc,Commodity,"TariffClassificationCode","");//����˰����
		
		Element AdditionalInformation = addElement(doc,parentElement,"AdditionalInformation");//������Ϣ
		addElement(doc,AdditionalInformation,"Content","");//��������������Ϣ
		
		Element GoodsMeasure = addElement(doc,parentElement,"GoodsMeasure");//����ë��
		addElement(doc,GoodsMeasure,"GrossMassMeasure","");//����ë��
		//n-9999
		Element  EquipmentIdentification = addElement(doc,parentElement,"EquipmentIdentification");//��װ������Ϣ
		addElement(doc,EquipmentIdentification,"ID","");//��װ����
		
		Element  CustomsProcedure = addElement(doc,parentElement,"CustomsProcedure");//����������Ϣ
		addElement(doc,CustomsProcedure,"CurrentCode","");//������������
		//n-99
		Element  UCR = addElement(doc,parentElement,"UCR");//Ψһ������Ϣ
		addElement(doc,UCR,"ID","");//Ψһ���˱��
		
		Element  Origin = addElement(doc,parentElement,"Origin");//ԭ������Ϣ
		addElement(doc,Origin,"OriginCountryCode","");//ԭ��������
		
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
