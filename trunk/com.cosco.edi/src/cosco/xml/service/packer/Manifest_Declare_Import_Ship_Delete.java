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
		Document doc = ManifestHead();// ����ͷ��Ϣ
		// generatersBooking();
		Declaration(doc, root);// ������
		Save2File(doc);
		return true;
	}

	public boolean generatersBooking() {
		// TODO Auto-generated method stub
		dataStore.Retrieve();
		return true;
	}

	/**
	 * ������
	 */
	public void Declaration(Document doc, Element root) {

		Element Declaration = doc.createElement("Declaration");// ������
		root.appendChild(Declaration);

		Element RepresentativePerson = addElement(doc, Declaration,
				"RepresentativePerson");// �յ���������Ϣ
		addElement(doc, RepresentativePerson, "Name", "02302320");// �յ�����������

		Element BorderTransportMeans = addElement(doc, Declaration,
				"BorderTransportMeans");// ���乤����Ϣ
		{
			addElement(doc, BorderTransportMeans, "JourneyID", StringUtils
					.stripToEmpty(this.getJourneyID()));// ���κ�����

			addElement(doc, BorderTransportMeans, "ID", StringUtils
					.stripToEmpty(this.basvslvoy.getVslcode()));// ���乤�ߴ���

		}

		// �ᵥ��Ϣ

		for (int i = 0; i < this.getDataStore().getXmlbooking().size(); i++) {
			this.xmlbooking = this.getDataStore().getXmlbooking().get(i);
			Element Consignment = addElement(doc, Declaration, "Consignment");// һƱ�ᵥ�Ŀ�ʼ
			Element TransportContractDocument = addElement(doc, Consignment,
					"TransportContractDocument");// �����ͬ��Ϣ
			addElement(doc, TransportContractDocument, "ID", xmlbooking
					.getBlno());// ���ᵥ��

			// 1-3
			{
				// AMDCODE1
				Values = StringUtils.trimToEmpty(xmlbooking.getAmdcode1());
				if (Values.trim().length() > 0) {
					addElement(doc, TransportContractDocument,
							"ChangeReasonCode", Values);// ����ԭ�����
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getAmdcode2());
				if (Values.trim().length() > 0) {
					addElement(doc, TransportContractDocument,
							"ChangeReasonCode", Values);// ����ԭ�����
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getAmdcode3());
				if (Values.trim().length() > 0) {
					addElement(doc, TransportContractDocument,
							"ChangeReasonCode", xmlbooking.getAmdcode3());// ����ԭ�����
				}
			}

			// =========================================
			Element AssociatedTransportDocument = addElement(doc, Consignment,
					"AssociatedTransportDocument");// �����ͬ������Ϣ

			Values = StringUtils.trimToEmpty(xmlbooking.getAsblno());
			if (Values.trim().length() > 0) {
				addElement(doc, AssociatedTransportDocument, "ID", Values);// �����˵���
			}
			
		}
		/*// 2009-01-16
		Values = StringUtils.trimToEmpty(xmlbooking.getRemark());
		if (Values.length() > 0) {
			Element AdditionalInformation = addElement(doc, Declaration,
					"AdditionalInformation");// ��ע
			addElement(doc, AdditionalInformation, "Content", Values);
		}*/

	}

}
