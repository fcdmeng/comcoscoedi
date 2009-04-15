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
	//��ҵ����
	private Text co_CodeText;
	//������
	private Text mnemonicText;
	//���ڳ���
	private Combo city_CodeCombo;
	//Ӣ������
	private Text co_En_NameText;
	//��������
	private Text co_Cn_NameText;
	//Ӣ�ļ��
	private Text co_Short_En_NameText;
	//���ļ��
	private Text co_Short_Cn_NameText;
	//Ӣ�ĵ�ַ
	private Text en_AddressText;
	//���ĵ�ַ
	private Text cn_AddressText;
	//����Ӣ������
	private Text law_Em_ManText;
	//������������
	private Text law_Cm_ManText;
	//��ϵ��
	private Text contactText;
	//�绰
	private Text phoneText;
	//����
	private Text faxText;
	//ע���ʽ�
	private Text registered_CapitalText;
	//��ҵ����
	private Combo propertyCombo;
	//EMAIL
	private Text emailText;
	//������
	private Button agentCheck;
	//������
	private Button carrierCheck;
	//�侭Ӫ��
	private Button container_OperatorCheck;
	//��վ
	private Button container_StationCheck;
	//������
	private Button booking_PersonCheck;
	//���乫˾
	private Button leasing_CompanyCheck;
	//��˾����
	private Button ownerCheck;
	//����
	private Button teamCheck;
	//EDI�������
	private Button ediCheck;
	//����
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
		setTitle("�������");
		setMessage("���������Ϣ", IMessageProvider.INFORMATION);
		
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
		control.createLabel(com1, "��  ��");
		co_CodeText = control.createTextField(com1);
		control.setInfoDecoration(co_CodeText, "", true);
		control.createLabel(com1,"������");
		mnemonicText = control.createTextField(com1);
		control.createLabel(com1, "���ڳ���");
		city_CodeCombo= control.createCombo(com1);
		
		
		
		Composite com2 = new Composite(container,SWT.BORDER); 
		com2.setLayoutData(gd1);
		com2.setLayout(new GridLayout(4,false));
		control.createLabel(com2, "Ӣ������");
		co_En_NameText = control.createMTextField(com2);
		co_En_NameText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));

		control.createLabel(com2, "��������");
		co_Cn_NameText = control.createMTextField(com2);
		co_Cn_NameText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
	
		
		
		Composite com3 = new Composite(container,SWT.BORDER); 
		com3.setLayoutData(gd1);
		com3.setLayout(new GridLayout(4,false));
		control.createLabel(com3, "Ӣ�ļ��");
		co_Short_En_NameText = control.createMTextField(com3);
		co_Short_En_NameText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
		control.createLabel(com3, "���ļ��");
		co_Short_Cn_NameText = control.createMTextField(com3);
		co_Short_Cn_NameText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
		
		Composite com4 = new Composite(container,SWT.BORDER); 
		com4.setLayoutData(gd1);
		com4.setLayout(new GridLayout(4,false));
		control.createLabel(com4, "Ӣ�ĵ�ַ");
		en_AddressText = control.createMTextField(com4);
		en_AddressText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
		control.createLabel(com4, "���ĵ�ַ");
		cn_AddressText = control.createMTextField(com4);
		cn_AddressText.setLayoutData(control.createGridData(GridData.FILL_BOTH,1,4));
		
		Composite com5 = new Composite(container,SWT.BORDER); 
		com5.setLayoutData(gb2);
		com5.setLayout(new GridLayout(4,false));
		control.createLabel(com5, "����Ӣ������");
		law_Em_ManText = control.createTextField(com5);
		control.createLabel(com5, "������������");
		law_Cm_ManText = control.createTextField(com5);
		
		Composite com6 = new Composite(container,SWT.BORDER); 
		com6.setLayoutData(gb2);
		com6.setLayout(new GridLayout(6,false));
		
		control.createLabel(com6, "ע���ʽ�");
		registered_CapitalText = control.createTextField(com6);
		control.createLabel(com6, "EMAIL");
		emailText = control.createTextField(com6);
		control.createLabel(com6, "��ҵ����");
		propertyCombo = control.createCombo(com6);
		
		Composite com7 = new Composite(container,SWT.BORDER); 
		com7.setLayoutData(gb2);
		com7.setLayout(new GridLayout(8,false));
		control.createLabel(com7, "�� ϵ ��");
		contactText = control.createTextField(com7);
		control.setInfoDecoration(co_CodeText, "", true);
		control.createLabel(com7,"�绰");
		phoneText = control.createTextField(com7);
		control.createLabel(com7, "����");
		faxText= control.createTextField(com7);
		
		Composite com8 = new Composite(container,SWT.BORDER); 
		com8.setLayoutData(gd1);
		com8.setLayout(new FillLayout());
		final Group group = new Group(com8, SWT.NULL);
		group.setText("����������");
		group.setLayout(new GridLayout(10,false));
		agentCheck = control.createCheckBox(group, "������");
		carrierCheck = control.createCheckBox(group, "������");
		container_OperatorCheck= control.createCheckBox(group, "�侭Ӫ��");
		container_StationCheck= control.createCheckBox(group, "��վ");
		booking_PersonCheck= control.createCheckBox(group, "������");
		leasing_CompanyCheck= control.createCheckBox(group, "���乫˾");
		ownerCheck= control.createCheckBox(group, "��˾����");
		teamCheck= control.createCheckBox(group, "����");
		ediCheck= control.createCheckBox(group, "����");
		otherCheck= control.createCheckBox(group, "����");
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
