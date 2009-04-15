package cosco.vsagent.ecd;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;



import cosco.vsagent.base.ChooseVslvoy;
import cosco.vsagent.console.ConsoleCommand;
import cosco.vsagent.dbo.ecd.EcdbookingDAO;
import cosco.vsagent.ecd.wizard.EcdBookingWizard;
import cosco.vsagent.mapping.base.Basvslvoy;
import cosco.vsagent.mapping.ecd.Ecdbooking;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.EditorActionGroup;
import cosco.vsagent.system.ImagesContext;

public class EcdBookingEditorActionGroup extends EditorActionGroup {
	TableViewer tv;
	private Action selectVoyage = new SelectVoyage();
	private ConsoleCommand logconsole = new ConsoleCommand();
	public EcdBookingEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	
	public void fillActionToolBars(ToolBarManager toolBarManager){
		super.fillActionToolBars(toolBarManager);
		toolBarManager.add(createActionContrItem(selectVoyage));
	
		//���¹��ߣ�û����һ�䣬�������ϻ�û���κ���Ϣ
		toolBarManager.update(true);	
	}
	
	//Ϊ��ͬʱ��ʾͼ�����֣���ActionContributionItem��װһ��Action
	private ActionContributionItem createActionContrItem(IAction action){
		ActionContributionItem aci = new ActionContributionItem(action);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		return aci;
	}
	
	public void fireAddAction(){
		if (Context.getInstance().getBasvslvoy() == null){
			logconsole.logconsole("��ѡѡ���ں���ִ���½���������", true);
//			MessageDialog.openWarning(null, "��ʾ", "��ѡ������ִ�е������");
			return;
		}
		
		EcdBookingWizard wizard = new EcdBookingWizard();

		WizardDialog dialog = new WizardDialog(null, wizard);
		
		wizard.setFlag("INSERT");
		if (dialog.open() == IDialogConstants.OK_ID){
			
		}
	}
	
	
	public void fireModifyAction(){
		IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
		
		Ecdbooking ecdbooking = (Ecdbooking)sel.getFirstElement();
		if (ecdbooking == null) return;
		
		EcdBookingWizard wizard = new EcdBookingWizard();
		WizardDialog dialog = new WizardDialog(null, wizard);
		
		wizard.setFlag("MODIFY");
		wizard.setEcdbooking(ecdbooking);
		
		if (dialog.open() == IDialogConstants.OK_ID){
			Ecdbooking upecdbooking = wizard.getEcdbooking();
			
			if (new EcdbookingDAO().modifyEcdbooking(upecdbooking) == true){

			}else{
				MessageDialog.openInformation(null, null, "�����޸�ʧ��");
			}
			
		}
	}
	
	public void fireRemoveAction(){
		IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
		
		Ecdbooking ecdbooking = (Ecdbooking)sel.getFirstElement();
		if (ecdbooking == null) return;
		if(MessageDialog.openConfirm(null, null, "ȷ��Ҫɾ����")){

			if(new EcdbookingDAO().removeEcdbooking(ecdbooking)){
				tv.remove(ecdbooking);
				List list = (List) tv.getInput();
				list.remove(ecdbooking);
			}else{
				MessageDialog.openConfirm(null, null, "ɾ��ʧ��");
			}
		}
	}
	
	/**
	 * ѡ������
	 * @author Administrator
	 *
	 */
	private class SelectVoyage extends Action{
		public SelectVoyage(){
			setText("ѡ��");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.SELECTVOYAGE));
		}
		
		public void run(){
			//�����־
			
			ChooseVslvoy dialog  = new ChooseVslvoy(null, "E");
			
			logconsole.clearConsole();
			
			if(dialog.open() == IDialogConstants.OK_ID)
			{
				Basvslvoy basvslvoy = Context.getInstance().getBasvslvoy();
				
				logconsole.logconsole("���Ĵ���:"+basvslvoy.getVslcname());
				logconsole.logconsole("Ӣ�Ĵ���"+basvslvoy.getVslename());
				logconsole.logconsole("����:"+basvslvoy.getVoyage());
				if(basvslvoy.getIe().equals("E")){
					logconsole.logconsole("�������:"+basvslvoy.getSaildate());
					logconsole.logconsole("���ʱ��:"+basvslvoy.getSailtime());
				}
				
				logconsole.logconsole(StringUtils.rightPad("",50,"*"));
				fireFirstAction();
			}
		}
	}
	
	
	public void fireFirstAction(){
		if (Context.getInstance().getBasvslvoy() == null){
//			MessageDialog.openWarning(null, "��ʾ", "��ѡ���ں���ִ�е�������");
			return;
		}
		final String vslkey_seq = Context.getInstance().getBasvslvoy().getSequence();
		
		tv = getTv();
		tv.setInput( new EcdbookingDAO().getEcdbooking(getQueryInfo(), vslkey_seq));
		refreshActionsState();
	}
	

	public void fireDownloadDataAction() {
		
		if (Context.getInstance().getBasvslvoy() == null){
			MessageDialog.openWarning(null, "��ʾ", "��ѡ���ں���ִ�е�������");
			return;
		}
		final String vslkey_seq = Context.getInstance().getBasvslvoy().getSequence();
		final String voyage = Context.getInstance().getBasvslvoy().getVoyage();
			Job job = new Job("��ȡ���ݡ���"){
				
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					
					final List<Ecdbooking> clientData;
					final List<Ecdbooking> serverData;
					monitor.beginTask("����ִ����...",IProgressMonitor.UNKNOWN);
					monitor.setTaskName("��ȡ�ͻ������ݡ���");
					clientData = new EcdbookingDAO().getEcdbooking("select * from Ecdbooking where vslkey_seq='"+vslkey_seq+"'");
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
					
					monitor.setTaskName("��ȡ�����������ݡ���");
					serverData = new EcdbookingDAO().getEcdbooking("select * from Ecdbooking where vslvoykey="+vslkey_seq+" ", true);
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
	
					// TODO Auto-generated method stub
					for(int i =0; i<clientData.size();i++){
						Ecdbooking o =(Ecdbooking)clientData.get(i);
						monitor.setTaskName("�ᵥ��:"+o.getBlno());
						
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
					}
					
					for(int i =0; i<serverData.size();i++){
						Ecdbooking o =(Ecdbooking)serverData.get(i);
						monitor.setTaskName("�ᵥ��:"+o.getBlno());
						if(new EcdbookingDAO().inserEcdbooking(o)== true){
							monitor.setTaskName("�ᵥ��:"+o.getBlno()+"�����������ݳɹ�!"+"["+i+"]");
						}else{
							System.out.println("�ᵥ��:"+o.getBlno()+"����ʧ��");
							//return Status.CANCEL_STATUS;
						}
						monitor.worked(i);
	/*					try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						
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
}
