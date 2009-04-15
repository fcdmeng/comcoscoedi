package com.freqds.base.editorpart;

import org.eclipse.jface.viewers.TableViewer;

import com.freqds.base.dialog.BaseCountryDialog;
import com.freqds.dbo.base.BaseCountryDAO;

import cosco.vsagent.system.EditorActionGroup;

public class BaseCountryEditorActionGroup extends EditorActionGroup{
	TableViewer tv;
	public BaseCountryEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	public void fireAddAction() {
		new BaseCountryDialog(null).open();
	}
	
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput( new BaseCountryDAO().getBaseCountry(getQueryInfo()));
		refreshActionsState();
	}
	
	public void firePrevAction(){
		tv = getTv();
		getQueryInfo().currentPage =1;
		tv.setInput( new BaseCountryDAO().getBaseCountry(getQueryInfo()));
		refreshActionsState();
	}
}
