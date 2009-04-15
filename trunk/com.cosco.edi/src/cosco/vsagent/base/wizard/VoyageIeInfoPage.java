package cosco.vsagent.base.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import com.swtdesigner.ResourceManager;
import cosco.vsagent.app.Activator;

public class VoyageIeInfoPage extends WizardPage {

	/**
	 * Create the wizard
	 */
	public VoyageIeInfoPage() {
		super("wizardPage");
		setTitle("������Ϣ");
		setDescription("�����뺽����ϸ��Ϣ");
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.getDefault(), "wizard/profile_wiz.gif"));
	}

	/**
	 * Create contents of the wizard
	 * @param parent
	 */
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NULL);
		//
		setControl(container);
	}

}
