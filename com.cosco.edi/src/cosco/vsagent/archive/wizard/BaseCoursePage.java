package cosco.vsagent.archive.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import cosco.vsagent.model.Course;

public class BaseCoursePage extends WizardPage implements ModifyListener{
	Text name;
	/**
	 * Create the wizard
	 */
	public BaseCoursePage() {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
	}

	/**
	 * Create contents of the wizard
	 * @param parent
	 */
	public void createControl(Composite parent) {
		setTitle("添加新课程");
		setMessage("添加新课程", INFORMATION);
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout());
		
		Composite c = new Composite(container, SWT.NULL);
		c.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		c.setLayout(new RowLayout());
		new Label(c, SWT.NONE).setText("新课程名");
		name = new Text(c, SWT.BORDER);
		name.setLayoutData(new RowData(120, -1));
		name.addModifyListener(this);
		//
		setControl(container);
	}

	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub
		setPageComplete(false);
		
		if(name.getText().equals("")){
			setErrorMessage("课程名称必须添写");
			return;
		}
		
		setErrorMessage(null);
		setPageComplete(true);
		
	}
	
	public void getValue(Course course){
		course.setName(name.getText());
	}

}
