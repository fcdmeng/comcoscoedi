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
	
		//更新工具，没有这一句，工具栏上会没有任何信息
		toolBarManager.update(true);	
	}
	
	//为了同时显示图像文字，用ActionContributionItem包装一下Action
	private ActionContributionItem createActionContrItem(IAction action){
		ActionContributionItem aci = new ActionContributionItem(action);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		return aci;
	}
	
	public void fireAddAction(){
		if (Context.getInstance().getBasvslvoy() == null){
			logconsole.logconsole("请选选择船期后再执行新建操作……", true);
//			MessageDialog.openWarning(null, "提示", "请选择船期再执行当入操作");
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
				MessageDialog.openInformation(null, null, "数据修改失败");
			}
			
		}
	}
	
	public void fireRemoveAction(){
		IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
		
		Ecdbooking ecdbooking = (Ecdbooking)sel.getFirstElement();
		if (ecdbooking == null) return;
		if(MessageDialog.openConfirm(null, null, "确定要删除吗？")){

			if(new EcdbookingDAO().removeEcdbooking(ecdbooking)){
				tv.remove(ecdbooking);
				List list = (List) tv.getInput();
				list.remove(ecdbooking);
			}else{
				MessageDialog.openConfirm(null, null, "删除失败");
			}
		}
	}
	
	/**
	 * 选船窗口
	 * @author Administrator
	 *
	 */
	private class SelectVoyage extends Action{
		public SelectVoyage(){
			setText("选船");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.SELECTVOYAGE));
		}
		
		public void run(){
			//清空日志
			
			ChooseVslvoy dialog  = new ChooseVslvoy(null, "E");
			
			logconsole.clearConsole();
			
			if(dialog.open() == IDialogConstants.OK_ID)
			{
				Basvslvoy basvslvoy = Context.getInstance().getBasvslvoy();
				
				logconsole.logconsole("中文船名:"+basvslvoy.getVslcname());
				logconsole.logconsole("英文船名"+basvslvoy.getVslename());
				logconsole.logconsole("航次:"+basvslvoy.getVoyage());
				if(basvslvoy.getIe().equals("E")){
					logconsole.logconsole("离港日期:"+basvslvoy.getSaildate());
					logconsole.logconsole("离港时间:"+basvslvoy.getSailtime());
				}
				
				logconsole.logconsole(StringUtils.rightPad("",50,"*"));
				fireFirstAction();
			}
		}
	}
	
	
	public void fireFirstAction(){
		if (Context.getInstance().getBasvslvoy() == null){
//			MessageDialog.openWarning(null, "提示", "请选择船期后再执行导入数据");
			return;
		}
		final String vslkey_seq = Context.getInstance().getBasvslvoy().getSequence();
		
		tv = getTv();
		tv.setInput( new EcdbookingDAO().getEcdbooking(getQueryInfo(), vslkey_seq));
		refreshActionsState();
	}
	

	public void fireDownloadDataAction() {
		
		if (Context.getInstance().getBasvslvoy() == null){
			MessageDialog.openWarning(null, "提示", "请选择船期后再执行导入数据");
			return;
		}
		final String vslkey_seq = Context.getInstance().getBasvslvoy().getSequence();
		final String voyage = Context.getInstance().getBasvslvoy().getVoyage();
			Job job = new Job("读取数据……"){
				
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					
					final List<Ecdbooking> clientData;
					final List<Ecdbooking> serverData;
					monitor.beginTask("任务执行中...",IProgressMonitor.UNKNOWN);
					monitor.setTaskName("读取客户端数据……");
					clientData = new EcdbookingDAO().getEcdbooking("select * from Ecdbooking where vslkey_seq='"+vslkey_seq+"'");
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
					
					monitor.setTaskName("读取服务器端数据……");
					serverData = new EcdbookingDAO().getEcdbooking("select * from Ecdbooking where vslvoykey="+vslkey_seq+" ", true);
					
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
	
					// TODO Auto-generated method stub
					for(int i =0; i<clientData.size();i++){
						Ecdbooking o =(Ecdbooking)clientData.get(i);
						monitor.setTaskName("提单呈:"+o.getBlno());
						
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
					}
					
					for(int i =0; i<serverData.size();i++){
						Ecdbooking o =(Ecdbooking)serverData.get(i);
						monitor.setTaskName("提单号:"+o.getBlno());
						if(new EcdbookingDAO().inserEcdbooking(o)== true){
							monitor.setTaskName("提单号:"+o.getBlno()+"……插入数据成功!"+"["+i+"]");
						}else{
							System.out.println("提单号:"+o.getBlno()+"插入失败");
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
					System.out.println("工作完成");
				else
					System.out.println("工作没有完成，请检查数据的完整性");
			}
		});
		
		job.schedule();
		
	}
}
