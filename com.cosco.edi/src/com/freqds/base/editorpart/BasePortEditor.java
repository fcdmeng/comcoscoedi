package com.freqds.base.editorpart;

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

import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;

public class BasePortEditor extends EditorPartAdapter {

	public static final String ID = "com.freqds.base.editorpart.BasePortEditor"; //$NON-NLS-1$

	private TableViewer tv;
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
		
		BasePortEditorActionGroup actionGroup = new BasePortEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
//		actionGroup.fireFirstAction();
	}

	private void createTableViewer(ViewForm parent) {
		tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		
		layout.addColumnData(new ColumnWeightData(10));
		new TableColumn(table, SWT.NONE).setText("港口代码");
		
		layout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("中文名称");
		
		layout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("英文名称");
		
		layout.addColumnData(new ColumnWeightData(10));
		new TableColumn(table, SWT.NONE).setText("录入人");
		
		layout.addColumnData(new ColumnWeightData(15));
		new TableColumn(table, SWT.NONE).setText("录入时间");
		
		layout.addColumnData(new ColumnWeightData(10));
		new TableColumn(table, SWT.NONE).setText("修改人");
		
		layout.addColumnData(new ColumnWeightData(15));
		new TableColumn(table, SWT.NONE).setText("修改时间");
		
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	private static final class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int col){

			/*Bascountry o = (Bascountry)element;
			switch (col){
			case 0: return o.getNationcode();
			case 1: return o.getCname();
			case 2: return o.getEname();
			case 3: return o.getMaker() == null ? "" : o.getMaker();
			case 4: return o.getMadetime() == null ? "" : o.getMadetime().toLocaleString();
			case 5: return o.getModifier();
			case 6: return o.getModitime() == null ? "" : o.getModitime().toLocaleString();

			default :*/
				return "";
//			}
		}
	}

	

}
