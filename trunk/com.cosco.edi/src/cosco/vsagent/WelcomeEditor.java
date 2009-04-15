package cosco.vsagent;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import com.swtdesigner.ResourceManager;
import cosco.vsagent.app.Activator;

public class WelcomeEditor extends EditorPart {
	GLCanvas canvas;
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());	
		parent.setBackgroundImage(ResourceManager.getPluginImage(Activator.getDefault(), "wizard/welcome.jpg"));
//		parent.setBackgroundImage(ResourceManager.getPluginImage(Activator.getDefault(), "wizard/welcome.jpg"));

	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		 this.setInput(input);
	     this.setSite(site);
		
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
