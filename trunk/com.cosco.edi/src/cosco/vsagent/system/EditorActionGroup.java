package cosco.vsagent.system;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.actions.ActionGroup;

import cosco.vsagent.app.Activator;
import cosco.vsagent.db.DbOperate;
import cosco.vsagent.db.QueryInfo;
import cosco.vsagent.preferences.DBPreferencePage;

public class EditorActionGroup extends ActionGroup {
	private TableViewer tv;
	private DbOperate db = SmsFactory.getDbOperate();
	private QueryInfo queryInfo = new QueryInfo();

	public DbOperate getDb() {
		return db;
	}

	public void setDb(DbOperate db) {
		this.db = db;
	}

	private Action addAction = new AddAction();
	private Action modifyAction = new ModifyAction();
	private Action removeAction = new RemoveAction();
	private Action firstAction = new FirstAction();

	private Action prevAction = new PrevAction();
	private Action nextAction = new NextAction();
	private Action lastAction = new LastAction();
	private Action downloadDataAction = new DownloadData();

	public void fillActionToolBars(ToolBarManager toolBarManager) {
		toolBarManager.add(createActionContrItem(addAction));
		toolBarManager.add(createActionContrItem(modifyAction));
		toolBarManager.add(createActionContrItem(removeAction));
		toolBarManager.add(createActionContrItem(firstAction));
		toolBarManager.add(createActionContrItem(prevAction));
		toolBarManager.add(createActionContrItem(nextAction));
		toolBarManager.add(createActionContrItem(lastAction));
		toolBarManager.add(createActionContrItem(downloadDataAction));
		// 更新工具，没有这一句，工具栏上会没有任何信息
		toolBarManager.update(true);
	}

	/**
	 * 鼠标右键菜单的支持
	 */
	public void fillContextMenu(IMenuManager menu) {
		// do nothing
	}

	public EditorActionGroup(TableViewer tv) {
		this.tv = tv;
		queryInfo.currentPage = 1;
		// queryInfo.pageSize = 12;//ARCHIVE_EDITOR_RS_NUM;
		IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
		String str = ps.getString(DBPreferencePage.ARCHIVE_EDITOR_RS_NUM_KEY);

		queryInfo.pageSize = Integer.parseInt(str);

	}

	// 为了同时显示图像文字，用ActionContributionItem包装一下Action
	private ActionContributionItem createActionContrItem(IAction action) {
		ActionContributionItem aci = new ActionContributionItem(action);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		return aci;
	}

	private class AddAction extends Action {
		public AddAction() {
			setText("新增");
			setHoverImageDescriptor(ImagesContext
					.getImageDescriptor(ImagesContext.REPORT));
		}

		public void run() {
			fireAddAction();
		}

	}

	private class ModifyAction extends Action {
		public ModifyAction() {
			setText("修改");
			setHoverImageDescriptor(ImagesContext
					.getImageDescriptor(ImagesContext.NOTE));
		}

		public void run() {
			fireModifyAction();
		}
	}

	private class RemoveAction extends Action {
		public RemoveAction() {
			setText("删除");
			setHoverImageDescriptor(ImagesContext
					.getImageDescriptor(ImagesContext.REMOVE));
		}

		public void run() {
			fireRemoveAction();
		}

	}

	private class FirstAction extends Action {
		public FirstAction() {
			setText("首页");
			setHoverImageDescriptor(ImagesContext
					.getImageDescriptor(ImagesContext.FIRST));
		}

		public void run() {
			fireFirstAction();
			/*
			 * queryInfo.currentPage = 1; tv.setInput(db.getUsers(queryInfo));
			 * refreshActionsState();
			 */
		}

	}

	private class PrevAction extends Action {
		public PrevAction() {
			setText("上一页");
			setHoverImageDescriptor(ImagesContext
					.getImageDescriptor(ImagesContext.PREV));
		}

		public void run() {
			// queryInfo.currentPage = queryInfo.currentPage - 1;
			// tv.setInput(db.getUsers(queryInfo));
			// refreshActionsState();
			firePrevAction();
		}

	}

	private class NextAction extends Action {
		public NextAction() {
			setText("下一页");
			setHoverImageDescriptor(ImagesContext
					.getImageDescriptor(ImagesContext.NEXT));
		}

		public void run() {
			// queryInfo.currentPage = queryInfo.currentPage + 1;
			// tv.setInput(db.getUsers(queryInfo));
			// refreshActionsState();
			fireNextAction();
		}

	}

	private class LastAction extends Action {
		public LastAction() {
			setText("末页");
			setHoverImageDescriptor(ImagesContext
					.getImageDescriptor(ImagesContext.LAST));
		}

		public void run() {
			// queryInfo.currentPage = queryInfo.pageCount;
			// tv.setInput(db.getUsers(queryInfo));
			refreshActionsState();
			fireLastAction();
		}

	}

	private class DownloadData extends Action {
		public DownloadData() {
			setText("下载历史数据");
			setHoverImageDescriptor(ImagesContext
					.getImageDescriptor(ImagesContext.DOWNLOADDATA));
		}

		public void run() {
			fireDownloadDataAction();
		}
	}

	public void refreshActionsState() {
		if (queryInfo.pageCount == 0) {
			firstAction.setEnabled(false);
			prevAction.setEnabled(false);
			nextAction.setEnabled(false);
			lastAction.setEnabled(false);
		} else {
			boolean b = (queryInfo.currentPage == 1);
			firstAction.setEnabled(!b);
			prevAction.setEnabled(!b);
			b = (queryInfo.currentPage == queryInfo.pageCount);
			lastAction.setEnabled(!b);
			nextAction.setEnabled(!b);
		}
	}

	public void fireFirstAction() {
		// firstAction.run();
		System.out.println("首页操作");
	}

	public void firePrevAction() {
		// firstAction.run();
		System.out.println("上一页操作");
	}

	public void fireNextAction() {
		// firstAction.run();
		System.out.println("下一页操作");
	}

	public void fireLastAction() {
		// firstAction.run();
		System.out.println("末页操作");
	}

	public void fireAddAction() {
		System.out.println("增加操作");
	}

	public void fireModifyAction() {
		System.out.println("修改操作");
	}

	public void fireRemoveAction() {
		System.out.println("删除操作");
	}

	public void fireDownloadDataAction() {
		System.out.println("下载数据");
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

	public Action getDownloadDataAction() {
		return downloadDataAction;
	}

	public void setDownloadDataAction(Action downloadDataAction) {
		this.downloadDataAction = downloadDataAction;
	}
}
