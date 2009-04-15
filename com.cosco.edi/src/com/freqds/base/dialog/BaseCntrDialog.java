package com.freqds.base.dialog;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.freqds.base.EdiControl;

public class BaseCntrDialog extends TitleAreaDialog {
	//箱型代码
	private Text typeCodeText;
	//箱型名称
	private Text typeNameText;
	//皮重
	private Text tareWeightText;
	
	/**
	 * Create the dialog
	 * @param parentShell
	 */
	public BaseCntrDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("箱型信息");
		setMessage("箱型信息维护");
		EdiControl control = new EdiControl();
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(2,false));
		
		control.createLabel(container, "箱型代码");
		typeCodeText = control.createTextField(container);
		control.setInfoDecoration(typeCodeText, "箱型代码", true);
		typeCodeText.addModifyListener(new MyModifyListener());
		
		control.createLabel(container, "箱型名称");
		typeNameText = control.createTextField(container);
		control.createLabel(container, "皮重");
		tareWeightText = control.createTextField(container);
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
	/*@Override
	protected Point getInitialSize() {
		return new Point(500, 375);
	}
	*/
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("箱型信息维护");
	}
	
	private class MyModifyListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (StringUtils.isBlank(typeCodeText.getText())) {
				setErrorMessage("箱型代码不允许为空");
				return;
			}
			
			
			setErrorMessage(null);

		}
	}
	// 单击对话框底部按钮会执行此方法，参数buttonId是被单击按钮的ID值。
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID){
			// 如果单击确定按钮，则将值保存到变量
			if(StringUtils.isBlank(typeCodeText.getText())){
				setErrorMessage("箱型代码不允许为空");
				typeCodeText.setFocus();
				return;
			}
			
		}
			
		super.buttonPressed(buttonId);
	}

}
