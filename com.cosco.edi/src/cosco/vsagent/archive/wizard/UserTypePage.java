package cosco.vsagent.archive.wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cosco.vsagent.app.Constants;



public class UserTypePage extends WizardPage {
	private Combo combo;

	/**
	 * Create the wizard
	 */
	public UserTypePage() {
		super("wizardPage");
	}

	/**
	 * Create contents of the wizard
	 * @param parent
	 */
	public void createControl(Composite parent) {
		setTitle("添加用户");
		setMessage("选择用户类型",this.INFORMATION);
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout());
		
		Composite c = new Composite(container, SWT.NULL);
		c.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		c.setLayout(new RowLayout());
		
		new Label(c, SWT.NONE).setText("用户类型");
		combo = new Combo(c, SWT.BORDER);
		combo.setLayoutData(new RowData(50,-1));
		combo.add("老师");
		combo.add("学生");
		
		combo.setData("老师", Constants.IUSER_TEACHER_TYPE);
		combo.setData("学生", Constants.IUSER_STUDENT_TYPE);
		
		combo.select(0);
		
		//
		setControl(container);
	}
	
	public String getUserType(){
		String key = combo.getText();
		return combo.getData(key)+"";
	}

}
