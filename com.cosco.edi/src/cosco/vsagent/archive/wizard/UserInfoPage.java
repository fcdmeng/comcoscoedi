package cosco.vsagent.archive.wizard;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import cosco.vsagent.model.IUser;
import cosco.vsagent.system.SmsFactory;


//����ʵ����ModifyListener�ӿڣ����Լ������һ���ı���ı��¼��ļ�����
public class UserInfoPage extends WizardPage implements ModifyListener {
	private Text useridText; // �û���
	private Text passwordText; // ����
	private Text nameText; // ����

	protected UserInfoPage(String pageName) {
		super(pageName);
	}

	public void createControl(Composite parent) {
		setTitle("����û�");
		setMessage("�����û�������Ϣ", IMessageProvider.INFORMATION);
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		// �����������
		topComp.setLayout(new GridLayout(3, false));// ���񲼾֣�3��
		new Label(topComp, SWT.NONE).setText("�û�����");
		useridText = createText(topComp, SWT.BORDER);
		useridText.addModifyListener(this);
		Button checkButton = new Button(topComp, SWT.NONE);
		checkButton.setText("���Ωһ��");
		checkButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String userid = useridText.getText();
				if (!userid.trim().equals("")) {// �ı��򴫳�ֵ������nullֵ�ж�
					if (SmsFactory.getDbOperate().getUser(userid) == null) {
						MessageDialog.openInformation(null, "", "ͨ��");
					} else {
						MessageDialog.openError(null, "", "���û����Ѿ�����");
						useridText.forceFocus();// ��ý���
						useridText.selectAll();// ȫѡ
					}
				}
			}
		});

		new Label(topComp, SWT.NONE).setText("���룺");
		passwordText = createText(topComp, SWT.BORDER);
		passwordText.addModifyListener(this);
		new Label(topComp, SWT.NONE);

		new Label(topComp, SWT.NONE).setText("������");
		nameText = createText(topComp, SWT.BORDER);
		new Label(topComp, SWT.NONE);
		nameText.addModifyListener(this);

		setPageComplete(false);
		setControl(topComp);
	}

	// �õ�һ���̶����ȵ�Text��
	private Text createText(Composite comp, int style) {
		Text text = new Text(comp, style);
		text.setLayoutData(new GridData(100, -1));
		return text;
	}

	// �ı��޸��¼��������ı����޸�ʱ����
	public void modifyText(ModifyEvent e) {
		setPageComplete(false);
		if (useridText.getText().trim().equals("")) {
			setErrorMessage("������д�û���");// ��ʾ������Ϣ
			return;
		}
		if (passwordText.getText().trim().equals("")) {
			setErrorMessage("������д����");// ��ʾ������Ϣ
			return;
		}
		if (nameText.getText().trim().equals("")) {
			setErrorMessage("������д����");// ��ʾ������Ϣ
			return;
		}
		setErrorMessage(null); // �����Ի����ϵĳ�����ʾ����
		setPageComplete(true);
	}

	//������ֵע�뵽Teacher������
	public void getValue(IUser user) {
		user.setUserId(useridText.getText());
		user.setPassword(passwordText.getText());
		user.setName(nameText.getText());
	}
}
