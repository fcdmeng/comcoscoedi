package cosco.vsagent.ecd;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;

import cosco.vsagent.base.wizard.VoyageWizard;
import cosco.vsagent.dbo.base.BasvslvoyDAO;
import cosco.vsagent.mapping.base.Basvslvoy;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.EditorActionGroup;

public class BasvslvoyEditorActionGroup extends EditorActionGroup {
	TableViewer tv;
	private String flag;
	public String getFlag() {		
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BasvslvoyEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	
	public void fireAddAction(){
		VoyageWizard wizard = new VoyageWizard(getFlag());

		WizardDialog dialog = new WizardDialog(null, wizard);
		
		if (dialog.open() == IDialogConstants.OK_ID){
			
		}
		
	}
	

	public void fireDownloadDataAction() {
		
		Job job = new Job("��ȡ���ݡ���"){
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				final List<Basvslvoy> clientData;
				final List<Basvslvoy> serverData;
				monitor.beginTask("����ִ����...",IProgressMonitor.UNKNOWN);
				monitor.setTaskName("��ȡ�ͻ������ݡ���");
				clientData = new BasvslvoyDAO().getBasvslvoy("select * from Basvslvoy ");
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				
				monitor.setTaskName("��ȡ�����������ݡ���");
				serverData = new BasvslvoyDAO().getBasvslvoy("select * from Basvslvoy ", true);
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;

				// TODO Auto-generated method stub
				for(int i =0; i<clientData.size();i++){
					Basvslvoy o =(Basvslvoy)clientData.get(i);
					monitor.setTaskName("����:"+o.getVslcname()+" ����:"+ o.getVoyage());
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
				}
				
				for(int i =0; i<serverData.size();i++){
					Basvslvoy o =(Basvslvoy)serverData.get(i);
					monitor.setTaskName("����:"+o.getVslcname()+" ����:"+ o.getVoyage());
					if(new BasvslvoyDAO().inserBasvslvoy(o)== true){
						monitor.setTaskName("����:"+o.getVslcname()+" ����:"+o.getVoyage()+"�����������ݳɹ�!"+"["+i+"]");
					}else{
						System.out.println(o.getMainkey()+"����ʧ��");
						//return Status.CANCEL_STATUS;
					}
					monitor.worked(i);
//					try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					
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
	
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput( new BasvslvoyDAO().getBasvslvoy(getQueryInfo()));
		refreshActionsState();
	}
	

	public void firePrevAction(){
		tv = getTv();
		getQueryInfo().currentPage =1;
		tv.setInput( new BasvslvoyDAO().getBasvslvoy(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireNextAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().currentPage + 1;
		tv.setInput( new BasvslvoyDAO().getBasvslvoy(getQueryInfo()));
		super.refreshActionsState();
	}
	
	public void fireLastAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().pageCount;
		tv.setInput( new BasvslvoyDAO().getBasvslvoy(getQueryInfo()));
		refreshActionsState();
	}
	

}
