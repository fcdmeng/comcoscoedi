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

public class Manifest_Declare_Export_Ship_Secondly extends AbstractXml {
	Xmlmapping xmlmap;

	public Manifest_Declare_Export_Ship_Secondly(String MessageDefine) {
		super(MessageDefine);
		// TODO Auto-generated constructor stub
	}

	public static void main(String argv[]) {
		Manifest_Declare_Import_Ship_First tt = new Manifest_Declare_Import_Ship_First(
				"");
		if (tt.ExecutePack() == true) {
			System.out.println("������,�ļ���:" + tt.getFName());

		}

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

		Element ExitCustomsOffice = addElement(doc, Declaration,
				"ExitCustomsOffice");// ���乤���뾳�غ�����Ϣ
		addElement(doc, ExitCustomsOffice, "ID", "");// ���乤���뾳�غ��ش���

		Element Agent = addElement(doc, Declaration, "Agent");// ���乤�ߴ�����ҵ��Ϣ
		addElement(doc, Agent, "ID", getSenderID());// ���乤�ߴ�����ҵ����

		Element Carrier = addElement(doc, Declaration, "Carrier");// ��������Ϣ
		addElement(doc, Carrier, "ID", "");// �����˴���

		Element BorderTransportMeans = addElement(doc, Declaration,
				"BorderTransportMeans");// ���乤����Ϣ
		{
			addElement(doc, BorderTransportMeans, "JourneyID", StringUtils
					.stripToEmpty(this.getJourneyID()));// ���κ�����

			addElement(doc, BorderTransportMeans, "TypeCode",
					new TransportTypeCode().getTransportTypeCode("��������"));// ���䷽ʽ����
			addElement(doc, BorderTransportMeans, "ID", StringUtils
					.stripToEmpty(this.basvslvoy.getVslcode()));// ���乤�ߴ���
			addElement(doc, BorderTransportMeans, "Name", this
					.getBorderTransportMeans());// ���乤������

			addElement(doc, BorderTransportMeans, "FirstArrivalLocationID", "");// ���乤�ߵִ�ؾ��ڵ�һ��Ŀ�ĸ۴���
			addElement(doc, BorderTransportMeans, "ArrivalDateTime", "");// ���乤�ߵִ�ؾ��ڵ�һ��Ŀ�ĸ۵����ں�ʱ��
			addElement(doc, BorderTransportMeans, "DepartureDateTime", "");// ���乤���������ں�ʱ��
		}

		// �ᵥ��Ϣ

		for (int i = 0; i < this.getDataStore().getXmlbooking().size(); i++) {
			this.xmlbooking = this.getDataStore().getXmlbooking().get(i);

			Element Consignment = addElement(doc, Declaration, "Consignment");// һƱ�ᵥ�Ŀ�ʼ
			Element TransportContractDocument = addElement(doc, Consignment,
					"TransportContractDocument");// �����ͬ��Ϣ
			{
				addElement(doc, TransportContractDocument, "ID", xmlbooking
						.getBlno());// ���ᵥ��

			}

			this.Values = StringUtils
					.trimToEmpty(new TransportContractDocumentConditionCode()
							.getTransportTypeCode(xmlbooking.getCarrtype()));
			if (this.Values.length() > 0) {
				xmlmap = this.getXmlmapping("CONTRASTTERM", this.Values);
				if (xmlmap != null) {
					this.Values = xmlmap.getExcode();
				}
			}

			addElement(doc, TransportContractDocument, "ConditionCode",
					this.Values);// �����������
			Values = StringUtils.trimToEmpty(xmlbooking.getDeconsid());
			if (Values.length() > 0) {
				Element Consolidator = addElement(doc,
						TransportContractDocument, "Consolidator");// ƴ����Ϣ
				addElement(doc, Consolidator, "ID", Values);// ƴ���˴���

			}

			Element AssociatedTransportDocument = addElement(doc, Consignment,
					"AssociatedTransportDocument");// �����ͬ������Ϣ

			Values = StringUtils.trimToEmpty(xmlbooking.getAsblno());
			if (Values.trim().length() > 0) {
				addElement(doc, AssociatedTransportDocument, "ID", Values);// �����˵���
			}

			if (xmlbooking.getGvmeasure() > 0)
				addElement(doc, Consignment, "GrossVolumeMeasure", Values);// �������

			Values = StringUtils.trimToEmpty(xmlbooking.getCvacurr());// ����
			if (Values.length() > 0) {
				Element ValueAmount = addElement(doc, Consignment,
						"ValueAmount", "");// ���˻����ֵ
				ValueAmount.setAttribute("CurrencyTypeCode", Values);// ������ʹ���
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getLdpcode());
			if (Values.length() > 0) {
				Element LoadingLocation = addElement(doc, Consignment,
						"LoadingLocation");// װ������Ϣ

				addElement(doc, LoadingLocation, "ID", Values);// װ���ش���
				addElement(doc, LoadingLocation, "LoadingDate", "");// ����װ�����乤��ʱ��
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getDispcode());
			if (Values.length() > 0) {
				Element UnloadingLocation = addElement(doc, Consignment,
						"UnloadingLocation");// ж������Ϣ
				addElement(doc, UnloadingLocation, "ID", Values);// ж���ش���
				addElement(doc, UnloadingLocation, "ArrivalDate", "");// ����ж��������
			}

			ValuesCode = StringUtils.trimToEmpty(xmlbooking.getDelpcode());
			ValuesName = StringUtils.trimToEmpty(xmlbooking.getDelpname());
			if (ValuesCode.length() > 0 && ValuesName.length() > 0) {
				Element GoodsReceiptPlace = addElement(doc, Consignment,
						"GoodsReceiptPlace");// �ջ�����Ϣ
				addElement(doc, GoodsReceiptPlace, "ID", ValuesCode);// �ջ��ص����
				addElement(doc, GoodsReceiptPlace, "NAME", ValuesName);// �ջ��ص�����
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getTransfrm());
			if (Values.length() > 0) {
				Element TranshipmentLocation = addElement(doc, Consignment,
						"TranshipmentLocation");// ��ת���˵���Ϣ
				addElement(doc, TranshipmentLocation, "ID", Values);// ��ת���˵ص����
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getTransend());
			if (Values.length() > 0) {
				Element TransitDestination = addElement(doc, Consignment,
						"TransitDestination");// ��תĿ�ĵ���Ϣ
				addElement(doc, TransitDestination, "ID", Values);// ��תĿ�ĵص����
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getRtcountry());
			if (Values.length() > 0) {
				addElement(doc, Consignment, "RoutingCountryCode", Values);// ;���Ĺ��Ҵ���
			}
			Values = StringUtils.trimToEmpty(xmlbooking.getConsignp());
			if (Values.length() > 0) {
				Element GoodsConsignedPlace = addElement(doc, Consignment,
						"GoodsConsignedPlace");// �������˵���Ϣ
				addElement(doc, GoodsConsignedPlace, "ID", Values);// �������˵ĵص���߹��Ҵ���

			}

			Values = StringUtils.trimToEmpty(xmlbooking.getCuststs());
			if (Values.length() > 0) {
				addElement(doc, Consignment, "CustomsStatusCode", Values);// ���ﺣ��״̬����
			}
			// ��Ҫ�����걨ʱ��Ҫ�����Ǳ�¼��
			/*Ԥ��յ�����Ҫ
			Values = StringUtils.trimToEmpty(xmlbooking.getSplitflag());
			if (Values.length() > 0) {
				addElement(doc, Consignment, "TransportSplitIndicator", Values);// �����˻��������/������ʶ
			}*/

			Values = StringUtils.trimToEmpty(xmlbooking.getFrtpayer());
			if (Values.length() > 0) {
				Element FreightPayment = addElement(doc, Consignment,
						"FreightPayment");// �˷�֧����Ϣ
				addElement(doc, FreightPayment, "MethodCode", Values);// �˷�֧��������
			} else {
				// Not Empty
			}

			Element ConsignmentPackaging = addElement(doc, Consignment,
					"ConsignmentPackaging");// ���˵���װ��Ϣ
			if (xmlbooking.getPiece() > 0) {
				Values = StringUtils.trimToEmpty(String.valueOf(xmlbooking
						.getPiece()));
				addElement(doc, ConsignmentPackaging, "QuantityQuantity",
						Values);// ���˻������
			} else {
				// Not Empty
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getPkgcode());

			if (Values.length() == 0) {
				// Not Empty
			} else {
				xmlmap = this.getXmlmapping("CONTRASTPKG", Values);
				if (xmlmap != null)
					Values = xmlmap.getExcode();

			}
			addElement(doc, ConsignmentPackaging, "TypeCode", Values);// ��װ�������

			if (xmlbooking.getGvmeasure() > 0) {
				Values = StringUtils.trimToEmpty(String.valueOf(xmlbooking
						.getGvmeasure()));
				addElement(doc, Consignment, "TotalGrossMassMeasure", Values);// ������ë��
			} else {
				// Not Empty
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getPcdocid());
			ValuesCode = StringUtils.trimToEmpty(xmlbooking.getPcdoctype());
			if (Values.length() > 0 || ValuesCode.length() > 0) {
				Element PreviousCustomsDocument = addElement(doc, Consignment,
						"PreviousCustomsDocument");// ǰһ���ص�֤��Ϣ
				if (Values.length() > 0)
					addElement(doc, PreviousCustomsDocument, "ID", Values);// ǰһ���ص�֤��
				if (ValuesCode.length() > 0)
					addElement(doc, PreviousCustomsDocument, "TypeCode",
							ValuesCode);// ǰһ���ص�֤���ʹ���
			}
			Values = StringUtils.trimToEmpty(xmlbooking.getDdline());
			if (Values.length() > 0) {
				Element DeliveryDestination = addElement(doc, Consignment,
						"DeliveryDestination");// ���ｻ����Ϣ
				addElement(doc, DeliveryDestination, "Line", Values);// ���ｻ��Ŀ�ĵص�ַ
				Values = StringUtils.trimToEmpty(xmlbooking.getDdcity());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination, "CityName", Values);// ��������
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getDdentid());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination, "CountrySubEntityID",
							Values);// ʡ�ݴ���
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getDdentname());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination,
							"CountrySubEntityName", Values);// ʡ������
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getDdpostid());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination, "PostcodeID", Values);// ��������
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getDdcountry());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination, "CountryCode", Values);// ���Ҵ���
				}

			}
			Values = StringUtils.trimToEmpty(xmlbooking.getHandcode());
			if (Values.length() > 0) {
				Element Handling = addElement(doc, Consignment, "Handling");// ��ͷ��ҵ��Ϣ
				addElement(doc, Handling, "InstructionsCodeCode", Values);// ��ͷ��ҵָ�����
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getMcarrid());
			if (Values.length() > 0) {
				Element IntermediateCarrier = addElement(doc, Consignment,
						"IntermediateCarrier");// �м��������Ϣ
				addElement(doc, IntermediateCarrier, "ID", Values);// �м�����˱�ʶ
				Values = StringUtils.trimToEmpty(xmlbooking.getMcarrcm1());
				if (Values.length() > 0) {
					// 1-3
					Element Communication = addElement(doc,
							IntermediateCarrier, "Communication");// �м��������ϵ��ʽ
					addElement(doc, Communication, "ID", Values);// �м��������ϵ����
					Values = StringUtils.trimToEmpty(xmlbooking.getMcarrty1());
					if (Values.length() > 0) {
						addElement(doc, Communication, "TypeID", Values);// ͨѶ��ʽ������
					}

				}

				Values = StringUtils.trimToEmpty(xmlbooking.getMcarrcm2());
				if (Values.length() > 0) {
					// 1-3
					Element Communication = addElement(doc,
							IntermediateCarrier, "Communication");// �м��������ϵ��ʽ
					addElement(doc, Communication, "ID", Values);// �м��������ϵ����
					Values = StringUtils.trimToEmpty(xmlbooking.getMcarrty2());
					if (Values.length() > 0) {
						addElement(doc, Communication, "TypeID", Values);// ͨѶ��ʽ������
					}

				}

				Values = StringUtils.trimToEmpty(xmlbooking.getMcarrcm3());
				if (Values.length() > 0) {
					// 1-3
					Element Communication = addElement(doc,
							IntermediateCarrier, "Communication");// �м��������ϵ��ʽ
					addElement(doc, Communication, "ID", Values);// �м��������ϵ����
					Values = StringUtils.trimToEmpty(xmlbooking.getMcarrty3());
					if (Values.length() > 0) {
						addElement(doc, Communication, "TypeID", Values);// ͨѶ��ʽ������
					}

				}

			}

			Set<Xmlbkparty> partySt = xmlbooking.getXmlbkparty();
			if (partySt != null && partySt.size() > 0) {
				for (Xmlbkparty party : partySt) {
					if (party.getPartytype().equals("CN"))// �ջ���
						this.nodeParty(doc, Consignment, "Consignee", party);
					if (party.getPartytype().equals("SH"))// ������
						this.nodeParty(doc, Consignment, "Consignor", party);
					if (party.getPartytype().equals("N1"))// ��һ֪ͨ��
						this.nodeParty(doc, Consignment, "NotifyParty", party);
				}
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getDgctname());
			if (Values.length() > 0) {
				Element UNDGContact = addElement(doc, Consignment,
						"UNDGContact"); // Σ��Ʒ��ϵ����Ϣ
				addElement(doc, UNDGContact, "Name", Values);// Σ��Ʒ��ϵ������

				// 1-3
				Values = StringUtils.trimToEmpty(xmlbooking.getDgctcm1());
				if (Values.length() > 0) {
					Element UNDGContactCommunication = addElement(doc,
							UNDGContact, "Communication");// Σ��Ʒ��ϵ��ʽ
					addElement(doc, UNDGContactCommunication, "ID", Values);// ��ϵ����
					Values = StringUtils.trimToEmpty(xmlbooking.getDgctty1());
					addElement(doc, UNDGContactCommunication, "TypeID", Values);// ��ϵ��ʽ������
				}

				Values = StringUtils.trimToEmpty(xmlbooking.getDgctcm2());
				if (Values.length() > 0) {
					Element UNDGContactCommunication = addElement(doc,
							UNDGContact, "Communication");// Σ��Ʒ��ϵ��ʽ
					addElement(doc, UNDGContactCommunication, "ID", Values);// ��ϵ����
					Values = StringUtils.trimToEmpty(xmlbooking.getDgctty2());
					addElement(doc, UNDGContactCommunication, "TypeID", Values);// ��ϵ��ʽ������
				}

				Values = StringUtils.trimToEmpty(xmlbooking.getDgctcm3());
				if (Values.length() > 0) {
					Element UNDGContactCommunication = addElement(doc,
							UNDGContact, "Communication");// Σ��Ʒ��ϵ��ʽ
					addElement(doc, UNDGContactCommunication, "ID", Values);// ��ϵ����
					Values = StringUtils.trimToEmpty(xmlbooking.getDgctty3());
					addElement(doc, UNDGContactCommunication, "TypeID", Values);// ��ϵ��ʽ������
				}

			}

			// ��Ӽ�װ����Ϣ
			Element TransportEquipment = addElement(doc, Consignment,
					"TransportEquipment");
			TransportEquipment(doc, TransportEquipment, "");

			// ��ӻ�����Ϣ
			Element ConsignmentItem = addElement(doc, Consignment,
					"ConsignmentItem");
			ConsignmentItem(doc, ConsignmentItem, "");

		}
		// 2008-12-05
		// �˱�ע��ϢӦ��Ϊ�������ģ������ᵥ�� if (xmlbooking.getRemark() != null &&

		Element AdditionalInformation = addElement(doc, Declaration,
		 "AdditionalInformation");// ��ע 
		addElement(doc, AdditionalInformation,
		 "Content", ""); 
	

	}

	/**
	 * ��װ����Ϣ
	 * 
	 * @param doc
	 * @param parentElement
	 * @param blnokey
	 *            �ᵥ����
	 */
	public void TransportEquipment(Document doc, Element parentElement,
			String blnokey) {
		// n-9999
		Set<Xmlbkcntr> set = xmlbooking.getXmlbkcntr();
		if (set != null && set.size() > 0) {
			for (Xmlbkcntr cntr : set) {
				Values = StringUtils.trimToEmpty(cntr.getCntrno());
				if (Values.length() > 0) {
					Element EquipmentIdentification = addElement(doc,
							parentElement, "EquipmentIdentification");// ��װ������Ϣ
					addElement(doc, EquipmentIdentification, "ID", Values);// ��װ����

					Values = StringUtils.trimToEmpty(cntr.getCntrtype());
					addElement(doc, parentElement, "CharacteristicCode", Values);// ��װ��ߴ������
					Values = StringUtils.trimToEmpty(cntr.getSupplier());
					if (Values.length() > 0) {
						Values = new TransportEquipmentSupplierPartyTypeCode()
								.getTransportEquipmentSupplierPartyTypeCode(Values);
						addElement(doc, parentElement, "SupplierPartyTypeCode",
								Values);// ��װ����Դ����
					}

					Values = StringUtils.trimToEmpty(cntr.getFlesign());
					if (Values.length() > 0) {
						addElement(doc, parentElement, "FullnessCode", cntr
								.getFlesign());// ������߿����ʶ����
					}

					// n-9
					Element SealID;
					// ��־���롢���ͺ�ʩ�ӷ�־��
					Values = StringUtils.trimToEmpty(cntr.getSealnocu());

					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("����"));
					}

					Values = StringUtils.trimToEmpty(cntr.getSealnoaa());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("ƴ����"));
					}

					Values = StringUtils.trimToEmpty(cntr.getSealnoab());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("δ֪"));
					}
					Values = StringUtils.trimToEmpty(cntr.getSealnoac());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("����"));
					}

					Values = StringUtils.trimToEmpty(cntr.getSealnoca());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("������"));
					}
					Values = StringUtils.trimToEmpty(cntr.getSealnoto());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("��ͷ"));
					}

					Values = StringUtils.trimToEmpty(cntr.getSealnosh());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("������"));
					}
				}

			}
		}

	}

	/**
	 * ���˻�����ϸ��Ϣ
	 * 
	 * @param doc
	 * @param parentElement
	 * @param blnokey�ᵥ����
	 */
	public void ConsignmentItem(Document doc, Element parentElement,
			String blnokey) {
		Set<Xmlbkcargo> set = xmlbooking.getXmlbkcargo();
		if (set != null && set.size() > 0)
			for (Xmlbkcargo cargo : set) {
				// n-9999
				addElement(doc, parentElement, "SequenceNumeric", cargo
						.getSeqno().toString());// ���˻������
				if (cargo.getPiece() > 0) {
					Element ConsignmentItemPackaging = addElement(doc,
							parentElement, "ConsignmentItemPackaging");// ���˻�����ϸ��װ��Ϣ
					addElement(doc, ConsignmentItemPackaging,
							"QuantityQuantity", cargo.getPiece().toString());// ������ϸ����
					Values = StringUtils.trimToEmpty(cargo.getPkgcode());
					if (Values.length() > 0) {
						addElement(doc, ConsignmentItemPackaging, "TypeCode",
								Values);// ��װ�������
					}

					Values = StringUtils.trimToEmpty(cargo.getMarks());
					if (Values.length() > 0)
						addElement(doc, ConsignmentItemPackaging,
								"MarksNumbers", Values);// ��ͷ
				}

				Values = StringUtils.trimToEmpty(cargo.getCargodesc());
				ValuesCode = StringUtils.trimToEmpty(cargo.getUndgcode());
				ValuesName = StringUtils.trimToEmpty(cargo.getCusttcode());
				if (Values.length() > 0 || ValuesCode.length() > 0
						|| ValuesName.length() > 0) {
					Element Commodity = addElement(doc, parentElement,
							"Commodity");// �����Ҫ��Ϣ
					if (Values.length() > 0) {
						addElement(doc, Commodity, "CargoDescription", Values);// �����Ҫ����
					}
					if (ValuesCode.length() > 0) {
						addElement(doc, Commodity, "UNDGCode", ValuesCode);// Σ��Ʒ���
					}
					if (ValuesName.length() > 0) {
						addElement(doc, Commodity, "TariffClassificationCode",
								ValuesName);// ����˰����
					}

				}
				ValuesCode = "";
				ValuesName = "";
				Values = StringUtils.trimToEmpty(cargo.getAdddesc());
				if (Values.length() > 0) {
					Element AdditionalInformation = addElement(doc,
							parentElement, "AdditionalInformation");// ������Ϣ
					addElement(doc, AdditionalInformation, "Content", Values);// ��������������Ϣ
				}

				if (cargo.getGtweight() > 0) {
					Element GoodsMeasure = addElement(doc, parentElement,
							"GoodsMeasure");// ����ë��
					addElement(doc, GoodsMeasure, "GrossMassMeasure", cargo
							.getGtweight().toString());// ����ë��
				}

				Element CustomsProcedure = addElement(doc, parentElement,
						"CustomsProcedure");// ����������Ϣ
				addElement(doc, CustomsProcedure, "CurrentCode", "");// ������������

				// n-99
				Element UCR = addElement(doc, parentElement, "UCR");// Ψһ������Ϣ
				addElement(doc, UCR, "ID", "");// Ψһ���˱��

				Element Origin = addElement(doc, parentElement, "Origin");// ԭ������Ϣ
				addElement(doc, Origin, "OriginCountryCode", "");// ԭ��������

				/*
				 * ���Բ�Ҫ //n-9999 Element EquipmentIdentification =
				 * addElement(doc
				 * ,parentElement,"EquipmentIdentification");//��װ������Ϣ
				 * addElement(doc,EquipmentIdentification,"ID","");//��װ����
				 */
			}
	}
}
