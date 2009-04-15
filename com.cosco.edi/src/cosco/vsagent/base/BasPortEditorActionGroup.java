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

import cosco.vsagent.dbo.base.BasportDAO;
import cosco.vsagent.mapping.base.Basport;
import cosco.vsagent.system.EditorActionGroup;

public class BasPortEditorActionGroup extends EditorActionGroup {
	TableViewer tv;
	public BasPortEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput( new BasportDAO().getBasport(getQueryInfo()));
		refreshActionsState();
	}
	

	public void firePrevAction(){
		tv = getTv();
		getQueryInfo().currentPage =1;
		tv.setInput( new BasportDAO().getBasport(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireNextAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().currentPage + 1;
		tv.setInput( new BasportDAO().getBasport(getQueryInfo()));
		super.refreshActionsState();
	}
	
	public void fireLastAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().pageCount;
		tv.setInput( new BasportDAO().getBasport(getQueryInfo()));
		refreshActionsState();
	}
	
public void fireDownloadDataAction() {
		
		Job job = new Job("��ȡ���ݡ���"){
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				final List<Basport> clientData;
				final List<Basport> serverData;
				monitor.beginTask("����ִ����...",IProgressMonitor.UNKNOWN);
				monitor.setTaskName("��ȡ�ͻ������ݡ���");
				clientData = new BasportDAO().getBasport("select * from Basport");
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				
				monitor.setTaskName("��ȡ�����������ݡ���");
				serverData = new BasportDAO().getBasport("select * from Basport", true);
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;

				// TODO Auto-generated method stub
				for(int i =0; i<clientData.size();i++){
					Basport o =(Basport)clientData.get(i);
					monitor.setTaskName("��ˮ��:"+o.getSequence());
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
				}
				
				for(int i =0; i<serverData.size();i++){
					Basport o =(Basport)serverData.get(i);
					monitor.setTaskName("Ӣ������:"+o.getEname()+" ��������:"+o.getCname());
					if(new BasportDAO().inserBasport(o)== true){
						monitor.setTaskName("Ӣ������:"+o.getEname()+" ��������:"+o.getCname()+"�����������ݳɹ�!");
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
					System.out.println("�������");
				else
					System.out.println("����û�����");
			}
		});
		
		job.schedule();
		
	}
	public void fireRemoveAction(){
		IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
		
		Basport basport = (Basport)sel.getFirstElement();
		if (basport == null) return;
		if(MessageDialog.openConfirm(null, null, "ȷ��Ҫɾ����")){

			if(new BasportDAO().removeBasport(basport)){
				tv.remove(basport);
				List list = (List) tv.getInput();
				list.remove(basport);
			}else{
				MessageDialog.openConfirm(null, null, "ɾ��ʧ��");
			}
		}
	}
	
	public void fireAddAction(){
		MessageDialog.openInformation(null, null, "��Ч����");
	}
	
	public void fireModifyAction(){
		MessageDialog.openInformation(null, null, "��Ч����");
	}

}
