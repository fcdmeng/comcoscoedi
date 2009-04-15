package com.freqds.base.editorpart;

import org.eclipse.jface.viewers.TableViewer;

import com.freqds.base.dialog.BasePartenersDialog;

import cosco.vsagent.system.EditorActionGroup;

public class BasePartnersEditorActionGroup extends EditorActionGroup {

	public BasePartnersEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	public void fireAddAction() {
		new BasePartenersDialog(null).open();
	}
}
