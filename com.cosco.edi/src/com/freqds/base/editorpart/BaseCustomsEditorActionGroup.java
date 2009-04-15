package com.freqds.base.editorpart;

import org.eclipse.jface.viewers.TableViewer;

import com.freqds.base.dialog.BaseCustomsDialog;

import cosco.vsagent.system.EditorActionGroup;

public class BaseCustomsEditorActionGroup extends EditorActionGroup {

	public BaseCustomsEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	public void fireAddAction() {
		new BaseCustomsDialog(null).open();
	}
}
