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


public class NavigatorView extends ViewPart implements ILogonListener {//增加了一个接口
	private TreeViewer tv;
	private Context ctx = Context.getInstance();

	public void createPartControl(Composite parent) {
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		tv = new TreeViewer(topComp, SWT.NONE);
		tv.setContentProvider(new MyContentProvider());// 内容器
		tv.setLabelProvider(new MyLableProvider());// 标签器
		//如果已经登录才显示功能结点树
		if (ctx.isLogon())
			tv.setInput(SmsFactory.createNavigatorEntryTree());
		// 加入导航栏按钮
		IActionBars bars = getViewSite().getActionBars();
		new NavigatorActionGroup().fillActionBars(bars);
		// 注册到Context类
		ctx.addLogonListener(this);
		tv.addDoubleClickListener(new MyDoubleClickListener());
		initializeToolBar();
		
		welcome();
	}

	//实现退出监听器的登录事件处理方法--设置视图的结点树
	public void logon() {
		tv.setInput(SmsFactory.createNavigatorEntryTree());
	}

	// 实现退出监听器的退出事件处理方法——将视图的结点树清空
	public void logoff() {
		tv.setInput(null);
	}

	// 改写父类的注销方法。当关闭视图时将自身从Context类中注销掉
	public void dispose() {
		ctx.removeLogonListener(this);
		super.dispose();
	}

	public void setFocus() {}

	// 标签器
	private static final class MyLableProvider extends LabelProviderAdapter {
		public String getText(Object element) {
			return ((ITreeEntry) element).getName();
		}

		public Image getImage(Object element) {
			return ((NavigatorEntry) element).getImage();
		}
	}

	// 内容器
	private static final class MyContentProvider extends TreeContentProviderAdapter {
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				return ((List) inputElement).toArray();
			} else {
				return new Object[0]; // 生成一个空数组
			}
		}

		public boolean hasChildren(Object element) {
			ITreeEntry entry = (ITreeEntry) element;
			return !entry.getChildren().isEmpty(); // 注意前面有个取反的感叹号
		}

		public Object[] getChildren(Object parentElement) {
			ITreeEntry entry = (ITreeEntry) parentElement;
			return entry.getChildren().toArray();
		}
	}
	private void doJob1(){
		Job job = new Job("我正忙着……"){

			@Override
			protected IStatus run(IProgressMonitor monitor) {
//				 monitor.beginTask("task", 1); 
//				try{
					for(int j =0;j<=200000;j++){
						
//						System.out.println("工作" + j);
						 monitor.setTaskName("任务"+j); 
					}
						
					monitor.beginTask("task", 50); 
					for(int j =0;j<=200000;j++)
						monitor.worked(j); 
					
					monitor.beginTask("task======", 1000); 
//						System.out.println("工作" + j);
//					System.out.println("思考中……");
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
					System.out.println("工作完成");
				else
					System.out.println("工作没有完成");
			}
		});
		
		job.schedule();
		System.out.println("深思中……");
	}
	
	private void doJob2() {
		final Job job = new Job("我正忙着，别烦我...") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					for (int i = 0; i < 5; i++) {
						try {
							
							Thread.sleep(1000);
							System.out.println("完成工作" + i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
					}
					return Status.OK_STATUS;
				} finally {
					schedule(10000); // 10秒钟后再次运行
				}
			}
		};
	
		
		job.addJobChangeListener(new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
				if (event.getResult().isOK())
					System.out.println("工作完成");
				else
					System.out.println("工作没有完成");
			}
		});
		job.schedule();
	}
	// 进度条实例3
	private void doJob3() {
		IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) {
				monitor.beginTask(null, 5);
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(100);
						monitor.setTaskName("完成工作" + i);
						monitor.worked(1);
						getViewSite().getShell().setText("完成工作" + i); // 访问另一个线程的界面
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
			if (entry.getName().equals("报表输出")) {
				doJob1();//doJob1();// 改为doJob2()、doJob3()可以看其他实例的效果
			} else if (entry.getName().equals("成绩管理")) {
				try {
					String viewId = SearchView.class.getName();
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					// 该方法的返回值是SearchView对象，它会自动判断视图是否已经打开，
					// 如果没有则创建视图，这时会执行视图类的createPartControl方法
					workbenchPage.showView(viewId);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			} else {
				IEditorInput editorInput = entry.getEditorInput();
				String editorID = entry.getEditorId();
				// 如果editorInput或editorID为空则中断返回
				if (editorInput == null || editorID == null)
					return;
				// 取得IWorkbenchPage，并搜索使用editorInput对象对应的编辑器
				IWorkbenchPage workbenchPage = getViewSite().getPage();
				IEditorPart editor = workbenchPage.findEditor(editorInput);
				// 如果此编辑器已经存在，则将它设为当前编辑器，否则重新打开一个编辑器
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
		
		// 如果editorInput或editorID为空则中断返回
		if (editorInput == null || welcome == null)
			return;
		// 取得IWorkbenchPage，并搜索使用editorInput对象对应的编辑器
		IWorkbenchPage workbenchPage = getViewSite().getPage();
		IEditorPart editor = workbenchPage.findEditor(editorInput);
		// 如果此编辑器已经存在，则将它设为当前编辑器，否则重新打开一个编辑器
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