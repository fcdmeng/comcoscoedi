package com.freqds.base.editorpart;

import org.eclipse.jface.viewers.TableViewer;

import com.freqds.base.dialog.BaseShipRecordDialog;

import cosco.vsagent.system.EditorActionGroup;

public class BaseShipRecordEditorActionGroup extends EditorActionGroup{

	public BaseShipRecordEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	public void fireAddAction() {
		new BaseShipRecordDialog(null).open();
	}
	
}
