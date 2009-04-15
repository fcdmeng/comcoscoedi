package com.freqds.view.base;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.freqds.base.EdiControl;

public class BaseShipRecordView extends ViewPart {

	public static final String ID = "com.freqds.view.base.BaseShipRecordView"; //$NON-NLS-1$
	// IMO号
	private Text imoText;
	// 船舶呼号
	private Text call_SignText;
	// 英文船名
	private Text ship_Name_EnText;
	// 中文船名
	private Text ship_Name_CnText;
	//船舶运营企业代码
	private Text co_CodeText;
	//运营企业英文名称
	private Text Co_Name_EnText;
	//运营企业中文名称
	private Text Co_Name_CnText;
	//船旗国
	private Combo nationalityCombo;
	//船舶国籍证书编号
	private Text certification_NoText;
	//国籍证书签发日期
	private Text certification_DateText;
	
	//船舶建造日期
	private Text Date_Of_BuiltText;
	//船籍港代码
	private Combo Hailing_PortCombo;
	//等级号
	private Text class_LefterText;
	//等级证书号
	private Text classification_NoText;
	//烟囱颜色
	private Text funnel_ColorText;
	//船体颜色
	private Text ship_ColorText;
	
	//船舶种类代码
	private Combo description_FlagCombo;
	//运营性质
	private Combo liner_FalgCombo;
	//马力
	private Text HorsepowerText;
	//航速
	private Text SpeedText;
	//总吨位
	private Text gross_TonnageText;
	//净吨位
	private Text net_TonnageText;
	//载重吨位
	private Text deadweight_TonnageText;
	//TPI/TPC
	private Text tpi_TpcText;
	//船长
	private Text lengthText;
	//船宽
	private Text breadthText;
	//船高
	private Text depthText;
	//满吃水
	private Text Deep_DraftText;
	//空吃水
	private Text light_DraftText;
	//舱口数
	private Text door_NumText;
	//起重设备
	private Text gargo_GearText;
	//包装舱容
	private Text bale_CapacityText;
	//散装舱容
	private Text grain_CapacityText;
	//舱内TEU
	private Text ceiling_TeuText;
	//甲板TEU
	private Text deck_TeuText;
	//船员限额
	private Text crew_LimitText;
	//旅客限额
	private Text passenger_LimitText;
	//船舶监管类型
	private Combo monitor_Ship_Type_CodeCombo;
	
	//备案人代码
	private Text recorder_CodeText;
	//备案人
	private Text recorderText;
	//关区代码
	private Combo customs_CodeCombo;
	//通讯方式
	private Combo communication_TypeCombo;
	//通讯号码
	private Text communication_NumberText;
	//录入时间
	private Text insert_Time;
	//备注
	private Text memoText;
	
	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		createDialogArea(container);
		createActions();
		initializeToolBar();
		initializeMenu();
		
	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	protected Control createDialogArea(Composite parent) {
		EdiControl control = new EdiControl();
		//setTitle("船舶规范");
		//setMessage("请在文本框中输入文字");
		//setMessage("对话框的说明文字", IMessageProvider.INFORMATION);
		// setErrorMessage("输入错误");
		Composite topComp = new Composite(parent, SWT.BORDER);

		topComp.setLayout(new GridLayout(4, false));
		topComp.setLayoutData(new GridData(GridData.FILL_BOTH));

		control.createLabel(topComp, "IMO");
		imoText = control.createTextField(topComp);
		control.setInfoDecoration(imoText, "IMO号", true);
		imoText.addModifyListener(new MyModifyListener());
		
		control.createLabel(topComp, "船舶呼号");
		call_SignText = control.createTextField(topComp);
		control.setInfoDecoration(call_SignText, "船舶呼号", true);
		call_SignText.addModifyListener(new MyModifyListener());
		
		control.createLabel(topComp, "英文船名");
		ship_Name_EnText =  control.createTextField(topComp);
		control.setInfoDecoration(ship_Name_EnText, "英文船名", true);
		ship_Name_EnText.addModifyListener(new MyModifyListener());
		
		control.createLabel(topComp, "中文船名");
		ship_Name_CnText = control.createTextField(topComp);

		control.createLabel(topComp, "船舶运营企业代码");
		co_CodeText = control.createTextField(topComp);
		control.setInfoDecoration(co_CodeText, "船舶运营企业代码", true);
		
		control.createLabel(topComp, "运营企业英文名称");
		Co_Name_EnText = control.createTextField(topComp);
		control.setInfoDecoration(Co_Name_EnText, "运营企业英文名称", true);
		
		EdiControl.createLabel(topComp, "运营企业中文名称");
		Co_Name_CnText = control.createTextField(topComp);

		control.createLabel(topComp, "船旗国");
		nationalityCombo = control.createCombo(topComp);
		control.setInfoDecoration(nationalityCombo, "船旗国", true);
		
		control.createLabel(topComp, "船舶国籍证书编号");
		certification_NoText =control.createTextField(topComp);

		control.createLabel(topComp, "国籍证书签发日期");
		certification_DateText = control.createTextField(topComp);
		
		control.createLabel(topComp, "船舶建造日期");
		Date_Of_BuiltText = control.createTextField(topComp);
		
		control.createLabel(topComp, "船籍港代码");
		Hailing_PortCombo =  control.createCombo(topComp);
		control.setInfoDecoration(Hailing_PortCombo, "船籍港代码", true);
		
		control.createLabel(topComp, "等级号");
		class_LefterText = control.createTextField(topComp);
		
		control.createLabel(topComp, "等级证书号");
		classification_NoText = control.createTextField(topComp);
		
		control.createLabel(topComp, "船体颜色");
		ship_ColorText = control.createTextField(topComp);

		control.createLabel(topComp, "烟囱颜色");
		funnel_ColorText = control.createTextField(topComp);
		
		control.createLabel(topComp, "船舶种类代码");
		description_FlagCombo = control.createCombo(topComp);
		control.setInfoDecoration(description_FlagCombo, "船舶种类代码", true);

		control.createLabel(topComp, "运营性质");
		liner_FalgCombo = control.createCombo(topComp);
		control.setInfoDecoration(liner_FalgCombo, "运营性质", true);
		liner_FalgCombo.setItems(new String [] {"班轮", "非班轮"});
		liner_FalgCombo.setData("班轮", "1");
		liner_FalgCombo.setData("非班轮", "2");
		
		control.createLabel(topComp, "马力");
		HorsepowerText = control.createTextField(topComp);
		
		control.createLabel(topComp, "航速");
		SpeedText = control.createTextField(topComp);

		control.createLabel(topComp, "总吨位");
		gross_TonnageText = control.createTextField(topComp);
		control.setInfoDecoration(gross_TonnageText, "总吨位", true);
		
		control.createLabel(topComp, "净吨位");
		net_TonnageText = control.createTextField(topComp);
		control.setInfoDecoration(net_TonnageText, "净吨位", true);

		control.createLabel(topComp, "载重吨位");
		deadweight_TonnageText = control.createTextField(topComp);
		control.setInfoDecoration(deadweight_TonnageText, "载重吨位", true);
		
		control.createLabel(topComp, "TPI/TPC");
		tpi_TpcText = control.createTextField(topComp);

		control.createLabel(topComp, "船长");
		lengthText = control.createTextField(topComp);
		control.setInfoDecoration(lengthText, "船长", true);

		control.createLabel(topComp, "船宽");
		breadthText = control.createTextField(topComp);
		control.setInfoDecoration(breadthText, "船宽", true);

		control.createLabel(topComp, "船高");
		depthText = control.createTextField(topComp);
		control.setInfoDecoration(depthText, "船高", true);

		control.createLabel(topComp, "满吃水");
		Deep_DraftText = control.createTextField(topComp);

		control.createLabel(topComp, "空吃水");
		light_DraftText = control.createTextField(topComp);
		
		control.createLabel(topComp, "舱口数");
		door_NumText = control.createTextField(topComp);
		
		control.createLabel(topComp, "起重设备");
		gargo_GearText = control.createTextField(topComp);
		
		control.createLabel(topComp, "包装舱容");
		bale_CapacityText = control.createTextField(topComp);
		
		control.createLabel(topComp, "散装舱容");
		grain_CapacityText = control.createTextField(topComp);
		
		control.createLabel(topComp, "舱内TEU");
		ceiling_TeuText = control.createTextField(topComp);

		control.createLabel(topComp, "甲板TEU");
		deck_TeuText = control.createTextField(topComp);
		
		control.createLabel(topComp, "船员限额");
		crew_LimitText = control.createTextField(topComp);

		control.createLabel(topComp, "旅客限额");
		passenger_LimitText = control.createTextField(topComp);
	
		control.createLabel(topComp, "船舶监管类型");
		monitor_Ship_Type_CodeCombo = control.createCombo(topComp);
		
		
		control.createLabel(topComp, "备案人代码");
		recorder_CodeText = control.createTextField(topComp);
		control.setInfoDecoration(recorder_CodeText, "备案人代码", true);

		control.createLabel(topComp, "备案人");
		recorderText = control.createTextField(topComp);
		control.setInfoDecoration(recorderText, "备案人", true);
		/*
		 * createLabel(topComp, "备案时间"); Text RECORD_TIME = new
		 * Text(topComp, SWT.BORDER); RECORD_TIME.setLayoutData(new
		 * GridData(GridData.FILL_HORIZONTAL));
		 */
		
		control.createLabel(topComp, "关区代码");
		customs_CodeCombo = control.createCombo(topComp);
		control.setInfoDecoration(customs_CodeCombo, "关区代码", true);

		control.createLabel(topComp, "通讯方式");
		communication_TypeCombo = control.createCombo(topComp);
	
		control.createLabel(topComp, "通讯号码");
		communication_NumberText = control.createTextField(topComp);

		control.createLabel(topComp, "录入时间");
		insert_Time = control.createTextField(topComp);
	
		Label introLabel = new Label(topComp, SWT.NONE);
		introLabel
				.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		introLabel.setText("备注");

		memoText = new Text(topComp, SWT.BORDER);
		memoText.setLayoutData(control.createGridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL, 3, 5));

		return topComp;
	}

	
	
	// 注释说明请参数NamePage页的监听器，两者现实相似。
	private class MyModifyListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (StringUtils.isBlank(imoText.getText())) {
				//setErrorMessage("IMO不允许为空");
				return;
			}
			
			if (StringUtils.isBlank(call_SignText.getText())){
				//setErrorMessage("船舶呼号不能为空");
				return;
			}
			
			if(StringUtils.isBlank(ship_Name_EnText.getText())){
				//setErrorMessage("船舶英文名称不能为空");
				return;
			}
			
			if(StringUtils.isBlank(co_CodeText.getText())){
				//setErrorMessage("船舶运营企业代码");
				return;
			}
			if(StringUtils.isBlank(Co_Name_EnText.getText())){
				//setErrorMessage("运营企业英文名称");
				return;
			}
			
			//setErrorMessage(null);

		}
	}

}
