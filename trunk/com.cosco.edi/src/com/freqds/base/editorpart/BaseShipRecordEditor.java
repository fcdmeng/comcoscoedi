package com.freqds.base.editorpart;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;

import cosco.vsagent.mapping.base.Bascountry;
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;

public class BaseShipRecordEditor extends EditorPartAdapter {

	public static final String ID = "com.freqds.base.editorpart.BaseShipRecordEditor"; //$NON-NLS-1$
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
		BaseShipRecordEditorActionGroup actionGroup = new BaseShipRecordEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		
		//将表格显示出来
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
		actionGroup.fireFirstAction();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	private void createTableViewer(ViewForm parent){
		tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
//		TableLayout layout = new TableLayout();
//		table.setLayout(layout);
//		layout.addColumnData(new ColumnWeightData(28));
		TableColumn imo = new TableColumn(table, SWT.NONE);
		imo.setText("IMO号");
		imo.setWidth(50);
//		layout.addColumnData(new ColumnWeightData(28));
		TableColumn callSign = new TableColumn(table, SWT.NONE);
		callSign.setText("船舶呼号");
		callSign.setWidth(80);
//		layout.addColumnData(new ColumnWeightData(50));
		TableColumn enname = new TableColumn(table, SWT.NONE);
		enname.setText("英文船名");
		enname.setWidth(200);
//		layout.addColumnData(new ColumnWeightData(50));
		TableColumn cnname = new TableColumn(table, SWT.NONE);
		cnname.setText("中文船名");
		cnname.setWidth(200);
		
		TableColumn nationality = new TableColumn(table, SWT.NONE);
		nationality.setText("船籍港");
		nationality.setWidth(60);
		
		TableColumn insertuser = new TableColumn(table, SWT.NONE);
		insertuser.setText("录入人");
		insertuser.setWidth(60);
		
		TableColumn inserttime = new TableColumn(table, SWT.NONE);
		inserttime.setText("录入时间");
		inserttime.setWidth(120);
		
		TableColumn updateuser = new TableColumn(table, SWT.NONE);
		updateuser.setText("修改人");
		updateuser.setWidth(60);
		TableColumn updatetime = new TableColumn(table, SWT.NONE);
		updatetime.setText("修改时间");
		updatetime.setWidth(120);
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
