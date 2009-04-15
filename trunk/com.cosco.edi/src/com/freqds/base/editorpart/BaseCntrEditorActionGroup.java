package com.freqds.base.editorpart;

import org.eclipse.jface.viewers.TableViewer;

import com.freqds.base.dialog.BaseCntrDialog;

import cosco.vsagent.system.EditorActionGroup;

public class BaseCntrEditorActionGroup extends EditorActionGroup {

	public BaseCntrEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	public void fireAddAction() {
		new BaseCntrDialog(null).open();
	}
}
