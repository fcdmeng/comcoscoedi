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

import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;

public class BasePartnersEditor extends EditorPartAdapter {

	public static final String ID = "com.freqds.base.editorpart.BasePartnersEditor"; //$NON-NLS-1$
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
//		tv.setLabelProvider()
		BasePartnersEditorActionGroup actionGroup = new BasePartnersEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
//		actionGroup.fireAddAction()
	}

	

	private void createTableViewer(ViewForm parent) {
		// TODO Auto-generated method stub
		tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn co_code = new TableColumn(table, SWT.NONE);
		co_code.setText("代码");
		co_code.setWidth(80);
		TableColumn mnemonic = new TableColumn(table,SWT.NONE);
		mnemonic.setText("助记码");
		mnemonic.setWidth(80);
		
		TableColumn cnname = new TableColumn(table, SWT.NONE);
		cnname.setText("中文名称");
		cnname.setWidth(200);
		TableColumn enname = new TableColumn(table,SWT.NONE);
		enname.setText("英文名称");
		enname.setWidth(200);
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



	@Override
	public void setFocus() {
		// Set the focus
	}
	
	private static final class TableViewerLableProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int col){
			return "";
		}
	}


}
