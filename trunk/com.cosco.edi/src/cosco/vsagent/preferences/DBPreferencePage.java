package cosco.vsagent.preferences;


import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import cosco.vsagent.app.Activator;
import cosco.vsagent.db.ConnectManager;

public class DBPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage, ModifyListener{
	
	public static final String CLASSNAME_KEY = "$CLASSNAME_KEY";
	public static final String URL_KEY = "$URL_KEY";
	public static final String USERNAME_KEY = "$USERNAME_KEY";
	public static final String PASSWORD_KEY = "$PASSWORD_KEY";
	public static final String ARCHIVE_EDITOR_RS_NUM_KEY = "ARCHIVE_EDITOR_RS_NUM_KEY";
	
	public static final String CLASSNAME_DEFAULT ="com.mysql.jdbc.Driver";
	public static final String URL_DEFAULT="jdbc:mysql://localhost/sms?characterEncoding=gb2312";
	public static final String USERNAME_DEFAULT="root";
	public static final String PASSWORD_DEFAULT="root";
	public static final String ARCHIVE_EDITOR_RS_NUM_DEFAULT="10";
	
	private Text classNameText, urlText, usernameText, passwordText, archiveText;
	private IPreferenceStore ps;
	
	private VerifyListener verifyListener = new VerifyListener(){
		public void verifyText(VerifyEvent e){
			e.doit =("0123456789".indexOf(e.text)>=0);
		}
	};

	public DBPreferencePage() {
		// TODO Auto-generated constructor stub
	}

	public DBPreferencePage(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public DBPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		ps = this.getPreferenceStore();
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new GridLayout());
		createDbConnectGroup(topComp);
		createTableRsCountGroup(topComp);
		
		return topComp;
	}

	private void createTableRsCountGroup(Composite topComp) {
		// TODO Auto-generated method stub
		Group group = new Group(topComp, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setLayout(new GridLayout(2, false));
		group.setText("表格记录显示条数");
		archiveText = createText(group,"档案管理表格每页显示记录数",ARCHIVE_EDITOR_RS_NUM_KEY,ARCHIVE_EDITOR_RS_NUM_DEFAULT);
		archiveText.addVerifyListener(verifyListener);
	}

	private void createDbConnectGroup(Composite topComp) {
		// TODO Auto-generated method stub
		Group group = new Group(topComp, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setLayout(new GridLayout(2, false));
		group.setText("数据库连接");
		classNameText = createText(group, "ClassName:", CLASSNAME_KEY, CLASSNAME_DEFAULT);
		urlText = createText(group, "URL:", URL_KEY, URL_DEFAULT);
		usernameText = createText(group,"用户名:", USERNAME_KEY, USERNAME_DEFAULT);
		passwordText = createText(group,"密码:",PASSWORD_KEY, PASSWORD_DEFAULT);
		passwordText.setEchoChar('*');
	}

	private Text createText(Group group, String label, String saveKey,
			String defaultValue) {
		// TODO Auto-generated method stub
		new Label(group, SWT.NONE).setText(label);
		Text text = new Text(group, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String value = ps.getString(saveKey);
		text.setText(isEmpty(value)?defaultValue : value);
		text.addModifyListener(this);
		return text;
	}

	private boolean isEmpty(String value) {
		// TODO Auto-generated method stub
		return value == null || value.equals("");
	}

	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		setPreferenceStore(Activator.getDefault().getPreferenceStore());

	}
	protected void performDefaults(){
		classNameText.setText(this.CLASSNAME_DEFAULT);
		urlText.setText(this.URL_DEFAULT);
		usernameText.setText(this.USERNAME_DEFAULT);
		passwordText.setText(this.PASSWORD_DEFAULT);
		archiveText.removeVerifyListener(verifyListener);
		archiveText.setText(this.ARCHIVE_EDITOR_RS_NUM_DEFAULT);
		archiveText.addVerifyListener(verifyListener);
	}
	protected void performApply(){
		doSave();
	}
	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub
		String errorStr = null;
		if(isEmpty(classNameText.getText())){
			errorStr ="ClassName 不能为空";
		}else if(isEmpty(urlText.getText())){
			errorStr ="URL 不能为空";
		}else if(isEmpty(usernameText.getText())){
			errorStr ="用户名不能为空";
		}else if(isEmpty(passwordText.getText())){
			errorStr ="密码不能为空";
		}else if(isEmpty(archiveText.getText())){
			errorStr ="档案管理表格每页显示记录不能为空";
		}
		setErrorMessage(errorStr);
		setValid(errorStr == null);
		getApplyButton().setEnabled(errorStr == null);
	}
	public boolean performOk() {
		doSave();
		ConnectManager.closeConnection();
		return true;
	}
	private void doSave(){
		ps.setValue(CLASSNAME_KEY, classNameText.getText());
		ps.setValue(URL_KEY, urlText.getText());
		ps.setValue(USERNAME_KEY, usernameText.getText());
		ps.setValue(PASSWORD_KEY, passwordText.getText());

		ps.setValue(ARCHIVE_EDITOR_RS_NUM_KEY, archiveText.getText());
		
	}

}
