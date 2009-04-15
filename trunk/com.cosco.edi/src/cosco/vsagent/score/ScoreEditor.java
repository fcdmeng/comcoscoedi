package cosco.vsagent.score;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import cosco.vsagent.model.Exam;
import cosco.vsagent.model.Student;
import cosco.vsagent.model.StudentScore;
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.SmsFactory;
import cosco.vsagent.system.TableLabelProviderAdapter;


public class ScoreEditor extends EditorPartAdapter {
	private Label examLabel;
	private TableViewer tv;
	
	public ScoreEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		setPartName(input.getName());
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		createTableViewer(topComp);
		tv.setContentProvider(new SmsContentProvider());
		tv.setLabelProvider(new TableViewerLabelProvider());
	}

	private void createTableViewer(Composite parent) {
		// TODO Auto-generated method stub
		Composite tableComp = new Composite(parent, SWT.NONE);
		tableComp.setLayout(new GridLayout());
		examLabel = new Label(tableComp, SWT.NONE);
		examLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		tv = new TableViewer(tableComp, SWT.NONE | SWT.BORDER|SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableLayout tLayout = new TableLayout();
		table.setLayout(tLayout);
		tLayout.addColumnData(new ColumnWeightData(10));
		new TableColumn(table, SWT.NONE).setText("ID");
		
		tLayout.addColumnData(new ColumnWeightData(20));
		new TableColumn(table, SWT.NONE).setText("学生");
		tLayout.addColumnData(new ColumnWeightData(20));
		new TableColumn(table, SWT.NONE).setText("成绩");
		
	}
	
	public void setExam(Exam exam){
		String s0 = exam.getName();
		String s1 = "	时间:"+exam.getDate();
		String s2 = "	课程:"+exam.getCourse().getName();
		String s3 = "	班级:"+exam.getSchoolClass().getName();
		String s4 = "	监考:"+exam.getTeacher().getName();
		String str = s0+s1+s2+s3+s4;
		examLabel.setText(str);
		
		tv.setInput(SmsFactory.getDbOperate().getStudentScore(exam));
	}
	
	private final static class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int columnIndex){
			StudentScore o = (StudentScore) element;
			switch(columnIndex){
			case 0: return o.getId().toString();
			case 1:
				Student student = o.getStudent();
				if(student != null)
					return student.getName();
				
				return "";
			case 2:return String.valueOf(o.getScore());
			}
			return "";
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
