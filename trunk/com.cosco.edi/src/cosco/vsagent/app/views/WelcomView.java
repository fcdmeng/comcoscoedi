package cosco.vsagent.app.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.swtdesigner.SWTResourceManager;

public class WelcomView extends ViewPart {

	public static final String ID = "cosco.vsagent.app.views.WelcomView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
    GLCanvas canvas;
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		final Label introductionLab = new Label(container, SWT.NONE);
		introductionLab.setFont(SWTResourceManager.getFont("SimSun", 16, SWT.BOLD)); //$NON-NLS-1$
//		introductionLab.setText(Messages.getString("WelcomView.welcomemessage")); //$NON-NLS-1$
		introductionLab.setText("船代管理个人版－说明");
		introductionLab.setBounds(66, 52, 345, 36);
		//
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	public void setFocus() {
		// Set the focus
	}

}
