package com.freqds.base.dialog;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.freqds.base.EdiControl;

public class BaseLineDialog extends TitleAreaDialog {
	//���ߴ���
	private Text lineCodeText;
	//��������
	private Text lineNameText;
	//����
	private Combo scheduleCombo;
	
	/**
	 * Create the dialog
	 * @param parentShell
	 */
	public BaseLineDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		EdiControl control = new EdiControl();
		this.setTitle("���ߴ���");
		this.setMessage("������Ϣά��");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(2,false));
		
		control.createLabel(container, "���ߴ���");
		lineCodeText = control.createTextField(container);
		control.setInfoDecoration(lineCodeText, "���ߴ���", true);
		lineCodeText.addModifyListener(new MyModifyListener());
		
		control.createLabel(container, "��������");
		lineNameText = control.createTextField(container);
		control.createLabel(container, "����");
		scheduleCombo = control.createCombo(container);
		
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
			if (StringUtils.isBlank(lineCodeText.getText())) {
				setErrorMessage("���ߴ��벻����Ϊ��");
				return;
			}
			
			
			setErrorMessage(null);

		}
	}
	// �����Ի���ײ���ť��ִ�д˷���������buttonId�Ǳ�������ť��IDֵ��
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID){
			// �������ȷ����ť����ֵ���浽����
			if(StringUtils.isBlank(lineCodeText.getText())){
				setErrorMessage("���ߴ��벻����Ϊ��");
				lineCodeText.setFocus();
				return;
			}
			
		}
			
		super.buttonPressed(buttonId);
	}

}
