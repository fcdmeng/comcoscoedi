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
		// queryInfo.pageSize = Constants.ARCHIVE_EDITOR_RS_NUM;//��ʾÿҳ��ʾ��¼���ĳ���
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
		// ���¹�������û����һ�䣬�������ϻ�û���κ���ʾ
		toolBarManager.update(true);
	}

	// Ϊ��ͬʱ��ʾͼ�����֣���ActionContributionItem��װһ��Action
	private ActionContributionItem createActionContrItem(IAction action) {
		ActionContributionItem aci = new ActionContributionItem(action);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);// ��ʾͼ��+����
		return aci;
	}

	private class AddAction extends Action {
		public AddAction() {
			setText("����");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(REPORT));
		}

		public void run() {
			ArchiveWizard wizard = new ArchiveWizard();
			WizardDialog dialog = new WizardDialog(null, wizard);
			dialog.setPageSize(-1, 120); // dialog��С,-1��ָ�ÿ���Զ�����
			if (dialog.open() == IDialogConstants.OK_ID) {
				IUser user = wizard.getUser();
				if (db.inserUser(user)) {
					MessageDialog.openInformation(null, "", "�ɹ�����");
					IUser o = db.getUser(user.getUserId());
					tv.add(o);
					List list = (List) tv.getInput();
					list.add(o);
				} else {
					MessageDialog.openError(null, "", "��¼����ʧ��");
				}
			}
		}
	}

	private class ModifyAction extends Action {
		public ModifyAction() {
			setText("�޸�");
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
				db.modifyUser(user);// �������ݿ�
				tv.refresh(user);// ���±����ʾ
			}
		}
	}

	private class RemoveAction extends Action {
		public RemoveAction() {
			setText("ɾ��");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(REMOVE));
		}

		public void run() {
			IStructuredSelection sel = (IStructuredSelection) tv.getSelection();
			IUser user = (IUser) sel.getFirstElement();
			if (user == null)
				return;
			if (MessageDialog.openConfirm(null, null, "���ɾ����")) {
				if (db.removeUser(user)) {
					//�ӱ�������ɾ���� ͬʱҲҪ������Դͷ��list��ɾ����
					//����ɾ���ļ�¼��tv.refreshʱ�ֻ���setInput�����������ʾ
					tv.remove(user);
					List list = (List) tv.getInput();
					list.remove(user);
				} else {
					MessageDialog.openConfirm(null, null, "ɾ��ʧ�ܣ�");
				}
			}
		}
	}

	private class FirstAction extends Action {
		public FirstAction() {
			setText("��ҳ");
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
			setText("��һҳ");
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
			setText("��һҳ");
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
			setText("ĩҳ");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(LAST));
		}

		public void run() {
			queryInfo.currentPage = queryInfo.pageCount;
			tv.setInput(db.getUsers(queryInfo));
			refreshActionsState();
		}
	}

	// ˢ�·�ҳ��ť����Ч/��Ч״̬
	private void refreshActionsState() {
		if (queryInfo.pageCount == 0) {// û�м�¼ʱ
			firstAction.setEnabled(false);
			prevAction.setEnabled(false);
			nextAction.setEnabled(false);
			lastAction.setEnabled(false);
		} else {
			// ����ǵ�һҳ������ҳ����һҳ����ť��Ч
			boolean b = (queryInfo.currentPage == 1);
			firstAction.setEnabled(!b);
			prevAction.setEnabled(!b);
			// ��������һҳ����ĩҳ����һҳ����ť��Ч
			b = (queryInfo.currentPage == queryInfo.pageCount);
			lastAction.setEnabled(!b);
			nextAction.setEnabled(!b);
		}
	}

	// �ṩ�����ִ��"��ҳ"��ť�ķ���
	public void fireFirstAction() {
		firstAction.run();
	}
}