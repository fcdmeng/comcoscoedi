package cosco.vsagent.base;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;

import cosco.vsagent.base.wizard.BasPartyWizard;
import cosco.vsagent.dbo.base.BaspartyDAO;
import cosco.vsagent.mapping.base.Basparty;
import cosco.vsagent.system.EditorActionGroup;

public class BaspartyEditorActionGroup extends EditorActionGroup {

	TableViewer tv;
	public BaspartyEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}



	public void fireFirstAction(){
		tv = getTv();
		tv.setInput( new BaspartyDAO().getBasparty(getQueryInfo()));
		refreshActionsState();
	}
	
	
	public void firePrevAction(){
		tv = getTv();
		getQueryInfo().currentPage =1;
		tv.setInput( new BaspartyDAO().getBasparty(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireNextAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().currentPage + 1;
		tv.setInput( new BaspartyDAO().getBasparty(getQueryInfo()));
		super.refreshActionsState();
	}
	
	public void fireLastAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().pageCount;
		tv.setInput( new BaspartyDAO().getBasparty(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireDownloadDataAction() {
		
		Job job = new Job("读取数据……"){
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				final List<Basparty> clientData;
				final List<Basparty> serverData;
				monitor.beginTask("任务执行中...",IProgressMonitor.UNKNOWN);
				monitor.setTaskName("记取客户端数据……");
				clientData = new BaspartyDAO().getBasparty("select * from Basparty ");
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				
				monitor.setTaskName("记取服务器端数据……");
				serverData = new BaspartyDAO().getBasparty("select * from Basparty ", true);
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
	
				// TODO Auto-generated method stub
				for(int i =0; i<clientData.size();i++){
					Basparty o =(Basparty)clientData.get(i);
	//				monitor.setTaskName("流水号:"+o.getSequence());
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
				}
				
				for(int i =0; i<serverData.size();i++){
					Basparty o =(Basparty)serverData.get(i);
					monitor.setTaskName("代码:"+o.getPartycode()+" 名称:"+o.getCname());
					if(new BaspartyDAO().inserBasparty(o)== true){
						monitor.setTaskName("代码:"+o.getPartycode()+" 名称:"+o.getCname()+"……插入数据成功!");
					}else{
	//					return Status.CANCEL_STATUS;
					}
					monitor.worked(i);
					 try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
				}
				
				monitor.done();
				
				return Status.OK_STATUS;
			}
			
		};	
		job.setPriority(job.SHORT);
		job.setUser(true);
		
		job.addJobChangeListener(new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
				if (event.getResult().isOK())
					System.out.println("工作完成");
				else
					System.out.println("工作没有完成，请检查数据的完整性");
			}
		});
		
		job.schedule();
		
	}
	public void fireRemoveAction(){
		IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
		
		Basparty basparty = (Basparty)sel.getFirstElement();
		if (basparty == null) return;
		if(MessageDialog.openConfirm(null, null, "确定要删除吗？")){
	
			if(new BaspartyDAO().removeBasparty(basparty)){
				tv.remove(basparty);
				List list = (List) tv.getInput();
				list.remove(basparty);
			}else{
				MessageDialog.openConfirm(null, null, "删除失败");
			}
		}
	}
	
	public void fireAddAction(){
		BasPartyWizard wizard = new BasPartyWizard();
		WizardDialog dialog = new WizardDialog(null, wizard);
		wizard.setFlag("INSERT");
//		dialog.setPageSize(-1, 100);
		if(dialog.open() == IDialogConstants.OK_ID){
			Basparty basparty = wizard.getBasparty();
//			basvslmsg.setMadetime(new Date());
			
			if(new BaspartyDAO().inserBasparty(basparty) == true){
				MessageDialog.openInformation(null, "", "插入成功");
				
				Basparty o = new BaspartyDAO().findByPartycode(basparty.getPartycode());
				tv.add(o);
				((List)tv.getInput()).add(o);
				
			}else{
				MessageDialog.openError(null, null, "插入失败");
			}
			
		}
		


//		MessageDialog.openInformation(null, null, workspace_path);
//		System.out.println(Activator.getDefault().getStateLocation().makeAbsolute().toFile().getAbsolutePath());
		//String path = FileLocator.toFileURL(Platform.getBundle("cosco.vsagent.app").getEntry("").getPath().toString+"db/");
		
		
		
	}
	
	
	public void fireModifyAction(){
		IStructuredSelection sel =(IStructuredSelection) tv.getSelection();
		Basparty basparty = (Basparty) sel.getFirstElement();
		if(basparty == null) return;
		
		BasPartyWizard wizard = new BasPartyWizard();
		WizardDialog dialog = new WizardDialog(null, wizard);
		wizard.setFlag("MODIFY");
		wizard.setBasparty(basparty);
		
		if (dialog.open() == IDialogConstants.OK_ID){
			Basparty basparty_d = wizard.getBasparty();
			if(new BaspartyDAO().modifyBasparty(basparty_d) == true){
				basparty_d = new BaspartyDAO().findByPartycode(basparty.getPartycode());
				
				tv.remove(basparty);
				List list = (List) tv.getInput();
				list.remove(basparty);
				tv.add(basparty_d);
				((List)tv.getInput()).add(basparty_d);
			}else{
				MessageDialog.openInformation(null, null, "数据修改失败");
			}
		}
	
	}
}
