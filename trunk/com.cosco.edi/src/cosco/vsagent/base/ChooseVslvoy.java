package cosco.vsagent.base;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cosco.vsagent.dbo.base.BasvslvoyDAO;
import cosco.vsagent.mapping.base.Basvslvoy;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.SmsUtil;


public class ChooseVslvoy extends Dialog {
	private Text vslText;
	private Text voyageText;
	private String IE;
	
	public ChooseVslvoy(Shell parentShell, String IE) {
		// TODO Auto-generated constructor stub
		super(parentShell);
		setIE(IE);
	}
	protected Control createDialogArea(Composite parent){
		getShell().setText("选船窗口");
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		topComp.setLayout(new GridLayout(2,false));

		new Label(topComp, SWT.NONE).setText("船名：");
		vslText = new SmsUtil().createText(topComp, SWT.BORDER);
		vslText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		vslText.setText("E3EFI3");
		new Label(topComp, SWT.NONE).setText("航次：");
		voyageText = new SmsUtil().createText(topComp, SWT.BORDER);
		voyageText.setText("0330");
		voyageText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return topComp;
	}
	
	public String getVslText() {
		return vslText.getText().trim();
	}
	
	public String getVoyageText() {
		return voyageText.getText().trim();
	}
	
	protected void buttonPressed(int buttonId){
		if(buttonId == IDialogConstants.OK_ID){
			String shipname = vslText.getText().trim();
			String voyage = voyageText.getText().trim();
			
			if(shipname == null || shipname.equals("")){
				MessageDialog.openWarning(getShell(), "提示", "请输入船舶代码或名称");
				vslText.setFocus();
				return;
			}
			
			if(voyage == null || voyage.equals("")){
				MessageDialog.openWarning(getShell(), "提示", "请输入航次");
				voyageText.setFocus();
				return;
			}
			
			Basvslvoy basvslvoy = new BasvslvoyDAO().findByVslvoy(shipname, voyage, IE);
			if(basvslvoy == null) return;
			Context.getInstance().setBasvslvoy(basvslvoy);
		}
		
		/**
		 * 增加航次是否存在
		 */
		
		
		super.buttonPressed(buttonId);
	}
	public String getIE() {
		return IE;
	}
	public void setIE(String ie) {
		IE = ie;
	}
	

}
