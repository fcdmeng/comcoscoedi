package cosco.vsagent.base.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import com.swtdesigner.ResourceManager;
import cosco.vsagent.app.Activator;

public class VoyageIeTypePage extends WizardPage {

	/**
	 * Create the wizard
	 */
	private Combo ieCombo,shipCombo;
	private String flag;
	
	public Combo getIeCombo() {
		return ieCombo;
	}

	public Combo getShipCombo() {
		return shipCombo;
	}

	public VoyageIeTypePage() {
		super("wizardPage");
		setTitle("船名航次");
		setDescription("请选择船名");
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.getDefault(), "wizard/gallery_tutorials.gif"));
	}

	/**
	 * Create contents of the wizard
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new GridLayout());
		
		Composite top = new Composite(container, SWT.NULL);
		top.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		top.setLayout(new GridLayout(2,false));
		new Label(top, SWT.NONE).setText("进出口类型");
		ieCombo = new Combo(top, SWT.BORDER | SWT.READ_ONLY);
		ieCombo.add("出口");
		ieCombo.setData("出口", "E");
		ieCombo.add("进口");
		ieCombo.setData("进口", "I");
		
		if (getFlag().equals("E")){
			ieCombo.select(0);
		}
		
		if (getFlag().equals("I")){
			ieCombo.select(1);
		}
		new Label(top, SWT.NONE).setText("船舶规范");
		shipCombo = new Combo(top, SWT.BORDER);
		
		setControl(container);
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
