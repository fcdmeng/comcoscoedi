package cosco.vsagent.archive;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import cosco.vsagent.model.SchoolClass;
import cosco.vsagent.system.EditorActionGroup;


public class SchoolClassEditorActionGroup extends EditorActionGroup {
	TableViewer tv;

	public SchoolClassEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput(getDb().getSchoolClass(getQueryInfo()));
		
	}
	
	public void fireRemoveAction(){
		IStructuredSelection sel = (IStructuredSelection) tv.getSelection();
		
		SchoolClass schoolClass = (SchoolClass)sel.getFirstElement();
		if(MessageDialog.openConfirm(null, null, "È·¶¨ÒªÉ¾³ýÂð£¿")){
			if(getDb().removeSchoolClass(schoolClass)){
				tv.remove(schoolClass);
				List list = (List)tv.getInput();
				list.remove(schoolClass);
				
			}else{
				MessageDialog.openConfirm(null, null, "É¾³ýÊ§°Ü!");
			}
		}
	}

}
