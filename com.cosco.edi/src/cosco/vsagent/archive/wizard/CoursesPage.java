package cosco.vsagent.archive.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import cosco.vsagent.model.Course;
import cosco.vsagent.model.Teacher;
import cosco.vsagent.system.CourseComposite;




public class CoursesPage extends WizardPage {
	private CourseComposite courseComp;
	/**
	 * Create the wizard
	 */
	public CoursesPage() {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
	}

	/**
	 * Create contents of the wizard
	 * @param parent
	 */
	public void createControl(Composite parent) {
		setTitle("添加用户");
		setMessage("输入老师所教的课程",INFORMATION);
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout());
		
		courseComp = new CourseComposite(container, SWT.NONE);
		//
		setControl(container);
	}
	
	public void getValue(Teacher teacher){
		teacher.clearCourses();
		String[]strs = courseComp.getItems();
		for(int i =0;i<strs.length;i++){
			Course course = courseComp.getData(strs[i]);
			teacher.addCourse(course);
		}
	}

}
