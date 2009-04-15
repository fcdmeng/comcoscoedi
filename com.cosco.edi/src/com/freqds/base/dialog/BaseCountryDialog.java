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
	//国家代码
	private Text countryCodeText;
	//国家中文名称
	private Text countryCnameText;
	//国家英文名称
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
		this.setTitle("国家代码");
		this.setMessage("国家信息维护");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(2,false));
		
		control.createLabel(container, "国家代码");
		countryCodeText = control.createTextField(container);
		countryCodeText.addModifyListener(new MyModifyListener());
		control.setInfoDecoration(countryCodeText, "国家代码", true);
		
		
		control.createLabel(container, "中文名称");
		countryCnameText = control.createTextField(container);
		control.createLabel(container, "英文名称");
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
	
	// 注释说明请参数NamePage页的监听器，两者现实相似。
	private class MyModifyListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (StringUtils.isBlank(countryCodeText.getText())) {
				setErrorMessage("国家代码不允许为空");
				return;
			}
			
			
			setErrorMessage(null);

		}
	}
	// 单击对话框底部按钮会执行此方法，参数buttonId是被单击按钮的ID值。
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID){
			// 如果单击确定按钮，则将值保存到变量
			if(StringUtils.isBlank(countryCodeText.getText())){
				setErrorMessage("国家代码不允许为空");
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
