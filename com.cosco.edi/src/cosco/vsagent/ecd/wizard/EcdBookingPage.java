package cosco.vsagent.ecd.wizard;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;

import com.swtdesigner.ResourceManager;

import cosco.vsagent.app.Activator;
import cosco.vsagent.ecd.CntrnoEditorActionGroup;
import cosco.vsagent.mapping.ecd.Ecdbkcargo;
import cosco.vsagent.mapping.ecd.Ecdbkcntr;
import cosco.vsagent.mapping.ecd.Ecdbkparty;
import cosco.vsagent.mapping.ecd.Ecdbooking;
import cosco.vsagent.model.IUser;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.SmsUtil;
import cosco.vsagent.system.TableLabelProviderAdapter;

public class EcdBookingPage extends WizardPage   implements ModifyListener{
	private TableViewer tv,tvparty,tvcntr;
	//BLNO
	private Combo bktypeCombo,ietypeCombo;
	private Text blnoText, mainblnoText, revpenameText,ldpenameText;
	private Text transenameText,dispenameText,delpenameText, pcenameText,blppatText,blcpatText;
	private Text carrtypeText,carrenameText,marketerText,bkcnameText,bkoffcnameText;
	private Text bknoText,blsignatText,blsigndateText,bkcountText,factloadText;
	private Button mfcusignButton,mfblsignButton;
	private Text remarkText,makerText,madetimeText,modifierText,moditimeText;
	//END
	
	//CARGO
	private Text pieceText, pkgnameText, gtweightText, measureText, cargodescText;
	private Text marksText, cargonameText, dgclassText, dguncodeText, rffromText, rftoText;
	private Button dgsignButton, rfsignButton; 
	private Combo rfunitCombo;
	//END
	
	String flag;
	private Ecdbooking ecdbooking;
	
	private String rcvpcode,ldpcode,transport, dispcode, delpcode, pccode;
	private String carrcode, pkgcode, bkoffcode, bkcode,mktcode;
	private String sequence,vslkey_seq;
	private int mainkey ,vslvoykey;
//	private String ,bkcnameText,revpenameText,ldpenameText;
//	private String transenameText,dispenameText,delpenameText;
//	private String pcenameText,,bachsignText,secvslsignText,;
//	private String preloadText;
	private String makercode,modicode;
//	private Text blldpText,bldelpText,bldispText,signText,issendediText,iscuspreText,prenameText;
//	private Text close_afterText,scnoText,billtypeText,is_webText;
	
	protected EcdBookingPage(String pageName) {
		super(pageName);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.getDefault(), "wizard/workset_wiz.gif"));
//		setDescription("什么地方");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		setTitle("提单信息");
		setMessage("提单详细信息", INFORMATION);
		ecdbooking(parent);
		
		if(!getFlag().equals("INSERT")) setValue(getEcdbooking());
		
	}
	
	public void getValue(Ecdbooking bk) {
		bk.setMainkey(-9);
		bk.setVslvoykey(-9);
		bk.setBlno(blnoText.getText());
		bk.setMainblno(mainblnoText.getText());
		bk.setCarrcode(carrcode);
		bk.setCarrename(carrenameText.getText());
		bk.setBkoffcode(bkoffcode);
		bk.setBkoffcname(bkoffcnameText.getText());
		bk.setBkcode(bkcode);
		bk.setBkcname(bkcnameText.getText());
		bk.setRcvpcode(rcvpcode);
		bk.setRevpename(revpenameText.getText());
		bk.setLdpcode(ldpcode);
		bk.setLdpename(ldpenameText.getText());
		bk.setTransport(transport);
		bk.setTransename(transenameText.getText());
		bk.setDispcode(dispcode);
		bk.setDispename(dispenameText.getText());
		bk.setDelpcode(delpcode);
		bk.setDelpename(delpenameText.getText());
		bk.setCarrtype(carrtypeText.getText());
		bk.setPccode(pccode);
		bk.setPcename(pcenameText.getText());
		bk.setBktype(String.valueOf(bktypeCombo.getData(bktypeCombo.getText())));
		bk.setBachsign("0");
		bk.setSecvslsign("0");
		bk.setMktcode(mktcode);
		bk.setMarketer(marketerText.getText());
		bk.setPreload("");
		bk.setFactload("");
		bk.setBlppat("");
		bk.setBlcpat("");
		bk.setBlsignat("");
		bk.setBlsigndate("");
		bk.setBkcount(bkcountText.getText());
		bk.setMfcusign(mfcusignButton.getSelection()== true?"1":"0");
		bk.setMfblsign(mfblsignButton.getSelection()== true?"1":"0");
		bk.setMakercode(makercode);
		bk.setMaker(makerText.getText());
		bk.setMadetime(Timestamp.valueOf(madetimeText.getText()));
		bk.setModicode(modicode);
		bk.setModifier(modifierText.getText());
		bk.setModitime(Timestamp.valueOf(moditimeText.getText()));
		bk.setRemark(remarkText.getText());
		bk.setBlldp("");
		bk.setBldelp("");
		bk.setBldisp("");
		bk.setSign("0");
		bk.setIssendedi("0");
		bk.setIetype(String.valueOf(ietypeCombo.getData(ietypeCombo.getText())));
		bk.setIscuspre(null);
		bk.setPrename(null);
		bk.setCloseAfter("0");
		bk.setIsweb("0");
		bk.setVslkey_seq(vslkey_seq);
		bk.setSequence(sequence);
		
		bk.setCargo(getValueCargo());
		bk.setCntr(getValueCntr());
		bk.setParty(getValueParty());
	}
	private List<Ecdbkparty> getValueParty(){
		List<Ecdbkparty> party = new ArrayList<Ecdbkparty>();
		for(int i = 0;i<tvparty.getTable().getItemCount();i++){
			Ecdbkparty o = (Ecdbkparty)tvparty.getElementAt(i);
			party.add(o);
		}
		return party;
	}
	private List<Ecdbkcntr> getValueCntr(){
		List<Ecdbkcntr> cntr = new ArrayList<Ecdbkcntr>();
		for(int i = 0;i<tvcntr.getTable().getItemCount();i++){
			Ecdbkcntr o = (Ecdbkcntr) tvcntr.getElementAt(i);
			System.out.println(o.getCntrno());
			cntr.add(o);
		}
		return cntr;
	}
	private List<Ecdbkcargo> getValueCargo(){
		List<Ecdbkcargo> cargo = new ArrayList<Ecdbkcargo>();
		Ecdbkcargo o = new Ecdbkcargo();
		o.setMainkey(null);
		o.setBkkey(null);
		o.setVslvoykey(null);
		o.setCargoname(cargonameText.getText());
		o.setTypecode(null);
		o.setTypename(null);
		o.setPiece(Integer.valueOf(pieceText.getText()));
	    o.setPkgcode(pkgcode);
	    o.setPkgname(pkgnameText.getText());
	    o.setNtweight(0.);
	    o.setGtweight(Double.valueOf(gtweightText.getText()));
	    o.setDgsign(dgsignButton.getSelection() == true?"1":"0");
	    o.setDguncode(dguncodeText.getText());
	    o.setDgpageno("");
	    o.setDgclass(dgclassText.getText());
	    o.setDgflag("0");
	    o.setMarks(marksText.getText());
	    o.setCargodesc(cargodescText.getText());
	    o.setRfsign(rfsignButton.getSelection() == true?"1":"0");
	    o.setRffrom(rffromText.getText());
	    o.setRfto(rftoText.getText());
	    o.setRfunit(rfunitCombo.getText());
	    o.setMarks(marksText.getText());
	    o.setMeasure(Double.valueOf(measureText.getText()));
	    o.setVslkey_seq(vslkey_seq);
	    o.setBlno_seq(sequence);
	    /* private String blpkgname;
	     private String blgtweight;
	     private String blmeasure;
	    */
	    cargo.add(o);
		return cargo;
	}
	private void setValue(Ecdbooking bk) {
		//BLNO
		bktypeCombo.setText(new SmsUtil().bktype(bk.getBktype(), true));
		ietypeCombo.setText(new SmsUtil().ietype(bk.getIetype(), true));
		blnoText.setText(bk.getBlno());
		mainblnoText.setText(bk.getMainblno());
		rcvpcode = bk.getRcvpcode();
		revpenameText.setText(bk.getRevpename());
		ldpcode = bk.getLdpcode();
		ldpenameText.setText(bk.getLdpename());
		transport = bk.getTransport();
		transenameText.setText(bk.getTransename());
		dispcode = bk.getDispcode();
		dispenameText.setText(bk.getDispename());
		delpcode = bk.getDelpcode();
		delpenameText.setText(bk.getDelpename());
		pccode = bk.getPccode();
		pcenameText.setText(bk.getPcename());
		blppatText.setText(bk.getBlppat());
		blcpatText.setText(bk.getBlcpat());
		carrtypeText.setText(bk.getCarrtype());
		carrcode = bk.getCarrcode();
		carrenameText.setText(bk.getCarrename());
		marketerText.setText(bk.getMaker());
		bkcode = bk.getBkcode();
		mktcode = bk.getMktcode();
		bkcnameText.setText(bk.getBkcname());
		bkoffcode = bk.getBkoffcode();
		bkoffcnameText.setText(bk.getBkoffcname());
		bknoText.setText(bk.getBkno());
		blsignatText.setText(bk.getBlsignat());
		blsigndateText.setText(bk.getBlsigndate());
		bkcountText.setText(bk.getBkcount());
		factloadText.setText(bk.getFactload());
		if(bk.getMfcusign().equals("1")){
			mfcusignButton.setSelection(true);
		}
		if(bk.getMfblsign().equals("1")){
			mfblsignButton.setSelection(true);
		}
		remarkText.setText(bk.getRemark());
		makercode = bk.getMakercode();
		makerText.setText(bk.getMaker());
		madetimeText.setText(bk.getMadetime().toLocaleString());
		modicode = bk.getModicode();
		modifierText.setText(bk.getModifier());
		moditimeText.setText(bk.getModitime()==null?"":bk.getModitime().toLocaleString()); 
		//END
		
		//CARGO
		if(bk.getCargo() != null && bk.getCargo().size()>0){
			//仅支持一票货物
			Ecdbkcargo cargo = bk.getCargo().get(0);
			pieceText.setText(cargo.getPiece().toString());
			pkgcode = cargo.getPkgcode();
			pkgnameText.setText(cargo.getPkgname());
			gtweightText.setText(cargo.getGtweight().toString());
			measureText.setText(cargo.getMeasure().toString());
			cargodescText.setText(cargo.getCargodesc());
			marksText.setText(cargo.getMarks());
			cargonameText.setText(cargo.getCargoname());
			dgclassText.setText(StringUtils.stripToEmpty(cargo.getDgclass()));
			dguncodeText.setText(cargo.getDguncode());
			rffromText.setText(cargo.getRffrom());
			rftoText.setText(cargo.getRfto());
			if(cargo.getDgsign().equals("1")){
				dgsignButton.setSelection(true);
			}
			if(cargo.getRfsign().equals("1")){
				rfsignButton.setSelection(true);
			}
			rfunitCombo.setText(cargo.getRfunit());
		}

		//END
	}
	
	public void ecdbooking(Composite parent){
		final TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		
		tabFolder.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent e){ //按钮的单击事件
//            	tt(tabFolder.getSelectionIndex());
            	System.out.println(tabFolder.getSelectionIndex());
            }
        });
		
		GridData gridTab = new GridData(GridData.FILL_BOTH);
		gridTab.horizontalSpan = 2;// 水平抢占两列
		tabFolder.setLayoutData(gridTab);
		// 定义第一个选项卡项
		final TabItem item1 = new TabItem(tabFolder, SWT.NONE);
		item1.setText("提单信息");
		{
			Composite comp1 = new Composite(tabFolder, SWT.BORDER);
			item1.setControl(comp1);
			comp1.setLayout(new GridLayout());
			
			Group group = new Group(comp1, SWT.BORDER);
			group.setText("");
			group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			
			group.setLayout(new GridLayout(8, false));
			blno(group);
			
			Group group1 = new Group(comp1, SWT.BORDER);
			group1.setText("货物信息");
			group1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			group1.setLayout(new GridLayout(8, false));
			cargo(group1);
			
			Group group2 = new Group(comp1, SWT.BORDER);
			group2.setText("");
			group2.setLayoutData(new GridData(GridData.FILL_BOTH));
			group2.setLayout(new GridLayout(8, false));
			blnobottom(group2);

		}
		{
			TabItem item = new TabItem(tabFolder, SWT.NONE);// 第二个选项页
			item.setText("收发货人信息");
			// 在TabFolder上创建一个Composite容器
			Composite comp = new Composite(tabFolder, SWT.NONE);
			comp.setLayout(new FillLayout());
			item.setControl(comp);
			
			// 在Composite上创建两个文本框
			party(comp);
		}
		
		{
			TabItem item = new TabItem(tabFolder, SWT.NONE);// 第二个选项页
			item.setText("集装箱信息");
			// 在TabFolder上创建一个Composite容器
			Composite comp = new Composite(tabFolder, SWT.NONE);
			comp.setLayout(new FillLayout());
			item.setControl(comp);
			
			cntrno(comp);

		}
		
		this.setControl(tabFolder);
		checkAddListener( );
		
	}
	public void checkAddListener( ){
		blnoText.addModifyListener(this);
		ldpenameText.addModifyListener(this);
		dispenameText.addModifyListener(this);
		delpenameText.addModifyListener(this);
		pcenameText.addModifyListener(this);
		pkgnameText.addModifyListener(this);
	}
	public void blno(Group top){
//		Composite top = new Composite(shell, SWT.BORDER);
//		top.setLayout(new GridLayout(8, false));
		new Label(top, SWT.NONE).setText("装运方式");
		Composite photoComp = new Composite(top, SWT.BORDER);
		photoComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		photoComp.setLayout(new GridLayout(2,false));
		bktypeCombo = new Combo(photoComp, SWT.BORDER|SWT.READ_ONLY);
		bktypeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		bktypeCombo.add("整箱");
		bktypeCombo.add("拼箱主票");
		bktypeCombo.add("拼箱分票");
		bktypeCombo.setData("整箱", "1");
		bktypeCombo.setData("拼箱主票", "2");
		bktypeCombo.setData("拼箱分票", "3");
		
		bktypeCombo.addSelectionListener(new SelectionListener() {
		        
	            public void widgetDefaultSelected(SelectionEvent e) {
	                setSelectedValue(
	                        ((Combo)e.widget).getSelectionIndex());
	            }
	        
	            public void widgetSelected(SelectionEvent e) {
	                setSelectedValue(
	                        ((Combo)e.widget).getSelectionIndex());
	            }
	        
	            private void setSelectedValue(int value) {
	            	if(String.valueOf(value).equals("1")){
	            		mainblnoText.setEnabled(true);
	            	}else{
	            		mainblnoText.setEnabled(false);
	            		mainblnoText.setText("");
	            	}
	            	
	            }
	        });
		 
		ietypeCombo = new Combo(photoComp, SWT.BORDER|SWT.READ_ONLY);
		ietypeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ietypeCombo.add("本港进出口");
		ietypeCombo.add("国际中转");
		ietypeCombo.add("调拨空箱");
		ietypeCombo.add("过境");
		ietypeCombo.add("海铁联运");
		ietypeCombo.add("内支线");
		ietypeCombo.add("通运");
		ietypeCombo.add("直通");
		
		ietypeCombo.setData("本港进出口", 1);
		ietypeCombo.setData("国际中转", 3);
		ietypeCombo.setData("调拨空箱", 5);
		ietypeCombo.setData("过境", 4);
		ietypeCombo.setData("海铁联运", 7);
		ietypeCombo.setData("内支线", 2);
		ietypeCombo.setData("通运", 6);
		ietypeCombo.setData("直通", 8);
		
		new Label(top, SWT.NONE).setText("提 单 号");
		blnoText = new SmsUtil().createText(top, SWT.BORDER);
		GridData sss = new GridData(GridData.FILL_HORIZONTAL);
		sss.widthHint = 120;
		blnoText.setLayoutData(sss);	
		
		new Label(top, SWT.NONE).setText("主提单号");
		mainblnoText = new SmsUtil().createText(top, SWT.BORDER);
		GridData ld = new GridData(GridData.FILL_HORIZONTAL);
		ld.widthHint = 120;
		mainblnoText.setLayoutData(ld);
		mainblnoText.setText("");
		mainblnoText.setEnabled(false);
		new Label(top, SWT.NONE).setText("接 货 港");
		revpenameText = new SmsUtil().createText(top, SWT.BORDER);
		revpenameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		revpenameText.addFocusListener(new FocusEventC("basport"));
		
		new Label(top, SWT.NONE).setText("装 货 港");
		ldpenameText = new SmsUtil().createText(top, SWT.BORDER);
		ldpenameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		ldpenameText.addFocusListener(new FocusEventC("basport"));
		
		new Label(top, SWT.NONE).setText("中 转 港");
		transenameText = new SmsUtil().createText(top, SWT.BORDER);
		transenameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		transenameText.addFocusListener(new FocusEventC("basport"));
		
		new Label(top, SWT.NONE).setText("卸 货 港");
		dispenameText = new SmsUtil().createText(top, SWT.BORDER);
		dispenameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		dispenameText.addFocusListener(new FocusEventC("basport"));
		new Label(top, SWT.NONE).setText("目 的 港");
		delpenameText = new SmsUtil().createText(top, SWT.BORDER);
		delpenameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		delpenameText.addFocusListener(new FocusEventC("basport"));
		
		new Label(top, SWT.NONE).setText("付费方式");
		pcenameText = new SmsUtil().createText(top, SWT.BORDER);
		pcenameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("预付地点");
		blppatText = new SmsUtil().createText(top, SWT.BORDER);
		blppatText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("到付地点");
		blcpatText = new SmsUtil().createText(top, SWT.BORDER);
		blcpatText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("运输条款");
		carrtypeText = new SmsUtil().createText(top, SWT.BORDER);
		carrtypeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		carrtypeText.addFocusListener(new FocusEventC("Bascarry"));
		
		new Label(top, SWT.NONE).setText("承 运 人");
		carrenameText = new SmsUtil().createText(top, SWT.BORDER);
		carrenameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 7));
//		carrenameText.addFocusListener(new FocusEventC("basparty"));
		
		new Label(top, SWT.NONE).setText("揽 货 人");
		marketerText = new SmsUtil().createText(top, SWT.BORDER);
		marketerText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("订 舱 人");
		bkcnameText = new SmsUtil().createText(top, SWT.BORDER);
		bkcnameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("订舱部门");
		bkoffcnameText = new SmsUtil().createText(top, SWT.BORDER);
		bkoffcnameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("订舱编号");
		bknoText = new SmsUtil().createText(top, SWT.BORDER);
		bknoText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(top, SWT.NONE).setText("签发地点");
		blsignatText = new SmsUtil().createText(top, SWT.BORDER);
		blsignatText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("签发日期");
		blsigndateText = new SmsUtil().createText(top, SWT.BORDER);
		blsigndateText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("提单份数");
		bkcountText = new SmsUtil().createText(top, SWT.BORDER);
		bkcountText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		
		Composite ss = new Composite(top, SWT.BORDER);
		ss.setLayoutData(createGridData(GridData.FILL_HORIZONTAL, 2));
		ss.setLayout(new FillLayout());
		
		new Label(ss, SWT.NONE).setText("海关舱单");
		mfcusignButton = new Button(ss, SWT.CHECK);
		
		new Label(ss, SWT.NONE).setText("目的港舱单");
		mfblsignButton = new Button(ss, SWT.CHECK);
		
		new Label(top, SWT.NONE).setText("实装箱量");
		factloadText = new Text(top, SWT.BORDER|SWT.READ_ONLY);
		factloadText.setEnabled(false);
		factloadText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 7));
		
	}

	public void cargo(Group top){
	
		new Label(top,SWT.BORDER).setText("件　　数");
		pieceText = new Text(top, SWT.BORDER);
		pieceText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top,SWT.BORDER).setText("包　　装");
		pkgnameText = new SmsUtil().createText(top, SWT.BORDER);
//		pkgnameText.addFocusListener(new FocusEventC("baspackage"));
		
		pkgnameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top,SWT.BORDER).setText("毛　　重");
		gtweightText = new Text(top, SWT.BORDER|SWT.RIGHT);
		gtweightText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top,SWT.BORDER).setText("体　　积");
		measureText = new Text(top, SWT.BORDER|SWT.RIGHT);
		measureText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(top,SWT.BORDER).setText("货　　描");
		cargodescText = new SmsUtil().createText(top, SWT.BORDER);
		cargodescText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 7));
		
		new Label(top,SWT.BORDER).setText("唛　　头");
		marksText = new SmsUtil().createText(top, SWT.BORDER);
		marksText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 7));
		
		new Label(top,SWT.BORDER).setText("货　　名");
		cargonameText = new SmsUtil().createText(top, SWT.BORDER);
		cargonameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 7));
		
		new Label(top, SWT.BORDER).setText("危品标示");
		
		Composite gp1 = new Composite(top, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = 28;// Composite默认的高度太高,故手工设定高度为20像素
		gp1.setLayoutData(gridData);
		gp1.setLayout(new RowLayout());
		
		dgsignButton = new Button(gp1, SWT.CHECK);
		
		new Label(gp1, SWT.BORDER).setText("危品级别");
		dgclassText = new Text(gp1, SWT.BORDER);
		new Label(gp1, SWT.BORDER).setText("危品编号");
		dguncodeText = new Text(gp1, SWT.BORDER);
		 
		new Label(top, SWT.BORDER).setText("冷藏标示");
		
		Composite gp2 = new Composite(top, SWT.BORDER);
		GridData gridData1 = new GridData(GridData.FILL_HORIZONTAL);
		gridData1.horizontalSpan = 7;
		gridData1.heightHint = 28;// Composite默认的高度太高,故手工设定高度为20像素
		gp2.setLayoutData(gridData);
		gp2.setLayout(new GridLayout(8,false));
		rfsignButton = new Button(gp2, SWT.CHECK);
		new Label(gp2, SWT.BORDER).setText("FROM");
		rffromText = new Text(gp2, SWT.BORDER);
		
		new Label(gp2, SWT.BORDER).setText("TO");
		rftoText = new Text(gp2, SWT.BORDER);
		new Label(gp2, SWT.BORDER).setText("单位");
		rfunitCombo = new Combo(gp2, SWT.BORDER|SWT.READ_ONLY);
		rfunitCombo.add("C");
		rfunitCombo.setData("C", "C");
		rfunitCombo.add("F");
		rfunitCombo.setData("F", "F");

		top.layout();
		
	}
	public void party(Composite parent){
		buildControl(parent, "party");
	}
	public void cntrno(Composite parent){
		buildControl(parent, "cntrno");
	}
	
	public void buildControl(Composite parent, String flag){
		ViewForm topComp = new ViewForm(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		if(flag.equals("party")){
			createTableViewerParty(topComp,flag);
//			tv = tvparty;
		}
		if(flag.equals("cntrno")){
			createTableViewerCntr(topComp,flag);
//			tv = tvparty;
		}
		
//		createTableViewer(topComp,flag);
		tv.setContentProvider(new SmsContentProvider());
		tv.setLabelProvider(new TableViewerLabelProvider());
		CntrnoEditorActionGroup actionGroup = new CntrnoEditorActionGroup(tv);
		
		//将数据传过去
		actionGroup.setEcdbooking(getEcdbooking());
		actionGroup.setFlag(flag);
		
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		
		actionGroup.fillActionToolBars(toolBarManager);
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
		actionGroup.fireFirstAction();
	}
	private void createTableViewerParty(ViewForm parent, String flag){
		tvparty = new TableViewer(parent, SWT.MULTI|SWT.BORDER|SWT.FULL_SELECTION);
		Table table = tvparty.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		if (flag.equals("party")){
			layout.addColumnData(new ColumnWeightData(20));
			new TableColumn(table, SWT.NONE).setText("参与方类型");
			layout.addColumnData(new ColumnWeightData(20));
			new TableColumn(table, SWT.NONE).setText("参与方名称");
			layout.addColumnData(new ColumnWeightData(72));
			new TableColumn(table, SWT.NONE).setText("参与方信息");
			
		}
		if (flag.equals("cntrno")){
			layout.addColumnData(new ColumnWeightData(20));
			new TableColumn(table, SWT.NONE).setText("箱型");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("装箱");
			layout.addColumnData(new ColumnWeightData(38));
			new TableColumn(table, SWT.NONE).setText("箱号");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("件数");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("包装");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("体积");
			layout.addColumnData(new ColumnWeightData(28));
			new TableColumn(table, SWT.NONE).setText("皮重");
			layout.addColumnData(new ColumnWeightData(28));
			new TableColumn(table, SWT.NONE).setText("封号");
			layout.addColumnData(new ColumnWeightData(38));
			new TableColumn(table, SWT.NONE).setText("场站");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("贝位");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("通风量");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("通风单位");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("SOC");
			layout.addColumnData(new ColumnWeightData(58));
			new TableColumn(table, SWT.NONE).setText("备注");
		}
		
		tv = tvparty;
		
	}
	private void createTableViewerCntr(ViewForm parent, String flag){
		tvcntr = new TableViewer(parent, SWT.MULTI|SWT.BORDER|SWT.FULL_SELECTION);
		Table table = tvcntr.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		if (flag.equals("party")){
			layout.addColumnData(new ColumnWeightData(20));
			new TableColumn(table, SWT.NONE).setText("参与方类型");
			layout.addColumnData(new ColumnWeightData(20));
			new TableColumn(table, SWT.NONE).setText("参与方名称");
			layout.addColumnData(new ColumnWeightData(72));
			new TableColumn(table, SWT.NONE).setText("参与方信息");
			
		}
		if (flag.equals("cntrno")){
			layout.addColumnData(new ColumnWeightData(20));
			new TableColumn(table, SWT.NONE).setText("箱型");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("装箱");
			layout.addColumnData(new ColumnWeightData(38));
			new TableColumn(table, SWT.NONE).setText("箱号");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("件数");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("包装");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("体积");
			layout.addColumnData(new ColumnWeightData(28));
			new TableColumn(table, SWT.NONE).setText("皮重");
			layout.addColumnData(new ColumnWeightData(28));
			new TableColumn(table, SWT.NONE).setText("封号");
			layout.addColumnData(new ColumnWeightData(38));
			new TableColumn(table, SWT.NONE).setText("场站");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("贝位");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("通风量");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("通风单位");
			layout.addColumnData(new ColumnWeightData(18));
			new TableColumn(table, SWT.NONE).setText("SOC");
			layout.addColumnData(new ColumnWeightData(58));
			new TableColumn(table, SWT.NONE).setText("备注");
		}
		tv = tvcntr;
	}
	// 生成GridData对象的重复代码太多，写成一个方法可以减少程序的行数，用起来也方便些
	private static GridData createGridData(int style, int horizontalSpan) {
		GridData gridData = new GridData(style);
		gridData.horizontalSpan = horizontalSpan;
		return gridData;
	}
	
	private static GridData createGridData(int style, int horizontalSpan, int verticalSpan) {
		GridData gridData = new GridData(style);
		gridData.horizontalSpan = horizontalSpan;
		gridData.verticalSpan = verticalSpan;
		return gridData;
	}
	
	public void blnobottom(Group top){
		Label memoLabel = new Label(top, SWT.NONE);
		memoLabel.setText("备 注");
		memoLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		remarkText = new Text(top, SWT.BORDER | SWT.WRAP);
		remarkText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL| GridData.FILL_VERTICAL ,7));
		
		new Label(top, SWT.NONE).setText("录 入 人");
		makerText = new Text(top, SWT.BORDER);
		makerText.setEnabled(false);
		makerText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("录入时间");
		madetimeText = new Text(top, SWT.BORDER);
		madetimeText.setEnabled(false);		
		if(flag.equals("INSERT")){
			makerText.setText(getIuser().getName());
			madetimeText.setText(new Date().toLocaleString());
		}
		
		madetimeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("修 改 人");
		
		modifierText = new Text(top, SWT.BORDER);
		modifierText.setEnabled(false);
		modifierText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("修改时间");
		moditimeText = new Text(top, SWT.BORDER);
		moditimeText.setEnabled(false);
		moditimeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		top.layout();
	}

	public void setEcdbooking(Ecdbooking ecdbooking) {
		// TODO Auto-generated method stub
		this.ecdbooking = ecdbooking;
	}

	public Ecdbooking getEcdbooking() {
		return ecdbooking;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	private static final class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int col){
			if (element == null) return "";
			
			if (element instanceof Ecdbkcntr){
				Ecdbkcntr o = (Ecdbkcntr)element;
				switch (col){
				case 0: return o.getCntrtype();
				case 1: return o.getFlesign();
				case 2: return o.getCntrno();
				case 3: return o.getPiece().toString();
				case 4: return o.getPkgcode();
				case 5: return o.getMeasure().toString();
				case 6: return o.getTare().toString();
				case 7: return o.getSealno();
				case 8: return o.getDockcode();
				case 9: return "";
				case 10: return o.getVent();
				case 11: return o.getVentunit();
				case 12: return o.getSocsign().equals("1")?"是":"";
				case 13: return o.getRemark();
				}
			}
			if (element instanceof Ecdbkparty){
				Ecdbkparty o = (Ecdbkparty)element;
				switch (col){
				case 0: return o.getPartytype();
				case 1: return o.getPartyname();
				case 2: return o.getPartymsg();

				}
			}
				return "====";
		}
	}
	
	public class FocusEventC extends FocusAdapter {
		private String tableName;
		
		public FocusEventC(String table) {
			
		}
		
		public void focusGained(FocusEvent e){
			
			System.out.println("begin");
			Text t = (Text)e.widget;
			
		}
		
		public void focusLost(FocusEvent e){
			/**
			 * System.out.println("over");
			 */
		}
	}

	public void modifyText(ModifyEvent e) {
		setPageComplete(false);
		if(blnoText.getText().equals("")){
			setErrorMessage("提单号不能为空");
			return ;
		}
		
		if(ldpenameText.getText().equals("")){
			setErrorMessage("装货港不能为空");
			return;
		}
		
		if(dispenameText.getText().equals("")){
			setErrorMessage("卸货港不能为空");
			return;
		}
		if(delpenameText.getText().equals("")){
			setErrorMessage("目的港不能为空");
			return;
		}
		
		if(pcenameText.getText().equals("")){
			setErrorMessage("付费方式不能为空");
			return;
		}
		
		if(pkgnameText.getText().equals("")){
			setErrorMessage("货物包装代码不能为空");
			return;
		}
		
		setErrorMessage(null);
		setPageComplete(true);
	}

	public Text getBlnoText() {
		return blnoText;
	}

	public Text getLdpenameText() {
		return ldpenameText;
	}

	public Text getDispenameText() {
		return dispenameText;
	}

	public Text getDelpenameText() {
		return delpenameText;
	}

	public Text getPcenameText() {
		return pcenameText;
	}

	public Text getPkgnameText() {
		return pkgnameText;
	}
	
	public IUser getIuser(){
		Context ctx = Context.getInstance();
		return ctx.getCurrentUser();
	}
}
