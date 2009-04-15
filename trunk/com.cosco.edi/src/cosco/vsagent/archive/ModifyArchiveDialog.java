package cosco.vsagent.archive;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cosco.vsagent.model.Course;
import cosco.vsagent.model.IUser;
import cosco.vsagent.model.SchoolClass;
import cosco.vsagent.model.Student;
import cosco.vsagent.model.Teacher;
import cosco.vsagent.system.CourseComposite;
import cosco.vsagent.system.SmsUtil;


public class ModifyArchiveDialog extends TitleAreaDialog {
	private IUser user;
	private Text useridText,passwordText,nameText;
	private Label idLabel,schoolClassLabel,latestOnlineLabel;

	private Combo schoolClassCombo;
	private CourseComposite courseComp;

	public IUser getUser() {
		return user;
	}

	public void setUser(IUser user) {
		this.user = user;
	}

	/**
	 * Create the dialog
	 * @param parentShell
	 */
	public ModifyArchiveDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		getShell().setText("用户档案");
		setTitle("修改用户档案");
		setMessage("请输入新的用户信息",IMessageProvider.INFORMATION);
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		topComp.setLayout(new GridLayout(2, false));
		Composite comp1 = new Composite(topComp, SWT.NONE);
		comp1.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp1.setLayout(new GridLayout(2, false));
		new Label(comp1, SWT.NONE).setText("ID:");
		idLabel = new Label(comp1, SWT.NONE);
		new Label(comp1, SWT.NONE).setText("用户名:");
		useridText = createText(comp1, SWT.BORDER);
		new Label(comp1, SWT.NONE).setText("密码:");
		passwordText = createText(comp1, SWT.BORDER);
		new Label(comp1, SWT.NONE).setText("姓名:");
		nameText = createText(comp1, SWT.BORDER);
		{
			schoolClassLabel = new Label(comp1, SWT.NONE);
			schoolClassLabel.setText("班级:");
			Composite classComp = new Composite(comp1, SWT.NONE);
			classComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			classComp.setLayout(new FillLayout());
			schoolClassCombo = SmsUtil.createSchoolClassCombo(classComp, SWT.BORDER |SWT.READ_ONLY);
		}
		
		new Label(comp1, SWT.NONE).setText("最近登录");
		latestOnlineLabel = new Label(comp1, SWT.NONE);
		courseComp = new CourseComposite(topComp, SWT.NONE);
		setValue(user);
		return topComp;
	}
	
	private void setValue(IUser user2) {
		// TODO Auto-generated method stub
		if(user == null) return;
		
		idLabel.setText(String.valueOf(user.getId()));
		latestOnlineLabel.setText(SmsUtil.dateToLongStr(user.getLatestOnline()));
		useridText.setText(user.getUserId());
		passwordText.setText(user.getPassword());
		nameText.setText(user.getName());
		if(user instanceof Student){
			Student o =(Student)user;
			SchoolClass schoolClass = o.getSchoolclass();
			schoolClassCombo.setText(schoolClass.getName());
			schoolClassCombo.setData(schoolClass.getName(),schoolClass);
			courseComp.setVisible(false);
			
		}else if(user instanceof Teacher){
			Teacher o =(Teacher) user;
			for(Course course : o.getCourses()){
				courseComp.add(course);
				schoolClassLabel.setVisible(false);
				schoolClassCombo.setVisible(false);
			}
		}
	}

	private Text createText(Composite comp, int style){
		Text text = new Text(comp, style);
		text.setLayoutData(new GridData(100, -1));
		return text;
	}
	protected void buttonPressed(int buttonId){
		if(buttonId == IDialogConstants.OK_ID){
			if(!checkValue()) return;
			getValue(user);
		}
		
		super.buttonPressed(buttonId);
	}
	private void getValue(IUser user2) {
		// TODO Auto-generated method stub
		if(user instanceof Teacher){
			Teacher teacher =(Teacher) user;
			teacher.clearCourses();
			for(String courseName : courseComp.getItems()){
				Course course = courseComp.getData(courseName);
				teacher.addCourse(course);
			}
		}else if(user instanceof Student){
			String sel = schoolClassCombo.getText();
			SchoolClass schoolclass =(SchoolClass) schoolClassCombo.getData(sel);
			((Student)user).setSchoolclass(schoolclass);
		}
		
		user.setId(new Long(idLabel.getText()));
		user.setUserId(useridText.getText());
		user.setPassword(passwordText.getText());
		user.setName(nameText.getText());
		user.setLatOnLine(user.getLatestOnline());
		
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
	@Override
	protected Point getInitialSize() {
		Point p = super.getInitialSize();
//		System.out.println(p.x);
//		System.out.println(p.y);
		p.x =400;
		return p;//new Point(500, 375);
	}
	
	private boolean checkValue(){
		
		if(useridText.getText().trim().equals("")){
			setErrorMessage("用户名不能为空");
			useridText.forceFocus();
			return false;
		}
		
		if(passwordText.getText().trim().equals("")){
			setErrorMessage("密码不能为空");
			passwordText.forceFocus();
			return false;
		}
		if(nameText.getText().trim().equals("")){
			setErrorMessage("姓名不能为空");
			return false;
		}
		return true;
	}

}
