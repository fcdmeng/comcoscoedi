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

import cosco.vsagent.model.SchoolClass;
import cosco.vsagent.model.Student;
import cosco.vsagent.system.SmsUtil;


public class SchoolClassPage extends WizardPage {
	private Combo combo;

	protected SchoolClassPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		setTitle("添加用户");
		setMessage("",INFORMATION);
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new GridLayout());
		
		Composite c = new Composite(topComp, SWT.NONE);
		c.setLayoutData(new GridData(GridData.CENTER,GridData.CENTER, true, true));
		c.setLayout(new RowLayout());
		new Label(c, SWT.NONE).setText("班级:");
		
		combo = SmsUtil.createSchoolClassCombo(c, SWT.BORDER|SWT.READ_ONLY);
		combo.setLayoutData(new RowData(100, -1));
		combo.select(0);
		setControl(topComp);

	}
	
	public void getValue(Student student){
		String key = combo.getText();
		SchoolClass sc =(SchoolClass) combo.getData(key);
		student.setSchoolclass(sc);
	}

}
