package com.freqds.base.dialog;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MyTitleAreaDialogClient {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		String ls_;
//		new BaseShipRecordDialog(shell).open();
//		new BasePartenersDialog(shell).open();
		new Ts(shell).open();
		shell.layout();
		display.dispose();
	}
}
