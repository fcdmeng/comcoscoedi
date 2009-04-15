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
			System.out.println("打包完成,文件名:" + tt.getFName());

		}

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

		Element ExitCustomsOffice = addElement(doc, Declaration,
				"ExitCustomsOffice");// 运输工具离境地海关信息
		addElement(doc, ExitCustomsOffice, "ID", "");// 运输工具离境地海关代码

		Element Agent = addElement(doc, Declaration, "Agent");// 运输工具代理企业信息
		addElement(doc, Agent, "ID", getSenderID());// 运输工具代理企业代码

		Element Carrier = addElement(doc, Declaration, "Carrier");// 承运人信息
		addElement(doc, Carrier, "ID", "");// 承运人代码

		Element BorderTransportMeans = addElement(doc, Declaration,
				"BorderTransportMeans");// 运输工具信息
		{
			addElement(doc, BorderTransportMeans, "JourneyID", StringUtils
					.stripToEmpty(this.getJourneyID()));// 航次航班编号

			addElement(doc, BorderTransportMeans, "TypeCode",
					new TransportTypeCode().getTransportTypeCode("海上运输"));// 运输方式代码
			addElement(doc, BorderTransportMeans, "ID", StringUtils
					.stripToEmpty(this.basvslvoy.getVslcode()));// 运输工具代码
			addElement(doc, BorderTransportMeans, "Name", this
					.getBorderTransportMeans());// 运输工具名称

			addElement(doc, BorderTransportMeans, "FirstArrivalLocationID", "");// 运输工具抵达关境内第一个目的港代码
			addElement(doc, BorderTransportMeans, "ArrivalDateTime", "");// 运输工具抵达关境内第一个目的港的日期和时间
			addElement(doc, BorderTransportMeans, "DepartureDateTime", "");// 运输工具启运日期和时间
		}

		// 提单信息

		for (int i = 0; i < this.getDataStore().getXmlbooking().size(); i++) {
			this.xmlbooking = this.getDataStore().getXmlbooking().get(i);

			Element Consignment = addElement(doc, Declaration, "Consignment");// 一票提单的开始
			Element TransportContractDocument = addElement(doc, Consignment,
					"TransportContractDocument");// 运输合同信息
			{
				addElement(doc, TransportContractDocument, "ID", xmlbooking
						.getBlno());// 总提单号

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
					this.Values);// 运输条款代码
			Values = StringUtils.trimToEmpty(xmlbooking.getDeconsid());
			if (Values.length() > 0) {
				Element Consolidator = addElement(doc,
						TransportContractDocument, "Consolidator");// 拼箱信息
				addElement(doc, Consolidator, "ID", Values);// 拼箱人代码

			}

			Element AssociatedTransportDocument = addElement(doc, Consignment,
					"AssociatedTransportDocument");// 运输合同附加信息

			Values = StringUtils.trimToEmpty(xmlbooking.getAsblno());
			if (Values.trim().length() > 0) {
				addElement(doc, AssociatedTransportDocument, "ID", Values);// 分提运单号
			}

			if (xmlbooking.getGvmeasure() > 0)
				addElement(doc, Consignment, "GrossVolumeMeasure", Values);// 货物体积

			Values = StringUtils.trimToEmpty(xmlbooking.getCvacurr());// 币制
			if (Values.length() > 0) {
				Element ValueAmount = addElement(doc, Consignment,
						"ValueAmount", "");// 托运货物价值
				ValueAmount.setAttribute("CurrencyTypeCode", Values);// 金额类型代码
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getLdpcode());
			if (Values.length() > 0) {
				Element LoadingLocation = addElement(doc, Consignment,
						"LoadingLocation");// 装货地信息

				addElement(doc, LoadingLocation, "ID", Values);// 装货地代码
				addElement(doc, LoadingLocation, "LoadingDate", "");// 货物装载运输工具时间
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getDispcode());
			if (Values.length() > 0) {
				Element UnloadingLocation = addElement(doc, Consignment,
						"UnloadingLocation");// 卸货地信息
				addElement(doc, UnloadingLocation, "ID", Values);// 卸货地代码
				addElement(doc, UnloadingLocation, "ArrivalDate", "");// 到达卸货地日期
			}

			ValuesCode = StringUtils.trimToEmpty(xmlbooking.getDelpcode());
			ValuesName = StringUtils.trimToEmpty(xmlbooking.getDelpname());
			if (ValuesCode.length() > 0 && ValuesName.length() > 0) {
				Element GoodsReceiptPlace = addElement(doc, Consignment,
						"GoodsReceiptPlace");// 收货地信息
				addElement(doc, GoodsReceiptPlace, "ID", ValuesCode);// 收货地点代码
				addElement(doc, GoodsReceiptPlace, "NAME", ValuesName);// 收货地点名称
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getTransfrm());
			if (Values.length() > 0) {
				Element TranshipmentLocation = addElement(doc, Consignment,
						"TranshipmentLocation");// 中转启运地信息
				addElement(doc, TranshipmentLocation, "ID", Values);// 中转启运地点代码
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getTransend());
			if (Values.length() > 0) {
				Element TransitDestination = addElement(doc, Consignment,
						"TransitDestination");// 中转目的地信息
				addElement(doc, TransitDestination, "ID", Values);// 中转目的地点代码
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getRtcountry());
			if (Values.length() > 0) {
				addElement(doc, Consignment, "RoutingCountryCode", Values);// 途经的国家代码
			}
			Values = StringUtils.trimToEmpty(xmlbooking.getConsignp());
			if (Values.length() > 0) {
				Element GoodsConsignedPlace = addElement(doc, Consignment,
						"GoodsConsignedPlace");// 货物托运地信息
				addElement(doc, GoodsConsignedPlace, "ID", Values);// 货物托运的地点或者国家代码

			}

			Values = StringUtils.trimToEmpty(xmlbooking.getCuststs());
			if (Values.length() > 0) {
				addElement(doc, Consignment, "CustomsStatusCode", Values);// 货物海关状态代码
			}
			// 次要数据申报时需要，不是必录项
			/*预配舱单不需要
			Values = StringUtils.trimToEmpty(xmlbooking.getSplitflag());
			if (Values.length() > 0) {
				addElement(doc, Consignment, "TransportSplitIndicator", Values);// 承运人货物分批到/发货标识
			}*/

			Values = StringUtils.trimToEmpty(xmlbooking.getFrtpayer());
			if (Values.length() > 0) {
				Element FreightPayment = addElement(doc, Consignment,
						"FreightPayment");// 运费支付信息
				addElement(doc, FreightPayment, "MethodCode", Values);// 运费支付方代码
			} else {
				// Not Empty
			}

			Element ConsignmentPackaging = addElement(doc, Consignment,
					"ConsignmentPackaging");// 提运单包装信息
			if (xmlbooking.getPiece() > 0) {
				Values = StringUtils.trimToEmpty(String.valueOf(xmlbooking
						.getPiece()));
				addElement(doc, ConsignmentPackaging, "QuantityQuantity",
						Values);// 托运货物件数
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
			addElement(doc, ConsignmentPackaging, "TypeCode", Values);// 包装种类代码

			if (xmlbooking.getGvmeasure() > 0) {
				Values = StringUtils.trimToEmpty(String.valueOf(xmlbooking
						.getGvmeasure()));
				addElement(doc, Consignment, "TotalGrossMassMeasure", Values);// 货物总毛重
			} else {
				// Not Empty
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getPcdocid());
			ValuesCode = StringUtils.trimToEmpty(xmlbooking.getPcdoctype());
			if (Values.length() > 0 || ValuesCode.length() > 0) {
				Element PreviousCustomsDocument = addElement(doc, Consignment,
						"PreviousCustomsDocument");// 前一海关单证信息
				if (Values.length() > 0)
					addElement(doc, PreviousCustomsDocument, "ID", Values);// 前一海关单证号
				if (ValuesCode.length() > 0)
					addElement(doc, PreviousCustomsDocument, "TypeCode",
							ValuesCode);// 前一海关单证类型代码
			}
			Values = StringUtils.trimToEmpty(xmlbooking.getDdline());
			if (Values.length() > 0) {
				Element DeliveryDestination = addElement(doc, Consignment,
						"DeliveryDestination");// 货物交付信息
				addElement(doc, DeliveryDestination, "Line", Values);// 货物交付目的地地址
				Values = StringUtils.trimToEmpty(xmlbooking.getDdcity());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination, "CityName", Values);// 城市名称
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getDdentid());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination, "CountrySubEntityID",
							Values);// 省份代码
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getDdentname());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination,
							"CountrySubEntityName", Values);// 省份名称
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getDdpostid());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination, "PostcodeID", Values);// 邮政编码
				}
				Values = StringUtils.trimToEmpty(xmlbooking.getDdcountry());
				if (Values.length() > 0) {
					addElement(doc, DeliveryDestination, "CountryCode", Values);// 国家代码
				}

			}
			Values = StringUtils.trimToEmpty(xmlbooking.getHandcode());
			if (Values.length() > 0) {
				Element Handling = addElement(doc, Consignment, "Handling");// 码头作业信息
				addElement(doc, Handling, "InstructionsCodeCode", Values);// 码头作业指令代码
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getMcarrid());
			if (Values.length() > 0) {
				Element IntermediateCarrier = addElement(doc, Consignment,
						"IntermediateCarrier");// 中间承运人信息
				addElement(doc, IntermediateCarrier, "ID", Values);// 中间承运人标识
				Values = StringUtils.trimToEmpty(xmlbooking.getMcarrcm1());
				if (Values.length() > 0) {
					// 1-3
					Element Communication = addElement(doc,
							IntermediateCarrier, "Communication");// 中间承运人联系方式
					addElement(doc, Communication, "ID", Values);// 中间承运人联系号码
					Values = StringUtils.trimToEmpty(xmlbooking.getMcarrty1());
					if (Values.length() > 0) {
						addElement(doc, Communication, "TypeID", Values);// 通讯方式类别代码
					}

				}

				Values = StringUtils.trimToEmpty(xmlbooking.getMcarrcm2());
				if (Values.length() > 0) {
					// 1-3
					Element Communication = addElement(doc,
							IntermediateCarrier, "Communication");// 中间承运人联系方式
					addElement(doc, Communication, "ID", Values);// 中间承运人联系号码
					Values = StringUtils.trimToEmpty(xmlbooking.getMcarrty2());
					if (Values.length() > 0) {
						addElement(doc, Communication, "TypeID", Values);// 通讯方式类别代码
					}

				}

				Values = StringUtils.trimToEmpty(xmlbooking.getMcarrcm3());
				if (Values.length() > 0) {
					// 1-3
					Element Communication = addElement(doc,
							IntermediateCarrier, "Communication");// 中间承运人联系方式
					addElement(doc, Communication, "ID", Values);// 中间承运人联系号码
					Values = StringUtils.trimToEmpty(xmlbooking.getMcarrty3());
					if (Values.length() > 0) {
						addElement(doc, Communication, "TypeID", Values);// 通讯方式类别代码
					}

				}

			}

			Set<Xmlbkparty> partySt = xmlbooking.getXmlbkparty();
			if (partySt != null && partySt.size() > 0) {
				for (Xmlbkparty party : partySt) {
					if (party.getPartytype().equals("CN"))// 收货人
						this.nodeParty(doc, Consignment, "Consignee", party);
					if (party.getPartytype().equals("SH"))// 发货人
						this.nodeParty(doc, Consignment, "Consignor", party);
					if (party.getPartytype().equals("N1"))// 第一通知人
						this.nodeParty(doc, Consignment, "NotifyParty", party);
				}
			}

			Values = StringUtils.trimToEmpty(xmlbooking.getDgctname());
			if (Values.length() > 0) {
				Element UNDGContact = addElement(doc, Consignment,
						"UNDGContact"); // 危险品联系人信息
				addElement(doc, UNDGContact, "Name", Values);// 危险品联系人姓名

				// 1-3
				Values = StringUtils.trimToEmpty(xmlbooking.getDgctcm1());
				if (Values.length() > 0) {
					Element UNDGContactCommunication = addElement(doc,
							UNDGContact, "Communication");// 危险品联系方式
					addElement(doc, UNDGContactCommunication, "ID", Values);// 联系号码
					Values = StringUtils.trimToEmpty(xmlbooking.getDgctty1());
					addElement(doc, UNDGContactCommunication, "TypeID", Values);// 联系方式类别代码
				}

				Values = StringUtils.trimToEmpty(xmlbooking.getDgctcm2());
				if (Values.length() > 0) {
					Element UNDGContactCommunication = addElement(doc,
							UNDGContact, "Communication");// 危险品联系方式
					addElement(doc, UNDGContactCommunication, "ID", Values);// 联系号码
					Values = StringUtils.trimToEmpty(xmlbooking.getDgctty2());
					addElement(doc, UNDGContactCommunication, "TypeID", Values);// 联系方式类别代码
				}

				Values = StringUtils.trimToEmpty(xmlbooking.getDgctcm3());
				if (Values.length() > 0) {
					Element UNDGContactCommunication = addElement(doc,
							UNDGContact, "Communication");// 危险品联系方式
					addElement(doc, UNDGContactCommunication, "ID", Values);// 联系号码
					Values = StringUtils.trimToEmpty(xmlbooking.getDgctty3());
					addElement(doc, UNDGContactCommunication, "TypeID", Values);// 联系方式类别代码
				}

			}

			// 添加集装箱信息
			Element TransportEquipment = addElement(doc, Consignment,
					"TransportEquipment");
			TransportEquipment(doc, TransportEquipment, "");

			// 添加货物信息
			Element ConsignmentItem = addElement(doc, Consignment,
					"ConsignmentItem");
			ConsignmentItem(doc, ConsignmentItem, "");

		}
		// 2008-12-05
		// 此备注信息应该为整条船的，而非提单的 if (xmlbooking.getRemark() != null &&

		Element AdditionalInformation = addElement(doc, Declaration,
		 "AdditionalInformation");// 备注 
		addElement(doc, AdditionalInformation,
		 "Content", ""); 
	

	}

	/**
	 * 集装箱信息
	 * 
	 * @param doc
	 * @param parentElement
	 * @param blnokey
	 *            提单主键
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
							parentElement, "EquipmentIdentification");// 集装箱编号信息
					addElement(doc, EquipmentIdentification, "ID", Values);// 集装箱编号

					Values = StringUtils.trimToEmpty(cntr.getCntrtype());
					addElement(doc, parentElement, "CharacteristicCode", Values);// 集装箱尺寸和类型
					Values = StringUtils.trimToEmpty(cntr.getSupplier());
					if (Values.length() > 0) {
						Values = new TransportEquipmentSupplierPartyTypeCode()
								.getTransportEquipmentSupplierPartyTypeCode(Values);
						addElement(doc, parentElement, "SupplierPartyTypeCode",
								Values);// 集装箱来源代码
					}

					Values = StringUtils.trimToEmpty(cntr.getFlesign());
					if (Values.length() > 0) {
						addElement(doc, parentElement, "FullnessCode", cntr
								.getFlesign());// 重箱或者空箱标识代码
					}

					// n-9
					Element SealID;
					// 封志号码、类型和施加封志人
					Values = StringUtils.trimToEmpty(cntr.getSealnocu());

					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("海关"));
					}

					Values = StringUtils.trimToEmpty(cntr.getSealnoaa());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("拼箱人"));
					}

					Values = StringUtils.trimToEmpty(cntr.getSealnoab());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("未知"));
					}
					Values = StringUtils.trimToEmpty(cntr.getSealnoac());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("检疫"));
					}

					Values = StringUtils.trimToEmpty(cntr.getSealnoca());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("承运人"));
					}
					Values = StringUtils.trimToEmpty(cntr.getSealnoto());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("码头"));
					}

					Values = StringUtils.trimToEmpty(cntr.getSealnosh());
					if (Values.length() > 0) {
						SealID = addElement(doc, parentElement, "SealID",
								Values);
						SealID.setAttribute("AgencyCode", new SealAgencyCode()
								.getTransportTypeCode("发货人"));
					}
				}

			}
		}

	}

	/**
	 * 托运货物详细信息
	 * 
	 * @param doc
	 * @param parentElement
	 * @param blnokey提单主键
	 */
	public void ConsignmentItem(Document doc, Element parentElement,
			String blnokey) {
		Set<Xmlbkcargo> set = xmlbooking.getXmlbkcargo();
		if (set != null && set.size() > 0)
			for (Xmlbkcargo cargo : set) {
				// n-9999
				addElement(doc, parentElement, "SequenceNumeric", cargo
						.getSeqno().toString());// 托运货物序号
				if (cargo.getPiece() > 0) {
					Element ConsignmentItemPackaging = addElement(doc,
							parentElement, "ConsignmentItemPackaging");// 托运货物详细包装信息
					addElement(doc, ConsignmentItemPackaging,
							"QuantityQuantity", cargo.getPiece().toString());// 货物详细件数
					Values = StringUtils.trimToEmpty(cargo.getPkgcode());
					if (Values.length() > 0) {
						addElement(doc, ConsignmentItemPackaging, "TypeCode",
								Values);// 包装种类代码
					}

					Values = StringUtils.trimToEmpty(cargo.getMarks());
					if (Values.length() > 0)
						addElement(doc, ConsignmentItemPackaging,
								"MarksNumbers", Values);// 唛头
				}

				Values = StringUtils.trimToEmpty(cargo.getCargodesc());
				ValuesCode = StringUtils.trimToEmpty(cargo.getUndgcode());
				ValuesName = StringUtils.trimToEmpty(cargo.getCusttcode());
				if (Values.length() > 0 || ValuesCode.length() > 0
						|| ValuesName.length() > 0) {
					Element Commodity = addElement(doc, parentElement,
							"Commodity");// 货物简要信息
					if (Values.length() > 0) {
						addElement(doc, Commodity, "CargoDescription", Values);// 货物简要描述
					}
					if (ValuesCode.length() > 0) {
						addElement(doc, Commodity, "UNDGCode", ValuesCode);// 危险品编号
					}
					if (ValuesName.length() > 0) {
						addElement(doc, Commodity, "TariffClassificationCode",
								ValuesName);// 海关税则编号
					}

				}
				ValuesCode = "";
				ValuesName = "";
				Values = StringUtils.trimToEmpty(cargo.getAdddesc());
				if (Values.length() > 0) {
					Element AdditionalInformation = addElement(doc,
							parentElement, "AdditionalInformation");// 补充信息
					addElement(doc, AdditionalInformation, "Content", Values);// 货物描述补充信息
				}

				if (cargo.getGtweight() > 0) {
					Element GoodsMeasure = addElement(doc, parentElement,
							"GoodsMeasure");// 货物毛重
					addElement(doc, GoodsMeasure, "GrossMassMeasure", cargo
							.getGtweight().toString());// 货物毛重
				}

				Element CustomsProcedure = addElement(doc, parentElement,
						"CustomsProcedure");// 海关手续信息
				addElement(doc, CustomsProcedure, "CurrentCode", "");// 海关手续代码

				// n-99
				Element UCR = addElement(doc, parentElement, "UCR");// 唯一托运信息
				addElement(doc, UCR, "ID", "");// 唯一托运编号

				Element Origin = addElement(doc, parentElement, "Origin");// 原产国信息
				addElement(doc, Origin, "OriginCountryCode", "");// 原产国代码

				/*
				 * 可以不要 //n-9999 Element EquipmentIdentification =
				 * addElement(doc
				 * ,parentElement,"EquipmentIdentification");//集装箱编号信息
				 * addElement(doc,EquipmentIdentification,"ID","");//集装箱编号
				 */
			}
	}
}
