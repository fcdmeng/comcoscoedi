package cosco.vsagent.archive.wizard;

import org.eclipse.jface.wizard.Wizard;

import cosco.vsagent.model.Course;

public class BaseCourseWizard extends Wizard {
	private BaseCoursePage baseCoursePage;
	private Course course;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void addPages(){
		baseCoursePage = new BaseCoursePage();
		addPage(baseCoursePage);
	}
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		
		course = new Course();
		baseCoursePage.getValue(course);
		
		if(course.getName().equals("")){
			baseCoursePage.setErrorMessage("课程名不能为空");
			baseCoursePage.name.forceFocus();
			return false;
		}
		return true;
	}

}
