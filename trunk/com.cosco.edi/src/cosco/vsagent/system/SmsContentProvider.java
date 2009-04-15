package cosco.vsagent.system;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class SmsContentProvider implements IStructuredContentProvider {

	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if(inputElement instanceof Collection){
			return ((Collection)inputElement).toArray();
		}else
			return new Object[0];

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
