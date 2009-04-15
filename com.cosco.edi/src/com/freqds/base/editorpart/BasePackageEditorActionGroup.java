package com.freqds.base.editorpart;

import org.eclipse.jface.viewers.TableViewer;

import com.freqds.base.dialog.BasePackageDialog;

import cosco.vsagent.system.EditorActionGroup;

public class BasePackageEditorActionGroup  extends EditorActionGroup{

	public BasePackageEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	public void fireAddAction() {
		new BasePackageDialog(null).open();
	}
}
