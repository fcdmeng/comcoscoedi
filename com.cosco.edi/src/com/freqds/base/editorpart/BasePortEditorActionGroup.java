package com.freqds.base.editorpart;

import org.eclipse.jface.viewers.TableViewer;

import com.freqds.base.dialog.BasePortDialog;

import cosco.vsagent.system.EditorActionGroup;

public class BasePortEditorActionGroup extends EditorActionGroup {

	public BasePortEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	public void fireAddAction() {
		new BasePortDialog(null).open();
	}

}
