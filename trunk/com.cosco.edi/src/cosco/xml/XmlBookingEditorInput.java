package cosco.xml;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class XmlBookingEditorInput implements IEditorInput {
	String flag;
	public XmlBookingEditorInput(String string) {
		// TODO Auto-generated constructor stub
		this.flag = string;
	}

	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		if(this.getFlag().equals("ECD")) return "��װ��������˵�";
		if(this.getFlag().equals("ICD")) return "��װ��������˵�";
		return "���˵�";
	}

	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToolTipText() {
		// TODO Auto-generated method stub
		if(this.getFlag().equals("ECD")) return "��װ��������˵�";
		if(this.getFlag().equals("ICD")) return "��װ��������˵�";
		return "���˵�";
	}

	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

}
