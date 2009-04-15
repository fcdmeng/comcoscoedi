package cosco.vsagent.ecd;

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

import cosco.vsagent.mapping.base.Basvslvoy;
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;

public class BasvslvoyEditor extends EditorPartAdapter {
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
		BasvslvoyEditorActionGroup actionGroup = new BasvslvoyEditorActionGroup(tv);
		actionGroup.setFlag(((BasvslvoyEditorInput) this.getEditorInput()).getFlag());

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
		
//		layout.addColumnData(new ColumnWeightData(28));
//		new TableColumn(table, SWT.NONE).setText("������");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("��������");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("��������");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("Ӣ������");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("����");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("�ָ�����");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("�������");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("ҵ������");
		layout.addColumnData(new ColumnWeightData(28));
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

			Basvslvoy o = (Basvslvoy)element;
			switch (col){
			case 0: return o.getVslcode()== null?"":o.getVslcode();
			case 1: return o.getVslcname()== null?"":o.getVslcname();
			case 2: return o.getVslename()== null?"":o.getVslename();
			case 3: return o.getVoyage()== null?"":o.getVoyage();
			case 4: return o.getArrdate()==null?"":o.getArrdate().toString();
			case 5: return o.getSaildate()==null?"":o.getSaildate().toString();
			case 6: return o.getTradetype()== null?"":o.getTradetype();
			case 7: return o.getLinecode()== null?"":o.getLinecode();

			case 8: return o.getMaker() == null ? "" : o.getMaker();
			case 9: return o.getMadetime() == null ? "" : o.getMadetime().toLocaleString();
			case 10: return o.getModifier();
			case 11: return o.getModitime() == null ? "" : o.getModitime().toLocaleString();
			default :
				return "************";
			}
		}
	}

}
