package cosco.vsagent.base;

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

import cosco.vsagent.mapping.base.Baspackage;
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;

public class BasPackageEditor extends EditorPartAdapter {
private TableViewer tv;
	
	public static final String ID = "cosco.vsagent.base.BasPackageEditor"; //$NON-NLS-1$

	/**
	 * Create contents of the editor part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {

		ViewForm topComp = new ViewForm(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		createTableViewer(topComp);
		tv.setContentProvider(new SmsContentProvider());
		tv.setLabelProvider(new TableViewerLabelProvider());
		BasPackageEditorActionGroup actionGroup = new BasPackageEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		//�������ʾ����
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
		actionGroup.fireFirstAction();
		
	}


	private void createTableViewer(ViewForm parent){
		tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		

		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("����");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("����");
		layout.addColumnData(new ColumnWeightData(20));
		new TableColumn(table, SWT.NONE).setText("¼��Ա");
		layout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("¼��ʱ��");
		layout.addColumnData(new ColumnWeightData(20));
		new TableColumn(table, SWT.NONE).setText("�޸�Ա");
		layout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("�޸�ʱ��");
		
	}
	
	private static final class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int col){

			Baspackage o = (Baspackage)element;
			switch (col){
			case 0: return o.getPkgcode();
			case 1: return o.getPkgname();
			case 2: return o.getMaker() == null ? "" : o.getMaker();
			case 3: return o.getMadetime() == null ? "" : o.getMadetime().toLocaleString();
			case 4: return o.getModifier();
			case 5: return o.getModitime() == null ? "" : o.getModitime().toLocaleString();

			default :
				return "";
			}
		}
	}


}
