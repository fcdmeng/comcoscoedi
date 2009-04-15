package cosco.vsagent.base;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import cosco.vsagent.dbo.base.BaslineDAO;
import cosco.vsagent.mapping.base.Basline;
import cosco.vsagent.system.EditorActionGroup;

public class BaslineEditorActionGroup extends EditorActionGroup {

	TableViewer tv;
	public BaslineEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	

	
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput( new BaslineDAO().getBasline(getQueryInfo()));
		refreshActionsState();
	}
	

	public void firePrevAction(){
		tv = getTv();
		getQueryInfo().currentPage =1;
		tv.setInput( new BaslineDAO().getBasline(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireNextAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().currentPage + 1;
		tv.setInput( new BaslineDAO().getBasline(getQueryInfo()));
		super.refreshActionsState();
	}
	
	public void fireLastAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().pageCount;
		tv.setInput( new BaslineDAO().getBasline(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireDownloadDataAction() {
		
		Job job = new Job("读取数据……"){
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				final List<Basline> clientData;
				final List<Basline> serverData;
				monitor.beginTask("任务执行中...",IProgressMonitor.UNKNOWN);
				monitor.setTaskName("读取客户端数据……");
				clientData = new BaslineDAO().getBasline("select * from Basline ");
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				
				monitor.setTaskName("读取服务器端数据……");
				serverData = new BaslineDAO().getBasline("select * from Basline ", true);
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;

				// TODO Auto-generated method stub
				for(int i =0; i<clientData.size();i++){
					Basline o =(Basline)clientData.get(i);
//					monitor.setTaskName("流水号:"+o.getSequence());
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
				}
				
				for(int i =0; i<serverData.size();i++){
					Basline o =(Basline)serverData.get(i);
					monitor.setTaskName("代码:"+o.getLinecode()+" 名称:"+o.getLinename());
					if(new BaslineDAO().inserBasline(o)== true){
						monitor.setTaskName("代码:"+o.getLinecode()+" 名称:"+o.getLinename()+"……插入数据成功!");
					}else{
//						return Status.CANCEL_STATUS;
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
		
		Basline basline = (Basline)sel.getFirstElement();
		if (basline == null) return;
		if(MessageDialog.openConfirm(null, null, "确定要删除吗？")){

			if(new BaslineDAO().removeBasline(basline)){
				tv.remove(basline);
				List list = (List) tv.getInput();
				list.remove(basline);
			}else{
				MessageDialog.openConfirm(null, null, "删除失败");
			}
		}
	}
	
	public void fireAddAction(){
		
	}
	
	
	public void fireModifyAction(){
//		java.util.Date st = new java.util.Date(new java.util.Date().getTime());
//		System.out.println(st);
		MessageDialog.openInformation(null, null, "无效操作");
	}
}
