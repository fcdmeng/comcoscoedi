package cosco.vsagent.archive;

import static cosco.vsagent.system.ImagesContext.FIRST;
import static cosco.vsagent.system.ImagesContext.LAST;
import static cosco.vsagent.system.ImagesContext.NEXT;
import static cosco.vsagent.system.ImagesContext.NOTE;
import static cosco.vsagent.system.ImagesContext.PREV;
import static cosco.vsagent.system.ImagesContext.REMOVE;
import static cosco.vsagent.system.ImagesContext.REPORT;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.actions.ActionGroup;

import cosco.vsagent.app.Activator;
import cosco.vsagent.archive.wizard.ArchiveWizard;
import cosco.vsagent.db.DbOperate;
import cosco.vsagent.db.QueryInfo;
import cosco.vsagent.model.IUser;
import cosco.vsagent.preferences.DBPreferencePage;
import cosco.vsagent.system.ImagesContext;
import cosco.vsagent.system.SmsFactory;


public class ArchiveEditorActionGroup extends ActionGroup {
	private TableViewer tv;
	private DbOperate db = SmsFactory.getDbOperate();
	private QueryInfo queryInfo = new QueryInfo();

	private Action addAction = new AddAction();
	private Action modifyAction = new ModifyAction();
	private Action removeAction = new RemoveAction();
	private Action firstAction = new FirstAction();
	private Action prevAction = new PrevAction();
	private Action nextAction = new NextAction();
	private Action lastAction = new LastAction();

	public ArchiveEditorActionGroup(TableViewer tv) {
		this.tv = tv;
		queryInfo.currentPage = 1;
		// queryInfo.pageSize = Constants.ARCHIVE_EDITOR_RS_NUM;//表示每页显示记录数的常数
		IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
		String str = ps.getString(DBPreferencePage.ARCHIVE_EDITOR_RS_NUM_KEY);
		queryInfo.pageSize = Integer.parseInt(str);
	}

	public void fillActionToolBars(ToolBarManager toolBarManager) {
		toolBarManager.add(createActionContrItem(addAction));
		toolBarManager.add(createActionContrItem(modifyAction));
		toolBarManager.add(createActionContrItem(removeAction));
		toolBarManager.add(createActionContrItem(firstAction));
		toolBarManager.add(createActionContrItem(prevAction));
		toolBarManager.add(createActionContrItem(nextAction));
		toolBarManager.add(createActionContrItem(lastAction));
		// 更新工具栏。没有这一句，工具栏上会没有任何显示
		toolBarManager.update(true);
	}

	// 为了同时显示图像文字，用ActionContributionItem包装一下Action
	private ActionContributionItem createActionContrItem(IAction action) {
		ActionContributionItem aci = new ActionContributionItem(action);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);// 显示图像+文字
		return aci;
	}

	private class AddAction extends Action {
		public AddAction() {
			setText("新增");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(REPORT));
		}

		public void run() {
			ArchiveWizard wizard = new ArchiveWizard();
			WizardDialog dialog = new WizardDialog(null, wizard);
			dialog.setPageSize(-1, 120); // dialog大小,-1是指让宽度自动调整
			if (dialog.open() == IDialogConstants.OK_ID) {
				IUser user = wizard.getUser();
				if (db.inserUser(user)) {
					MessageDialog.openInformation(null, "", "成功插入");
					IUser o = db.getUser(user.getUserId());
					tv.add(o);
					List list = (List) tv.getInput();
					list.add(o);
				} else {
					MessageDialog.openError(null, "", "记录插入失败");
				}
			}
		}
	}

	private class ModifyAction extends Action {
		public ModifyAction() {
			setText("修改");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(NOTE));
		}

		public void run() {
			IStructuredSelection sel = (IStructuredSelection) tv.getSelection();
			IUser user = (IUser) sel.getFirstElement();
			if (user == null)
				return;
			ModifyArchiveDialog dialog = new ModifyArchiveDialog(null);
			dialog.setUser(user);
			if (dialog.open() == IDialogConstants.OK_ID) {
				db.modifyUser(user);// 更新数据库
				tv.refresh(user);// 更新表格显示
			}
		}
	}

	private class RemoveAction extends Action {
		public RemoveAction() {
			setText("删除");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(REMOVE));
		}

		public void run() {
			IStructuredSelection sel = (IStructuredSelection) tv.getSelection();
			IUser user = (IUser) sel.getFirstElement();
			if (user == null)
				return;
			if (MessageDialog.openConfirm(null, null, "真的删除吗？")) {
				if (db.removeUser(user)) {
					//从表格界面中删除， 同时也要将它从源头（list）删除，
					//否则被删除的记录在tv.refresh时又会由setInput传到表格里显示
					tv.remove(user);
					List list = (List) tv.getInput();
					list.remove(user);
				} else {
					MessageDialog.openConfirm(null, null, "删除失败！");
				}
			}
		}
	}

	private class FirstAction extends Action {
		public FirstAction() {
			setText("首页");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(FIRST));
		}

		public void run() {
			queryInfo.currentPage = 1;
			tv.setInput(db.getUsers(queryInfo));
			refreshActionsState();
		}
	}

	private class PrevAction extends Action {
		public PrevAction() {
			setText("上一页");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(PREV));
		}

		public void run() {
			queryInfo.currentPage = queryInfo.currentPage - 1;
			tv.setInput(db.getUsers(queryInfo));
			refreshActionsState();
		}
	}

	private class NextAction extends Action {
		public NextAction() {
			setText("下一页");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(NEXT));
		}

		public void run() {
			queryInfo.currentPage = queryInfo.currentPage + 1;
			tv.setInput(db.getUsers(queryInfo));
			refreshActionsState();
		}
	}

	private class LastAction extends Action {
		public LastAction() {
			setText("末页");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(LAST));
		}

		public void run() {
			queryInfo.currentPage = queryInfo.pageCount;
			tv.setInput(db.getUsers(queryInfo));
			refreshActionsState();
		}
	}

	// 刷新翻页按钮的有效/无效状态
	private void refreshActionsState() {
		if (queryInfo.pageCount == 0) {// 没有记录时
			firstAction.setEnabled(false);
			prevAction.setEnabled(false);
			nextAction.setEnabled(false);
			lastAction.setEnabled(false);
		} else {
			// 如果是第一页，则首页、上一页两按钮无效
			boolean b = (queryInfo.currentPage == 1);
			firstAction.setEnabled(!b);
			prevAction.setEnabled(!b);
			// 如果是最后一页，则末页、下一页两按钮无效
			b = (queryInfo.currentPage == queryInfo.pageCount);
			lastAction.setEnabled(!b);
			nextAction.setEnabled(!b);
		}
	}

	// 提供给外界执行"首页"按钮的方法
	public void fireFirstAction() {
		firstAction.run();
	}
}