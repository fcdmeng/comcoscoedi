package cosco.vsagent.navigator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.IProgressService;

import cosco.vsagent.WelcomeEditor;
import cosco.vsagent.WelcomeEditorInput;
import cosco.vsagent.model.ITreeEntry;
import cosco.vsagent.score.SearchView;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.ILogonListener;
import cosco.vsagent.system.LabelProviderAdapter;
import cosco.vsagent.system.SmsFactory;
import cosco.vsagent.system.TreeContentProviderAdapter;


public class NavigatorView extends ViewPart implements ILogonListener {//������һ���ӿ�
	private TreeViewer tv;
	private Context ctx = Context.getInstance();

	public void createPartControl(Composite parent) {
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		tv = new TreeViewer(topComp, SWT.NONE);
		tv.setContentProvider(new MyContentProvider());// ������
		tv.setLabelProvider(new MyLableProvider());// ��ǩ��
		//����Ѿ���¼����ʾ���ܽ����
		if (ctx.isLogon())
			tv.setInput(SmsFactory.createNavigatorEntryTree());
		// ���뵼������ť
		IActionBars bars = getViewSite().getActionBars();
		new NavigatorActionGroup().fillActionBars(bars);
		// ע�ᵽContext��
		ctx.addLogonListener(this);
		tv.addDoubleClickListener(new MyDoubleClickListener());
		initializeToolBar();
		
		welcome();
	}

	//ʵ���˳��������ĵ�¼�¼�������--������ͼ�Ľ����
	public void logon() {
		tv.setInput(SmsFactory.createNavigatorEntryTree());
	}

	// ʵ���˳����������˳��¼���������������ͼ�Ľ�������
	public void logoff() {
		tv.setInput(null);
	}

	// ��д�����ע�����������ر���ͼʱ�������Context����ע����
	public void dispose() {
		ctx.removeLogonListener(this);
		super.dispose();
	}

	public void setFocus() {}

	// ��ǩ��
	private static final class MyLableProvider extends LabelProviderAdapter {
		public String getText(Object element) {
			return ((ITreeEntry) element).getName();
		}

		public Image getImage(Object element) {
			return ((NavigatorEntry) element).getImage();
		}
	}

	// ������
	private static final class MyContentProvider extends TreeContentProviderAdapter {
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				return ((List) inputElement).toArray();
			} else {
				return new Object[0]; // ����һ��������
			}
		}

		public boolean hasChildren(Object element) {
			ITreeEntry entry = (ITreeEntry) element;
			return !entry.getChildren().isEmpty(); // ע��ǰ���и�ȡ���ĸ�̾��
		}

		public Object[] getChildren(Object parentElement) {
			ITreeEntry entry = (ITreeEntry) parentElement;
			return entry.getChildren().toArray();
		}
	}
	private void doJob1(){
		Job job = new Job("����æ�š���"){

			@Override
			protected IStatus run(IProgressMonitor monitor) {
//				 monitor.beginTask("task", 1); 
//				try{
					for(int j =0;j<=200000;j++){
						
//						System.out.println("����" + j);
						 monitor.setTaskName("����"+j); 
					}
						
					monitor.beginTask("task", 50); 
					for(int j =0;j<=200000;j++)
						monitor.worked(j); 
					
					monitor.beginTask("task======", 1000); 
//						System.out.println("����" + j);
//					System.out.println("˼���С���");
//					Thread.sleep(10000);
//				}catch (InterruptedException e){e.printStackTrace();}
				// TODO Auto-generated method stub
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
		System.out.println("��˼�С���");
	}
	
	private void doJob2() {
		final Job job = new Job("����æ�ţ�����...") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					for (int i = 0; i < 5; i++) {
						try {
							
							Thread.sleep(1000);
							System.out.println("��ɹ���" + i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
					}
					return Status.OK_STATUS;
				} finally {
					schedule(10000); // 10���Ӻ��ٴ�����
				}
			}
		};
	
		
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
	// ������ʵ��3
	private void doJob3() {
		IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) {
				monitor.beginTask(null, 5);
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(100);
						monitor.setTaskName("��ɹ���" + i);
						monitor.worked(1);
						getViewSite().getShell().setText("��ɹ���" + i); // ������һ���̵߳Ľ���
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				monitor.done();
			}
		};
		try {
			progressService.runInUI(progressService, runnable, null);
			// progressService.busyCursorWhile(runnable);
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
	}

	
	private class MyDoubleClickListener implements IDoubleClickListener{
		public void doubleClick(DoubleClickEvent event) {
			IStructuredSelection sel = (IStructuredSelection) event.getSelection();
			NavigatorEntry entry = (NavigatorEntry) sel.getFirstElement();
			if (entry.getName().equals("�������")) {
				doJob1();//doJob1();// ��ΪdoJob2()��doJob3()���Կ�����ʵ����Ч��
			} else if (entry.getName().equals("�ɼ�����")) {
				try {
					String viewId = SearchView.class.getName();
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					// �÷����ķ���ֵ��SearchView���������Զ��ж���ͼ�Ƿ��Ѿ��򿪣�
					// ���û���򴴽���ͼ����ʱ��ִ����ͼ���createPartControl����
					workbenchPage.showView(viewId);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			} else {
				IEditorInput editorInput = entry.getEditorInput();
				String editorID = entry.getEditorId();
				// ���editorInput��editorIDΪ�����жϷ���
				if (editorInput == null || editorID == null)
					return;
				// ȡ��IWorkbenchPage��������ʹ��editorInput�����Ӧ�ı༭��
				IWorkbenchPage workbenchPage = getViewSite().getPage();
				IEditorPart editor = workbenchPage.findEditor(editorInput);
				// ����˱༭���Ѿ����ڣ�������Ϊ��ǰ�༭�����������´�һ���༭��
				if (editor != null) {
					workbenchPage.bringToTop(editor);
				} else {
					try {
						editor = workbenchPage.openEditor(editorInput, editorID);
					} catch (PartInitException e2) {
						e2.printStackTrace();
					}
				}
			}
		}
	}
	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}
	
	private void welcome(){
		//WelcomeEditorInput editorInput = new.getEditorInput();
		//String editorID = entry.getEditorId();
		
		String welcome = WelcomeEditor.class.getName();
		WelcomeEditorInput editorInput = new WelcomeEditorInput();
		
		// ���editorInput��editorIDΪ�����жϷ���
		if (editorInput == null || welcome == null)
			return;
		// ȡ��IWorkbenchPage��������ʹ��editorInput�����Ӧ�ı༭��
		IWorkbenchPage workbenchPage = getViewSite().getPage();
		IEditorPart editor = workbenchPage.findEditor(editorInput);
		// ����˱༭���Ѿ����ڣ�������Ϊ��ǰ�༭�����������´�һ���༭��
		if (editor != null) {
			workbenchPage.bringToTop(editor);
		} else {
			try {
				editor = workbenchPage.openEditor(editorInput, welcome);
			} catch (PartInitException e2) {
				e2.printStackTrace();
			}
		}

	}
}