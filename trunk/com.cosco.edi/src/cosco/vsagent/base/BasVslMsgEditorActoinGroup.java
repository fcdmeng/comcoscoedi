package cosco.vsagent.base;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;

import cosco.vsagent.base.wizard.BasVslMsgWizard;
import cosco.vsagent.dbo.base.BasvslmsgDAO;
import cosco.vsagent.mapping.base.Basvslmsg;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.EditorActionGroup;

public class BasVslMsgEditorActoinGroup extends EditorActionGroup {
	TableViewer tv;
	public BasVslMsgEditorActoinGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	
	public void fireAddAction() {
		BasVslMsgWizard wizard = new BasVslMsgWizard();
		WizardDialog dialog = new WizardDialog(null, wizard);
		wizard.setFlag("INSERT");
//		dialog.setPageSize(-1, 100);
		if(dialog.open() == IDialogConstants.OK_ID){
			Basvslmsg basvslmsg = wizard.getBasvslmsg();
//			basvslmsg.setMadetime(new Date());
			
			if(new BasvslmsgDAO().inserBasvslmsg(basvslmsg) == true){
				MessageDialog.openInformation(null, "", "插入成功");
				
				Basvslmsg o = new BasvslmsgDAO().findByVslcode(basvslmsg.getVslcode());
				tv.add(o);
				((List)tv.getInput()).add(o);
				
			}else{
				MessageDialog.openError(null, null, "插入失败");
			}
			
		}
	}
	
	public void fireModifyAction(){
		IStructuredSelection sel  = (IStructuredSelection) tv.getSelection();
		Basvslmsg basvslmsg1 = (Basvslmsg)sel.getFirstElement();
		if(basvslmsg1 == null) return;
		BasVslMsgWizard wizard = new BasVslMsgWizard();
		WizardDialog dialog = new WizardDialog(null, wizard);
		wizard.setFlag("MODIFY");
		wizard.setBasvslmsg(basvslmsg1);
		if(dialog.open() == IDialogConstants.OK_ID){
			Basvslmsg basvslmsg = wizard.getBasvslmsg();
			if(new BasvslmsgDAO().modifyBasvslmsg(basvslmsg) == true){
//				MessageDialog.openInformation(null, null, "数据修改成功");
				basvslmsg = new BasvslmsgDAO().findByVslcode(basvslmsg.getVslcode());
				
//				tv.refresh(basvslmsg);
				/**
				 * refresh不知为何不好用
				 */
				tv.remove(basvslmsg1);
				List list = (List) tv.getInput();
				list.remove(basvslmsg1);
				
				tv.add(basvslmsg);
				((List)tv.getInput()).add(basvslmsg);
				

			}else{
				MessageDialog.openInformation(null, null, "数据修改失败");
			}
			
		}
		
		
	}
	
	public void fireDownloadDataAction() {
		
		Job job = new Job("读取数据……"){
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				final List<Basvslmsg> clientData;
				final List<Basvslmsg> serverData;
				monitor.beginTask("任务执行中...",IProgressMonitor.UNKNOWN);
				monitor.setTaskName("读取客户端数据……");
				clientData = new BasvslmsgDAO().getBasvslmsg("select * from Basvslmsg ");
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				
				monitor.setTaskName("读取服务器端数据……");
				serverData = new BasvslmsgDAO().getBasvslmsg("select * from Basvslmsg ", true);
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;

				// TODO Auto-generated method stub
				for(int i =0; i<clientData.size();i++){
					Basvslmsg o =(Basvslmsg)clientData.get(i);
					monitor.setTaskName("流水号:"+o.getSequence());
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
				}
				
				for(int i =0; i<serverData.size();i++){
					Basvslmsg o =(Basvslmsg)serverData.get(i);
					monitor.setTaskName("英文船名:"+o.getEnname()+" 中文船名:"+o.getCnname());
					if(new BasvslmsgDAO().inserBasvslmsg(o)== true){
						monitor.setTaskName("英文船名:"+o.getEnname()+" 中文船名:"+o.getCnname()+"……插入数据成功!");
					}else{
						return null;
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
					System.out.println("工作没有完成");
			}
		});
		
		job.schedule();
		
	}
	
	public void fireRemoveAction(){
		IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
		
		Basvslmsg basvslmsg = (Basvslmsg)sel.getFirstElement();
		if (basvslmsg == null) return;
		if(MessageDialog.openConfirm(null, null, "确定要删除吗？")){

			if(new BasvslmsgDAO().removeBasvslmsg(basvslmsg)){
				tv.remove(basvslmsg);
				List list = (List) tv.getInput();
				list.remove(basvslmsg);
			}else{
				MessageDialog.openConfirm(null, null, "删除失败");
			}
		}
	}
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput( new BasvslmsgDAO().getBasvslmsg(getQueryInfo()));
		refreshActionsState();
	}
	

	public void firePrevAction(){
		tv = getTv();
		getQueryInfo().currentPage =1;
		tv.setInput( new BasvslmsgDAO().getBasvslmsg(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireNextAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().currentPage + 1;
		tv.setInput( new BasvslmsgDAO().getBasvslmsg(getQueryInfo()));
		super.refreshActionsState();
	}
	
	public void fireLastAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().pageCount;
		tv.setInput( new BasvslmsgDAO().getBasvslmsg(getQueryInfo()));
		refreshActionsState();
	}
	
}


