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
	//航线代码
	private Text lineCodeText;
	//航线名称
	private Text lineNameText;
	//班期
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
		this.setTitle("航线代码");
		this.setMessage("航线信息维护");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(2,false));
		
		control.createLabel(container, "航线代码");
		lineCodeText = control.createTextField(container);
		control.setInfoDecoration(lineCodeText, "航线代码", true);
		lineCodeText.addModifyListener(new MyModifyListener());
		
		control.createLabel(container, "航线名称");
		lineNameText = control.createTextField(container);
		control.createLabel(container, "班期");
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
	
	// 注释说明请参数NamePage页的监听器，两者现实相似。
	private class MyModifyListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (StringUtils.isBlank(lineCodeText.getText())) {
				setErrorMessage("航线代码不允许为空");
				return;
			}
			
			
			setErrorMessage(null);

		}
	}
	// 单击对话框底部按钮会执行此方法，参数buttonId是被单击按钮的ID值。
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID){
			// 如果单击确定按钮，则将值保存到变量
			if(StringUtils.isBlank(lineCodeText.getText())){
				setErrorMessage("航线代码不允许为空");
				lineCodeText.setFocus();
				return;
			}
			
		}
			
		super.buttonPressed(buttonId);
	}

}
