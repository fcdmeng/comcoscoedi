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
	// IMO��
	private Text imoText;
	// ��������
	private Text call_SignText;
	// Ӣ�Ĵ���
	private Text ship_Name_EnText;
	// ���Ĵ���
	private Text ship_Name_CnText;
	//������Ӫ��ҵ����
	private Text co_CodeText;
	//��Ӫ��ҵӢ������
	private Text Co_Name_EnText;
	//��Ӫ��ҵ��������
	private Text Co_Name_CnText;
	//�����
	private Combo nationalityCombo;
	//��������֤����
	private Text certification_NoText;
	//����֤��ǩ������
	private Text certification_DateText;
	
	//������������
	private Text Date_Of_BuiltText;
	//�����۴���
	private Combo Hailing_PortCombo;
	//�ȼ���
	private Text class_LefterText;
	//�ȼ�֤���
	private Text classification_NoText;
	//�̴���ɫ
	private Text funnel_ColorText;
	//������ɫ
	private Text ship_ColorText;
	
	//�����������
	private Combo description_FlagCombo;
	//��Ӫ����
	private Combo liner_FalgCombo;
	//����
	private Text HorsepowerText;
	//����
	private Text SpeedText;
	//�ܶ�λ
	private Text gross_TonnageText;
	//����λ
	private Text net_TonnageText;
	//���ض�λ
	private Text deadweight_TonnageText;
	//TPI/TPC
	private Text tpi_TpcText;
	//����
	private Text lengthText;
	//����
	private Text breadthText;
	//����
	private Text depthText;
	//����ˮ
	private Text Deep_DraftText;
	//�ճ�ˮ
	private Text light_DraftText;
	//�տ���
	private Text door_NumText;
	//�����豸
	private Text gargo_GearText;
	//��װ����
	private Text bale_CapacityText;
	//ɢװ����
	private Text grain_CapacityText;
	//����TEU
	private Text ceiling_TeuText;
	//�װ�TEU
	private Text deck_TeuText;
	//��Ա�޶�
	private Text crew_LimitText;
	//�ÿ��޶�
	private Text passenger_LimitText;
	//�����������
	private Combo monitor_Ship_Type_CodeCombo;
	
	//�����˴���
	private Text recorder_CodeText;
	//������
	private Text recorderText;
	//��������
	private Combo customs_CodeCombo;
	//ͨѶ��ʽ
	private Combo communication_TypeCombo;
	//ͨѶ����
	private Text communication_NumberText;
	//¼��ʱ��
	private Text insert_Time;
	//��ע
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
		//setTitle("�����淶");
		//setMessage("�����ı�������������");
		//setMessage("�Ի����˵������", IMessageProvider.INFORMATION);
		// setErrorMessage("�������");
		Composite topComp = new Composite(parent, SWT.BORDER);

		topComp.setLayout(new GridLayout(4, false));
		topComp.setLayoutData(new GridData(GridData.FILL_BOTH));

		control.createLabel(topComp, "IMO");
		imoText = control.createTextField(topComp);
		control.setInfoDecoration(imoText, "IMO��", true);
		imoText.addModifyListener(new MyModifyListener());
		
		control.createLabel(topComp, "��������");
		call_SignText = control.createTextField(topComp);
		control.setInfoDecoration(call_SignText, "��������", true);
		call_SignText.addModifyListener(new MyModifyListener());
		
		control.createLabel(topComp, "Ӣ�Ĵ���");
		ship_Name_EnText =  control.createTextField(topComp);
		control.setInfoDecoration(ship_Name_EnText, "Ӣ�Ĵ���", true);
		ship_Name_EnText.addModifyListener(new MyModifyListener());
		
		control.createLabel(topComp, "���Ĵ���");
		ship_Name_CnText = control.createTextField(topComp);

		control.createLabel(topComp, "������Ӫ��ҵ����");
		co_CodeText = control.createTextField(topComp);
		control.setInfoDecoration(co_CodeText, "������Ӫ��ҵ����", true);
		
		control.createLabel(topComp, "��Ӫ��ҵӢ������");
		Co_Name_EnText = control.createTextField(topComp);
		control.setInfoDecoration(Co_Name_EnText, "��Ӫ��ҵӢ������", true);
		
		EdiControl.createLabel(topComp, "��Ӫ��ҵ��������");
		Co_Name_CnText = control.createTextField(topComp);

		control.createLabel(topComp, "�����");
		nationalityCombo = control.createCombo(topComp);
		control.setInfoDecoration(nationalityCombo, "�����", true);
		
		control.createLabel(topComp, "��������֤����");
		certification_NoText =control.createTextField(topComp);

		control.createLabel(topComp, "����֤��ǩ������");
		certification_DateText = control.createTextField(topComp);
		
		control.createLabel(topComp, "������������");
		Date_Of_BuiltText = control.createTextField(topComp);
		
		control.createLabel(topComp, "�����۴���");
		Hailing_PortCombo =  control.createCombo(topComp);
		control.setInfoDecoration(Hailing_PortCombo, "�����۴���", true);
		
		control.createLabel(topComp, "�ȼ���");
		class_LefterText = control.createTextField(topComp);
		
		control.createLabel(topComp, "�ȼ�֤���");
		classification_NoText = control.createTextField(topComp);
		
		control.createLabel(topComp, "������ɫ");
		ship_ColorText = control.createTextField(topComp);

		control.createLabel(topComp, "�̴���ɫ");
		funnel_ColorText = control.createTextField(topComp);
		
		control.createLabel(topComp, "�����������");
		description_FlagCombo = control.createCombo(topComp);
		control.setInfoDecoration(description_FlagCombo, "�����������", true);

		control.createLabel(topComp, "��Ӫ����");
		liner_FalgCombo = control.createCombo(topComp);
		control.setInfoDecoration(liner_FalgCombo, "��Ӫ����", true);
		liner_FalgCombo.setItems(new String [] {"����", "�ǰ���"});
		liner_FalgCombo.setData("����", "1");
		liner_FalgCombo.setData("�ǰ���", "2");
		
		control.createLabel(topComp, "����");
		HorsepowerText = control.createTextField(topComp);
		
		control.createLabel(topComp, "����");
		SpeedText = control.createTextField(topComp);

		control.createLabel(topComp, "�ܶ�λ");
		gross_TonnageText = control.createTextField(topComp);
		control.setInfoDecoration(gross_TonnageText, "�ܶ�λ", true);
		
		control.createLabel(topComp, "����λ");
		net_TonnageText = control.createTextField(topComp);
		control.setInfoDecoration(net_TonnageText, "����λ", true);

		control.createLabel(topComp, "���ض�λ");
		deadweight_TonnageText = control.createTextField(topComp);
		control.setInfoDecoration(deadweight_TonnageText, "���ض�λ", true);
		
		control.createLabel(topComp, "TPI/TPC");
		tpi_TpcText = control.createTextField(topComp);

		control.createLabel(topComp, "����");
		lengthText = control.createTextField(topComp);
		control.setInfoDecoration(lengthText, "����", true);

		control.createLabel(topComp, "����");
		breadthText = control.createTextField(topComp);
		control.setInfoDecoration(breadthText, "����", true);

		control.createLabel(topComp, "����");
		depthText = control.createTextField(topComp);
		control.setInfoDecoration(depthText, "����", true);

		control.createLabel(topComp, "����ˮ");
		Deep_DraftText = control.createTextField(topComp);

		control.createLabel(topComp, "�ճ�ˮ");
		light_DraftText = control.createTextField(topComp);
		
		control.createLabel(topComp, "�տ���");
		door_NumText = control.createTextField(topComp);
		
		control.createLabel(topComp, "�����豸");
		gargo_GearText = control.createTextField(topComp);
		
		control.createLabel(topComp, "��װ����");
		bale_CapacityText = control.createTextField(topComp);
		
		control.createLabel(topComp, "ɢװ����");
		grain_CapacityText = control.createTextField(topComp);
		
		control.createLabel(topComp, "����TEU");
		ceiling_TeuText = control.createTextField(topComp);

		control.createLabel(topComp, "�װ�TEU");
		deck_TeuText = control.createTextField(topComp);
		
		control.createLabel(topComp, "��Ա�޶�");
		crew_LimitText = control.createTextField(topComp);

		control.createLabel(topComp, "�ÿ��޶�");
		passenger_LimitText = control.createTextField(topComp);
	
		control.createLabel(topComp, "�����������");
		monitor_Ship_Type_CodeCombo = control.createCombo(topComp);
		
		
		control.createLabel(topComp, "�����˴���");
		recorder_CodeText = control.createTextField(topComp);
		control.setInfoDecoration(recorder_CodeText, "�����˴���", true);

		control.createLabel(topComp, "������");
		recorderText = control.createTextField(topComp);
		control.setInfoDecoration(recorderText, "������", true);
		/*
		 * createLabel(topComp, "����ʱ��"); Text RECORD_TIME = new
		 * Text(topComp, SWT.BORDER); RECORD_TIME.setLayoutData(new
		 * GridData(GridData.FILL_HORIZONTAL));
		 */
		
		control.createLabel(topComp, "��������");
		customs_CodeCombo = control.createCombo(topComp);
		control.setInfoDecoration(customs_CodeCombo, "��������", true);

		control.createLabel(topComp, "ͨѶ��ʽ");
		communication_TypeCombo = control.createCombo(topComp);
	
		control.createLabel(topComp, "ͨѶ����");
		communication_NumberText = control.createTextField(topComp);

		control.createLabel(topComp, "¼��ʱ��");
		insert_Time = control.createTextField(topComp);
	
		Label introLabel = new Label(topComp, SWT.NONE);
		introLabel
				.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		introLabel.setText("��ע");

		memoText = new Text(topComp, SWT.BORDER);
		memoText.setLayoutData(control.createGridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL, 3, 5));

		return topComp;
	}

	
	
	// ע��˵�������NamePageҳ�ļ�������������ʵ���ơ�
	private class MyModifyListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (StringUtils.isBlank(imoText.getText())) {
				//setErrorMessage("IMO������Ϊ��");
				return;
			}
			
			if (StringUtils.isBlank(call_SignText.getText())){
				//setErrorMessage("�������Ų���Ϊ��");
				return;
			}
			
			if(StringUtils.isBlank(ship_Name_EnText.getText())){
				//setErrorMessage("����Ӣ�����Ʋ���Ϊ��");
				return;
			}
			
			if(StringUtils.isBlank(co_CodeText.getText())){
				//setErrorMessage("������Ӫ��ҵ����");
				return;
			}
			if(StringUtils.isBlank(Co_Name_EnText.getText())){
				//setErrorMessage("��Ӫ��ҵӢ������");
				return;
			}
			
			//setErrorMessage(null);

		}
	}

}
