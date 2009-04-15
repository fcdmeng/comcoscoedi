package cosco.vsagent.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RootPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	public RootPreferencePage() {
		// TODO Auto-generated constructor stub
	}

	public RootPreferencePage(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public RootPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new RowLayout());
		new Label(topComp, SWT.NONE).setText("有你的支持，我们会做的更好");
		return topComp;
	}

	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

}
