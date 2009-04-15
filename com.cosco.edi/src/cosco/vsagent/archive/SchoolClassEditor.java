package cosco.vsagent.archive;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;

import cosco.vsagent.model.Grade;
import cosco.vsagent.model.SchoolClass;
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;


public class SchoolClassEditor extends EditorPartAdapter {
	TableViewer tv;
	
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		ViewForm topComp = new ViewForm(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		createTableView(topComp);
		tv.setContentProvider(new SmsContentProvider());
		tv.setLabelProvider(new TableViewerLabelProvider());
		SchoolClassEditorActionGroup actionGroup = new SchoolClassEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
		actionGroup.fireFirstAction();

	}

	private void createTableView(ViewForm topComp) {
		// TODO Auto-generated method stub
		tv = new TableViewer(topComp, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		
		layout.addColumnData(new ColumnWeightData(15));
		new TableColumn(table, SWT.NONE).setText("年级");
		
		layout.addColumnData(new ColumnWeightData(15));
		new TableColumn(table, SWT.NONE).setText("班级 ID");
		
		layout.addColumnData(new ColumnWeightData(20));
		new TableColumn(table, SWT.NONE).setText("班级名称");
		
	}
	
	private static final class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int col){
			SchoolClass o =(SchoolClass) element;
			switch (col){
			case 0: 
				Grade grade = o.getGrade();
			
				return grade.getName();
			case 1: return o.getId().toString();
			case 2: return o.getName();
				
			default :
				return "";
			}
		}
	}

}
