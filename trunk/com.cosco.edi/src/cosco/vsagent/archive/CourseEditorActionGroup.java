package cosco.vsagent.archive;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;

import cosco.vsagent.archive.wizard.BaseCourseWizard;
import cosco.vsagent.model.Course;
import cosco.vsagent.system.EditorActionGroup;


public class CourseEditorActionGroup extends EditorActionGroup {
	TableViewer tv;
	public CourseEditorActionGroup(TableViewer tv) {
		super(tv);
		
		// TODO Auto-generated constructor stub
	}
	
	public void fireFirstAction(){
		//firstAction.run();
		
		tv = getTv();
		tv.setInput(getDb().getCourse(getQueryInfo()));
		
	}
	
	public void fireRemoveAction(){
		IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
		
		Course course = (Course)sel.getFirstElement();
		if (course == null) return;
		if(MessageDialog.openConfirm(null, null, "确定要删除吗？")){

			if(getDb().removeCourse(course)){
				tv.remove(course);
				List list = (List) tv.getInput();
				list.remove(course);
			}else{
				MessageDialog.openConfirm(null, null, "删除失败");
			}
		}
	}
	
	public void fireAddAction(){
		BaseCourseWizard wizard = new BaseCourseWizard();
		WizardDialog dialog = new WizardDialog(null, wizard);
		dialog.setPageSize(-1, 100);
		if(dialog.open() == IDialogConstants.OK_ID){
			Course course = wizard.getCourse();
			if(getDb().insertCourse(course)){
				MessageDialog.openInformation(null, "", "插入成功");
				
				Course o = getDb().getCourses(course.getName());
				tv.add(o);
				((List)tv.getInput()).add(o);
				
			}else{
				MessageDialog.openError(null, null, "插入失败");
			}
			
		}
	}

}
