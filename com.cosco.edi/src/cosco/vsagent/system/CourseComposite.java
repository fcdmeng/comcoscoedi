package cosco.vsagent.system;


import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;

import cosco.vsagent.model.Course;


public class CourseComposite {
	private Group group;
	private List courseList;
	
	public CourseComposite(Composite parent, int style){
		createCourseComp(parent, style);
	}

	private Composite createCourseComp(Composite parent, int style) {
		// TODO Auto-generated method stub
		group = new Group(parent, style);
		group.setText("课程");
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout(2, false));
		
		courseList = new List(group, SWT.BORDER |SWT.H_SCROLL | SWT.V_SCROLL);
		courseList.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite cmdComp = new Composite(group, SWT.NONE);
		cmdComp.setLayout(new RowLayout(SWT.VERTICAL));
		Button addButton = new Button(cmdComp, SWT.NONE);
		addButton.setText("增加");
		
		addButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				CourseDialog dialog = new CourseDialog(null);
				if (dialog.open() == IDialogConstants.OK_ID){
					Course course = dialog.getCourse();
					add(course);
				}
			}
		});
		
		Button removeButton = new Button(cmdComp, SWT.NONE);
		removeButton.setText("删除");
		removeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				if(courseList.getSelection().length !=0){
					String sel = courseList.getSelection()[0];
					courseList.remove(sel);
				}
			}
		});
		
		return group;
		
	}
	
	public void add(Course course){
		if(courseList.indexOf(course.getName())<0){
			String name = course.getName();
			courseList.add(name);
			courseList.setData(name, course);
		}else{
			MessageDialog.openError(null, "", "已存在的课程不能添加");
		}
	}
	
	public String[] getItems(){
		return courseList.getItems();
	}
	
	public Course getData(String key){
		return (Course)courseList.getData(key);
	}
	
	public void setVisible(boolean enabled){
		group.setVisible(false);
	}

}
