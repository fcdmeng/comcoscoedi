package cosco.vsagent.system;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import cosco.vsagent.app.Activator;


public class EditorPartAdapter extends EditorPart implements ILogonListener{

	public static final String ID = "cn.com.tt.sms.system.EditorPartAdapter"; //$NON-NLS-1$

	/**
	 * Create contents of the editor part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		//
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		  this.setInput(input);
	      this.setSite(site);
	      
	      Context.getInstance().addLogonListener(this);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	public void logoff() {
		// TODO Auto-generated method stub
		IWorkbenchPage activePage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if(activePage != null){
			
			boolean success = activePage.closeEditor(this, true);
			if(!success){
				if(MessageDialog.openConfirm(null, "", "±£´æ±à¼­Æ÷Ê§°Ü£¬ÊÇ·ñÖ±½Ó¹Ø±Õ"))
					activePage.closeEditor(this, false);
			}
		}
	}

	public void logon() {
		// TODO Auto-generated method stub
		
	}

	
	public void dispose(){
		Context.getInstance().removeLogonListener(this);
		super.dispose();
	}
}
