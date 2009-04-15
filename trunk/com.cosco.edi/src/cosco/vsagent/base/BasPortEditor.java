package cosco.vsagent.base;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;

import cosco.vsagent.mapping.base.Basport;
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;

public class BasPortEditor extends EditorPartAdapter {


	private TableViewer tv;
	
	public static final String ID = "cosco.vsagent.base.BasPortEditor"; //$NON-NLS-1$

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
		BasPortEditorActionGroup actionGroup = new BasPortEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		//�������ʾ����
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
		actionGroup.fireFirstAction();
		
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}


	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	

	
	private void createTableViewer(ViewForm parent){
		tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("�ۿ�����");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("����");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("��������");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("Ӣ������");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("���Ҵ���");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("�Ƿ����");
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

			Basport o = (Basport)element;
			switch (col){
			case 0: 
				if(o.getPorttype().equals("1"))
					return "����";
				else if(o.getPorttype().equals("2"))
					return "��֧�߸ۿ�";
				else if(o.getPorttype().equals("3"))
					return "������ת�ۿ�";
				else if(o.getPorttype().equals("4"))
					return "�������˸ۿ�";
				else if(o.getPorttype().equals("5"))
					return "ֱͨ�ۿ�";
			case 1: return o.getPortcode();
			case 2: return o.getCname();
			case 3: return o.getEname();
			case 4: return o.getNationcode() == null ? "": o.getNationcode();
			case 5: return o.getIfbaseprot().equals("1")? "��" : "";
			case 6: return o.getMaker() == null ? "" : o.getMaker();
			case 7: return o.getMadetime() == null ? "" : o.getMadetime().toLocaleString();
			case 8: return o.getModifier();
			case 9: return o.getModitime() == null ? "" : o.getModitime().toLocaleString();

			default :
				return "";
			}
		}
	}

}
