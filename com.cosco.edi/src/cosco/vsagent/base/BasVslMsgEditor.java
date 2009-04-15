package cosco.vsagent.base;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;

import cosco.vsagent.mapping.base.Basvslmsg;
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;

public class BasVslMsgEditor extends EditorPartAdapter {
	private TableViewer tv;
	public static final String ID = "cosco.vsagent.base.BasVslMsgEditor"; //$NON-NLS-1$
	
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		ViewForm topComp = new ViewForm(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		createTableViewer(topComp);
		tv.setContentProvider(new SmsContentProvider());
		tv.setLabelProvider(new TableViewerLabelProvider());
		BasVslMsgEditorActoinGroup actionGroup = new BasVslMsgEditorActoinGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		//将表格显示出来
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
		actionGroup.fireFirstAction();
	}
	
	private void createTableViewer(ViewForm parent){
		tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		final Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		
		layout.addColumnData(new ColumnWeightData(40));
		new TableColumn(table, SWT.NONE).setText("船舶代码");
		layout.addColumnData(new ColumnWeightData(40));
		new TableColumn(table, SWT.NONE).setText("船舶呼号");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("英文船名");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("中文船名");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("国籍");
		layout.addColumnData(new ColumnWeightData(40));
		new TableColumn(table, SWT.NONE).setText("船舶类型");
		layout.addColumnData(new ColumnWeightData(20));
		new TableColumn(table, SWT.NONE).setText("在线");
		layout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("录入员");
		layout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("录入时间");
		layout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("修改员");
		layout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("修改时间");
			
		
		
	}
	
	private static final class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int col){

			Basvslmsg o = (Basvslmsg)element;
			switch (col){
			case 0: return o.getVslcode();
			case 1: return o.getShipcode();
			case 2: return o.getEnname();
			case 3: return o.getCnname();
			case 4: return o.getNationcnam() == null ? "": o.getNationcnam();
			case 5: return o.getTypename() == null ? "" : o.getTypename();
			case 6: return o.getOnline().equals("0" )? "" : "在线";
			case 7: return o.getMaker() == null ? "" : o.getMaker();
			case 8: return o.getMadetime() == null ? "" : o.getMadetime().toLocaleString();
			case 9: return o.getModifier();
			case 10: return o.getModitime() == null ? "" : o.getModitime().toLocaleString();

			default :
				return "";
			}
		}
	}
	
	

}
