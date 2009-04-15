package cosco.vsagent.navigator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.actions.ActionGroup;

import cosco.vsagent.app.Activator;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.ILogonListener;
import cosco.vsagent.system.ImagesContext;


public class NavigatorActionGroup extends ActionGroup {
	private Action logonAction = new LogonAction();
	private Action logoffAction = new LogoffAction();
	private Context ctx = Context.getInstance();
	private ILogonListener logonListener = new MyLogonListener();
	
	public NavigatorActionGroup(){
		ctx.addLogonListener(logonListener);
		setActionsEnabled(ctx.isLogon());
	}
	
	private void setActionsEnabled(boolean logon){
		logonAction.setEnabled(!logon);
		logoffAction.setEnabled(logon);
	}
	
	public void dispose(){
		ctx.removeLogonListener(logonListener);
		super.dispose();
	}
	
	public void fillActionBars(IActionBars actionBars){
		IToolBarManager toolBar = actionBars.getToolBarManager();
		toolBar.add(logonAction);
		toolBar.add(logoffAction);
	}
	private class LogonAction extends Action{
		public LogonAction(){
			setText("µÇÂ¼");
			this.setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.LOGON));
		}
		public void run(){
			LogonDialog dialog = new LogonDialog(new Shell());
			if(dialog.open() == IDialogConstants.OK_ID)
			{
				ctx.fireLogonEvent();
				String s ="µÇÂ¼: " + Context.getInstance().getCurrentUser().getName();
				Image image = Activator.getDefault().getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_WARNING);
				cosco.vsagent.app.Activator.getDefault().getStatusLine().setMessage(image,s);
			}
		}
	}
	

	private class LogoffAction extends Action{
		public LogoffAction(){
			setText("ÍË³ö");
			this.setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.LOGOFF));
		}
		public void run(){
			ctx.fireLogoffEvent();
		}
	}
	
	private class MyLogonListener implements ILogonListener{
		public void logon(){
			setActionsEnabled(true);
		}
		public void logoff(){
			setActionsEnabled(false);
		}
	}

}
