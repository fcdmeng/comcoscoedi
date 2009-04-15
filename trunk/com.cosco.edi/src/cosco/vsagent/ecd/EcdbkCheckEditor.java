package cosco.vsagent.ecd;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import cosco.vsagent.system.EditorPartAdapter;

public class EcdbkCheckEditor extends EditorPartAdapter {

	public EcdbkCheckEditor() {
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		SashForm sashForm = new SashForm(container, SWT.NONE);
		
		TreeViewer tv = new TreeViewer(sashForm, SWT.BORDER);
		TableViewer tb = new TableViewer(sashForm, SWT.MULTI|SWT.BORDER);
		Table table = tb.getTable();
		sashForm.setWeights(new int[]{1,3});
	
	}
	
	

	

}
