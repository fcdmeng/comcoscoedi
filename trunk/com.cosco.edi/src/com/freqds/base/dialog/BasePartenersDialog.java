package com.freqds.base.dialog;


import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.freqds.base.EdiControl;

public class BasePartenersDialog extends TitleAreaDialog {
	//企业代码
	private Text co_CodeText;
	//助记码
	private Text mnemonicText;
	//所在城市
	private Combo city_CodeCombo;
	//英文名称
	private Text co_En_NameText;
	//中文名称
	private Text co_Cn_NameText;
	//英文简称
	private Text co_Short_En_NameText;
	//中文简称
	private Text co_Short_Cn_NameText;
	//英文地址
	private Text en_AddressText;
	//中文地址
	private Text cn_AddressText;
	//法人英文名称
	private Text law_Em_ManText;
	//法人中文名称
	private Text law_Cm_ManText;
	//联系人
	private Text contactText;
	//电话
	private Text phoneText;
	//传真
	private Text faxText;
	//注册资金
	private Text registered_CapitalText;
	//企业性质
	private Combo propertyCombo;
	//EMAIL
	private Text emailText;
	//代理人
	private Button agentCheck;
	//承运人
	private Button carrierCheck;
	//箱经营人
	private Button container_OperatorCheck;
	//场站
	private Button container_StationCheck;
	//订舱人
	private Button booking_PersonCheck;
	//租箱公司
	private Button leasing_CompanyCheck;
	//本司部门
	private Button ownerCheck;
	//车队
	private Button teamCheck;
	//EDI合作伙伴
	private Button ediCheck;
	//其它
	private Button otherCheck;
	/**
	 * Create the dialog
	 * @param parentShell
	 */
	public BasePartenersDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		EdiControl control = new EdiControl();
		setTitle("合作伙伴");
		setMessage("合作伙伴信息", IMessageProvider.INFORMATION);
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.BORDER);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(4,false));
		GridData gd1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
		gd1.heightHint = 49;
		
		Composite com1 = new Composite(container,SWT.BORDER); 
		GridData gb2 = new GridData(GridData.FILL_HORIZONTAL);
		gb2.horizontalSpan = 4;
		com1.setLayoutData(gb2);
		com1.setLayout(new GridLayout(6,false));
		control.createLabel(com1, "代  码");
		co_CodeText = control.createTextField(com1);
		control.setInfoDecoration(co_CodeText, "", true);
		control.createLabel(com1,"助记码");
		mnemonicText = control.createTextField(com1);
		control.createLabel(com1, "所在城市");
		city_CodeCombo= control.createCombo(com1);
		
		
		
		Composite com2 = new Composite(container,SWT.BORDER); 
		com2.setLayoutData(gd1);
		com2.setLayout(new GridLayout(4,false));
		control.createLabel(com2, "英文名称");
		co_En_NameText = control.createMTextField(com2);
		co_En_NameText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));

		control.createLabel(com2, "中文名称");
		co_Cn_NameText = control.createMTextField(com2);
		co_Cn_NameText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
	
		
		
		Composite com3 = new Composite(container,SWT.BORDER); 
		com3.setLayoutData(gd1);
		com3.setLayout(new GridLayout(4,false));
		control.createLabel(com3, "英文简称");
		co_Short_En_NameText = control.createMTextField(com3);
		co_Short_En_NameText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
		control.createLabel(com3, "中文简称");
		co_Short_Cn_NameText = control.createMTextField(com3);
		co_Short_Cn_NameText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
		
		Composite com4 = new Composite(container,SWT.BORDER); 
		com4.setLayoutData(gd1);
		com4.setLayout(new GridLayout(4,false));
		control.createLabel(com4, "英文地址");
		en_AddressText = control.createMTextField(com4);
		en_AddressText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
		control.createLabel(com4, "中文地址");
		cn_AddressText = control.createMTextField(com4);
		cn_AddressText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
		
		Composite com5 = new Composite(container,SWT.BORDER); 
		com5.setLayoutData(gb2);
		com5.setLayout(new GridLayout(4,false));
		control.createLabel(com5, "法人英文名称");
		law_Em_ManText = control.createTextField(com5);
		control.createLabel(com5, "法人中文名称");
		law_Cm_ManText = control.createTextField(com5);
		
		Composite com6 = new Composite(container,SWT.BORDER); 
		com6.setLayoutData(gb2);
		com6.setLayout(new GridLayout(6,false));
		
		control.createLabel(com6, "注册资金");
		registered_CapitalText = control.createTextField(com6);
		control.createLabel(com6, "EMAIL");
		emailText = control.createTextField(com6);
		control.createLabel(com6, "企业性质");
		propertyCombo = control.createCombo(com6);
		
		Composite com7 = new Composite(container,SWT.BORDER); 
		com7.setLayoutData(gb2);
		com7.setLayout(new GridLayout(8,false));
		control.createLabel(com7, "联 系 人");
		contactText = control.createTextField(com7);
		control.setInfoDecoration(co_CodeText, "", true);
		control.createLabel(com7,"电话");
		phoneText = control.createTextField(com7);
		control.createLabel(com7, "传真");
		faxText= control.createTextField(com7);
		
		Composite com8 = new Composite(container,SWT.BORDER); 
		com8.setLayoutData(gd1);
		com8.setLayout(new FillLayout());
		final Group group = new Group(com8, SWT.NULL);
		group.setText("合作伙伴身份");
		group.setLayout(new GridLayout(10,false));
		agentCheck = control.createCheckBox(group, "代理人");
		carrierCheck = control.createCheckBox(group, "承运人");
		container_OperatorCheck= control.createCheckBox(group, "箱经营人");
		container_StationCheck= control.createCheckBox(group, "场站");
		booking_PersonCheck= control.createCheckBox(group, "订舱人");
		leasing_CompanyCheck= control.createCheckBox(group, "租箱公司");
		ownerCheck= control.createCheckBox(group, "本司部门");
		teamCheck= control.createCheckBox(group, "车队");
		ediCheck= control.createCheckBox(group, "车队");
		otherCheck= control.createCheckBox(group, "其它");
		return area;
	}
	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(700, 550);
	}
	
	
}
