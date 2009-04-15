package cosco.vsagent.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import cosco.vsagent.db.ConnectManager;
import cosco.vsagent.dbo.base.BaspackageDAO;
import cosco.vsagent.mapping.base.Baspackage;
import cosco.vsagent.system.EditorActionGroup;

public class BasPackageEditorActionGroup extends EditorActionGroup {
	TableViewer tv;
	public BasPackageEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	

	
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput( new BaspackageDAO().getBaspackage(getQueryInfo()));
		refreshActionsState();
	}
	

	public void firePrevAction(){
		tv = getTv();
		getQueryInfo().currentPage =1;
		tv.setInput( new BaspackageDAO().getBaspackage(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireNextAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().currentPage + 1;
		tv.setInput( new BaspackageDAO().getBaspackage(getQueryInfo()));
		super.refreshActionsState();
	}
	
	public void fireLastAction(){
		tv = getTv();
		getQueryInfo().currentPage = getQueryInfo().pageCount;
		tv.setInput( new BaspackageDAO().getBaspackage(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireDownloadDataAction() {
		
		Job job = new Job("��ȡ���ݡ���"){
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				final List<Baspackage> clientData;
				final List<Baspackage> serverData;
				monitor.beginTask("����ִ����...",IProgressMonitor.UNKNOWN);
				monitor.setTaskName("��ȡ�ͻ������ݡ���");
				clientData = new BaspackageDAO().getBaspackage("select * from Baspackage ");
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				
				monitor.setTaskName("��ȡ�����������ݡ���");
				serverData = new BaspackageDAO().getBaspackage("select * from Baspackage ", true);
				
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;

				// TODO Auto-generated method stub
				for(int i =0; i<clientData.size();i++){
					Baspackage o =(Baspackage)clientData.get(i);
					monitor.setTaskName("��ˮ��:"+o.getSequence());
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
				}
				
				for(int i =0; i<serverData.size();i++){
					Baspackage o =(Baspackage)serverData.get(i);
					monitor.setTaskName("����:"+o.getPkgcode()+" ����:"+o.getPkgname());
					if(new BaspackageDAO().inserBaspackage(o)== true){
						monitor.setTaskName("����:"+o.getPkgcode()+" ����:"+o.getPkgname()+"�����������ݳɹ�!");
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
		
		Baspackage baspackage = (Baspackage)sel.getFirstElement();
		if (baspackage == null) return;
		if(MessageDialog.openConfirm(null, null, "ȷ��Ҫɾ����")){

			if(new BaspackageDAO().removeBaspackage(baspackage)){
				tv.remove(baspackage);
				List list = (List) tv.getInput();
				list.remove(baspackage);
			}else{
				MessageDialog.openConfirm(null, null, "ɾ��ʧ��");
			}
		}
	}
	
	public void fireAddAction(){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;


			try {
				con = ConnectManager.getServerConnection();
				sm = con.createStatement();
				rs = sm.executeQuery("select * from baspackage");
				while (rs.next()){
					
					System.out.println(rs.getTimestamp("madetime"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			
			
	}
	
	
	public void fireModifyAction(){
		java.util.Date st = new java.util.Date(new java.util.Date().getTime());
		System.out.println(st);
//		MessageDialog.openInformation(null, null, "��Ч����");
	}
}
