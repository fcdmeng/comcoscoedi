package cosco.vsagent.system;

import java.util.ArrayList;
import java.util.List;

import com.freqds.base.editorpart.BaseCntrEditor;
import com.freqds.base.editorpart.BaseCntrEditorInput;
import com.freqds.base.editorpart.BaseCountryEditor;
import com.freqds.base.editorpart.BaseCountryEditorInput;
import com.freqds.base.editorpart.BaseCustomsEditor;
import com.freqds.base.editorpart.BaseCustomsEditorInput;
import com.freqds.base.editorpart.BaseLineEditor;
import com.freqds.base.editorpart.BaseLineEditorInput;
import com.freqds.base.editorpart.BasePackageEditor;
import com.freqds.base.editorpart.BasePackageEditorInput;
import com.freqds.base.editorpart.BasePartnersEditor;
import com.freqds.base.editorpart.BasePartnersEditorInput;
import com.freqds.base.editorpart.BasePortEditor;
import com.freqds.base.editorpart.BasePortEditorInput;
import com.freqds.base.editorpart.BaseShipRecordEditor;
import com.freqds.base.editorpart.BaseShipRecordEditorInput;

import cosco.vsagent.app.Constants;
import cosco.vsagent.archive.ArchiveEditor;
import cosco.vsagent.archive.ArchiveEditorInput;
import cosco.vsagent.archive.CourseEditor;
import cosco.vsagent.archive.CourseEditorInput;
import cosco.vsagent.archive.ExamEditor;
import cosco.vsagent.archive.ExamEditorInput;
import cosco.vsagent.archive.GradeEditor;
import cosco.vsagent.archive.GradeEditorInput;
import cosco.vsagent.archive.SchoolClassEditor;
import cosco.vsagent.archive.SchoolClassEditorInput;
import cosco.vsagent.archive.StudentScoreEditor;
import cosco.vsagent.archive.StudentScoreEditorInput;
import cosco.vsagent.base.BasPackageEditor;
import cosco.vsagent.base.BasPackageEditorInput;
import cosco.vsagent.base.BasPortEditor;
import cosco.vsagent.base.BasPortEditorInput;
import cosco.vsagent.base.BasVslMsgEditor;
import cosco.vsagent.base.BasVslMsgEditorInput;
import cosco.vsagent.base.BascountryEditor;
import cosco.vsagent.base.BascountryEditorInput;
import cosco.vsagent.base.BaseEdiCodeEditor;
import cosco.vsagent.base.BaseEdiCodeEditorInput;
import cosco.vsagent.base.BaslineEditor;
import cosco.vsagent.base.BaslineEditorInput;
import cosco.vsagent.base.BaspartyEditor;
import cosco.vsagent.base.BaspartyEditorInput;
import cosco.vsagent.base.BaspaytypeEditor;
import cosco.vsagent.base.BaspaytypeEditorInput;
import cosco.vsagent.db.DbOperate;
import cosco.vsagent.db.MysqlOperate;
import cosco.vsagent.db.OracleOperate;
import cosco.vsagent.db.SqlServerOperate;
import cosco.vsagent.ecd.BasvslvoyEditor;
import cosco.vsagent.ecd.BasvslvoyEditorInput;
import cosco.vsagent.ecd.EcdBookingEditor;
import cosco.vsagent.ecd.EcdBookingEditorInput;
import cosco.vsagent.ecd.EcdbkCheckEditor;
import cosco.vsagent.ecd.EcdbkCheckEditorInput;
import cosco.vsagent.ecd.EcdbkcntrEditor;
import cosco.vsagent.ecd.EcdbkcntrEditorInput;
import cosco.vsagent.ecd.EcdbookingQueryEditor;
import cosco.vsagent.ecd.EcdbookingQueryEditorInput;
import cosco.vsagent.model.ITreeEntry;
import cosco.vsagent.navigator.NavigatorEntry;
import cosco.xml.XmlBookingEditor;
import cosco.xml.XmlBookingEditorInput;


public class SmsFactory {
	public static List<ITreeEntry> createNavigatorEntryTree(){
		NavigatorEntry t1 = new NavigatorEntry("���ݹ���");
		t1.setImage(ImagesContext.getImage(ImagesContext.STUDENT));
		
		NavigatorEntry t2 = new NavigatorEntry("�������");
		t2.setImage(ImagesContext.getImage(ImagesContext.REPORT));
		
		NavigatorEntry t3 = new NavigatorEntry("ϵͳ����");
		t3.setImage(ImagesContext.getImage(ImagesContext.SYSCONFIG));
		{
			NavigatorEntry c1 = new NavigatorEntry("��������");
			c1.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			c1.setEditorInput(new ArchiveEditorInput());
			c1.setEditorId(ArchiveEditor.class.getName());
			
			NavigatorEntry c2 = new NavigatorEntry("�γ̹���");
			c2.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			c2.setEditorInput(new CourseEditorInput());
			c2.setEditorId(CourseEditor.class.getName());
			t1.addChild(c2);
			
			NavigatorEntry c3 = new NavigatorEntry("�꼶����");
			c3.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			c3.setEditorInput(new GradeEditorInput());
			c3.setEditorId(GradeEditor.class.getName());
			t1.addChild(c3);
			
			NavigatorEntry c4 = new NavigatorEntry("�༶����");
			c4.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			c4.setEditorInput(new SchoolClassEditorInput());
			c4.setEditorId(SchoolClassEditor.class.getName());
			
			t1.addChild(c4);
			
			NavigatorEntry c5 = new NavigatorEntry("���Ա�");
			c5.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			c5.setEditorInput(new ExamEditorInput());
			c5.setEditorId(ExamEditor.class.getName());
			
			t1.addChild(c5);
			
			t1.addChild(c1);
			NavigatorEntry c9 = new NavigatorEntry("�ɼ�����");
			c9.setImage(ImagesContext.getImage(ImagesContext.EDITING));
			c9.setEditorInput(new StudentScoreEditorInput());
			c9.setEditorId(StudentScoreEditor.class.getName());
			t1.addChild(c9);
		}
		
		NavigatorEntry base = new NavigatorEntry("��������");
		base.setImage(ImagesContext.getImage(ImagesContext.SET));
		{
			NavigatorEntry basvslmsg = new NavigatorEntry("�����淶");
			basvslmsg.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			basvslmsg.setEditorInput(new BasVslMsgEditorInput());
			basvslmsg.setEditorId(BasVslMsgEditor.class.getName());
			base.addChild(basvslmsg);
			
			NavigatorEntry baseport = new NavigatorEntry("�ۿ�����");
			baseport.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			baseport.setEditorInput(new BasPortEditorInput());
			baseport.setEditorId(BasPortEditor.class.getName());
			base.addChild(baseport);
			
			NavigatorEntry baspackage = new NavigatorEntry("��װ����");
			baspackage.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			baspackage.setEditorInput(new BasPackageEditorInput());
			baspackage.setEditorId(BasPackageEditor.class.getName());
			base.addChild(baspackage);
			
			NavigatorEntry bascountry = new NavigatorEntry("���Ҵ���");
			bascountry.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			bascountry.setEditorInput(new BascountryEditorInput());
			bascountry.setEditorId(BascountryEditor.class.getName());
			base.addChild(bascountry);
			
			NavigatorEntry basline = new NavigatorEntry("���ߴ���");
			basline.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			basline.setEditorInput(new BaslineEditorInput());
			basline.setEditorId(BaslineEditor.class.getName());
			base.addChild(basline);
			
			NavigatorEntry baspaytype = new NavigatorEntry("���ѷ�ʽ");
			baspaytype.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			baspaytype.setEditorInput(new BaspaytypeEditorInput());
			baspaytype.setEditorId(BaspaytypeEditor.class.getName());
			base.addChild(baspaytype);
			
			NavigatorEntry basparty = new NavigatorEntry("���뷽��Ϣ");
			basparty.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			basparty.setEditorInput(new BaspartyEditorInput());
			basparty.setEditorId( BaspartyEditor.class.getName());
			base.addChild(basparty);
			
			NavigatorEntry edicode = new NavigatorEntry("EDI������");
			edicode.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			edicode.setEditorInput(new BaseEdiCodeEditorInput());
			edicode.setEditorId( BaseEdiCodeEditor.class.getName());
			base.addChild(edicode);
			
			
//			NavigatorEntry cntrbase = new NavigatorEntry("���ʹ���");
//			cntrbase.setImage(ImagesContext.getImage(ImagesContext.NOTE));
//			cntrbase.setEditorInput(new BasVslMsgEditorInput());
//			cntrbase.setEditorId(BasVslMsgEditor.class.getName());
//			base.addChild(cntrbase);
			
		}
		
		NavigatorEntry ecd = new NavigatorEntry("���ڼ�װ�䵥֤");
		ecd.setImage(ImagesContext.getImage(ImagesContext.ECD));
		{
			NavigatorEntry basvslvoy = new NavigatorEntry("���ڴ��ڱ�");
			basvslvoy.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			basvslvoy.setEditorInput(new BasvslvoyEditorInput("E"));
			basvslvoy.setEditorId(BasvslvoyEditor.class.getName());
			ecd.addChild(basvslvoy);
			
			NavigatorEntry booking = new NavigatorEntry("�»�ֽ");
			booking.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			booking.setEditorInput(new EcdBookingEditorInput());
			booking.setEditorId(EcdBookingEditor.class.getName());
			ecd.addChild(booking);
			
			
			
			
			NavigatorEntry cntrno = new NavigatorEntry("װ�����");
			cntrno.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			cntrno.setEditorInput(new EcdbkcntrEditorInput());
			cntrno.setEditorId(EcdbkcntrEditor.class.getName());
			ecd.addChild(cntrno);
			
			NavigatorEntry bookCheck = new NavigatorEntry("����У��");
			bookCheck.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			bookCheck.setEditorInput(new EcdbkCheckEditorInput());
			bookCheck.setEditorId(EcdbkCheckEditor.class.getName());
			ecd.addChild(bookCheck);
			
			NavigatorEntry blnoquery = new NavigatorEntry("�����ᵥ��Ϣ��ѯ");
			blnoquery.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			blnoquery.setEditorInput(new EcdbookingQueryEditorInput());
			blnoquery.setEditorId(EcdbookingQueryEditor.class.getName());
			ecd.addChild(blnoquery);
			
			
		}
		NavigatorEntry icd = new NavigatorEntry("���ڼ�װ�䵥֤");
		icd.setImage(ImagesContext.getImage(ImagesContext.ICD));
		{
			NavigatorEntry basvslvoy = new NavigatorEntry("���ڴ��ڱ�");
			basvslvoy.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			basvslvoy.setEditorInput(new BasvslvoyEditorInput("I"));
			basvslvoy.setEditorId(BasvslvoyEditor.class.getName());
			icd.addChild(basvslvoy);
			
			NavigatorEntry booking = new NavigatorEntry("�»�ֽ");
			booking.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			booking.setEditorInput(new EcdBookingEditorInput());
			booking.setEditorId(EcdBookingEditor.class.getName());
			icd.addChild(booking);
			
			NavigatorEntry xmlbooking = new NavigatorEntry("XML�»�ֽ");
			xmlbooking.setImage(ImagesContext.getImage(ImagesContext.TARGET_PROFILE_XML_OBJ));
			xmlbooking.setEditorInput(new XmlBookingEditorInput("ICD"));
			xmlbooking.setEditorId(XmlBookingEditor.class.getName());
			icd.addChild(xmlbooking);
			NavigatorEntry cntrno = new NavigatorEntry("װ�����");
			cntrno.setImage(ImagesContext.getImage(ImagesContext.NOTE));
//			cntrno.setEditorInput(new EcdBookingEditorInput());
//			cntrno.setEditorId(EcdBookingEditor.class.getName());
			icd.addChild(cntrno);
			
			NavigatorEntry bookCheck = new NavigatorEntry("����У��");
			bookCheck.setImage(ImagesContext.getImage(ImagesContext.NOTE));
//			cntrno.setEditorInput(new EcdBookingEditorInput());
//			cntrno.setEditorId(EcdBookingEditor.class.getName());
			icd.addChild(bookCheck);
			
			NavigatorEntry blnoquery = new NavigatorEntry("�ᵥ��Ϣ��ѯ");
			blnoquery.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			blnoquery.setEditorInput(new EcdbookingQueryEditorInput());
			blnoquery.setEditorId(EcdbookingQueryEditor.class.getName());
			icd.addChild(blnoquery);
		}
		
		NavigatorEntry frebas = new NavigatorEntry("FRE������Ϣ");
		{
			NavigatorEntry freShipRecord = new NavigatorEntry("��������");
			freShipRecord.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			freShipRecord.setEditorInput(new BaseShipRecordEditorInput());
			freShipRecord.setEditorId(BaseShipRecordEditor.class.getName());
			frebas.addChild(freShipRecord);
			
			NavigatorEntry basePartners = new NavigatorEntry("�������");
			basePartners.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			basePartners.setEditorInput(new BasePartnersEditorInput());
			basePartners.setEditorId(BasePartnersEditor.class.getName());
			frebas.addChild(basePartners);
			
			NavigatorEntry baseCountry = new NavigatorEntry("���Ҵ���");
			baseCountry.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			baseCountry.setEditorInput(new BaseCountryEditorInput());
			baseCountry.setEditorId(BaseCountryEditor.class.getName());
			frebas.addChild(baseCountry);
			
			NavigatorEntry basePort = new NavigatorEntry("�ۿڴ���");
			basePort.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			basePort.setEditorInput(new BasePortEditorInput());
			basePort.setEditorId(BasePortEditor.class.getName());
			frebas.addChild(basePort);
			
			NavigatorEntry basePackage = new NavigatorEntry("��װ����");
			basePackage.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			basePackage.setEditorInput(new BasePackageEditorInput());
			basePackage.setEditorId(BasePackageEditor.class.getName());
			frebas.addChild(basePackage);
			
			NavigatorEntry baseCntr = new NavigatorEntry("���ʹ���");
			baseCntr.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			baseCntr.setEditorInput(new BaseCntrEditorInput());
			baseCntr.setEditorId(BaseCntrEditor.class.getName());
			frebas.addChild(baseCntr);
			
			NavigatorEntry baseLine = new NavigatorEntry("���ߴ���");
			baseLine.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			baseLine.setEditorInput(new BaseLineEditorInput());
			baseLine.setEditorId(BaseLineEditor.class.getName());
			frebas.addChild(baseLine);
			
			NavigatorEntry baseCustoms = new NavigatorEntry("���ع�������");
			baseCustoms.setImage(ImagesContext.getImage(ImagesContext.NOTE));
			baseCustoms.setEditorInput(new BaseCustomsEditorInput());
			baseCustoms.setEditorId(BaseCustomsEditor.class.getName());
			frebas.addChild(baseCustoms);
			
		}
		NavigatorEntry freebd = new NavigatorEntry("FRE����ɢ��");
		NavigatorEntry freibd = new NavigatorEntry("FRE����ɢ��");
		NavigatorEntry freecd = new NavigatorEntry("FRE���ڼ�װ��");
		NavigatorEntry freicd = new NavigatorEntry("FRE���ڼ�װ��");
		
		
//		icd.setEditorInput(new AEditorInput());
//		icd.setEditorId(AEditor.class.getName());
		
		ArrayList<ITreeEntry> list = new ArrayList<ITreeEntry>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		
		list.add(base);
		list.add(ecd);
		list.add(icd);
		
		list.add(frebas);
		list.add(freebd);
		list.add(freibd);
		list.add(freecd);
		list.add(freicd);
		return list;
		
	}
	
	private static DbOperate db ;
	public static DbOperate getDbOperate(){
		if (db == null){
			int dbType = Constants.DBTYPE_MYSQL;
			switch(dbType){
			case Constants.DBTYPE_MYSQL:
				db = new MysqlOperate();
				break;
			case Constants.DBTYPE_ORACLE:
				db = new OracleOperate();
				break;
			case Constants.DBTYPE_SQLSERVER:
				db = new SqlServerOperate();
				break;
			default:
				db = new MysqlOperate();
				break;
			}
		}
		
		return db;
	}

}
