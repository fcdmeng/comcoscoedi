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
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;


public class GradeEditor extends EditorPartAdapter {
	private TableViewer tv;
	
	public void createPartControl(Composite parent){
		ViewForm topComp = new ViewForm(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		
		createTableViewer(topComp);
		tv.setContentProvider(new SmsContentProvider());
		tv.setLabelProvider(new TableViewerLabelProvider());
		GradeEditorActionGroup actionGroup = new GradeEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		//将表格显示出来
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
		actionGroup.fireFirstAction();
	}

	//创建表样式
	private void createTableViewer(ViewForm parent) {
		// TODO Auto-generated method stub
		tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableLayout layout = new TableLayout(); 
		table.setLayout(layout);
		
		layout.addColumnData(new ColumnWeightData(15));
		new TableColumn(table, SWT.NONE).setText("ID 号");
		layout.addColumnData(new ColumnWeightData(20));
		new TableColumn(table, SWT.NONE).setText("年级");
		
		
	}
	
	private static final class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int col){
			Grade o = (Grade) element;
			switch (col){
			case 0 : return o.getId().toString();
			case 1 : return o.getName();
			default :
				return "";
			}
		}
	}
	

}
