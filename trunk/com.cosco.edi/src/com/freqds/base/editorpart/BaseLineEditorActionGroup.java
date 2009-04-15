package com.freqds.base.editorpart;

import org.eclipse.jface.viewers.TableViewer;

import com.freqds.base.dialog.BaseLineDialog;

import cosco.vsagent.system.EditorActionGroup;

public class BaseLineEditorActionGroup extends EditorActionGroup {

	public BaseLineEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	public void fireAddAction() {
		new BaseLineDialog(null).open();
	}
//	
}
