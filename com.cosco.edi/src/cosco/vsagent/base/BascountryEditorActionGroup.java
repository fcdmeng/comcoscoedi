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

import cosco.vsagent.dbo.base.BascountryDAO;
import cosco.vsagent.mapping.base.Bascountry;
import cosco.vsagent.system.EditorActionGroup;

public class BascountryEditorActionGroup extends EditorActionGroup {

	TableViewer tv;
	public BascountryEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	

	
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput( new BascountryDAO().getBascountry(getQueryInfo()));
		refreshActionsState();
	}
	

	public void firePrevAction(){
		tv = getTv();
		getQueryInfo().currentPage =1;
		tv.setInput( new BascountryDAO().getBascountry(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireNextAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().currentPage + 1;
		tv.setInput( new BascountryDAO().getBascountry(getQueryInfo()));
		super.refreshActionsState();
	}
	
	public void fireLastAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().pageCount;
		tv.setInput( new BascountryDAO().getBascountry(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireDownloadDataAction() {
		
		Job job = new Job("��ȡ���ݡ���"){
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				final List<Bascountry> clientData;
				final List<Bascountry> serverData;
				monitor.beginTask("����ִ����...",IProgressMonitor.UNKNOWN);
				monitor.setTaskName("��ȡ�ͻ������ݡ���");
				clientData = new BascountryDAO().getBascountry("select * from Bascountry ");
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				
				monitor.setTaskName("��ȡ�����������ݡ���");
				serverData = new BascountryDAO().getBascountry("select * from Bascountry ", true);
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;

				// TODO Auto-generated method stub
				for(int i =0; i<clientData.size();i++){
					Bascountry o =(Bascountry)clientData.get(i);
//					monitor.setTaskName("��ˮ��:"+o.getSequence());
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
				}
				
				for(int i =0; i<serverData.size();i++){
					Bascountry o =(Bascountry)serverData.get(i);
					monitor.setTaskName("����:"+o.getNationcode()+" ����:"+o.getCname());
					if(new BascountryDAO().inserBascountry(o)== true){
						monitor.setTaskName("����:"+o.getNationcode()+" ����:"+o.getCname()+"�����������ݳɹ�!");
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
					System.out.println("�������");
				else
					System.out.println("����û����ɣ��������ݵ�������");
			}
		});
		
		job.schedule();
		
	}
	public void fireRemoveAction(){
		IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
		
		Bascountry bascountry = (Bascountry)sel.getFirstElement();
		if (bascountry == null) return;
		if(MessageDialog.openConfirm(null, null, "ȷ��Ҫɾ����")){

			if(new BascountryDAO().removeBascountry(bascountry)){
				tv.remove(bascountry);
				List list = (List) tv.getInput();
				list.remove(bascountry);
			}else{
				MessageDialog.openConfirm(null, null, "ɾ��ʧ��");
			}
		}
	}
	
	public void fireAddAction(){
		
	}
	
	
	public void fireModifyAction(){
//		java.util.Date st = new java.util.Date(new java.util.Date().getTime());
//		System.out.println(st);
		MessageDialog.openInformation(null, null, "��Ч����");
	}
}
