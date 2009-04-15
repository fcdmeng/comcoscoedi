package cosco.vsagent.ecd;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class BasvslvoyEditorInput implements IEditorInput {
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public BasvslvoyEditorInput(String flag){
		setFlag(flag);
	}
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		String ls_title="";
		if (getFlag().equals("E")) ls_title =  "出口船期表";
		if (getFlag().equals("I")) ls_title =  "进口船期表";
		return ls_title;
	}

	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToolTipText() {
		// TODO Auto-generated method stub
		String ls_tip="";
		if (getFlag().equals("E")) ls_tip =  "出口船期表";
		if (getFlag().equals("I")) ls_tip =  "进口船期表";

		return ls_tip;
	}

	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

}
