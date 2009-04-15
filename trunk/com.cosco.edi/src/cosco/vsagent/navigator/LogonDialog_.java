package cosco.vsagent.navigator;

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
import com.swtdesigner.ResourceManager;

import cosco.vsagent.db.DbOperate;
import cosco.vsagent.model.IUser;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.SmsFactory;
import cosco.vsagent.app.Activator;


public class LogonDialog_ extends Dialog {
	
	private Text userIdText;
	private Text passwordText;
	protected LogonDialog_(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(Composite parent){
		getShell().setText("�û���¼");
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		topComp.setLayout(new GridLayout(2,false));

		new Label(topComp, SWT.NONE).setText("�û�����");
		userIdText = new Text(topComp, SWT.BORDER);
		userIdText.setText("system");
		userIdText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(topComp, SWT.NONE).setText("�ܡ��룺");
		passwordText = new Text(topComp, SWT.BORDER);
		passwordText.setEchoChar('*');
		passwordText.setText("system");
		passwordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		return topComp;
		
	}
	
	protected void buttonPressed(int buttonId){
		if (buttonId == IDialogConstants.OK_ID){
			String userId = userIdText.getText().trim();
			String password = passwordText.getText().trim();
			
			if(userId == null || userId.equals("")){
				MessageDialog.openWarning(getShell(), "��ʾ", "�û�������Ϊ��");
				userIdText.setFocus();
				return;
			}
			
			if(password == null || password.equals("")){
				MessageDialog.openWarning(getShell(), "��ʾ", "���벻��Ϊ��");
				passwordText.setFocus();
				return;
			}
			
			DbOperate db  = SmsFactory.getDbOperate();
			IUser user = db.getUser(userId);
			
			if (user == null){
				MessageDialog.openInformation(getShell(), "", "�û���������");
				userIdText.setFocus();
				userIdText.selectAll();
				return ;
			}
			
			String dbPassword = user.getPassword();
			
			
			if (dbPassword == null || !dbPassword.equals(password))
			{
				MessageDialog.openInformation(getShell(), "", "���벻��ȷ");
				passwordText.setFocus();
				passwordText.selectAll();
				return;
			}
			
			Context.getInstance().setCurrentUser(user);
		}
		
		super.buttonPressed(buttonId);
	}
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/animation4.gif"));
	}
	

}
