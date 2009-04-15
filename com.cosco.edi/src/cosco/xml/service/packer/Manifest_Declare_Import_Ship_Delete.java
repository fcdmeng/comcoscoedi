package cosco.xml.service.packer;

import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cosco.xml.mapping.Xmlbkcargo;
import cosco.xml.mapping.Xmlbkcntr;
import cosco.xml.mapping.Xmlbkparty;
import cosco.xml.mapping.Xmlmapping;
import cosco.xml.service.packer.mapping.MessageDefine;
import cosco.xml.service.packer.mapping.SealAgencyCode;
import cosco.xml.service.packer.mapping.TransportContractDocumentConditionCode;
import cosco.xml.service.packer.mapping.TransportEquipmentSupplierPartyTypeCode;
import cosco.xml.service.packer.mapping.TransportTypeCode;

public class Manifest_Declare_Import_Ship_Delete extends AbstractXml {
	Xmlmapping xmlmap;

	public Manifest_Declare_Import_Ship_Delete(String MessageDefine) {
		super(MessageDefine);
		// TODO Auto-generated constructor stub
	}

	public boolean pack() {
		// TODO Auto-generated method stub
		Document doc = ManifestHead();// 生成头信息
		// generatersBooking();
		Declaration(doc, root);// 报文体
		Save2File(doc);
		return true;
	}

	public boolean generatersBooking() {
		// TODO Auto-generated method stub
		dataStore.Retrieve();
		return true;
	}

	/**
	 * 报文体
	 */
	public void Declaration(Document doc, Element root) {

		Element Declaration = doc.createElement("Declaration");// 报文体
		root.appendChild(Declaration);

		Element RepresentativePerson = addElement(doc, Declaration,
				"RepresentativePerson");// 舱单传输人信息
		addElement(doc, RepresentativePerson, "Name", "02302320");// 舱单传输人名称

		Element BorderTransportMeans = addElement(doc, Declaration,
				"BorderTransportMeans");// 运输工具信息
		{
			addElement(doc, BorderTransportMeans, "JourneyID", StringUtils
					.stripToEmpty(this.getJourneyID()));// 航次航班编号

			addElement(doc, BorderTransportMeans, "ID", StringUtils
					.stripToEmpty(this.basvslvoy.getVslcode()));// 运输工具代码

		}

		// 提单信息

		for (int i = 0; i < this.getDataStore().getXmlbooking().size(); i++) {
			this.xmlbooking = this.getDataStore().getXmlbooking().get(i);
			Element Consignment = addElement(doc, Declaration, "Consignment");// 一票提单的开始
			Element TransportContractDocument = addElement(doc, Consignment,
					"TransportContractDocument");// 运输合同信息
			addElement(doc, TransportContractDocument, "ID", xmlbooking
					.getBlno());// 总提单号

			// 1-3
			{
				// AMDCODE1
				Values = StringUtils.trimToEmpty(xmlbooking.getAmdcode1());
				if (Values.trim().length() > 0) {
					addElement(doc, TransportContractDocument,
							"ChangeReasonCode", Values);// 更改原因代码
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getAmdcode2());
				if (Values.trim().length() > 0) {
					addElement(doc, TransportContractDocument,
							"ChangeReasonCode", Values);// 更改原因代码
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getAmdcode3());
				if (Values.trim().length() > 0) {
					addElement(doc, TransportContractDocument,
							"ChangeReasonCode", xmlbooking.getAmdcode3());// 更改原因代码
				}
			}

			// =========================================
			Element AssociatedTransportDocument = addElement(doc, Consignment,
					"AssociatedTransportDocument");// 运输合同附加信息

			Values = StringUtils.trimToEmpty(xmlbooking.getAsblno());
			if (Values.trim().length() > 0) {
				addElement(doc, AssociatedTransportDocument, "ID", Values);// 分提运单号
			}
			
		}
		/*// 2009-01-16
		Values = StringUtils.trimToEmpty(xmlbooking.getRemark());
		if (Values.length() > 0) {
			Element AdditionalInformation = addElement(doc, Declaration,
					"AdditionalInformation");// 备注
			addElement(doc, AdditionalInformation, "Content", Values);
		}*/

	}

}
