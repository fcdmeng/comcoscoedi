package cosco.vsagent.score;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import cosco.vsagent.db.DbOperate;
import cosco.vsagent.model.Course;
import cosco.vsagent.model.Exam;
import cosco.vsagent.model.SchoolClass;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.ILogonListener;
import cosco.vsagent.system.SmsFactory;
import cosco.vsagent.system.SmsUtil;


public class SearchView extends ViewPart implements ModifyListener, ILogonListener{
	private Combo schoolClassCombo, courseCombo, examCombo;
	private Composite parent;
	private Group group;
	
	public SearchView() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		this.parent = parent;
		if(Context.getInstance().isLogon()) createGroup();
		Context.getInstance().addLogonListener(this);

	}
	
	public void createGroup(){
		group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new FillLayout(SWT.VERTICAL));
		group.setText("¸ù¾Ý¿¼ÊÔ³É¼¨²éÑ¯");
		
		new Label(group, SWT.NONE).setText("°à¼¶");
		schoolClassCombo = SmsUtil.createSchoolClassCombo(group, SWT.READ_ONLY);
		schoolClassCombo.setText(schoolClassCombo.getItem(0));
		schoolClassCombo.addModifyListener(this);
		
		new Label(group, SWT.NONE).setText("¿Î³Ì:");
		courseCombo = SmsUtil.createCourseCombo(group, SWT.READ_ONLY);
		courseCombo.select(0);
		courseCombo.addModifyListener(this);
		
		new Label(group, SWT.NONE).setText("¿¼ÊÔ:");
		examCombo = new Combo(group, SWT.READ_ONLY);
		
		modifyText(null);
		
		Button searchButton = new Button(group, SWT.READ_ONLY);
		searchButton.setText("ËÑË÷");
		
		searchButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				String key = examCombo.getText();
				Exam exam =(Exam)examCombo.getData(key);
				if(exam == null) return;
				try{
					ScoreEdirorInput ei = new ScoreEdirorInput();
					ei.setName(exam.getDate() + exam.getName());
					String editorId = ScoreEditor.class.getName();
					
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					ScoreEditor editor =(ScoreEditor)workbenchPage.openEditor( ei, editorId);
					editor.setExam(exam);
				}catch(PartInitException e2){e2.printStackTrace();}
			}
		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}

	public void logoff() {
		// TODO Auto-generated method stub
		group.dispose();
		group = null;
	}

	public void logon() {
		// TODO Auto-generated method stub
		createGroup();
		parent.layout();
	}
	private String old1 ="",old2 ="";
	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub
		String new1 = schoolClassCombo.getText();
		String new2 = courseCombo.getText();
		
		if(old1.equals(new1) && old2.equals(new2)) return;
		old1 = new1;
		old2 = new2;
		examCombo.removeAll();
		
		SchoolClass o1 = (SchoolClass)schoolClassCombo.getData(new1);
		
		Course o2 =(Course) courseCombo.getData(new2);
		
		DbOperate db = SmsFactory.getDbOperate();
		
		for(Exam exam : db.getExam(o2, o1)){
			String s = SmsUtil.dateToShortStr(exam.getDate()) + exam.getName();
			examCombo.add(s);
			examCombo.setData(s, exam);
		}
		examCombo.select(0);
	}
	
	public void dispose(){
		Context.getInstance().removeLogonListener(this);
		super.dispose();
	}

}
