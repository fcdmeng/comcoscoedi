package cosco.vsagent.navigator;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;

import cosco.vsagent.app.Activator;
import cosco.vsagent.db.DbOperate;
import cosco.vsagent.model.IUser;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.SmsFactory;

public class LogonDialog extends TitleAreaDialog {
	private Text userIdText;
	private Text passwordText;

	public LogonDialog(Shell parentShell) {
		super(parentShell);
		parentShell.setMaximized(false);
	}

	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		// ���ñ���
		setTitle("��¼");
		// ���öԻ�����ʽЧ��

		setMessage("�������¼�ʺż�����", IMessageProvider.INFORMATION);
		return contents;
	}

	protected Control createDialogArea(Composite parent) {
		// Composite composite = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(new GridLayout(2, false));
		MyModifyListener listener = new MyModifyListener();
		
		new Label(composite, SWT.NONE).setText("�û�����");
		userIdText = new Text(composite, SWT.BORDER);
		userIdText.setText("system");
		userIdText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		userIdText.addModifyListener(listener);
		
		new Label(composite, SWT.NONE).setText("�ܡ��룺");
		passwordText = new Text(composite, SWT.BORDER);
		passwordText.setEchoChar('*');
		passwordText.setText("system");
		passwordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setTitleImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"wizard/share_wizban.gif"));

		return composite;
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("��¼����");
	}

	/**
	 * ��SWT.RESIZE��SWT.MAX��SWT.MIN�ֱ����ô���Ϊ���Ա��С�����ڿ��� �󻯡���С��
	 */
	protected int getShellStyle() {
		return super.getShellStyle() | SWT.RESIZE | SWT.MIN;// | SWT.MAX
	}

	/**
	 * ������ť����ȷ�ϡ�ȡ����ť
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, true);
	}

	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			String userId = userIdText.getText().trim();
			String password = passwordText.getText().trim();

			if (userId == null || userId.equals("")) {
				setErrorMessage("�û�������Ϊ��");
				// MessageDialog.openWarning(getShell(), "��ʾ", "�û�������Ϊ��");
				userIdText.setFocus();
				return;
			}

			if (password == null || password.equals("")) {
				// MessageDialog.openWarning(getShell(), "��ʾ", "���벻��Ϊ��");
				setErrorMessage("���벻��Ϊ��");
				passwordText.setFocus();
				return;
			}

			DbOperate db = SmsFactory.getDbOperate();
			IUser user = db.getUser(userId);

			if (user == null) {
				setErrorMessage("�û���������");
				userIdText.setFocus();
				userIdText.selectAll();
				return;
			}

			String dbPassword = user.getPassword();

			if (dbPassword == null || !dbPassword.equals(password)) {
				setErrorMessage("���벻��ȷ");
				passwordText.setFocus();
				passwordText.selectAll();
				return;
			}
			setErrorMessage(null);
			Context.getInstance().setCurrentUser(user);
		}

		super.buttonPressed(buttonId);
	}

	// ע��˵�������NamePageҳ�ļ�������������ʵ���ơ�
	private class MyModifyListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (StringUtils.isBlank(userIdText.getText())) {
				setErrorMessage("��¼�û���������Ϊ��");
				return;
			}
		}
	}
}
