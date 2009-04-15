package cosco.vsagent.system;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import cosco.vsagent.model.Course;


//�������пγ̵ĶԻ���
public class CourseDialog extends Dialog {
	private Combo combo;
	private Course course;

	public CourseDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		topComp.setLayout(new FillLayout());
		// �õ�һ���������пγ̼�¼��Combo
		combo = SmsUtil.createCourseCombo(topComp, SWT.BORDER | SWT.READ_ONLY);
		// ����γ�course������ֵ����combo��ǰ����Ϊ��ֵ���������һ��
		if (course == null)
			combo.select(0);
		else
			combo.setText(course.getName());
		return topComp;
	}

	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			String sel = combo.getText();
			course = (Course) combo.getData(sel);
		}
		super.buttonPressed(buttonId);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
