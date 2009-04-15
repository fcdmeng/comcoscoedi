package cosco.vsagent.ecd;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.actions.ActionGroup;

import cosco.vsagent.app.Activator;
import cosco.vsagent.db.DbOperate;
import cosco.vsagent.db.QueryInfo;
import cosco.vsagent.mapping.ecd.Ecdbkcntr;
import cosco.vsagent.mapping.ecd.Ecdbooking;
import cosco.vsagent.preferences.DBPreferencePage;
import cosco.vsagent.system.ImagesContext;
import cosco.vsagent.system.SmsFactory;


public class CntrnoEditorActionGroup extends ActionGroup {
	private TableViewer tv;
	private DbOperate db = SmsFactory.getDbOperate();
	private QueryInfo queryInfo = new QueryInfo();
	private Ecdbooking ecdbooking;
	private String flag;
	
	public DbOperate getDb() {
		return db;
	}

	public void setDb(DbOperate db) {
		this.db = db;
	}

	private Action addAction = new AddAction();
	private Action modifyAction = new ModifyAction();
	private Action removeAction = new RemoveAction();
//	private Action firstAction = new FirstAction();

	

	//private Action prevAction = new PrevAction();
	//private Action nextAction = new NextAction();
	//private Action lastAction = new LastAction();
	//private Action downloadDataAction = new DownloadData();
	
	public void fillActionToolBars(ToolBarManager toolBarManager){
		toolBarManager.add(createActionContrItem(addAction));
		toolBarManager.add(createActionContrItem(modifyAction));
		toolBarManager.add(createActionContrItem(removeAction));
		/*	toolBarManager.add(createActionContrItem(firstAction));
		toolBarManager.add(createActionContrItem(prevAction));
		toolBarManager.add(createActionContrItem(nextAction));
		toolBarManager.add(createActionContrItem(lastAction));
		toolBarManager.add(createActionContrItem(downloadDataAction));*/
		//���¹��ߣ�û����һ�䣬�������ϻ�û���κ���Ϣ
		toolBarManager.update(true);
	}
	
	public CntrnoEditorActionGroup(TableViewer tv){
		this.tv = tv;
		queryInfo.currentPage = 1;
//		queryInfo.pageSize = 12;//ARCHIVE_EDITOR_RS_NUM;
		IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
		String str = ps.getString(DBPreferencePage.ARCHIVE_EDITOR_RS_NUM_KEY);
		
		queryInfo.pageSize = Integer.parseInt(str);
		
	}
	//Ϊ��ͬʱ��ʾͼ�����֣���ActionContributionItem��װһ��Action
	private ActionContributionItem createActionContrItem(IAction action){
		ActionContributionItem aci = new ActionContributionItem(action);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		return aci;
	}
	
	private class AddAction extends Action{
		public AddAction(){
			setText("����");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.REPORT));
		}
		public void run(){
			fireAddAction();
		}
		
	}
	private class ModifyAction extends Action{
		public ModifyAction(){
			setText("�޸�");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.NOTE));
		}
		public void run(){
			fireModifyAction();
		}
	}
	private class RemoveAction extends Action{
		public RemoveAction(){
			setText("ɾ��");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.REMOVE));
		}
		public void run(){
			fireRemoveAction();
		}
		
	}
	
	
	private class FirstAction extends Action{
		public FirstAction(){
			setText("��ҳ");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.FIRST));
		}
		public void run(){
			fireFirstAction();
			/*
			queryInfo.currentPage = 1;
			tv.setInput(db.getUsers(queryInfo));
			refreshActionsState();
			*/
		}
		
	}
	
	
	private class PrevAction extends Action{
		public PrevAction(){
			setText("��һҳ");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.PREV));
		}
		public void run(){
//			queryInfo.currentPage = queryInfo.currentPage - 1;
//			tv.setInput(db.getUsers(queryInfo));
//			refreshActionsState();
			firePrevAction();
		}
		
	}
	private class NextAction extends Action{
		public NextAction(){
			setText("��һҳ");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.NEXT));
		}
		public void run(){
//			queryInfo.currentPage = queryInfo.currentPage + 1;
//			tv.setInput(db.getUsers(queryInfo));
//			refreshActionsState();
			fireNextAction();
		}
		
	}
	private class LastAction extends Action{
		public LastAction(){
			setText("ĩҳ");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.LAST));
		}
		public void run(){
//			queryInfo.currentPage = queryInfo.pageCount;
//			tv.setInput(db.getUsers(queryInfo));
			refreshActionsState();
			fireLastAction();
		}
		
	}
	
	private class DownloadData extends Action{
		public DownloadData(){
			setText("������ʷ����");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.DOWNLOADDATA));
		}
		public void run(){
			fireDownloadDataAction();
		}
	}
	public void refreshActionsState(){
		if(queryInfo.pageCount == 0){
			/*			firstAction.setEnabled(false);
			prevAction.setEnabled(false);
			nextAction.setEnabled(false);
			lastAction.setEnabled(false);*/
		}else{	
			boolean b =(queryInfo.currentPage == 1);
			/*			firstAction.setEnabled(!b);
			prevAction.setEnabled(!b);
			b =(queryInfo.currentPage == queryInfo.pageCount);
			lastAction.setEnabled(!b);
			nextAction.setEnabled(!b);*/
		}
	}
	
	
	public void fireFirstAction(){
		//firstAction.run();
//		System.out.println("��ҳ����");
		tv = getTv();
		if(getFlag().equals("party")){
			if(getEcdbooking() != null && getEcdbooking().getParty()!= null)
				tv.setInput( getEcdbooking().getParty() );
		}
		
		if(getFlag().equals("cntrno")){
			if( getEcdbooking() != null && getEcdbooking().getCntr() != null)
				tv.setInput( getEcdbooking().getCntr() );
		}
		
		refreshActionsState();
	}
	
	public void firePrevAction(){
		//firstAction.run();
		System.out.println("��һҳ����");
	}
	
	public void fireNextAction(){
		//firstAction.run();
		System.out.println("��һҳ����");
	}
	
	public void fireLastAction(){
		//firstAction.run();
		System.out.println("ĩҳ����");
	}
	public void fireAddAction(){
		System.out.println("���Ӳ���");
	}
	
	public void fireModifyAction(){
		for(int i = 0; i<getTv().getTable().getItemCount();i++){
			/*TableItem cntr = getTv().getTable().getItem(i);
			Ecdbkcntr c = (Ecdbkcntr)cntr.getData();
			*/
			Ecdbkcntr cntr = (Ecdbkcntr)getTv().getElementAt(i);
			
			System.out.println(cntr.getCntrno());
		}		
		System.out.println("�޸Ĳ���");
	}
	public void fireRemoveAction(){
		System.out.println("ɾ������");
	}

	public void fireDownloadDataAction(){
		System.out.println("��������");
	}
	
	public QueryInfo getQueryInfo() {
		return queryInfo;
	}

	public void setQueryInfo(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}

	public TableViewer getTv() {
		return tv;
	}

	public void setTv(TableViewer tv) {
		this.tv = tv;
	}

	public Ecdbooking getEcdbooking() {
		return ecdbooking;
	}

	public void setEcdbooking(Ecdbooking ecdbooking) {
		this.ecdbooking = ecdbooking;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
