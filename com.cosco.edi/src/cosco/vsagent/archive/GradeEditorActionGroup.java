package cosco.vsagent.archive;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import cosco.vsagent.model.Grade;
import cosco.vsagent.system.EditorActionGroup;


public class GradeEditorActionGroup extends EditorActionGroup {
	TableViewer tv;
	public GradeEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput(getDb().getGrade(getQueryInfo()));
	}
	
	public void fireRemoveAction(){
		IStructuredSelection sel = (IStructuredSelection) tv.getSelection();
		Grade grade = (Grade)sel.getFirstElement();
		if (grade == null) return;
		if (MessageDialog.openConfirm(null, null, "È·¶¨ÒªÉ¾³ýÂð?")){
			if(getDb().removeGrade(grade)){
				tv.remove(grade);
				List list = (List) tv.getInput();
				list.remove(grade);
			}else{
				MessageDialog.openConfirm(null, null, "É¾³ýÊ§°Ü!");
			}
		}
	}
	

}
