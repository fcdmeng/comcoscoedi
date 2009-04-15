package com.freqds.base.dialog;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.freqds.base.EdiControl;
import com.freqds.mapping.base.BaseCountry;

public class BaseCountryDialog extends TitleAreaDialog {
	//���Ҵ���
	private Text countryCodeText;
	//������������
	private Text countryCnameText;
	//����Ӣ������
	private Text countryEnameText;
	private BaseCountry country;
	/**
	 * Create the dialog
	 * @param parentShell
	 */
	public BaseCountryDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		EdiControl control = new EdiControl();
		this.setTitle("���Ҵ���");
		this.setMessage("������Ϣά��");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(2,false));
		
		control.createLabel(container, "���Ҵ���");
		countryCodeText = control.createTextField(container);
		countryCodeText.addModifyListener(new MyModifyListener());
		control.setInfoDecoration(countryCodeText, "���Ҵ���", true);
		
		
		control.createLabel(container, "��������");
		countryCnameText = control.createTextField(container);
		control.createLabel(container, "Ӣ������");
		countryEnameText = control.createTextField(container);
		setValues();
		return area;
	}

	/**
	 * Create contents of the button bar
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog
	 */
//	@Override
//	protected Point getInitialSize() {
//		return new Point(500, 375);
//	}
	
	// ע��˵�������NamePageҳ�ļ�������������ʵ���ơ�
	private class MyModifyListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (StringUtils.isBlank(countryCodeText.getText())) {
				setErrorMessage("���Ҵ��벻����Ϊ��");
				return;
			}
			
			
			setErrorMessage(null);

		}
	}
	// �����Ի���ײ���ť��ִ�д˷���������buttonId�Ǳ�������ť��IDֵ��
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID){
			// �������ȷ����ť����ֵ���浽����
			if(StringUtils.isBlank(countryCodeText.getText())){
				setErrorMessage("���Ҵ��벻����Ϊ��");
				return;
			}
			
			country.setCountry_code(StringUtils.stripToEmpty(countryCodeText.getText()));
			country.setCountry_ename(StringUtils.stripToEmpty(countryEnameText.getText()));
			country.setCountry_cname(StringUtils.stripToEmpty(countryCnameText.getText()));
		}
			
		super.buttonPressed(buttonId);
	}

	public BaseCountry getCountry() {
		return country;
	}

	public void setCountry(BaseCountry country) {
		this.country = country;
	}
	public void setValues(){
		countryCodeText.setText(StringUtils.stripToEmpty(country.getCountry_code()));
		countryCnameText.setText(StringUtils.stripToEmpty(country.getCountry_cname()));
		countryEnameText.setText(StringUtils.stripToEmpty(country.getCountry_ename()));
	}

}
