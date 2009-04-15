package cosco.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.console.IConsoleConstants;

import cosco.vsagent.mapping.ecd.Ecdbkcntr;
import cosco.vsagent.mapping.ecd.Ecdbooking;
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.SmsUtil;
import cosco.vsagent.system.TableLabelProviderAdapter;
import cosco.xml.mapping.Xmlbooking;

public class XmlBookingEditor extends EditorPartAdapter{
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
		XmlBookingEditorActionGroup actionGroup = new XmlBookingEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		//�������ʾ����
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
		actionGroup.fireFirstAction();
		
		IWorkbenchPage workbenchPage =  getEditorSite().getPage();
		try {
			workbenchPage.showView(IConsoleConstants.ID_CONSOLE_VIEW);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	private void createTableViewer(ViewForm parent){
		tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		layout.addColumnData(new ColumnWeightData(100));
		new TableColumn(table, SWT.NONE).setText("�����˵���");
		layout.addColumnData(new ColumnWeightData(100));
		new TableColumn(table, SWT.NONE).setText("���ᵥ��");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("�����˴���");
		layout.addColumnData(new ColumnWeightData(100));
		new TableColumn(table, SWT.NONE).setText("����������");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("�ջ��ش���");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("�ջ�������");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("װ���ش���");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("װ������");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("ж���ش���");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("ж������");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("��ת���˵ش���");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("��תĿ�ĵش���");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("���ѷ�ʽ");
		layout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table, SWT.NONE).setText("��������");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("¼����");
		layout.addColumnData(new ColumnWeightData(38));
		new TableColumn(table, SWT.NONE).setText("¼��ʱ��");
		layout.addColumnData(new ColumnWeightData(28));
		new TableColumn(table, SWT.NONE).setText("�޸���");
		layout.addColumnData(new ColumnWeightData(38));
		new TableColumn(table, SWT.NONE).setText("�޸�ʱ��");
		
	}
	
	private static final class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int col){

			Xmlbooking o = (Xmlbooking)element;
			switch (col){
			case 0: return o.getBlno();
			case 1: return o.getAsblno();
			case 2: return o.getCarrcode();
			case 3: return o.getCarrename();
			case 4: 
				/*List<Ecdbkcntr> lt = o.getXmlbkcntr();
				if (lt != null && lt.size()>0){
					 HashMap hash=new HashMap();
					for ( int i = 0; i<lt.size();i++){
						Ecdbkcntr data = lt.get(i);
						int num = 0;
						try{
							num = Integer.valueOf(hash.get(data.getCntrtype()).toString());
							num = num + 1;
						}catch(Exception E){
							*//**
							 * �����ڣ���˵��������Ϊ��һ��
							 *//*
							num = 1;
						}
						
						hash.put(data.getCntrtype(), num);
					}
					
					Iterator it=hash.keySet().iterator();
					StringBuffer str = new StringBuffer();
					while(it.hasNext()){
						String ls_title = (String) it.next();
						str.append(ls_title+"x"+hash.get(ls_title)+",");
//						System.out.println("it.Next���ݵ�ֵ��:"+it.next()+"="+hash.get(it.next())); //��ü�����Ӧ��ֵ��
					}
					
					return str.toString().substring(0, str.toString().length() - 1);
					
					
				}*/
				return o.getDelpcode();
			case 5: return o.getDelpname();
			case 6: return o.getCarrtype();
			case 7: return "";
			case 8: return "";
			case 9: return "";
			case 10: return "";
			case 11: return "";
			case 12: return o.getMaker() == null ? "" : o.getMaker();
			case 13: return o.getMadetime() == null ? "" : o.getMadetime().toLocaleString();
			case 14: return o.getModifier();
			case 15: return o.getModitime() == null ? "" : o.getModitime().toLocaleString();

			default :
				return "*********";
			}
		}
	}
	
	/*public void dispose(){
		Context.getInstance().setBasvslvoy(null);
		super.dispose();
	}*/
	//
}
